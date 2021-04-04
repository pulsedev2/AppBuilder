package fr.pulsedev.appbuilder.UI.panels.projectchooser;

import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.UI.Window;
import fr.pulsedev.appbuilder.UI.panels.MainPanel;
import fr.pulsedev.appbuilder.UI.panels.PopupPanel;
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
import java.awt.event.*;
import java.io.File;

public class ProjectPanel extends JPanel {
    private JButton openButton = new JButton(Language.USER.interface_.getString("open_project"));
    private JButton createButoon = new JButton(Language.USER.interface_.getString("new_project"));

    private JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.home"), "Desktop"));

    private JLabel researchBar = new JLabel(new ImageIcon(UiUtils.resize(UiUtils.imageIconToBufferedImage(new ImageIcon(Main.RESOURCES_PATH + "images/research_bar.png")), 275, 40)));

    private JTextField textField = new JTextField(Language.USER.interface_.getString("research_bar.project"));

    public ProjectPanel() {
        this.setLayout(null);
        this.setBackground(Theme.USER.themesInterface.getBACKGROUND());

        this.fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);


        MouseListener onHoverListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ((JButton) e.getSource()).setForeground(Theme.USER.themesInterface.getHOVER_TEXT());
                ((JButton) e.getSource()).setFont(new Font("Dialog", Font.BOLD, 15));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ((JButton) e.getSource()).setForeground(Theme.USER.themesInterface.getTEXT());
                ((JButton) e.getSource()).setFont(new Font("Dialog", Font.PLAIN, 15));
            }
        };

        this.textField.setForeground(Theme.USER.themesInterface.getTEXT());
        this.textField.setBackground(null);
        this.textField.setOpaque(false);
        this.textField.setBorder(null);
        this.textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                JTextField textField = (JTextField) e.getSource();
                if (textField.getText().equals(Language.USER.interface_.getString("research_bar.project")))
                    textField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                JTextField textField = (JTextField) e.getSource();
                if (textField.getText().isEmpty())
                    textField.setText(Language.USER.interface_.getString("research_bar.project"));
            }
        });

        this.add(this.textField);

        this.researchBar.setFocusable(true);
        this.add(this.researchBar);

        this.researchBar.setBounds(15, 10, this.researchBar.getIcon().getIconWidth(), this.researchBar.getIcon().getIconHeight());
        this.textField.setBounds(this.researchBar.getX() + this.researchBar.getWidth() / 8, this.researchBar.getY(), this.researchBar.getWidth() - this.researchBar.getWidth() / 7, this.researchBar.getHeight());

        this.createButoon.setFont(new Font("Dialog", Font.PLAIN, 15));
        this.createButoon.setFocusable(false);
        this.createButoon.setBorder(null);
        this.createButoon.setBorderPainted(false);
        this.createButoon.setForeground(Theme.USER.themesInterface.getTEXT());
        this.createButoon.setBackground(null);
        this.createButoon.addActionListener(e -> {
            fileChooser.resetChoosableFileFilters();
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setFileFilter(new FolderFilter(true));
            int returnedVal = fileChooser.showDialog(this, "Accept");

            if (returnedVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                PanelManager.PROJECT.window.setVisible(false);
                Window popupWindow = new Window.Builder().setName("New Project").setPanel(new PopupPanel(file)).setBackground(Settings.getSettingsFromJSon().getTheme().getThemes().themesInterface.getBACKGROUND()).setResizable(false).createWindow();
                popupWindow.run();
            }
        });
        this.createButoon.addMouseListener(onHoverListener);
        this.add(this.createButoon);
        this.createButoon.setBounds(this.researchBar.getWidth() + this.researchBar.getX() + 20
                , 20
                , this.createButoon.getFontMetrics(new Font("Dialog", Font.BOLD, 15)).stringWidth(this.createButoon.getText())
                , (int) this.createButoon.getFontMetrics(new Font("Dialog", Font.BOLD, 15)).getStringBounds(this.createButoon.getText(), this.getGraphics()).getHeight());

        this.openButton.setFont(new Font("Dialog", Font.PLAIN, 15));
        this.openButton.setFocusable(false);
        this.openButton.setBorder(null);
        this.openButton.setBorderPainted(false);
        this.openButton.setForeground(Theme.USER.themesInterface.getTEXT());
        this.openButton.setBackground(null);
        this.openButton.addActionListener(e -> {
            fileChooser.resetChoosableFileFilters();
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setFileFilter(new FolderFilter(false));
            int returnedVal = fileChooser.showDialog(this, "Accept");

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
        });
        this.openButton.addMouseListener(onHoverListener);
        this.add(this.openButton);
        this.openButton.setBounds(this.createButoon.getX() + this.createButoon.getWidth() + 20
                , 20
                , this.openButton.getFontMetrics(new Font("Dialog", Font.BOLD, 15)).stringWidth(this.openButton.getText())
                , (int) this.openButton.getFontMetrics(new Font("Dialog", Font.BOLD, 15)).getStringBounds(this.openButton.getText(), this.getGraphics()).getHeight());
    }
}
