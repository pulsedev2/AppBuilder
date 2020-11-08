package fr.pulsedev.appbuilder.UI;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

    private final Dimension size;
    private final String name;
    private final int constants;
    private final JPanel panel;
    private final boolean resizable;

    public Window(Dimension size, String name, boolean resizable, int constants, JPanel panel){
        this.size = size;
        this.name = name;
        this.resizable = resizable;
        this.constants = constants;
        this.panel = panel;
    }

    public void run(){
        this.setSize(size);
        this.setName(name);
        this.setDefaultCloseOperation(constants);
        this.setResizable(resizable);

        this.setContentPane(this.panel);

        this.setVisible(true);
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

    public static class Builder {

        private Dimension size = new Dimension(500, 500);
        private String name = "Default name";
        private int constants = JFrame.EXIT_ON_CLOSE;
        private JPanel panel = null;
        private boolean resizable = true;

        public Window createWindow() {
            return new Window(this.size, this.name, this.resizable, this.constants, this.panel);
        }

        public Builder setSize(Dimension size) {
            this.size = size;
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