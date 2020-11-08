package fr.pulsedev.appbuilder.themes;

import java.awt.*;

public class White extends Themes.ThemesInterface {

    public static final Color BACKGROUND = new Color(41, 50, 65);
    public static final Color LIGHTER_BACKGROUND = new Color(61, 90,128);
    public static final Color FOREGROUND = new Color(152, 193, 217);
    public static final Color LIGHTER_FOREGROUND = new Color(224, 251, 252);
    public static final Color TEXT = new Color(238, 108, 77);

    public White() {
        super(BACKGROUND, LIGHTER_BACKGROUND, FOREGROUND, LIGHTER_FOREGROUND, TEXT);
    }
}
