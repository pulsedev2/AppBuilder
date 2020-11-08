package fr.pulsedev.appbuilder.themes;

import java.awt.*;

public class Dark extends Themes.ThemesInterface {

    public static final Color BACKGROUND = new Color(13, 27, 42);
    public static final Color LIGHTER_BACKGROUND = new Color(27, 38, 59);
    public static final Color FOREGROUND = new Color(65, 90, 119);
    public static final Color LIGHTER_FOREGROUND = new Color(119, 141, 169);
    public static final Color TEXT = new Color(224, 225, 221);


    public Dark() {
        super(BACKGROUND, LIGHTER_BACKGROUND, FOREGROUND, LIGHTER_FOREGROUND, TEXT);
    }
}

