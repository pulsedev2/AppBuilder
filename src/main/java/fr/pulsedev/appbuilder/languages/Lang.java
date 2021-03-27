package fr.pulsedev.appbuilder.languages;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public enum Lang {
    FRENCH(new French()),
    ENGLISH(new English());

    public Lang.Interface interface_;

    Lang(Interface interface_) {
        this.interface_ = interface_;
    }

    public static abstract class Interface {

        File file;

        public Interface(File file) {
            this.file = file;
        }

        @SuppressWarnings("unchecked")
        public String getString(String key) {
            Yaml yaml = new Yaml();
            HashMap<String, Object> result = null;
            try {
                result = (HashMap<String, Object>) yaml.load(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return result.get(key).toString();
        }
    }
}
