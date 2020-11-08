package fr.pulsedev.appbuilder;

import fr.pulsedev.appbuilder.UI.panels.EditorPanel;
import fr.pulsedev.appbuilder.UI.panels.ProjectChooserPanel;
import fr.pulsedev.appbuilder.UI.Window;
import fr.pulsedev.appbuilder.projects.ProjectOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static String EDITOR_VERSION;
    public static Window projectWindow;
    public static ProjectOptions<String> options = new ProjectOptions<String>();
    public static Window editorWindow;


    public static void main(String[] args) throws IOException {

        Properties properties = new Properties();
        InputStream buidlProperties = new FileInputStream("./build.properties");
        properties.load(buidlProperties);
        EDITOR_VERSION = properties.getProperty("version");

        editorWindow = new Window.Builder().setName("Editor").setPanel(new EditorPanel()).setResizable(false).createWindow();

        projectWindow = new Window.Builder().setName("Main").setPanel(new ProjectChooserPanel()).setResizable(false).createWindow();
        projectWindow.run();
    }
}
