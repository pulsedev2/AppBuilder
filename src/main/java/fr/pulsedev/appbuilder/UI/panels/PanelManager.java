package fr.pulsedev.appbuilder.UI.panels;

import fr.pulsedev.appbuilder.UI.Window;
import fr.pulsedev.appbuilder.themes.Themes;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public enum  PanelManager {

    EDITOR(new Window.Builder().setName("Editor").setPanel(new EditorPanel()).setResizable(false).createWindow()),
    PROJECT(new Window.Builder().setName("Main").setPanel(new ProjectChooserPanel()).setResizable(false).setBackground(Themes.DEFAULT.themesInterface.getBACKGROUND()).createWindow());

    public Window window;

    PanelManager(Window window){
        this.window = window;
    }

}
