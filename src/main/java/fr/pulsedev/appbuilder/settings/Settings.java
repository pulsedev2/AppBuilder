package fr.pulsedev.appbuilder.settings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Settings {

    private Gson gson;
    private Language language;
    private Theme theme;

    public Settings getSettingsFromJSon(File file){
        try(FileReader reader = new FileReader(file)){
            gson = new Gson();
            return gson.fromJson(reader, Settings.class);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public void saveSettingsToJson(File file){
        try(FileWriter writer = new FileWriter(file)){
            gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(this, writer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Language getLanguage() {
        return language;
    }

    public Theme getTheme() {
        return theme;
    }
}
