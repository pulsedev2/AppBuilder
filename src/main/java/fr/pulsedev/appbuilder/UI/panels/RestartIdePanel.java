package fr.pulsedev.appbuilder.UI.panels;

import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.UI.panels.enums.PanelManager;
import fr.pulsedev.appbuilder.settings.Language;
import fr.pulsedev.appbuilder.settings.Theme;
import fr.pulsedev.appbuilder.themes.Themes;
import fr.pulsedev.appbuilder.utils.UiUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class RestartIdePanel extends JPanel {

    public RestartIdePanel(){
        this.setLayout(null);

        Themes themes = Theme.USER;

        // Restart Button
        JButton restart = new JButton(Language.USER.interface_.getString("restart_now"));
        restart.setFont(new Font("Dialog", Font.PLAIN, 20));
        UiUtils.makeJButtonTransparent(restart);
        restart.setForeground(themes.themesInterface.getTEXT());
        restart.setBackground(themes.themesInterface.getFOREGROUND());
        restart.setContentAreaFilled(true);

        // Cancel Button
        JButton cancel = new JButton(Language.USER.interface_.getString("cancel"));
        cancel.setFont(new Font("Dialog", Font.PLAIN, 20));
        UiUtils.makeJButtonTransparent(cancel);
        cancel.setForeground(themes.themesInterface.getTEXT());
        cancel.setBackground(themes.themesInterface.getFOREGROUND());
        cancel.setContentAreaFilled(true);

        // Text
        JLabel label = new JLabel("<html>" + Language.USER.interface_.getString("restart_text") + "</html>");
        label.setFont(new Font("Dialog", Font.PLAIN, 20));
        label.setForeground(themes.themesInterface.getTEXT());

        MouseListener onHoverListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ((JButton)e.getSource()).setForeground(themes.themesInterface.getHOVER_TEXT());
                ((JButton)e.getSource()).setBackground(themes.themesInterface.getLIGHTER_FOREGROUND());
                ((JButton)e.getSource()).setFont(new Font("Dialog", Font.BOLD, 20));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ((JButton)e.getSource()).setForeground(themes.themesInterface.getTEXT());
                ((JButton)e.getSource()).setBackground(themes.themesInterface.getFOREGROUND());
                ((JButton)e.getSource()).setFont(new Font("Dialog", Font.PLAIN, 20));
            }
        };

        ActionListener actionListener = e -> {
            if(e.getSource() == restart){
                try {
                    Main.restartApplication();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            else if(e.getSource() == cancel){
                PanelManager.RESTART.window.dispose();
            }
        };

        restart.addMouseListener(onHoverListener);
        cancel.addMouseListener(onHoverListener);
        restart.addActionListener(actionListener);
        cancel.addActionListener(actionListener);

        this.add(restart);
        this.add(cancel);
        this.add(label);

        restart.setBounds(230, 150, 150, 25);
        cancel.setBounds(30, 150, 150, 25);
        label.setBounds(10, 30, 400, 100);
    }
}
