package fr.pulsedev.appbuilder.UI;

import fr.pulsedev.appbuilder.utils.UiUtils;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

    private final Dimension size;
    private final String name;
    private final int constants;
    private final JPanel panel;
    private final boolean resizable;
    private final Color color;
    private final boolean underdecorated;

    public Window(Dimension size, String name, boolean resizable, int constants, JPanel panel, Color color, boolean underdecorated){
        this.size = size;
        this.name = name;
        this.resizable = resizable;
        this.constants = constants;
        this.panel = panel;
        this.color = color;
        this.underdecorated = underdecorated;
    }

    public void run(){
        this.setSize(size);
        this.setName(name);
        this.setDefaultCloseOperation(constants);
        this.setResizable(resizable);
        this.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", true);
        this.setLocation(100, 100);

        this.setUndecorated(underdecorated);

        this.setContentPane(this.panel);

        this.getContentPane().setBackground(color);

        UiUtils.FrameDragListener frameDragListener = new UiUtils.FrameDragListener(this);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);

        this.setVisible(true);
    }

    public boolean isDecorated(){
        return !underdecorated;
    }

    public Dimension getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public int getConstants() {
        return constants;
    }

    public JPanel getPanel() {
        return panel;
    }

    public Color getColor(){return color;}

    public static class Builder {

        private Dimension size = new Dimension(500, 500);
        private String name = "Default name";        private int constants = JFrame.EXIT_ON_CLOSE;
        private JPanel panel = null;
        private boolean resizable = true;
        private Color color = Color.WHITE;
        private boolean underdecorated = true;

        public Window createWindow() {
            return new Window(this.size, this.name, this.resizable, this.constants, this.panel, this.color, this.underdecorated);
        }

        public Builder setBackground(Color color){
            this.color = color;
            return this;
        }

        public Builder setSize(Dimension size) {
            this.size = size;
            return this;
        }

        public Builder setUnderdecorated(boolean b){
            this.underdecorated = b;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setConstants(int constants) {
            this.constants = constants;
            return this;
        }

        public Builder setPanel(JPanel panel) {
            this.panel = panel;
            return this;
        }

        public Builder setResizable(boolean b) {
            this.resizable = b;
            return this;
        }
    }
}