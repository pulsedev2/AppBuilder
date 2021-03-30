package fr.pulsedev.appbuilder.UI.panels;

import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.UI.Window;
import fr.pulsedev.appbuilder.UI.panels.enums.PanelManager;
import fr.pulsedev.appbuilder.projects.Project;
import fr.pulsedev.appbuilder.projects.ProjectOptions;
import fr.pulsedev.appbuilder.projects.errors.ProjectErrors;
import fr.pulsedev.appbuilder.settings.Language;
import fr.pulsedev.appbuilder.settings.Theme;
import fr.pulsedev.appbuilder.utils.UiUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

public class PopupPanel extends JPanel {

    ProjectOptions<String> projectOptions = Main.options;
    HashMap<String, JTextField> components = new HashMap<>();
    Window window = (Window) SwingUtilities.getWindowAncestor(this);

    public PopupPanel(File file){
        int y_default_label = 0;
        int y_default_field = 95;
        int i = 0;

        this.setLayout(null);

        JLabel title = new JLabel(Language.USER.interface_.getString("project_information"));
        title.setForeground(Theme.USER.themesInterface.getTEXT());
        title.setFont(new Font("Dialog", Font.BOLD, 35));
        JButton close = UiUtils.getCloseButton();
        List<JLabel> labelList = new ArrayList<>();
        List<JTextField> fieldList = new ArrayList<>();
        for(String key : projectOptions.params.keySet()){
            i++;
            JLabel label = new JLabel(Language.USER.interface_.getString("enter") +" " + Language.USER.interface_.getString("projectOptions." + key));
            label.setForeground(Theme.USER.themesInterface.getTEXT());
            label.setFont(new Font("DIALOG", Font.PLAIN, 15));
            JTextField field = new JTextField(projectOptions.get(key), 33);
            field.setBorder(null);
            field.setBackground(Theme.USER.themesInterface.getLIGHTER_FOREGROUND());
            components.put(key, field);
            this.add(label);
            this.add(field);
            labelList.add(label);
            fieldList.add(field);
        }

        JButton submit = new JButton(Language.USER.interface_.getString("submit"));
        submit.setFont(new Font("Dialog", Font.PLAIN, 15));
        submit.setForeground(Theme.USER.themesInterface.getTEXT());
        submit.setBorder(null);
        submit.setBackground(Theme.USER.themesInterface.getBACKGROUND());

        ActionListener actionListener = e -> {
            if(e.getSource() == submit){
                for(String key : components.keySet()){
                    projectOptions.set(key, components.get(key).getText());
                }

                try {
                    SwingUtilities.getWindowAncestor(this).setVisible(false);
                    Main.PROJECT = new Project(file);
                } catch (ProjectErrors projectErrors) {
                    projectErrors.printStackTrace();
                }
            }

            PanelManager.EDITOR.window.setPanel(new MainPanel());
            PanelManager.EDITOR.window.run();
        };

        MouseAdapter onSubmitHover = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                submit.setForeground(Theme.USER.themesInterface.getHOVER_TEXT());
                submit.setFont(new Font("Dialog", Font.BOLD, 15));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                submit.setForeground(Theme.USER.themesInterface.getTEXT());
                submit.setFont(new Font("Dialog", Font.PLAIN, 15));
            }
        };

        submit.addActionListener(actionListener);
        submit.addMouseListener(onSubmitHover);
        this.add(submit);
        this.add(title);
        this.add(close);

        PopupPanel instance = this;
        int lastI = i;

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                title.setBounds(250 - instance.getGraphics().getFontMetrics(new Font("DIALOG", Font.BOLD, 35)).stringWidth(title.getText())/2, 50, instance.getGraphics().getFontMetrics(new Font("DIALOG", Font.BOLD, 35)).stringWidth(title.getText()), 50);
                submit.setBounds(250 - instance.getGraphics().getFontMetrics(new Font("DIALOG", Font.PLAIN, 15)).stringWidth(submit.getText())/2, y_default_label + (120*lastI), instance.getGraphics().getFontMetrics(new Font("DIALOG", Font.BOLD, 15)).stringWidth(submit.getText()), 30);
                close.setBounds(500 - 30,0,30,30);

                for(JLabel label: labelList){
                    label.setBounds(250 - instance.getGraphics().getFontMetrics(new Font("DIALOG", Font.PLAIN, 15)).stringWidth(label.getText())/2, y_default_label + (65*(labelList.indexOf(label)+1)), instance.getGraphics().getFontMetrics(new Font("DIALOG", Font.PLAIN, 15)).stringWidth(label.getText()), 150);
                }
                for(JTextField field: fieldList){
                    field.setBounds(250-150, y_default_field + (65*(fieldList.indexOf(field)+1)), 300, 30);
                }
            }
        };

        java.util.Timer timer = new java.util.Timer();
        timer.schedule(timerTask, 100);
    }

}
