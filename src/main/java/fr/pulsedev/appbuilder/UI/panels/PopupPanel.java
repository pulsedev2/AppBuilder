package fr.pulsedev.appbuilder.UI.panels;

import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.UI.Window;
import fr.pulsedev.appbuilder.UI.panels.enums.PanelManager;
import fr.pulsedev.appbuilder.projects.Project;
import fr.pulsedev.appbuilder.projects.ProjectOptions;
import fr.pulsedev.appbuilder.projects.errors.ProjectErrors;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

public class PopupPanel extends JPanel {

    ProjectOptions<String> projectOptions = Main.options;
    HashMap<String, JTextField> components = new HashMap<>();
    Window window = (Window) SwingUtilities.getWindowAncestor(this);

    public PopupPanel(File file){

        for(String key : projectOptions.params.keySet()){
            JLabel label = new JLabel("Enter " + key);
            JTextField field = new JTextField(projectOptions.get(key), 33);
            components.put(key, field);
            this.add(label);
            this.add(field);
        }

        JButton submit = new JButton("Submit");

        ActionListener actionListener = e -> {
            if(e.getSource() == submit){
                for(String key : components.keySet()){
                    projectOptions.set(key, components.get(key).getText());
                }

                try {
                    new Project(file);
                } catch (ProjectErrors projectErrors) {
                    projectErrors.printStackTrace();
                }
            }


            PanelManager.EDITOR.window.run();
        };

        submit.addActionListener(actionListener);
        this.add(submit);

    }

}
