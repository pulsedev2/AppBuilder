package fr.pulsedev.appbuilder.visualeditor.blocks.UI;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;

public class BlockUI extends ComponentUI {

    public static final String NAME = "BlockUI";

    public static ComponentUI createUI(JComponent c){
        return new BlockUI();
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
    public void paint(Graphics g, JComponent c){
        g.setColor(c.getBackground());
        g.fillRect((int) c.getAlignmentX(),(int) c.getAlignmentY(), (int) c.getSize().getWidth(), (int) c.getSize().getHeight());
    }

}
