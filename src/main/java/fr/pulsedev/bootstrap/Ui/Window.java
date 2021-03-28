package fr.pulsedev.bootstrap.Ui;

import fr.pulsedev.bootstrap.Ui.panels.BootstrapPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

public class Window extends JFrame {
    private final BootstrapPanel bootstrapPanel;

    public Window() {
        this.setTitle("AppBuilder");
        this.setSize(650, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setResizable(false);

        try {
            this.setIconImage(ImageIO.read(getClass().getClassLoader().getResource("images/logo.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setContentPane(this.bootstrapPanel = new BootstrapPanel());
        this.bootstrapPanel.setBounds(0, 0, this.getWidth(), this.getHeight());

        this.setVisible(true);
    }

    public void close() {
        System.exit(0);
    }

    public BootstrapPanel getBootstrapPanel() {
        return bootstrapPanel;
    }
}
