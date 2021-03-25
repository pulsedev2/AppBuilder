package fr.pulsedev.appbuilder.visualeditor;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Block<A extends ComponentUI> extends JComponent {
    List<Tag<?>> tags;
    protected final String uiClassID;


    public Block(String uiClassID) {
        this.uiClassID = uiClassID;
    }

    public void setTags(List<Tag<?>> tags) {
        this.tags = tags;
    }

    public String getUIClassID(){
        return uiClassID;
    }

    public void updateUI() {
        setUI(UIManager.getUI(this));
    }

    public void setUI(ComponentUI ui){
        super.setUI(ui);
    }

    public A getUI(){
        return (A) ui;
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

    public List<Tag<?>> getTagsByName(String name){
        return this.tags.stream()
                .filter(tag -> tag.getName().equals(name))
                .collect(Collectors.toList());
    }

    public List<Tag<?>> getTagsByValue(Object value){
        return this.tags.stream()
                .filter(tag -> {
                    if(value.getClass().isInstance(tag.getType())){
                        return tag.getValue() == value;
                    }
                    return false;
                }).collect(Collectors.toList());
    }

    public void update(){}
}
