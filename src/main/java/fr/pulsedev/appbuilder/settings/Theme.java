package fr.pulsedev.appbuilder.settings;

import fr.pulsedev.appbuilder.themes.Themes;

public class Theme extends Settings{

    private Themes themes;
    public static Themes USER;

    public Theme(Themes themes){
        this.themes = themes;
    }

    @SuppressWarnings("deprecation")
    public Theme(String[] args){
        USER = Settings.getSettingsFromJSon().getTheme().getThemes();
    }

    public Themes getThemes() {
        return themes;
    }

    public void setThemes(Themes themes){
        this.themes = themes;
    }
}
