package fr.pulsedev.appbuilder.UI.panels.bar.enums;

public enum MenuTypes {
    FILE("File"),
    EDIT("Edit"),
    CODE("Code"),
    HELP("Help");

    private final String TEXT;

    MenuTypes(String TEXT) {
        this.TEXT = TEXT;
    }

    public String getTEXT() {
        return TEXT;
    }
}
