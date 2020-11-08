package fr.pulsedev.appbuilder.settings;

public class Language extends Settings{

    private String language;

    public Language(String language){
        this.language = language;
    }

    public String getLang() {
        return language;
    }
}
