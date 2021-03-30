package fr.pulsedev.appbuilder.UI.panels;

import fr.pulsedev.appbuilder.UI.panels.editor.EditorPanel;
import fr.pulsedev.appbuilder.UI.panels.editor.bar.VerticalBar;

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

public class MainPanel extends JDesktopPane {
    public static final int verticalBarWidth = 50;
    private static EditorPanel editorPanel;
    VerticalBar verticalBar;

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
        editorPanel.setBounds(55, 0, this.getWidth(), this.getHeight());

        verticalBar = new VerticalBar();
        this.add(verticalBar);
        verticalBar.setBounds(0, 0, 50, this.getHeight());
    }

    public static EditorPanel getEditorPanel() {
        return editorPanel;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = SwingUtilities.getWindowAncestor(this).getWidth();
        int height = SwingUtilities.getWindowAncestor(this).getHeight();

        editorPanel.setBounds(verticalBarWidth + 5, 0, width - (verticalBarWidth + 5), height);

        verticalBar.setBounds(0, 0, verticalBarWidth, height);
    }
}