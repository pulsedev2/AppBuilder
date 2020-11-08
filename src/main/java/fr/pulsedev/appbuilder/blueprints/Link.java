package fr.pulsedev.appbuilder.blueprints;

public class Link<A> {

    private Node<A> in;
    private Node<A> out;

    public Link(Node<A> in, Node<A> out){
        this.in = in;
        this.out = out;
    }

    public Node<A> getIn() {
        return in;
    }

    public void setIn(Node<A> in) {
        this.in = in;
    }

    public Node<A> getOut() {
        return out;
    }

    public void setOut(Node<A> out) {
        this.out = out;
    }
}
