package fr.pulsedev.appbuilder.UI.panels.editor;

import fr.pulsedev.appbuilder.UI.panels.editor.bar.VerticalBar;

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

public class EditorPanel extends JDesktopPane {
    public static final int verticalBarWidth = 50;
    private static SliderPanel sliderPanel;
    VerticalBar verticalBar;

    public EditorPanel() {
        this.setLayout(null);
        this.setFocusable(false);
        this.setBackground(null);

        Component component = this;
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                component.repaint();
            }
        }, 0, 1);

        sliderPanel = new SliderPanel();
        this.add(sliderPanel);
        sliderPanel.setBounds(55, 0, this.getWidth(), this.getHeight());

        verticalBar = new VerticalBar();
        this.add(verticalBar);
        verticalBar.setBounds(0, 0, 50, this.getHeight());
        verticalBar.repaint();
    }

    public static SliderPanel getSliderPanel() {
        return sliderPanel;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        sliderPanel.setBounds(55, 0, this.getWidth(), this.getHeight());

        verticalBar.setBounds(0, 0, verticalBarWidth, this.getHeight());
    }
}