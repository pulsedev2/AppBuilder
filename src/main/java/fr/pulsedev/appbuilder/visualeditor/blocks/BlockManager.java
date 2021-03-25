package fr.pulsedev.appbuilder.visualeditor.blocks;

import fr.pulsedev.appbuilder.visualeditor.Block;

public enum BlockManager {

    BLOCK(JBlock.class.getSimpleName(), JBlock.class),
    TEXT_AREA(JTextArea.class.getSimpleName(), JTextArea.class);

    String className;
    Class<? extends Block<?>> aClass;

    BlockManager(String className, Class<? extends Block<?>> aClass) {
        this.className = className;
        this.aClass = aClass;
    }

    public static Class<? extends Block<?>> getClass(String className) {
        for (BlockManager block : BlockManager.values()) {
            if (block.className.equalsIgnoreCase(className)) {
                return block.aClass;
            }
        }
        return null;
    }
}
