package fr.pulsedev.appbuilder.UI;

import fr.pulsedev.appbuilder.shortcuts.ShortCutsListener;
import fr.pulsedev.appbuilder.utils.UiUtils;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

    private final Dimension size;
    private final String name;
    private final int constants;
    private JComponent panel;
    private final boolean resizable;
    private final Color color;
    private final boolean undecorated;
    private final boolean draggable;
    private boolean isRunning = false;
    private final static ShortCutsListener listener = new ShortCutsListener();
    private final boolean shortcutsEnabled;

    public Window(Dimension size, String name, boolean resizable, int constants, JPanel panel, Color color, boolean undecorated, boolean draggable, boolean shortcuts){
        this.size = size;
        this.name = name;
        this.resizable = resizable;
        this.constants = constants;
        this.panel = panel;
        this.color = color;
        this.undecorated = undecorated;
        this.draggable = draggable;
        this.shortcutsEnabled = shortcuts;
    }

    public void run(){
        if(!this.isActive()){
            isRunning = false;
        }
        if(!isRunning && !this.isActive()) {
            this.setSize(size);
            this.setName(name);
            this.setDefaultCloseOperation(constants);
            this.setResizable(resizable);
            this.setLocationRelativeTo(null);
            if(this.shortcutsEnabled) {
                this.setFocusable(true);
                this.addKeyListener(listener);
                this.requestFocusInWindow();
            }

            try{
                this.setUndecorated(undecorated);
            }catch (IllegalComponentStateException ignored){
                this.dispose();
                return;
            }


            this.setContentPane(this.panel);

            this.getContentPane().setBackground(color);

            if (draggable) {
                UiUtils.FrameDragListener frameDragListener = new UiUtils.FrameDragListener(this);
                this.addMouseListener(frameDragListener);
                this.addMouseMotionListener(frameDragListener);
                this.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", true);
            }

            this.setVisible(true);
            isRunning = true;
        }else {
            this.dispose();
            isRunning = false;
        }
    }

    public void setPanel(JComponent panel){
        this.panel = panel;
    }

    public boolean isDecorated(){
        return !undecorated;
    }

    public boolean isDraggable(){
        return draggable;
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

    public JComponent getPanel() {
        return panel;
    }

    public Color getColor(){return color;}

    public static class Builder {

        private Dimension size = new Dimension(500, 500);
        private String name = "Default name";
        private int constants = JFrame.EXIT_ON_CLOSE;
        private JPanel panel = null;
        private boolean resizable = true;
        private Color color = Color.WHITE;
        private boolean undecorated = true;
        private boolean draggable = false;
        private boolean shortcuts = false;

        public Window createWindow() {
            return new Window(this.size, this.name, this.resizable, this.constants, this.panel, this.color, this.undecorated, this.draggable, this.shortcuts);
        }

        public Builder setBackground(Color color){
            this.color = color;
            return this;
        }

        public Builder addShortcuts(){
            this.shortcuts = true;
            return this;
        }

        public Builder setSize(Dimension size) {
            this.size = size;
            return this;
        }

        public Builder setUndecorated(boolean b){
            this.undecorated = b;
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

        public Builder setDraggable(boolean b){
            this.draggable = b;
            return this;
        }
    }
}
