package fr.pulsedev.appbuilder.themes;

import java.awt.*;

public class Dark extends Themes.ThemesInterface {

    public static final Color BACKGROUND = Color.decode("#0D1B2A");
    public static final Color LIGHTER_BACKGROUND = Color.decode("#1B263B");
    public static final Color FOREGROUND = Color.decode("#E0E1DD");
    public static final Color LIGHTER_FOREGROUND = Color.decode("#778DA9");
    public static final Color TEXT = Color.decode("#415A77");


    public Dark() {
        super(BACKGROUND, LIGHTER_BACKGROUND, FOREGROUND, LIGHTER_FOREGROUND, TEXT);
    }
}

