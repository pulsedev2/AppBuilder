package fr.pulsedev.appbuilder.UI;

import fr.pulsedev.appbuilder.UI.panels.ProjectChooserPanel;

import javax.swing.*;
import java.awt.*;

public class ProjectWindow extends JFrame {

    ProjectChooserPanel panel;

    public ProjectWindow(){
        this.setSize(new Dimension(600, 600));
        this.setName("Choose a project");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.panel = new ProjectChooserPanel();
        this.setContentPane(this.panel);

        this.setVisible(true);
    }

    public ProjectChooserPanel getPanel() {
        return panel;
    }
}
