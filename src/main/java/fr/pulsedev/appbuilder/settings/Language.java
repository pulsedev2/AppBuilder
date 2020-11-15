package fr.pulsedev.appbuilder.settings;

import fr.pulsedev.appbuilder.Languages.Lang;

public class Language extends Settings{

    private Lang lang;
    public static Lang USER;

    public Language(Lang lang){
        this.lang = lang;
    }

    @SuppressWarnings("deprecation")
    public Language(String[] args){
        USER = Settings.getSettingsFromJSon().getLanguage().getLang();
    }

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang language){
        this.lang = language;
    }

}
