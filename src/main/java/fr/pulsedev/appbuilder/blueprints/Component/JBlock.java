package fr.pulsedev.appbuilder.blueprints.Component;

import fr.pulsedev.appbuilder.blueprints.Block;
import fr.pulsedev.appbuilder.blueprints.UI.BlockUI;

import javax.swing.*;
import java.awt.*;

public class JBlock extends JComponent {

    private static  final String uiClassID = "BlockUI";

    public JBlock(Color color, Block block, Dimension size){
        setUI(new BlockUI());
        setBackground(color);
        setBounds(block.getCoord().getX(), block.getCoord().getY(), size.width, size.height);
    }

    public JBlock(Color color, Block block){
        this(color, block, new Dimension(100,75));
    }

    public String getUIClassID(){
        return uiClassID;
    }

    public void updateUI() {
        setUI((BlockUI)UIManager.getUI(this));
    }

    public void setUI(BlockUI ui){
        super.setUI(ui);
    }

    public BlockUI getUI(){
        return (BlockUI) ui;
    }
}
