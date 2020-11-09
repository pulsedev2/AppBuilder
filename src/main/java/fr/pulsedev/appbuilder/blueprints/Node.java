package fr.pulsedev.appbuilder.blueprints;

import fr.pulsedev.appbuilder.utils.Coordinates;

public class Node<A> {

    private final Block PARENT;
    private final A VALUE;
    private final boolean MODE;
    private final String NAME;
    private final Coordinates COORDINATES;

    public Node(Block parent, boolean out, A value, String name, Coordinates coordinates){
        this.PARENT = parent;
        this.COORDINATES = coordinates;
        this.MODE = out;
        this.NAME = name;
        this.VALUE = value;
    }

    public Block getParent() {
        return PARENT;
    }

    public boolean getMode(){
        return MODE;
    }

    public boolean isOutput(){
        return MODE;
    }

    public boolean isInput(){
        return !MODE;
    }

    public String getName() {
        return NAME;
    }

    public A getValue(){return VALUE;}

    public Coordinates getCoordinates(){
        return COORDINATES;
    }

}
