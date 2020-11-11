package fr.pulsedev.appbuilder.UI.panels;

import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.UI.panels.enums.PanelManager;
import fr.pulsedev.appbuilder.settings.Settings;
import fr.pulsedev.appbuilder.settings.Theme;
import fr.pulsedev.appbuilder.themes.Themes;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;

public class SettingsPanel extends JPanel {


    public SettingsPanel(){
        List<String> itemsL = new ArrayList<>();
        for (Themes theme: Themes.values()) {
            itemsL.add(theme.name().toLowerCase().substring(0,1).toUpperCase() + theme.name().toLowerCase().substring(1));
        }
        JComboBox<String> theme = new JComboBox<String>(itemsL.toArray(String[]::new));
        theme.setSelectedItem(Settings.getSettingsFromJSon().getTheme().getThemes().name().toLowerCase().substring(0,1).toUpperCase() + Settings.getSettingsFromJSon().getTheme().getThemes().name().toLowerCase().substring(1));

        theme.addActionListener(e -> {
            if(e.getSource() == theme){
                Themes themes = Themes.valueOf(((String) Objects.requireNonNull(theme.getSelectedItem())).toUpperCase());
                Settings settings = Settings.getSettingsFromJSon(); // Use deprecated method because it's needed
                settings.setTheme(new Theme(themes));
                settings.saveSettingsToJson();

                PanelManager.RESTART.window.run();
            }
        });

        JLabel themeLabel = new JLabel("Choose a theme: ");
        themeLabel.setForeground(Theme.USER.themesInterface.getTEXT());
        themeLabel.setFont(new Font("Dialog", Font.PLAIN, 15));

        theme.setFont(new Font("Dialog", Font.PLAIN, 15));

        this.setLayout(null);
        this.add(themeLabel);
        this.add(theme);

        themeLabel.setBounds(20, 50, 150, 25);
        theme.setBounds(180, 50, 150, 25);
    }

}
