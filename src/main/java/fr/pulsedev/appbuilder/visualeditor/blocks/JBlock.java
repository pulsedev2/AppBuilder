package fr.pulsedev.appbuilder.visualeditor.blocks;

import fr.pulsedev.appbuilder.visualeditor.Tag;
import fr.pulsedev.appbuilder.visualeditor.blocks.UI.BlockUI;
import fr.pulsedev.appbuilder.visualeditor.blocks.UI.UITypes;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class JBlock extends fr.pulsedev.appbuilder.visualeditor.Block<BlockUI> {

    private static final List<Tag<?>> TAGS = Arrays.asList(
            new Tag<String>("id", "block", String.class),
            new Tag<Color>("color", new Color(255, 255, 255), Color.class),
            new Tag<Boolean>("visible", true, Boolean.class)
    );

    public JBlock(Dimension size){
        super(TAGS, UITypes.BLOCK.name);
        setUI(new BlockUI());
        setBackground((Color) super.getTagsByName("color").get(0).getValue());
        setBounds(super.getCoord().getX(), super.getCoord().getY(), size.width, size.height);
    }

    public JBlock(){
        this(new Dimension(100,75));
    }

    @Override
    public void update() {
        this.setVisible((boolean) super.getTagsByName("visible").get(0).getValue());
        this.setBackground((Color) super.getTagsByName("color").get(0).getValue());
        this.repaint();
    }
}
