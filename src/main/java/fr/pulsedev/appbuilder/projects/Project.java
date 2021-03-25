package fr.pulsedev.appbuilder.projects;

import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.projects.errors.ProjectDirIsNotEmpty;
import fr.pulsedev.appbuilder.projects.errors.ProjectErrors;
import fr.pulsedev.appbuilder.projects.errors.ProjectSelectedIsAFile;
import fr.pulsedev.appbuilder.projects.errors.ProjectXmlCorrupted;
import fr.pulsedev.appbuilder.utils.Coordinates;
import fr.pulsedev.appbuilder.utils.FileUtils;
import fr.pulsedev.appbuilder.utils.XMLHelper;
import fr.pulsedev.appbuilder.visualeditor.Block;
import fr.pulsedev.appbuilder.visualeditor.Tag;
import fr.pulsedev.appbuilder.visualeditor.blocks.BlockManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import java.awt.*;
import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Project {

    File directory;
    File appBuilderDir;
    File windowDir;
    ProjectOptions<String> options =  Main.options;

    /**
     *
     * @param directory the opened directory, <b>must be a directory</b>, can be an empty directory
     * @throws ProjectErrors if the given directory is not correct (File, Unreadable, Corrupted)
     * @return an opened {@link Project} with all option set
     */
    public static Project open(File directory) throws ProjectErrors {
        Project project = new Project();
        project.directory = directory;
        if(directory.isFile()) throw new ProjectSelectedIsAFile(project);

        // Get all subDir name
        List<String> subDir = FileUtils.getSubFolders(project.directory).stream()
                .map(File::getName)
                .collect(Collectors.toList());

        // Check if it's a project directory
        if (subDir.contains(".appbuilder")){
            if(!subDir.contains("window")){
                project.windowDir = new File(project.directory.getPath() + "/windows");
                if(!project.windowDir.exists()) project.windowDir.mkdir();
                else project.load();
            }
            project.readProjectXML();
        }
        else {
            try {
                project.init();
            } catch (ParserConfigurationException | TransformerException e) {
                e.printStackTrace();
            }
        }


        return project;
    }

    /**
     * Like new method, used to create a project;
     * @param directory the opened directory, <b>must be a directory</b>, can be an empty directory
     */
    public Project(File directory) throws ProjectErrors {
        this.directory = directory;
        if(directory.isFile()) throw new ProjectSelectedIsAFile(this);
        System.out.println(directory);
        if (directory.listFiles() != null && directory.listFiles().length != 0) throw new ProjectDirIsNotEmpty(this);

        try {
            this.init();
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * To use only when opening a project
     */
    private Project(){
    }


    void readProjectXML() throws ProjectXmlCorrupted {
        this.appBuilderDir = new File(this.directory.getPath() + "/.appbuilder");
        File projectXML = new File(this.appBuilderDir.getPath() + "/" + "project.xml");

        if (!projectXML.exists()){
            throw new ProjectXmlCorrupted(this);
        }

        Document projectDocument = FileUtils.getDocument(projectXML);
        assert projectDocument != null;
        Element project = projectDocument.getDocumentElement();
        NodeList projectChild = project.getChildNodes();
        for (int i = 0; i < projectChild.getLength(); i++) {
            Node node = projectChild.item(i);

            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;
                this.options.set(element.getNodeName(), element.getAttribute("value"));
            }
        }
    }

    @SuppressWarnings("unused")
    public void init() throws ParserConfigurationException, TransformerException, ProjectXmlCorrupted {
        this.appBuilderDir = new File(this.directory.getPath() + "/.appbuilder");
        boolean ignored = appBuilderDir.mkdir();
        this.windowDir = new File(this.directory.getPath() + "/windows");
        if(!windowDir.exists()) this.windowDir.mkdir();

        XMLHelper helper = new XMLHelper();
        boolean isCreated = helper.create(new File(appBuilderDir.getPath() + "/" + "project.xml"));
        //Header
        XMLHelper.CustomElement editorVersion = new XMLHelper.CustomElement("version", String.valueOf(Main.EDITOR_VERSION));
        XMLHelper.MasterElement projectM = new XMLHelper.MasterElement("project", Collections.singletonList(editorVersion));
        Element project = helper.addElement(projectM, false);
        //Settings
        List<XMLHelper.CustomElement> customElements = new ArrayList<>();
        for(String key : this.options.params.keySet()){
            XMLHelper.CustomElement element = new XMLHelper.CustomElement(key, String.valueOf(this.options.get(key)));
            customElements.add(element);
        }
        XMLHelper.MasterElement settingsM = new XMLHelper.MasterElement("settings", customElements);
        helper.addElement(project, settingsM, true, false);
        boolean isSaved = helper.save();

        readProjectXML();
    }

    public File getDirectory(){
        return this.directory;
    }

    public void save(){
        File file = new File(windowDir.getPath() + "/" + "window.xml");
        file.delete();
        XMLHelper helper = new XMLHelper();
        helper.create(file);
        Element window = helper.addElement(new XMLHelper.MasterElement("window", Collections.emptyList()), false);
        for (Block<?> block : Main.blocksInWindow){
            List<XMLHelper.CustomElement> elements = new ArrayList<>();
            for (Tag<?> tag: block.getTags()) {
                XMLHelper.CustomElement a = new XMLHelper.CustomElement(tag.getName(), tag.getValue());
                elements.add(a);
            }
            XMLHelper.MasterElement masterElement = new XMLHelper.MasterElement(block.getClass().getSimpleName(), elements);
            helper.addElement(window, masterElement, true, true);
        }
        helper.save();
    }

    public void load(){
        File file = new File(windowDir.getPath() + "/" + "window.xml");
        XMLHelper helper = new XMLHelper();
        if(FileUtils.getDocument(file) == null) return;
        helper.load(file);
        List<Node> nodes = helper.getNodes();
        if (nodes == null) return;
        for (Node node : helper.getNodes()) {
            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;
                Class<? extends Block<?>> blockClass = BlockManager.getClass(element.getNodeName());
                Constructor<? extends Block<?>> constructor;
                try {
                    assert blockClass != null;
                    constructor = blockClass.getConstructor();
                    Block<?> block = constructor.newInstance();
                    for (Node node_: helper.getChildNodes(element)) {
                        if(node_.getNodeType() == Node.ELEMENT_NODE){
                            Element element_ = (Element) node_;
                            switch (element_.getNodeName()) {
                    /*COLOR*/   case "color" -> {
                                    int r, g, b;
                                    r = g = b = 0;
                                    for (Node node__ : helper.getChildNodes(element_)) {
                                        if (node__.getNodeType() == Node.ELEMENT_NODE) {
                                            Element element__ = (Element) node__;
                                            if (element__.getNodeName().equals("r")) {
                                                r = Integer.parseInt(helper.getValueOfANode(element__));
                                            }
                                            if (element__.getNodeName().equals("g")) {
                                                g = Integer.parseInt(helper.getValueOfANode(element__));
                                            }
                                            if (element__.getNodeName().equals("b")) {
                                                b = Integer.parseInt(helper.getValueOfANode(element__));
                                            }
                                        }
                                    }
                                    block.editTag("color", new Color(r, g, b));
                                }
                /*DIMENSION*/   case "dimension" -> {
                                    Dimension dimension = new Dimension();
                                    for (Node node__ : helper.getChildNodes(element_)) {
                                        if (node__.getNodeType() == Node.ELEMENT_NODE) {
                                            Element element__ = (Element) node__;
                                            if (element__.getNodeName().equals("width")) {
                                                dimension.width = (int) Float.parseFloat(helper.getValueOfANode(element__));
                                            }
                                            if (element__.getNodeName().equals("height")) {
                                                dimension.height = (int) Float.parseFloat(helper.getValueOfANode(element__));
                                            }
                                        }
                                    }
                                    block.editTag("dimension", dimension);
                                }
                    /*COORD*/   case "coordinates" -> {
                                    Coordinates coordinates = new Coordinates();
                                    for (Node node__ : helper.getChildNodes(element_)) {
                                        if (node__.getNodeType() == Node.ELEMENT_NODE) {
                                            Element element__ = (Element) node__;
                                            if (element__.getNodeName().equals("x")) {
                                                coordinates.setX(Integer.parseInt(helper.getValueOfANode(element__)));
                                            }
                                            if (element__.getNodeName().equals("y")) {
                                                coordinates.setY(Integer.parseInt(helper.getValueOfANode(element__)));
                                            }
                                        }
                                    }
                                    block.editTag("coordinates", coordinates);
                                }
                     /*BOOL*/   case "visible" -> block.editTag(element_.getNodeName(), Boolean.parseBoolean(helper.getValueOfANode(element_)));
                   /*STRING*/   case "text", "id" -> block.editTag(element_.getNodeName(), helper.getValueOfANode(element_));
                      /*INT*/   case "layer" -> block.editTag(element_.getNodeName(), Integer.parseInt(helper.getValueOfANode(element_)));
                            }
                        }
                    }
                    Main.blocksInWindow.add(block);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
