package fr.pulsedev.appbuilder.shortcuts;

import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.shortcuts.runnables.DeleteRunnable;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum ShortCuts {

    SAVE(Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_S), () -> { Main.PROJECT.save(); }),
    DEL(Collections.singletonList(KeyEvent.VK_DELETE), new DeleteRunnable());

    List<Integer> keyPressed;
    Runnable runnable;

    ShortCuts(List<Integer> keyPressed, Runnable runnable) {
        this.keyPressed = keyPressed;
        this.runnable = runnable;
    }

    public static void executeShortcuts(List<Integer> keyPressed){
        for (ShortCuts shortcut : ShortCuts.values()) {
            if(shortcut.keyPressed.size() == keyPressed.size()){
                if(shortcut.keyPressed.containsAll(keyPressed)){
                    System.out.println("Shortcuts called: " + shortcut.name());
                    shortcut.runnable.run();
                }
            }
        }
    }
}
