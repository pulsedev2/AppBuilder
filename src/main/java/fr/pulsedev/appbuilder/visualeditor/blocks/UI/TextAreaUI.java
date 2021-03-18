package fr.pulsedev.appbuilder.visualeditor.blocks.UI;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;

public class TextAreaUI extends ComponentUI{

    public static final String NAME = "TextAreaUI";

    public static ComponentUI createUI(JComponent c){
        return new TextAreaUI();
    }

    public Dimension getMinimumSize(JComponent c){
        return new Dimension(100,75);
    }

    public Dimension getMaximumSize(JComponent c){
        return new Dimension(500,375);
    }

    public Dimension getPreferredSize(JComponent c){
        return new Dimension(250,187);
    }

    @Override
    public void paint(Graphics g, JComponent c_){
        fr.pulsedev.appbuilder.visualeditor.blocks.JTextArea c = (fr.pulsedev.appbuilder.visualeditor.blocks.JTextArea) c_;
        g.setColor(c.getForeground());
        g.drawString((String) c.getTagsByName("text").get(0).getValue(),0,10);
    }

}
