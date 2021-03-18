package fr.pulsedev.appbuilder.visualeditor.blocks.UI;

public enum UITypes {

    BLOCK(BlockUI.NAME, BlockUI.class.getCanonicalName()),
    TEXT_AREA(TextAreaUI.NAME, TextAreaUI.class.getCanonicalName()),;

    public String name;
    public String path;

    UITypes(String name, String path) {
        this.name = name;
        this.path = path;
    }
}
