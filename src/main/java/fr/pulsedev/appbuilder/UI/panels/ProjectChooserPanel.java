package fr.pulsedev.appbuilder.UI.panels;

import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.UI.Window;
import fr.pulsedev.appbuilder.projects.Project;
import fr.pulsedev.appbuilder.projects.errors.ProjectErrors;
import fr.pulsedev.appbuilder.utils.FolderFilter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

public class ProjectChooserPanel extends JPanel {

    public ProjectChooserPanel(){
        JButton open = new JButton("Open a project ...");
        JButton create = new JButton("New project ...");
        // Get user desktop location
        File desktop = new File(System.getProperty("user.home"), "Desktop");
        // Init a JFileChooser
        JFileChooser fileChooser = new JFileChooser(desktop);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Listener of open & create button
        ActionListener actionListener = e -> {
            if (e.getSource() == open){
                fileChooser.setFileFilter(new FolderFilter(false));
                int returnedVal = fileChooser.showDialog(ProjectChooserPanel.this, "Accept");

                if (returnedVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        Project.open(file);
                        PanelManager.EDITOR.window.run();
                    } catch (ProjectErrors projectErrors) {
                        projectErrors.printStackTrace();
                    }
                }
            }
            else if (e.getSource() == create){
                fileChooser.setFileFilter(new FolderFilter(true));
                int returnedVal = fileChooser.showDialog(ProjectChooserPanel.this, "Accept");

                if (returnedVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    PanelManager.PROJECT.window.setVisible(false);
                    Window popupWindow = null;
                    popupWindow = new Window.Builder().setName("Test").setPanel(new PopupPanel(file)).setBackground(Color.BLUE).setResizable(false).createWindow();
                    popupWindow.run();
                }
            }
        };

        open.addActionListener(actionListener);
        create.addActionListener(actionListener);
        this.add(open);
        this.add(create);
    }
}
