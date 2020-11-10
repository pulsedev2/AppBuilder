package fr.pulsedev.appbuilder.UI.panels.enums;

import fr.pulsedev.appbuilder.UI.Window;
import fr.pulsedev.appbuilder.UI.panels.SettingsPanel;
import fr.pulsedev.appbuilder.UI.panels.editor.EditorPanel;
import fr.pulsedev.appbuilder.UI.panels.ProjectChooserPanel;
import fr.pulsedev.appbuilder.settings.Settings;

import javax.swing.*;
import java.awt.*;

public enum  PanelManager {

    EDITOR(new Window.Builder()
            .setName("Editor")
            .setPanel(new EditorPanel())
            .setResizable(true)
            .setBackground(Settings.getSettingsFromJSon().getTheme().getThemes().themesInterface.getBACKGROUND())
            .setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()))
            .setUndecorated(false)
            .createWindow()),
    PROJECT(new Window.Builder()
            .setName("Projects")
            .setPanel(new ProjectChooserPanel())
            .setResizable(false)
            .setDraggable(true)
            .setBackground(Settings.getSettingsFromJSon().getTheme().getThemes().themesInterface.getBACKGROUND())
            .createWindow()),
    SETTINGS(new Window.Builder()
            .setName("Settings")
            .setPanel(new SettingsPanel())
            .setResizable(false)
            .setDraggable(false)
            .setBackground(Settings.getSettingsFromJSon().getTheme().getThemes().themesInterface.getBACKGROUND())
            .setUndecorated(false)
            .setConstants(JFrame.DISPOSE_ON_CLOSE)
            .createWindow());

    public Window window;

    PanelManager(Window window){
        this.window = window;
    }

}
