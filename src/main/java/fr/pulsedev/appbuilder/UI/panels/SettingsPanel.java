package fr.pulsedev.appbuilder.UI.panels;

import fr.pulsedev.appbuilder.Languages.Lang;
import fr.pulsedev.appbuilder.UI.panels.enums.PanelManager;
import fr.pulsedev.appbuilder.settings.Language;
import fr.pulsedev.appbuilder.settings.Settings;
import fr.pulsedev.appbuilder.settings.Theme;
import fr.pulsedev.appbuilder.themes.Themes;
import fr.pulsedev.appbuilder.utils.UiUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class SettingsPanel extends JPanel {

    @SuppressWarnings("deprecation")
    public SettingsPanel(){
        List<String> themeList = new ArrayList<>();
        for (Themes theme: Themes.values()) {
            themeList.add(UiUtils.lowCaseApartTheFirstChar(theme.name()));
        }
        JComboBox<String> theme = new JComboBox<>(themeList.toArray(String[]::new));
        theme.setSelectedItem(UiUtils.lowCaseApartTheFirstChar(Settings.getSettingsFromJSon().getTheme().getThemes().name()));

        List<String> langList = new ArrayList<>();
        for(Lang lang: Lang.values()){
            langList.add(UiUtils.lowCaseApartTheFirstChar(lang.name()));
        }
        JComboBox<String> lang = new JComboBox<>(langList.toArray(String[]::new));
        lang.setSelectedItem(UiUtils.lowCaseApartTheFirstChar(Settings.getSettingsFromJSon().getLanguage().getLang().name()));

        ActionListener actionListener = e -> {
            if (e.getSource() == theme) {
                Themes themes = Themes.valueOf(((String) Objects.requireNonNull(theme.getSelectedItem())).toUpperCase());
                Settings settings = Settings.getSettingsFromJSon(); // Use deprecated method because it's needed
                settings.setTheme(new Theme(themes));
                settings.saveSettingsToJson();

                PanelManager.RESTART.window.run();
            }
            else if (e.getSource() == lang){
                Lang selectedLang = Lang.valueOf(((String) Objects.requireNonNull(lang.getSelectedItem())).toUpperCase());
                Settings settings = Settings.getSettingsFromJSon(); // Use deprecated method because it's needed
                settings.setLanguage(new Language(selectedLang));
                settings.saveSettingsToJson();

                PanelManager.RESTART.window.run();
            }
        };

        theme.addActionListener(actionListener);
        lang.addActionListener(actionListener);

        JLabel themeLabel = new JLabel(Language.USER.interface_.getString("theme_option"));
        themeLabel.setForeground(Theme.USER.themesInterface.getTEXT());
        themeLabel.setFont(new Font("Dialog", Font.PLAIN, 15));

        JLabel langLabel = new JLabel(Language.USER.interface_.getString("lang_option"));
        langLabel.setForeground(Theme.USER.themesInterface.getTEXT());
        langLabel.setFont(new Font("Dialog", Font.PLAIN, 15));

        theme.setFont(new Font("Dialog", Font.PLAIN, 15));
        lang.setFont(new Font("Dialog", Font.PLAIN, 15));

        this.setLayout(null);

        SettingsPanel instance = this;

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(instance.getGraphics() != null) {
                    int themeLabelSize = instance.getGraphics().getFontMetrics(new Font("Dialog", Font.PLAIN, 15)).stringWidth(themeLabel.getText());
                    int langLabelSize = instance.getGraphics().getFontMetrics(new Font("Dialog", Font.PLAIN, 15)).stringWidth(langLabel.getText());

                    instance.add(themeLabel);
                    instance.add(theme);
                    instance.add(lang);
                    instance.add(langLabel);

                    themeLabel.setBounds(20, 50, themeLabelSize, 25);
                    theme.setBounds(20 + themeLabelSize + 15, 50, 150, 25);
                    langLabel.setBounds(20, 80, langLabelSize, 25);
                    theme.doLayout();
                    lang.setBounds(20 + langLabelSize + 15,80, 150, 25);
                    lang.doLayout();
                }
            }
        };

        java.util.Timer timer = new Timer();

        timer.schedule(timerTask, 100);
    }

}
