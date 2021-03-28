package fr.pulsedev.bootstrap.Ui.panels;

import fr.pulsedev.bootstrap.Main;
import fr.pulsedev.bootstrap.enums.StateText;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BootstrapPanel extends JPanel {
    private final JProgressBar progressBar;
    private final JLabel label;

    private int currentState = -1;

    public BootstrapPanel() {
        this.setLayout(null);
        this.setBorder(null);
        this.setBackground(null);
        this.setOpaque(false);

        this.progressBar = new JProgressBar();
        this.progressBar.setBackground(null);
        this.progressBar.setOpaque(false);
        this.progressBar.setForeground(Color.GREEN);
        this.progressBar.setIndeterminate(false);
        this.progressBar.setBorderPainted(false);

        this.add(progressBar);

        this.label = new JLabel();
        this.label.setBackground(null);
        this.label.setForeground(Color.WHITE);
        this.label.setBorder(null);

        this.add(label);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        try {
            Image image = ImageIO.read(getClass().getClassLoader().getResource("images/bootstrap/background.png"));
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.progressBar.setBounds(0, 395, this.getWidth(), 5);

        FontMetrics metrics = this.label.getFontMetrics(this.label.getFont());
        int messageWidth = metrics.stringWidth(this.label.getText());
        this.label.setBounds(10, 370, messageWidth, 10);

        if (Main.downloader.getState() != this.currentState) {
            this.currentState = Main.downloader.getState();

            this.label.setText(StateText.getById(this.currentState).getText());
        }
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public JLabel getLabel() {
        return label;
    }
}
