package fr.pulsedev.appbuilder.projects;

import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.projects.errors.ProjectDirIsNotEmpty;
import fr.pulsedev.appbuilder.projects.errors.ProjectErrors;
import fr.pulsedev.appbuilder.projects.errors.ProjectSelectedIsAFile;
import fr.pulsedev.appbuilder.projects.errors.ProjectXmlCorrupted;
import fr.pulsedev.appbuilder.utils.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class Project {

    File directory;
    File appBuilderDir;
    ProjectOptions options = new ProjectOptions();

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
        if (!FileUtils.isDirectoryEmpty(directory.toPath())) throw new ProjectDirIsNotEmpty(this);

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
        this.options.setVersion(Double.parseDouble(project.getAttribute("version")));
    }

    @SuppressWarnings("unused")
    public void init() throws ParserConfigurationException, TransformerException, ProjectXmlCorrupted {
        this.appBuilderDir = new File(this.directory.getPath() + "/.appbuilder");
        boolean ignored = appBuilderDir.mkdir();

        // Making project.xml file
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        // project Element with Version
        Element element = document.createElement("project");
        element.setAttribute("version", String.valueOf(Main.VERSION));
        document.appendChild(element);

        // Create project.xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(appBuilderDir.getPath() + "/" + "project.xml"));

        transformer.transform(domSource, streamResult);

        readProjectXML();
    }

    public File getDirectory(){
        return this.directory;
    }
}
