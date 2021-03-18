package fr.pulsedev.appbuilder.visualeditor.blocks;

import fr.pulsedev.appbuilder.visualeditor.Block;
import fr.pulsedev.appbuilder.visualeditor.Tag;
import fr.pulsedev.appbuilder.visualeditor.blocks.UI.TextAreaUI;
import fr.pulsedev.appbuilder.visualeditor.blocks.UI.UITypes;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class JTextArea extends Block<TextAreaUI> {

    private static final List<Tag<?>> TAGS = Arrays.asList(
            new Tag<String>("id", "text_area", String.class),
            new Tag<String>("text", "Some text", String.class),
            new Tag<Boolean>("visible", true, Boolean.class),
            new Tag<Color>("color", new Color(255, 255, 255), Color.class)
    );

    public JTextArea() {
        super(TAGS, UITypes.TEXT_AREA.name);
        setUI(new TextAreaUI());
        setBounds(super.getCoord().getX(), super.getCoord().getY(), 100, 200);
    }

    @Override
    public void update() {
        this.setVisible((boolean) super.getTagsByName("visible").get(0).getValue());
        this.setForeground((Color) super.getTagsByName("color").get(0).getValue());
        this.repaint();
    }

}
