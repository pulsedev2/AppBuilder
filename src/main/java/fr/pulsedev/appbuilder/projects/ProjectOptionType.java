package fr.pulsedev.appbuilder.projects;

public enum ProjectOptionType {
    VERSION("1.0"),
    NAME("AppBuilderProject"),
    DESCRIPTION("Default description");

    Object defaultValue;

    ProjectOptionType(Object defaultValue){
        this.defaultValue = defaultValue;
    }

}
