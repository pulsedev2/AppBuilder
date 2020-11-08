package fr.pulsedev.appbuilder.blueprints;

import fr.pulsedev.appbuilder.utils.Coordinates;

import java.util.HashMap;

public class Block<A, T> {

    private HashMap<A, T> parametersIn = new HashMap<>();
    private HashMap<A, T> parametersOut = new HashMap<>();
    private Coordinates coord = new Coordinates();

    public Block(Coordinates coord){
        this.coord = coord;
    }

    public void setInParameters(HashMap<A, T> parameters){
        this.parametersIn = parameters;
    }

    public void setOutParameters(HashMap<A, T> parameters){
        this.parametersOut = parameters;
    }

    public void addInParameters(A key, T value){
        parametersIn.put(key, value);
    }

    public void addOutParameters(A key, T value){
        parametersOut.put(key, value);
    }

    public HashMap<A, T> getInParameters() {
        return parametersIn;
    }

    public HashMap<A, T> getOutParameters(){
        return parametersOut;
    }

    public Coordinates getCoord() {
        return coord;
    }
}
