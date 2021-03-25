package fr.pulsedev.appbuilder.shortcuts;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class ShortCutsListener implements KeyListener {

    public List<Integer> keyPressed = new ArrayList<>();

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (keyPressed.contains(e.getKeyCode())) return;
        keyPressed.add(e.getKeyCode());
        System.out.println(e.getKeyChar() + "↓ (" +e.getKeyCode() + ")");
        ShortCuts.executeShortcuts(keyPressed);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!keyPressed.contains(e.getKeyCode())) return;
        keyPressed.remove(keyPressed.indexOf(e.getKeyCode()));
        System.out.println(e.getKeyChar() + "↑ (" +e.getKeyCode() + ")");
    }
}
