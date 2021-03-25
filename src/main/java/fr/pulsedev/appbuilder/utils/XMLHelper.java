package fr.pulsedev.appbuilder.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLHelper {

    Document document;
    File file;

    public void load(File file){
        if (file.exists()){
            this.document = FileUtils.getDocument(file);
            this.file = file;
        }
        else {
            this.create(file);
        }
    }

    public boolean create(File file){
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        if (documentBuilder == null) return false;

        this.document = documentBuilder.newDocument();
        this.file = file;
        this.save();
        return true;
    }

    public boolean save(){
        Document document = this.document;

        // Create file.xml
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        } catch (TransformerConfigurationException e) {
            return false;
        }
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(this.file);

        try {
            transformer.transform(domSource, streamResult);
        } catch (TransformerException e) {
            return false;
        }
        this.document = document;
        return true;
    }

    public Element addElement(MasterElement element, boolean hasChild){
        Element a = this.document.createElement(element.name);
        this.addElement(a, element, hasChild, false);
        this.document.appendChild(a);
        return a;
    }

    public void addElement(Element root, MasterElement element, boolean hasChild, boolean createMasterElement){
        if(createMasterElement){
            Element temp = document.createElement(element.name);
            root.appendChild(temp);
            root = temp;
        }
        for(CustomElement b: element.elements){
            if(hasChild){
                Element c = document.createElement(b.name);
                if(b.value instanceof Color){
                    Element r = document.createElement("r");
                    r.setAttribute("value", String.valueOf(((Color) b.value).getRed()));
                    Element g = document.createElement("g");
                    g.setAttribute("value", String.valueOf(((Color) b.value).getGreen()));
                    Element b_ = document.createElement("b");
                    b_.setAttribute("value", String.valueOf(((Color) b.value).getBlue()));
                    c.appendChild(r);
                    c.appendChild(g);
                    c.appendChild(b_);
                }
                else if(b.value instanceof Coordinates){
                    Element x = document.createElement("x");
                    x.setAttribute("value", String.valueOf(((Coordinates) b.value).getX()));
                    Element y = document.createElement("y");
                    y.setAttribute("value", String.valueOf(((Coordinates) b.value).getY()));
                    c.appendChild(x);
                    c.appendChild(y);
                }
                else if(b.value instanceof Dimension){
                    Element width = document.createElement("width");
                    width.setAttribute("value", String.valueOf(((Dimension) b.value).getWidth()));
                    Element height = document.createElement("height");
                    height.setAttribute("value", String.valueOf(((Dimension) b.value).getHeight()));
                    c.appendChild(width);
                    c.appendChild(height);
                }
                else {
                    c.setAttribute("value", b.value.toString());
                }
                root.appendChild(c);
            }
            else {
                root.setAttribute(b.name, b.value.toString());
            }
        }
    }

    public List<Node> getNodesByName(String name){
        Element a = document.getDocumentElement();
        NodeList nodes = a.getChildNodes();
        List<Node> result = new ArrayList<>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if(node != null){
                if(node.getNodeName().equalsIgnoreCase(name)){
                    result.add(node);
                }
            }
        }
        return result;
    }

    public List<Node> getNodes(){
        Element a = document.getDocumentElement();
        if (a == null) return null;
        return this.getChildNodes(a);
    }

    public List<Node> getChildNodes(Element element){
        NodeList nodes = element.getChildNodes();
        List<Node> result = new ArrayList<>();
        for (int i = 0; i < nodes.getLength(); i++) {
            result.add(nodes.item(i));
        }
        return result;
    }

    public String getValueOfANode(Element element){
        return element.getAttribute("value");
    }

    public static class MasterElement{

        public String name;
        public List<CustomElement> elements;

        public MasterElement(String name, List<CustomElement> elements) {
            this.name = name;
            this.elements = elements;
        }
    }

    public static class CustomElement{

        public String name;
        public Object value;

        public CustomElement(String name, Object value) {
            this.name = name;
            this.value = value;
        }
    }
}
