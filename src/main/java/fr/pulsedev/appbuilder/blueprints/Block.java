package fr.pulsedev.appbuilder.blueprints;

import fr.pulsedev.appbuilder.utils.Coordinates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Block {

    private List<Node<?>> in = new ArrayList<>();
    private List<Node<?>> out = new ArrayList<>();
    private Coordinates coord = new Coordinates();

    public Block(Coordinates coord){
        this.coord = coord;
    }

    /**
     * Set from scratch the list of input nodes
     * @param in a {@link java.util.List} of {@link fr.pulsedev.appbuilder.blueprints.Node} <b> Each node must be in input node</b>
     */
    public void setIn(List<Node<?>> in){
        this.in = in;
    }

    /**
     * Set from scratch the list of output nodes
     * @param out a {@link java.util.List} of {@link fr.pulsedev.appbuilder.blueprints.Node} <b> Each node must be in output node</b>
     */
    public void setOut(List<Node<?>> out){
        this.out = out;
    }

    /**
     * Add a input node to the List of this block
     * @param value is an Object which the node will receive
     */
    public void addInNode(Object value, String name){
        in.add(new Node<>(this, false, value, name));
    }

    /**
     * Add an output node the List of this Block
     * @param value is an Object which the node will send through a {@link Link}
     */
    public void addOutNode(Object value, String name){
        out.add(new Node<>(this, true, value, name));
    }

    /**
     * 
     * @return the list of output nodes
     */
    public List<Node<?>> getOutNodes() {
        return this.out;
    }

    /**
     * 
     * @return the list of input nodes
     */
    public List<Node<?>> getInNodes(){
        return this.in;
    }

    /**
     * 
     * @param aClass the class you want the nodes to be (i.e) {@link Integer} will return every nodes that take or return an {@link Integer} 
     * @return the {@link List} of nodes that match the class type
     * @see #getNodesByType(Class, boolean) 
     */
    public List<Node<?>> getNodesByType(Class<?> aClass){
        List<Node<?>> correct = in.stream()
                .filter(c -> c.getClass().isInstance(aClass))
                .collect(Collectors.toList());
        correct.addAll(out.stream()
                .filter(c -> c.getClass().isInstance(aClass))
                .collect(Collectors.toList()));
        
        return correct;
    }

    /**
     * 
     * @param aClass the class you want the node to be
     * @param out true if you only want the output nodes and false if you only want the input nodes
     * @return a list of matching nodes
     * @see #getNodesByType(Class) 
     */
    public List<Node<?>> getNodesByType(Class<?> aClass,  boolean out){
        return out ? this.out.stream().filter(c -> c.getClass().isInstance(aClass)).collect(Collectors.toList()) : in.stream().filter(c -> c.getClass().isInstance(aClass)).collect(Collectors.toList());
    }

    /**
     * 
     * @param name of the nodes to get <b>Case sensitive</b>
     * @return a {@link List} of nodes that a have the matching name
     * @see #getNodesByType(Class, boolean) 
     */
    public List<Node<?>> getNodesByName(String name){
        List<Node<?>> matching = in.stream()
                .filter(n -> n.getName().equals(name))
                .collect(Collectors.toList());
        matching.addAll(out.stream()
                .filter(n -> n.getName().equals(name))
                .collect(Collectors.toList()));
        
        return matching;
    }

    /**
     * 
     * @param name tha name that the node should have <b>Case sensitive</b>
     * @param mode true - output nodes / false = input nodes
     * @return a {@link List} of matching nodes
     * @see #getNodesByType(Class) 
     */
    public List<Node<?>> getNodesByName(String name, boolean mode){
        return mode ? out.stream().filter(c -> c.getName().equals(name)).collect(Collectors.toList()) : in.stream().filter(c -> c.getName().equals(name)).collect(Collectors.toList());
    }
    
    

    /**
     * 
     * @return {@link Coordinates} of this block
     */
    public Coordinates getCoord() {
        return coord;
    }
}
