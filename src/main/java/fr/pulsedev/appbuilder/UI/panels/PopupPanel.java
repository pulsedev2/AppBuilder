package fr.pulsedev.appbuilder.UI.panels;

import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.UI.Window;
import fr.pulsedev.appbuilder.UI.panels.enums.PanelManager;
import fr.pulsedev.appbuilder.projects.Project;
import fr.pulsedev.appbuilder.projects.ProjectOptions;
import fr.pulsedev.appbuilder.projects.errors.ProjectErrors;
import fr.pulsedev.appbuilder.settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;

public class PopupPanel extends JPanel {

    ProjectOptions<String> projectOptions = Main.options;
    HashMap<String, JTextField> components = new HashMap<>();
    Window window = (Window) SwingUtilities.getWindowAncestor(this);

    public PopupPanel(File file){

        int x_default_label = 215;
        int x_default_field = 100;
        int y_default_label = 0;
        int y_default_field = 95;
        int i = 0;

        this.setLayout(null);

        JLabel title = new JLabel("Project Information");
        title.setForeground(Settings.getSettingsFromJSon().getTheme().getThemes().themesInterface.getTEXT());
        title.setFont(new Font("Dialong", Font.BOLD, 35));

        for(String key : projectOptions.params.keySet()){
            i++;
            JLabel label = new JLabel("Enter " + key);
            label.setForeground(Settings.getSettingsFromJSon().getTheme().getThemes().themesInterface.getTEXT());
            JTextField field = new JTextField(projectOptions.get(key), 33);
            field.setBorder(null);
            field.setBackground(Settings.getSettingsFromJSon().getTheme().getThemes().themesInterface.getLIGHTER_FOREGROUND());
            components.put(key, field);
            this.add(label);
            this.add(field);
            label.setBounds(x_default_label - (2*key.length()) , y_default_label + (65*i), 130, 150);
            field.setBounds(x_default_field, y_default_field + (65*i), 300, 30);
        }

        JButton submit = new JButton("Submit");
        submit.setFont(new Font("Dialog", Font.PLAIN, 15));
        submit.setForeground(Settings.getSettingsFromJSon().getTheme().getThemes().themesInterface.getTEXT());
        submit.setBorder(null);
        submit.setBackground(Settings.getSettingsFromJSon().getTheme().getThemes().themesInterface.getBACKGROUND());

        ActionListener actionListener = e -> {
            if(e.getSource() == submit){
                for(String key : components.keySet()){
                    projectOptions.set(key, components.get(key).getText());
                }

                try {
                    this.getParent().setVisible(false);
                    new Project(file);
                } catch (ProjectErrors projectErrors) {
                    projectErrors.printStackTrace();
                }
            }


            PanelManager.EDITOR.window.run();
        };

        MouseAdapter onSubmitHover = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                submit.setForeground(Settings.getSettingsFromJSon().getTheme().getThemes().themesInterface.getHOVER_TEXT());
                submit.setFont(new Font("Dialog", Font.BOLD, 15));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                submit.setForeground(Settings.getSettingsFromJSon().getTheme().getThemes().themesInterface.getTEXT());
                submit.setFont(new Font("Dialog", Font.PLAIN, 15));
            }
        };

        submit.addActionListener(actionListener);
        submit.addMouseListener(onSubmitHover);
        this.add(submit);
        this.add(title);
        title.setBounds(95, 50, 325, 50);
        submit.setBounds(x_default_label - 45, y_default_label + (120*i), 150, 30);

    }

}
