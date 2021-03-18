package fr.pulsedev.appbuilder.visualeditor;

import fr.pulsedev.appbuilder.utils.Coordinates;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Block<A extends ComponentUI> extends JComponent {
    List<Tag<?>> tags;
    protected final String uiClassID;


    public Block(List<Tag<?>> tags, String uiClassID) {
        this.tags = tags;
        this.uiClassID = uiClassID;
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

    private Coordinates coord = new Coordinates();

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

    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    public void setX(int x){
        this.coord.setX(x);
    }

    public void setY(int y){
        this.coord.setY(y);
    }

    public void update(){};
}
