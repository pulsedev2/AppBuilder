package fr.pulsedev.appbuilder.UI.panels;

import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.UI.Window;
import fr.pulsedev.appbuilder.UI.panels.enums.PanelManager;
import fr.pulsedev.appbuilder.projects.Project;
import fr.pulsedev.appbuilder.projects.errors.ProjectErrors;
import fr.pulsedev.appbuilder.settings.Language;
import fr.pulsedev.appbuilder.settings.Settings;
import fr.pulsedev.appbuilder.settings.Theme;
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

    @SuppressWarnings("deprecation")
    public ProjectChooserPanel(){
        JButton open = new JButton(Language.USER.interface_.getString("open_project"));
        open.setFont(new Font("Dialog", Font.PLAIN, 15));
        JButton create = new JButton(Language.USER.interface_.getString("new_project"));
        create.setFont(new Font("Dialog", Font.PLAIN, 15));
        // Get user desktop location
        File desktop = new File(System.getProperty("user.home"), "Desktop");
        // Init a JFileChooser
        JFileChooser fileChooser = new JFileChooser(desktop);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        ImageIcon logoIcon = new ImageIcon(Main.RESOURCES_PATH + "images/logo.png");
        JLabel logo = new JLabel(logoIcon);
        JButton close = UiUtils.getCloseButton();
        JButton settings = new JButton(Language.USER.interface_.getString("options"));
        settings.setFont(new Font("Dialog", Font.PLAIN, 15));

        // Listener of open & create button
        ActionListener actionListener = e -> {
            if (e.getSource() == open){
                fileChooser.resetChoosableFileFilters();
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setFileFilter(new FolderFilter(false));
                int returnedVal = fileChooser.showDialog(ProjectChooserPanel.this, "Accept");

                if (returnedVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        Main.PROJECT = Project.open(file);
                        SwingUtilities.getWindowAncestor(this).setVisible(false);
                        PanelManager.EDITOR.window.setPanel(new MainPanel());
                        PanelManager.EDITOR.window.run();
                    } catch (ProjectErrors projectErrors) {
                        projectErrors.printStackTrace();
                    }
                }
            }
            else if (e.getSource() == create){
                fileChooser.resetChoosableFileFilters();
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setFileFilter(new FolderFilter(true));
                int returnedVal = fileChooser.showDialog(ProjectChooserPanel.this, "Accept");

                if (returnedVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    PanelManager.PROJECT.window.setVisible(false);
                    Window popupWindow = new Window.Builder().setName("New Project").setPanel(new PopupPanel(file)).setBackground(Settings.getSettingsFromJSon().getTheme().getThemes().themesInterface.getBACKGROUND()).setResizable(false).createWindow();
                    popupWindow.run();
                }
            }
            else if (e.getSource() == settings){
                PanelManager.SETTINGS.window.setPanel(new SettingsPanel());
                PanelManager.SETTINGS.window.run();
            }

        };

        MouseListener onHoverListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ((JButton)e.getSource()).setForeground(Theme.USER.themesInterface.getHOVER_TEXT());
                ((JButton)e.getSource()).setFont(new Font("Dialog", Font.BOLD, 15));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ((JButton)e.getSource()).setForeground(Theme.USER.themesInterface.getTEXT());
                ((JButton)e.getSource()).setFont(new Font("Dialog", Font.PLAIN, 15));
            }
        };

        this.setLayout(null);

        // Action Listener
        open.addActionListener(actionListener);
        create.addActionListener(actionListener);
        settings.addActionListener(actionListener);

        // Mouse Listener
        open.addMouseListener(onHoverListener);
        create.addMouseListener(onHoverListener);
        settings.addMouseListener(onHoverListener);

        // Make all Buttons Transparent
        UiUtils.makeJButtonTransparent(open);
        UiUtils.makeJButtonTransparent(create);
        UiUtils.makeJButtonTransparent(settings);

        // Change text color
        open.setForeground(Theme.USER.themesInterface.getTEXT());
        create.setForeground(Theme.USER.themesInterface.getTEXT());
        settings.setForeground(Theme.USER.themesInterface.getTEXT());

        // Create image computing
        BufferedImage createImage = UiUtils.imageIconToBufferedImage(new ImageIcon(Main.RESOURCES_PATH + "images/new.png"));
        UiUtils.changeColor(createImage, Color.BLACK, Theme.USER.themesInterface.getHOVER_TEXT());
        createImage = UiUtils.resize(createImage, 15, 15);
        create.setIcon(new ImageIcon(createImage));

        // Open image computing
        BufferedImage openImage = UiUtils.imageIconToBufferedImage(new ImageIcon(Main.RESOURCES_PATH + "images/open.png"));
        UiUtils.changeColor(openImage, Color.BLACK, Theme.USER.themesInterface.getHOVER_TEXT());
        openImage = UiUtils.resize(openImage, 15, 15);
        open.setIcon(new ImageIcon(openImage));

        // Settings image computing
        BufferedImage settingsImage = UiUtils.imageIconToBufferedImage(new ImageIcon(Main.RESOURCES_PATH + "images/settings.png"));
        UiUtils.changeColor(settingsImage, Color.BLACK, Theme.USER.themesInterface.getHOVER_TEXT());
        settingsImage = UiUtils.resize(settingsImage, 15, 15);
        settings.setIcon(new ImageIcon(settingsImage));

        // Add All buttons
        this.add(open);
        this.add(create);
        this.add(close);
        this.add(logo);
        this.add(settings);

        // Set bounds
        close.setBounds(500 - 30,0,30,30);
        open.setBounds((int) (250 - open.getText().length()*7.5f), 250, open.getText().length()*15, 20);
        create.setBounds((int) (250 - create.getText().length()*7.5f), 300, create.getText().length()*15, 25);
        logo.setBounds(250 - logoIcon.getIconWidth()/2,110,150,135);
        settings.setBounds(500 - settings.getText().length()*15, 500 - 25, settings.getText().length()*15, 25);
    }
}
