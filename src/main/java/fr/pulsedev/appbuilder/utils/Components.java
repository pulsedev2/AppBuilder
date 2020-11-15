package fr.pulsedev.appbuilder.utils;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;

public class Components extends java.awt.Component {


    public static Component getClickedComponent(JComponent toGet, Coordinates coordinates){
        Component[] components = toGet.getComponents();
        for(Component component : components){
            List<Coordinates> coord = Coordinates.makeCuboid(new Coordinates(component.getX(), component.getY()),
                    new Coordinates(component.getX()+component.getWidth(), component.getY()+component.getHeight()));

            Coordinates coord1 = coord.get(0);
            Coordinates coord2 = coord.get(1);

            if((coordinates.getX() >= coord1.getX() && coordinates.getX() <= coord2.getX()) &&
                    coordinates.getY() >= coord1.getY() && coordinates.getY() <= coord2.getY()){
                return component;
            }
        }

        return null;
    }

    public Component toComponent(){
        return this;
    }

    public static Component cloneComponent(Component component){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(component);
            ByteArrayInputStream bias = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bias);
            return (Component) ois.readObject();
        } catch (IOException |ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
