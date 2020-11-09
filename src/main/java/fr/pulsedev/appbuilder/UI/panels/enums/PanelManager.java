package fr.pulsedev.appbuilder.UI.panels.enums;

import fr.pulsedev.appbuilder.UI.Window;
import fr.pulsedev.appbuilder.UI.panels.EditorPanel;
import fr.pulsedev.appbuilder.UI.panels.ProjectChooserPanel;
import fr.pulsedev.appbuilder.settings.Settings;

public enum  PanelManager {

    EDITOR(new Window.Builder()
            .setName("Editor")
            .setPanel(new EditorPanel())
            .setResizable(true)
            .setBackground(Settings.getSettingsFromJSon().getTheme().getThemes().themesInterface.getBACKGROUND())
            .setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()))
            .setUnderdecorated(false)
            .createWindow()),
    PROJECT(new Window.Builder()
            .setName("Main")
            .setPanel(new ProjectChooserPanel())
            .setResizable(false)
            .setBackground(Settings.getSettingsFromJSon().getTheme().getThemes().themesInterface.getBACKGROUND())
            .createWindow());

    public Window window;

    PanelManager(Window window){
        this.window = window;
    }

}
