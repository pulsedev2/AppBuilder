package fr.pulsedev.appbuilder.UI.panels.projectchooser.bar;

import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.UI.panels.ProjectChooserPanel;
import fr.pulsedev.appbuilder.settings.Language;
import fr.pulsedev.appbuilder.settings.Theme;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class VerticalBar extends JPanel {
    private Image logo;
    private JButton project = new JButton(Language.USER.interface_.getString("projects"));
    private JButton settings = new JButton(Language.USER.interface_.getString("options"));

    public VerticalBar() {
        this.setLayout(null);
        this.setBackground(Theme.USER.themesInterface.getLIGHTER_BACKGROUND());

        try {
            logo = ImageIO.read(new File(Main.RESOURCES_PATH + "images/logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.project.setForeground(Color.WHITE);
        this.project.setBackground(Theme.USER.themesInterface.getBACKGROUND());
        this.project.setBorderPainted(false);
        this.project.setFocusable(false);
        this.project.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                project.setBackground(Theme.USER.themesInterface.getBACKGROUND());
                settings.setBackground(null);
                ProjectChooserPanel.switchPanel(ProjectChooserPanel.INSTANCE.getProjectPanel(), new Rectangle(200, 30, 600, 600));
            }
        });
        this.add(this.project);
        this.project.setBounds(0, 175, 200, 50);

        this.settings.setForeground(Color.WHITE);
        this.settings.setBackground(null);
        this.settings.setBorderPainted(false);
        this.settings.setFocusable(false);
        this.settings.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                settings.setBackground(Theme.USER.themesInterface.getBACKGROUND());
                project.setBackground(null);
                ProjectChooserPanel.switchPanel(ProjectChooserPanel.INSTANCE.getSettingsPanel(), new Rectangle(200, 30, 600, 600));
            }
        });
        this.add(this.settings);
        this.settings.setBounds(0, 225, 200, 50);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(logo, 0, 0, this.getWidth(), logo.getHeight(this), this);
    }
}
