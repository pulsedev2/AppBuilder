package fr.pulsedev.appbuilder.settings;

public class Language extends Settings{

    private String lang;

    public Language(String lang){
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }

    public void setLanguage(String language){
        this.lang = language;
    }

}
