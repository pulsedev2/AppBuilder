package fr.pulsedev.appbuilder.themes;

import java.awt.*;

public class White extends Themes.ThemesInterface {

    public static final Color BACKGROUND = Color.decode("#3D5A80");
    public static final Color LIGHTER_BACKGROUND = Color.decode("#98C1D9");
    public static final Color FOREGROUND = Color.decode("#293241");
    public static final Color LIGHTER_FOREGROUND = Color.decode("#EE6C4D");
    public static final Color TEXT = Color.decode("#E0FBFC");

    public White() {
        super(BACKGROUND, LIGHTER_BACKGROUND, FOREGROUND, LIGHTER_FOREGROUND, TEXT);
    }
}
