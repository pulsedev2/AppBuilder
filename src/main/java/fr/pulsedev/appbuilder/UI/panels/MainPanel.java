package fr.pulsedev.appbuilder.UI.panels;

import fr.pulsedev.appbuilder.UI.panels.bar.HorizontalBar;
import fr.pulsedev.appbuilder.UI.panels.bar.VerticalBar;
import fr.pulsedev.appbuilder.UI.panels.editor.EditorPanel;

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

public class MainPanel extends JDesktopPane {
    public static final int verticalBarWidth = 35;
    public static final int horizontalBarHeight = 40;
    private static EditorPanel editorPanel;
    VerticalBar verticalBar;
    HorizontalBar horizontalBar;

    public MainPanel() {
        this.setLayout(null);
        this.setFocusable(false);

        Component component = this;
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                component.repaint();
            }
        }, 0, 1);

        editorPanel = new EditorPanel();
        this.add(editorPanel);

        verticalBar = new VerticalBar();
        this.add(verticalBar);

        horizontalBar = new HorizontalBar();
        this.add(horizontalBar);
    }

    public static EditorPanel getEditorPanel() {
        return editorPanel;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = SwingUtilities.getWindowAncestor(this).getWidth();
        int height = SwingUtilities.getWindowAncestor(this).getHeight();

        editorPanel.setBounds(verticalBarWidth + 5, horizontalBarHeight, width - (verticalBarWidth + 5), height);

        verticalBar.setBounds(0, horizontalBarHeight, verticalBarWidth, height);

        horizontalBar.setBounds(0, 0, width, horizontalBarHeight);
    }
}