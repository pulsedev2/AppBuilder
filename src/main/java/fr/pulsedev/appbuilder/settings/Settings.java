package fr.pulsedev.appbuilder.settings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.themes.Themes;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Settings {

    private transient Gson gson;
    private Language language;
    private Theme theme;
    private transient final File file = new File(Main.RESOURCES_PATH + "settings/settings.json");

    public static void init(){
        File file = new File(Main.RESOURCES_PATH + "settings/settings.json");
        if(!new File(Main.RESOURCES_PATH + "settings").exists()){
            boolean ignored = new File(Main.RESOURCES_PATH + "settings").mkdir();
        }
        if(!file.exists()){
            try {
                boolean ignored = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Settings getSettingsFromJSon(){
        File file = new File(Main.RESOURCES_PATH + "settings/settings.json");
        try(FileReader reader = new FileReader(file)){
            Gson gson = new Gson();
            return gson.fromJson(reader, Settings.class);
        }catch (IOException e){
            e.printStackTrace();
        }
        Settings temp = new Settings();
        temp.setTheme(new Theme(Themes.DEFAULT));
        temp.setLanguage(new Language("fr_FR"));
        temp.saveSettingsToJson();
        return temp;
    }

    public void saveSettingsToJson(){
        try(FileWriter writer = new FileWriter(file)){
            gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(this, writer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Settings setLanguage(Language language){
        this.language = language;
        return this;
    }
    public Settings setTheme(Theme theme){
        this.theme = theme;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public Theme getTheme() {
        return theme;
    }
}
