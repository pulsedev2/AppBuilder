package fr.pulsedev.appbuilder.UI.panels;

import fr.pulsedev.appbuilder.UI.panels.projectchooser.ProjectPanel;
import fr.pulsedev.appbuilder.UI.panels.projectchooser.SettingsPanel;
import fr.pulsedev.appbuilder.UI.panels.projectchooser.bar.HorizontalBar;
import fr.pulsedev.appbuilder.UI.panels.projectchooser.bar.VerticalBar;

import javax.swing.*;
import java.awt.*;

public class ProjectChooserPanel extends JPanel {
    public static ProjectChooserPanel INSTANCE;

    private VerticalBar verticalBar;
    private HorizontalBar horizontalBar;
    private ProjectPanel projectPanel;
    private SettingsPanel settingsPanel;

    public ProjectChooserPanel() {
        INSTANCE = this;
        this.setLayout(null);

        verticalBar = new VerticalBar();
        horizontalBar = new HorizontalBar();

        projectPanel = new ProjectPanel();
        settingsPanel = new SettingsPanel();


        // Add All buttons
        this.add(verticalBar);
        this.add(horizontalBar);

        // Set bounds
        verticalBar.setBounds(0, 30, 200, 600);
        horizontalBar.setBounds(0, 0, 800, 30);

        switchPanel(projectPanel, new Rectangle(200, 30, 600, 600));
    }

    public static void switchPanel(JComponent jComponent, Rectangle bounds) {
        ProjectChooserPanel projectChooserPanel = ProjectChooserPanel.INSTANCE;
        for (Component component : projectChooserPanel.getComponents()) {
            if (component == projectChooserPanel.horizontalBar || component == projectChooserPanel.verticalBar)
                continue;

            projectChooserPanel.remove(component);
        }

        projectChooserPanel.add(jComponent);
        jComponent.setBounds(bounds);

        projectChooserPanel.updateUI();
    }

    public VerticalBar getVerticalBar() {
        return verticalBar;
    }

    public HorizontalBar getHorizontalBar() {
        return horizontalBar;
    }

    public ProjectPanel getProjectPanel() {
        return projectPanel;
    }

    public SettingsPanel getSettingsPanel() {
        return settingsPanel;
    }
}
