package fr.pulsedev.appbuilder.projects;

import fr.pulsedev.appbuilder.Main;

import java.util.ArrayList;

public enum  ProjectOptionType {
    VERSION("1.0"),
    NAME("AppBuilderProject"),
    DESCRIPTION("Default description");

    Object defaultValue;

    ProjectOptionType(Object defaultValue){
        this.defaultValue = defaultValue;
    }

}
