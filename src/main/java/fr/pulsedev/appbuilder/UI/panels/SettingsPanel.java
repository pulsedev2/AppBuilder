package fr.pulsedev.appbuilder.UI.panels;

import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.settings.Settings;
import fr.pulsedev.appbuilder.settings.Theme;
import fr.pulsedev.appbuilder.themes.Themes;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SettingsPanel extends JPanel {


    public SettingsPanel(){
        List<String> itemsL = new ArrayList<>();
        for (Themes theme: Themes.values()) {
            itemsL.add(theme.name());
        }
        JComboBox<String> theme = new JComboBox<String>(itemsL.toArray(String[]::new));
        theme.setSelectedItem(Settings.getSettingsFromJSon().getTheme().getThemes().name());

        theme.addActionListener(e -> {
            if(e.getSource() == theme){
                Themes themes = Themes.valueOf((String) theme.getSelectedItem());
                Settings settings = Settings.getSettingsFromJSon();
                settings.setTheme(new Theme(themes));
                settings.saveSettingsToJson();
                try {
                    Main.restartApplication();
                } catch (InterruptedException | IOException uriSyntaxException) {
                    uriSyntaxException.printStackTrace();
                }
            }
        });

        JLabel themeLabel = new JLabel("Choose a theme: ");
        themeLabel.setForeground(Settings.getSettingsFromJSon().getTheme().getThemes().themesInterface.getTEXT());
        themeLabel.setFont(new Font("Dialog", Font.PLAIN, 15));

        this.setLayout(null);
        this.add(themeLabel);
        this.add(theme);

        themeLabel.setBounds(20, 50, 150, 25);
        theme.setBounds(180, 50, 150, 25);
    }

}
