package fr.pulsedev.appbuilder.utils;

import java.util.ArrayList;
import java.util.List;

public class Coordinates {

    private int x,y;

    public Coordinates(int x, int y){
        this.x=x;
        this.y=y;
    }

    public Coordinates(){

    }

    public void setX(int x){
        this.x=x;
    }

    public void setY(int y){
        this.y=y;
    }

    public int getX(){return x;}
    public int getY(){return y;}

    public static List<Coordinates> makeCuboid(Coordinates coordinates1, Coordinates coordinates2){
         return new ArrayList<>(){{
            add(new Coordinates(Math.min(coordinates1.getX(), coordinates2.getX()), Math.min(coordinates1.getY(), coordinates2.getY())));
            add(new Coordinates(Math.max(coordinates1.getX(), coordinates2.getX()), Math.max(coordinates1.getY(), coordinates2.getY())));
        }};
    }

}
