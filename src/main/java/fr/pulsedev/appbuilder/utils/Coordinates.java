package fr.pulsedev.appbuilder.utils;

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

}
