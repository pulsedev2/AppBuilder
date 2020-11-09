package fr.pulsedev.appbuilder.blueprints;

import java.awt.geom.CubicCurve2D;

public class Link<A> {

    private Node<A> in;
    private Node<A> out;

    public Link(Node<A> in, Node<A> out){
        this.in = in;
        this.out = out;
    }

    public CubicCurve2D calculateLink(){
        return new CubicCurve2D.Float(in.getCoordinates().getX(),
                in.getCoordinates().getY(),
                in.getCoordinates().getY()+200,
                in.getCoordinates().getY()-115,
                out.getCoordinates().getX()-200,
                out.getCoordinates().getY()+115,
                out.getCoordinates().getX(),
                out.getCoordinates().getY());
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
