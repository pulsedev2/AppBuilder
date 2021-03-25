package fr.pulsedev.appbuilder.visualeditor.blocks;

import fr.pulsedev.appbuilder.utils.Coordinates;
import fr.pulsedev.appbuilder.visualeditor.Tag;
import fr.pulsedev.appbuilder.visualeditor.blocks.UI.BlockUI;
import fr.pulsedev.appbuilder.visualeditor.blocks.UI.UITypes;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class JBlock extends fr.pulsedev.appbuilder.visualeditor.Block<BlockUI> {

    private final List<Tag<?>> TAGS = Arrays.asList(
            new Tag<String>("id", "block", String.class),
            new Tag<Color>("color", new Color(255, 255, 255), Color.class),
            new Tag<Boolean>("visible", true, Boolean.class),
            new Tag<Coordinates>("coordinates", new Coordinates(0, 0), Coordinates.class),
            new Tag<Dimension>("dimension", new Dimension(100, 75), Dimension.class),
            new Tag<Integer>("layer", 1, Integer.class)
    );

    public JBlock(Dimension size){
        super(UITypes.BLOCK.name);
        super.setTags(TAGS);
        this.setUI(new BlockUI());
        this.setBackground((Color) super.getTagsByName("color").get(0).getValue());
        super.editTag("dimension", size);
        Coordinates coordinates = (Coordinates) super.getTagsByName("coordinates").get(0).getValue();
        this.setBounds(coordinates.getX(), coordinates.getY(), size.width, size.height);
    }

    public JBlock(){
        this(new Dimension(100,75));
    }

    @Override
    public void update() {
        this.setVisible((boolean) super.getTagsByName("visible").get(0).getValue());
        this.setBackground((Color) super.getTagsByName("color").get(0).getValue());
        Coordinates coordinates = (Coordinates) super.getTagsByName("coordinates").get(0).getValue();
        Dimension dimension = (Dimension) super.getTagsByName("dimension").get(0).getValue();
        this.setBounds(coordinates.getX(), coordinates.getY(), dimension.width, dimension.height);
        this.repaint();
    }
}
