package fr.pulsedev.appbuilder;

import fr.pulsedev.appbuilder.UI.panels.enums.PanelManager;
import fr.pulsedev.appbuilder.projects.ProjectOptions;
import fr.pulsedev.appbuilder.settings.Language;
import fr.pulsedev.appbuilder.settings.Settings;
import fr.pulsedev.appbuilder.settings.Theme;
import fr.pulsedev.appbuilder.themes.Themes;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;

public class Main {

    public static String EDITOR_VERSION;
    public static ProjectOptions<String> options = new ProjectOptions<>();
    public static final String RESOURCES_PATH = Paths.get("").toAbsolutePath() + "/src/main/resources/";

    public static void main(String[] args) throws IOException {
        Settings.init();

        Properties properties = new Properties();
        InputStream buildProperties = new FileInputStream("./build.properties");
        properties.load(buildProperties);
        EDITOR_VERSION = properties.getProperty("version");

        PanelManager.PROJECT.window.run();
    }
}
