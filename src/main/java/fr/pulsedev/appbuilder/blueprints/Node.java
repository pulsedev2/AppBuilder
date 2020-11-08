package fr.pulsedev.appbuilder.blueprints;

public class Node<A> {

    private final Block PARENT;
    private A out;
    private A in;
    private final boolean MODE;
    private final String NAME;

    public Node(Block parent, boolean out, A value, String name){
        this.PARENT = parent;
        this.MODE = out;
        this.NAME = name;
        if(out){
            this.out = value;
        }else{
            this.in = value;
        }
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

    public A getIn(){return in;}
    public A getOut(){return out;}

}
