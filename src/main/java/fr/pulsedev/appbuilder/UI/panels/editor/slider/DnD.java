package fr.pulsedev.appbuilder.UI.panels.editor.slider;

import fr.pulsedev.appbuilder.UI.panels.enums.PanelManager;
import fr.pulsedev.appbuilder.utils.Components;
import fr.pulsedev.appbuilder.utils.Coordinates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class DnD {

    private final JComponent component;
    private final ArrayList<Component> toNotDrag;
    private final Container parent;
    private Component toDrag;

    public DnD(JComponent component, ArrayList<Component> toNotDrag, Container parent){
        this.component = component;
        this.toNotDrag =toNotDrag;
        this.parent = parent;
    }

    public DnD(JComponent component){
        this(component, new ArrayList<>(), null);
    }

    public DnD(JComponent component, ArrayList<Component> toNotDrag){
        this(component, toNotDrag, null);
    }

    public DnD(JComponent component, Container parent){
        this(component, new ArrayList<>(), parent);
    }

    public MouseAdapter getDnD(){

        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Component componentToDrag = Components.getClickedComponent(component, new Coordinates(e.getX(), e.getY()));
                System.out.println(componentToDrag);
                if(componentToDrag == null || toNotDrag.contains(componentToDrag))return;
                toDrag = Components.cloneComponent(componentToDrag);
                component.add(toDrag);
            }

            @Override
            public void mouseReleased(MouseEvent e){
                if(toDrag==null) return;
                component.remove(toDrag);
                Container parentLocal = PanelManager.EDITOR.window.getContentPane();
                Component[] components = parentLocal.getComponents();
                if(toDrag.getX() <= component.getWidth() && Arrays.asList(components).contains(toDrag)) {
                    parentLocal.remove(toDrag);
                }

                toDrag = null;
            }

            @Override
            public void mouseDragged(MouseEvent e){
                super.mouseDragged(e);
                if(toDrag == null)return;
                toDrag.setBounds(e.getX(), e.getY(), toDrag.getWidth(), toDrag.getHeight());
                Container parentLocal = PanelManager.EDITOR.window.getContentPane();
                if(e.getX() > component.getWidth()-toDrag.getWidth()){
                    if(parentLocal != null){
                        parentLocal.add(toDrag);
                        toDrag.setBounds(e.getX(), e.getY(), toDrag.getWidth(), toDrag.getHeight());
                    }
                }
            }
        };
    }

}
