package fr.pulsedev.appbuilder.UI.panels.projectchooser.bar;

import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.settings.Language;
import fr.pulsedev.appbuilder.settings.Theme;
import fr.pulsedev.appbuilder.utils.UiUtils;

import javax.swing.*;
import java.awt.*;

public class HorizontalBar extends JPanel {
    private JLabel logo = new JLabel(new ImageIcon(UiUtils.resize(UiUtils.imageIconToBufferedImage(new ImageIcon(Main.RESOURCES_PATH + "images/logo.png")), 30, 30)));
    private JLabel text = new JLabel(Language.USER.interface_.getString("welcome"));
    private JButton closeButton = UiUtils.getCloseButton();

    public HorizontalBar() {
        this.setLayout(null);
        this.setBackground(Theme.USER.themesInterface.getBACKGROUND());

        this.add(this.logo);
        this.logo.setBounds(0, 0, this.logo.getIcon().getIconWidth(), 30);

        this.text.setForeground(Color.WHITE);
        this.text.setBackground(null);
        this.text.setFocusable(false);
        this.add(this.text);
        this.text.setBounds(this.logo.getIcon().getIconWidth(), 0, (int) this.text.getFontMetrics(this.text.getFont()).getStringBounds(this.text.getText(), this.getGraphics()).getWidth(), 30);

        this.add(closeButton);
        this.closeButton.setBounds(785 - this.closeButton.getIcon().getIconWidth(), (30 - this.closeButton.getIcon().getIconHeight()) / 2, this.closeButton.getIcon().getIconWidth(), this.closeButton.getIcon().getIconHeight());
    }
}
