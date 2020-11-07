package fr.pulsedev.appbuilder;

import fr.pulsedev.appbuilder.UI.panels.ProjectChooserPanel;
import fr.pulsedev.appbuilder.UI.utils.Window;
import fr.pulsedev.appbuilder.projects.ProjectOptions;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static String EDITOR_VERSION;
    public static Window projectWindow;
    public static ProjectOptions<String> options = new ProjectOptions<String>();


    public static void main(String[] args) throws IOException {

        Properties properties = new Properties();
        InputStream buidlProperties = new FileInputStream("./build.properties");
        properties.load(buidlProperties);
        EDITOR_VERSION = properties.getProperty("version");

        projectWindow = new Window.Builder().setName("Test").setPanel(new ProjectChooserPanel()).setResizable(false).createWindow();
        projectWindow.run();
    }
}
