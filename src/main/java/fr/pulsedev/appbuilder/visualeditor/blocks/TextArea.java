package fr.pulsedev.appbuilder.visualeditor.blocks;

import fr.pulsedev.appbuilder.visualeditor.Block;
import fr.pulsedev.appbuilder.visualeditor.Tag;

import java.util.Arrays;
import java.util.List;

public class TextArea extends Block {

    private static final List<Tag<?>> TAGS = Arrays.asList(
            new Tag<String>("id", "text_area", String.class),
            new Tag<String>("text", "Some text", String.class),
            new Tag<Boolean>("visible", true, Boolean.class)
    );

    public TextArea() {
        super(TAGS);
    }



}
