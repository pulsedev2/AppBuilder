package fr.pulsedev.appbuilder;

import fr.pulsedev.appbuilder.UI.panels.EditorPanel;
import fr.pulsedev.appbuilder.UI.panels.PanelManager;
import fr.pulsedev.appbuilder.UI.panels.ProjectChooserPanel;
import fr.pulsedev.appbuilder.UI.Window;
import fr.pulsedev.appbuilder.projects.ProjectOptions;
import fr.pulsedev.appbuilder.themes.Themes;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static String EDITOR_VERSION;
    public static ProjectOptions<String> options = new ProjectOptions<>();

    public static void main(String[] args) throws IOException {

        Properties properties = new Properties();
        InputStream buidlProperties = new FileInputStream("./build.properties");
        properties.load(buidlProperties);
        EDITOR_VERSION = properties.getProperty("version");

        PanelManager.PROJECT.window.run();
    }
}
