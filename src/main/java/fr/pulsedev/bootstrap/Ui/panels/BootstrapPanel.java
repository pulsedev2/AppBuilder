package fr.pulsedev.bootstrap.Ui.panels;

import javax.swing.*;
import java.awt.*;

public class BootstrapPanel extends JPanel {
    public JProgressBar progressBar;

    public BootstrapPanel() {
        progressBar = new JProgressBar();
        progressBar.setBackground(Color.BLACK);
        progressBar.setForeground(Color.BLUE);

        this.add(progressBar);
        progressBar.setBounds(0, 200, this.getWidth(), 50);
        progressBar.setIndeterminate(false);

    }

}
