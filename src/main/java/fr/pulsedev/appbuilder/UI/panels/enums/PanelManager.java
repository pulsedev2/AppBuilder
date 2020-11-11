package fr.pulsedev.appbuilder.UI.panels.enums;

import fr.pulsedev.appbuilder.UI.Window;
import fr.pulsedev.appbuilder.UI.panels.RestartIdePanel;
import fr.pulsedev.appbuilder.UI.panels.SettingsPanel;
import fr.pulsedev.appbuilder.UI.panels.editor.EditorPanel;
import fr.pulsedev.appbuilder.UI.panels.ProjectChooserPanel;
import fr.pulsedev.appbuilder.settings.Settings;
import fr.pulsedev.appbuilder.settings.Theme;

import javax.swing.*;
import java.awt.*;

public enum  PanelManager {

    EDITOR(new Window.Builder()
            .setName("Editor")
            .setPanel(new EditorPanel())
            .setResizable(true)
            .setBackground(Theme.USER.themesInterface.getBACKGROUND())
            .setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()))
            .setUndecorated(false)
            .createWindow()),
    PROJECT(new Window.Builder()
            .setName("Projects")
            .setPanel(new ProjectChooserPanel())
            .setResizable(false)
            .setDraggable(true)
            .setBackground(Theme.USER.themesInterface.getBACKGROUND())
            .createWindow()),
    SETTINGS(new Window.Builder()
            .setName("Settings")
            .setPanel(new SettingsPanel())
            .setResizable(false)
            .setDraggable(false)
            .setBackground(Theme.USER.themesInterface.getBACKGROUND())
            .setUndecorated(false)
            .setConstants(JFrame.DISPOSE_ON_CLOSE)
            .createWindow()),
    RESTART(new Window.Builder()
            .setName("Restart")
            .setPanel(new RestartIdePanel())
            .setResizable(false)
            .setDraggable(true)
            .setBackground(Theme.USER.themesInterface.getBACKGROUND())
            .setUndecorated(true)
            .setConstants(JFrame.DISPOSE_ON_CLOSE)
            .setSize(new Dimension(400, 200))
            .createWindow());

    public Window window;

    PanelManager(Window window){
        this.window = window;
    }

}
