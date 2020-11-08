package fr.pulsedev.appbuilder.settings;

import fr.pulsedev.appbuilder.themes.Themes;

public class Theme extends Settings{

    private Themes themes;

    public Theme(Themes themes){
        this.themes = themes;
    }

    public Themes getThemes() {
        return themes;
    }

    public void setThemes(Themes themes){
        this.themes = themes;
    }
}
