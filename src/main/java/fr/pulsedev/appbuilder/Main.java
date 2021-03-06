package fr.pulsedev.appbuilder;

import fr.pulsedev.appbuilder.UI.panels.enums.PanelManager;
import fr.pulsedev.appbuilder.projects.ProjectOptions;
import fr.pulsedev.appbuilder.settings.Language;
import fr.pulsedev.appbuilder.settings.Settings;
import fr.pulsedev.appbuilder.settings.Theme;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.nio.file.Paths;
import java.util.Properties;

public class Main {

    public static String EDITOR_VERSION;
    public static ProjectOptions<String> options = new ProjectOptions<>();
    public static final String RESOURCES_PATH = Paths.get("").toAbsolutePath() + "/src/main/resources/";
    public static String[] args;

    public static void main(String[] args) throws IOException {
        Main.args = args;
        UIManager.put("BlockUI", "fr.pulsedev.appbuilder.blueprints.UI.BlockUI");
        Settings.init();
        new Theme(args);
        new Language(args);

        Properties properties = new Properties();
        InputStream buildProperties = new FileInputStream("./build.properties");
        properties.load(buildProperties);
        EDITOR_VERSION = properties.getProperty("version");

        PanelManager.PROJECT.window.run();
    }

    public static void restartApplication() throws IOException {
        StringBuilder cmd = new StringBuilder();
        cmd.append(System.getProperty("java.home")).append(File.separator).append("bin").append(File.separator).append("java ");
        for (String jvmArg : ManagementFactory.getRuntimeMXBean().getInputArguments()) {
            cmd.append(jvmArg).append(" ");
        }
        cmd.append("-cp ").append(ManagementFactory.getRuntimeMXBean().getClassPath()).append(" ");
        cmd.append(Main.class.getName()).append(" ");
        for (String arg : args) {
            cmd.append(arg).append(" ");
        }
        Runtime.getRuntime().exec(cmd.toString());
        System.exit(0);
    }
}
