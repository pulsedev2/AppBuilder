package fr.pulsedev.appbuilder.themes;

import java.awt.*;

public enum Themes {

    DEFAULT(new Dark()),
    WHITE(new White());

    public ThemesInterface themesInterface;

    Themes(ThemesInterface themesInterface){
        this.themesInterface = themesInterface;
    }

    public static abstract class ThemesInterface{

        Color BACKGROUND;
        Color LIGHTER_BACKGROUND;
        Color FOREGROUND;
        Color LIGHTER_FOREGROUND;
        Color TEXT;

        public ThemesInterface(Color BACKGROUND, Color LIGHTER_BACKGROUND, Color FOREGROUND, Color LIGHTER_FOREGROUND, Color TEXT) {
            this.BACKGROUND = BACKGROUND;
            this.LIGHTER_BACKGROUND = LIGHTER_BACKGROUND;
            this.FOREGROUND = FOREGROUND;
            this.LIGHTER_FOREGROUND = LIGHTER_FOREGROUND;
            this.TEXT = TEXT;
        }

        public Color getBACKGROUND() {
            return BACKGROUND;
        }

        public Color getLIGHTER_BACKGROUND() {
            return LIGHTER_BACKGROUND;
        }

        public Color getFOREGROUND() {
            return FOREGROUND;
        }

        public Color getLIGHTER_FOREGROUND() {
            return LIGHTER_FOREGROUND;
        }

        public Color getTEXT() {
            return TEXT;
        }
    }

}
