package fr.pulsedev.appbuilder.languages;

import fr.pulsedev.appbuilder.Main;

import java.io.File;

public class English extends Lang.Interface {

    public static final File file = new File(Main.RESOURCES_PATH + "lang/en_en.yml");

    public English() {
        super(file);
    }
}
