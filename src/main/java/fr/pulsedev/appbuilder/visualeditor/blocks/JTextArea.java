package fr.pulsedev.appbuilder.visualeditor.blocks;

import fr.pulsedev.appbuilder.utils.Coordinates;
import fr.pulsedev.appbuilder.visualeditor.Block;
import fr.pulsedev.appbuilder.visualeditor.Tag;
import fr.pulsedev.appbuilder.visualeditor.blocks.UI.TextAreaUI;
import fr.pulsedev.appbuilder.visualeditor.blocks.UI.UITypes;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class JTextArea extends Block<TextAreaUI> {

    private final List<Tag<?>> TAGS = Arrays.asList(
            new Tag<String>("id", "text_area", String.class),
            new Tag<String>("text", "Some text", String.class),
            new Tag<Boolean>("visible", true, Boolean.class),
            new Tag<Color>("color", new Color(255, 255, 255), Color.class),
            new Tag<Coordinates>("coordinates", new Coordinates(0, 0), Coordinates.class),
            new Tag<Integer>("layer", 1, Integer.class)
    );

    public JTextArea() {
        super(UITypes.TEXT_AREA.name);
        super.setTags(TAGS);
        setUI(new TextAreaUI());
        Coordinates coordinates = (Coordinates) super.getTagsByName("coordinates").get(0).getValue();
        setBounds(coordinates.getX(), coordinates.getY(), 100, 200);
    }

    @Override
    public void update() {
        this.setVisible((boolean) super.getTagsByName("visible").get(0).getValue());
        this.setForeground((Color) super.getTagsByName("color").get(0).getValue());
        Coordinates coordinates = (Coordinates) super.getTagsByName("coordinates").get(0).getValue();
        setBounds(coordinates.getX(), coordinates.getY(), 100, 200);
        this.repaint();
    }

}
