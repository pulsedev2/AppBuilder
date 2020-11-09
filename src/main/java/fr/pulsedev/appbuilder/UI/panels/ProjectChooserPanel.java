package fr.pulsedev.appbuilder.UI.panels;

import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.UI.Window;
import fr.pulsedev.appbuilder.UI.panels.enums.PanelManager;
import fr.pulsedev.appbuilder.projects.Project;
import fr.pulsedev.appbuilder.projects.errors.ProjectErrors;
import fr.pulsedev.appbuilder.settings.Settings;
import fr.pulsedev.appbuilder.themes.Themes;
import fr.pulsedev.appbuilder.utils.FolderFilter;
import fr.pulsedev.appbuilder.utils.UiUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class ProjectChooserPanel extends JPanel {

    public ProjectChooserPanel(){
        JButton open = new JButton("Open a project ...");
        open.setFont(new Font("Dialog", Font.PLAIN, 15));
        JButton create = new JButton("New project ...");
        create.setFont(new Font("Dialog", Font.PLAIN, 15));
        // Get user desktop location
        File desktop = new File(System.getProperty("user.home"), "Desktop");
        // Init a JFileChooser
        JFileChooser fileChooser = new JFileChooser(desktop);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        JButton close = new JButton();
        ImageIcon logoIcon = new ImageIcon(Main.RESOURCES_PATH + "images/logo.png");
        JLabel logo = new JLabel(logoIcon);

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
            else if (e.getSource() == close){
                System.exit(0);
            }
        };

        MouseListener onHoverListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource() == open){
                    open.setForeground(Settings.getSettingsFromJSon().getTheme().getThemes().themesInterface.getHOVER_TEXT());
                }
                else if(e.getSource() == create){
                    create.setForeground(Settings.getSettingsFromJSon().getTheme().getThemes().themesInterface.getHOVER_TEXT());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource() == open){
                    open.setForeground(Settings.getSettingsFromJSon().getTheme().getThemes().themesInterface.getTEXT());
                }
                else if(e.getSource() == create){
                    create.setForeground(Settings.getSettingsFromJSon().getTheme().getThemes().themesInterface.getTEXT());
                }
            }
        };

        this.setLayout(null);

        // Action Listener
        open.addActionListener(actionListener);
        create.addActionListener(actionListener);
        close.addActionListener(actionListener);

        // Mouse Listener
        open.addMouseListener(onHoverListener);
        create.addMouseListener(onHoverListener);

        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                close.setContentAreaFilled(true);
                close.setBackground(Color.RED);
                System.out.println("entered");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                UiUtils.makeJButtonTransparent(close);
            }
        });

        // Make all Buttons Transparent
        UiUtils.makeJButtonTransparent(open);
        UiUtils.makeJButtonTransparent(create);
        UiUtils.makeJButtonTransparent(close);

        // Change text color
        open.setForeground(Settings.getSettingsFromJSon().getTheme().getThemes().themesInterface.getTEXT());
        create.setForeground(Settings.getSettingsFromJSon().getTheme().getThemes().themesInterface.getTEXT());

        // Close image computing
        BufferedImage closeImage = UiUtils.imageIconToBufferedImage(new ImageIcon(Main.RESOURCES_PATH + "images/close.png"));
        UiUtils.changeColor(closeImage, Color.BLACK, Settings.getSettingsFromJSon().getTheme().getThemes().themesInterface.getTEXT());
        closeImage = UiUtils.resize(closeImage, 15, 15);
        close.setIcon(new ImageIcon(closeImage));

        // Add All buttons
        this.add(open);
        this.add(create);
        this.add(close);
        this.add(logo);

        // Set bounds
        close.setBounds(500 - 30,0,30,30);
        open.setBounds(180, 250, 130, 20);
        create.setBounds(180, 300, 130, 25);
        logo.setBounds(170,110,150,135);
    }
}
