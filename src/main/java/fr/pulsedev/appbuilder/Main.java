package fr.pulsedev.appbuilder;

import fr.pulsedev.appbuilder.UI.panels.PanelManager;
import fr.pulsedev.appbuilder.projects.ProjectOptions;
import fr.pulsedev.appbuilder.settings.Language;
import fr.pulsedev.appbuilder.settings.Settings;
import fr.pulsedev.appbuilder.settings.Theme;
import fr.pulsedev.appbuilder.themes.Themes;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class Main {

    public static String EDITOR_VERSION;
    public static ProjectOptions<String> options = new ProjectOptions<>();

    public static void main(String[] args) throws IOException {

        Properties properties = new Properties();
        InputStream buildProperties = new FileInputStream("./build.properties");
        properties.load(buildProperties);
        EDITOR_VERSION = properties.getProperty("version");

        Settings.init();
        PanelManager.PROJECT.window.run();
        Language language = new Language("Fr");
        Theme theme = new Theme(Themes.DEFAULT);
        Settings settings = new Settings().setLanguage(language).setTheme(theme);
        settings.saveSettingsToJson();

        System.out.println(Objects.requireNonNull(Settings.getSettingsFromJSon()).getLanguage().getLang());
    }
}
