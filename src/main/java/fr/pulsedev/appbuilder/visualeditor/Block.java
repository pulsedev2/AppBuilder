package fr.pulsedev.appbuilder.visualeditor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Block {
    List<Tag<?>> tags;

    public Block(List<Tag<?>> tags) {
        this.tags = tags;
    }

    public List<Tag<?>> getTags() {
        return tags;
    }

    public void editTag(String tagName, Object value){
        for (Tag<?> tag : this.tags) {
            if(tag.getName().equals(tagName)){
                tag.setValue(value);
            }
        }
    }

    public Collection<Tag<?>> getTagsByName(String name){
        return this.tags.stream()
                .filter(tag -> tag.getName().equals(name))
                .collect(Collectors.toList());
    }

    public Collection<Tag<?>> getTagsByValue(Object value){
        return this.tags.stream()
                .filter(tag -> {
                    if(value.getClass().isInstance(tag.getType())){
                        return tag.getValue() == value;
                    }
                    return false;
                }).collect(Collectors.toList());
    }
}
