package fr.pulsedev.appbuilder.UI.panels.bar;

import fr.pulsedev.appbuilder.UI.panels.MainPanel;
import fr.pulsedev.appbuilder.settings.Theme;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class HorizontalBar extends JPanel {
    Image logo;
    JMenuBar menuBar;

    public HorizontalBar() {
        this.setLayout(null);
        this.setBackground(Theme.USER.themesInterface.getFOREGROUND());

        try {
            this.logo = ImageIO.read(getClass().getClassLoader().getResource("images/logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        this.menuBar.add(fileMenu);

        JMenuItem close = new JMenuItem("CC");
        close.addActionListener(e -> {
            System.out.println("1");
        });
        fileMenu.add(close);

        JMenu editMenu = new JMenu("Edit");
        this.menuBar.add(editMenu);

        this.add(this.menuBar);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(this.logo, 0, 0, this.logo.getWidth(this), MainPanel.horizontalBarHeight, this);

        this.menuBar.setBounds(this.logo.getWidth(this) + 20, 0, this.getWidth(), MainPanel.horizontalBarHeight);
    }
}
