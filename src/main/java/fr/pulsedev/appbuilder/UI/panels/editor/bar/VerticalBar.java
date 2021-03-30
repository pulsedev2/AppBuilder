package fr.pulsedev.appbuilder.UI.panels.editor.bar;

import fr.pulsedev.appbuilder.UI.panels.MainPanel;
import fr.pulsedev.appbuilder.settings.Language;
import fr.pulsedev.appbuilder.settings.Theme;
import fr.pulsedev.appbuilder.utils.JVerticalButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VerticalBar extends JPanel {
    JVerticalButton visuel = new JVerticalButton();
    JVerticalButton code = new JVerticalButton();

    public VerticalBar() {
        this.setLayout(null);
        this.setBackground(Theme.USER.themesInterface.getFOREGROUND());

        visuel.setText(Language.USER.interface_.getString("vertical_bar.graphic"));
        visuel.setForeground(Color.WHITE);
        visuel.setBackground(Theme.USER.themesInterface.getBACKGROUND());
        visuel.setBorderPainted(false);
        visuel.setFocusable(false);
        visuel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                visuel.setBackground(Theme.USER.themesInterface.getBACKGROUND());
                code.setBackground(null);
            }
        });

        this.add(visuel);
        visuel.setBounds(0, 0, MainPanel.verticalBarWidth, 100);

        code.setText(Language.USER.interface_.getString("vertical_bar.bp"));
        code.setBorderPainted(false);
        code.setForeground(Color.WHITE);
        code.setBackground(null);
        code.setFocusable(false);
        code.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                visuel.setBackground(null);
                code.setBackground(Theme.USER.themesInterface.getBACKGROUND());
            }
        });

        this.add(code);
        code.setBounds(0, visuel.getHeight(), MainPanel.verticalBarWidth, 100);
    }
}
