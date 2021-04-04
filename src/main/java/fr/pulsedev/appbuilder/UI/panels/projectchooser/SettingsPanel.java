package fr.pulsedev.appbuilder.UI.panels.projectchooser;

import fr.pulsedev.appbuilder.UI.panels.enums.PanelManager;
import fr.pulsedev.appbuilder.languages.Lang;
import fr.pulsedev.appbuilder.settings.Language;
import fr.pulsedev.appbuilder.settings.Settings;
import fr.pulsedev.appbuilder.settings.Theme;
import fr.pulsedev.appbuilder.themes.Themes;
import fr.pulsedev.appbuilder.utils.UiUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SettingsPanel extends JPanel {
    private JComboBox<String> theme;
    private JComboBox<String> lang;

    private JLabel generalLabel = new JLabel(Language.USER.interface_.getString("general_option"));
    private JLabel themeLabel = new JLabel(Language.USER.interface_.getString("option.theme"));
    private JLabel langLabel = new JLabel(Language.USER.interface_.getString("option.lang"));

    public SettingsPanel() {
        this.setLayout(null);
        this.setBackground(Theme.USER.themesInterface.getBACKGROUND());

        this.generalLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        this.add(this.generalLabel);
        this.generalLabel.setBounds(20, 20, this.generalLabel.getFontMetrics(this.generalLabel.getFont()).stringWidth(this.generalLabel.getText()), 30);

        this.themeLabel.setForeground(Theme.USER.themesInterface.getTEXT());
        this.themeLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
        this.add(this.themeLabel);
        this.themeLabel.setBounds(this.generalLabel.getX(), this.generalLabel.getY() + this.generalLabel.getHeight() + 20, this.themeLabel.getFontMetrics(this.themeLabel.getFont()).stringWidth(this.themeLabel.getText()), 20);

        List<String> themeList = new ArrayList<>();
        for (Themes theme : Themes.values()) {
            themeList.add(UiUtils.lowCaseApartTheFirstChar(theme.name()));
        }
        this.theme = new JComboBox<>(themeList.toArray(String[]::new));
        this.theme.setFont(new Font("Dialog", Font.PLAIN, 15));
        this.theme.setSelectedItem(UiUtils.lowCaseApartTheFirstChar(Settings.getSettingsFromJSon().getTheme().getThemes().name()));
        this.theme.addActionListener(e -> {
            Themes themes = Themes.valueOf(((String) Objects.requireNonNull(theme.getSelectedItem())).toUpperCase());
            Settings settings = Settings.getSettingsFromJSon(); // Use deprecated method because it's needed
            settings.setTheme(new Theme(themes));
            settings.saveSettingsToJson();

            PanelManager.RESTART.window.run();
        });
        this.add(this.theme);
        this.theme.setBounds(this.themeLabel.getX() + this.themeLabel.getWidth() + 10, this.themeLabel.getY(), 90, this.themeLabel.getHeight());

        this.langLabel.setForeground(Theme.USER.themesInterface.getTEXT());
        this.langLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
        this.add(this.langLabel);
        this.langLabel.setBounds(this.themeLabel.getX(), this.themeLabel.getY() + this.themeLabel.getHeight() + 20, this.langLabel.getFontMetrics(this.langLabel.getFont()).stringWidth(this.langLabel.getText()), 20);

        List<String> langList = new ArrayList<>();
        for (Lang lang : Lang.values()) {
            langList.add(UiUtils.lowCaseApartTheFirstChar(lang.name()));
        }
        this.lang = new JComboBox<>(langList.toArray(String[]::new));
        this.lang.setFont(new Font("Dialog", Font.PLAIN, 15));
        this.lang.setSelectedItem(UiUtils.lowCaseApartTheFirstChar(Settings.getSettingsFromJSon().getLanguage().getLang().name()));
        this.lang.addActionListener(e -> {
            Lang selectedLang = Lang.valueOf(((String) Objects.requireNonNull(lang.getSelectedItem())).toUpperCase());
            Settings settings = Settings.getSettingsFromJSon(); // Use deprecated method because it's needed
            settings.setLanguage(new Language(selectedLang));
            settings.saveSettingsToJson();

            PanelManager.RESTART.window.run();
        });
        this.add(this.lang);
        this.lang.setBounds(this.langLabel.getX() + this.langLabel.getWidth() + 10, this.langLabel.getY(), 90, this.langLabel.getHeight());
    }
}
