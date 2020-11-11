package fr.pulsedev.appbuilder.Languages;

import fr.pulsedev.appbuilder.Main;

import java.io.File;

public class French extends Lang.Interface {

    public static final File file = new File(Main.RESOURCES_PATH + "lang/fr_fr.yml");

    public French() {
        super(file);
    }
}
