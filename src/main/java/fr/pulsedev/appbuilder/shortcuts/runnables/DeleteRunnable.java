package fr.pulsedev.appbuilder.shortcuts.runnables;

import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.UI.panels.enums.PanelManager;
import fr.pulsedev.appbuilder.visualeditor.Block;

import javax.swing.*;
import java.awt.*;

public class DeleteRunnable implements Runnable {

    @Override
    public void run() {
        Point cursor = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(cursor, PanelManager.EDITOR.window.getContentPane());
        Component component = PanelManager.EDITOR.window.getContentPane().getComponentAt(cursor);
        if (component instanceof Block<?>){
            Main.blocksInWindow.remove(component);
            PanelManager.EDITOR.window.remove(component);
        }
    }
}
