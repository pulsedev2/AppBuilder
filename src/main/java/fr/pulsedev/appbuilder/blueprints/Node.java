package fr.pulsedev.appbuilder.blueprints;

public class Node {

    private Block in;
    private Block out;

    public Node(Block in, Block out){
        this.in = in;
        this.out = out;
    }

    public Block getIn(){return in;}
    public Block getOut(){return out;}

}
