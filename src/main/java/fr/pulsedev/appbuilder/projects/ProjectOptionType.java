package fr.pulsedev.appbuilder.projects;

import fr.pulsedev.appbuilder.Main;

public enum  ProjectOptionType {
    VERSION(Main.VERSION);

    Object defaultValue;

    ProjectOptionType(Object defaultValue){
        this.defaultValue = defaultValue;
    }
}
