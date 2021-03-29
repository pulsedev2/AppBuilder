package fr.pulsedev.appbuilder.UI.panels.editor.slider;

import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.UI.panels.editor.EditorPanel;
import fr.pulsedev.appbuilder.UI.panels.enums.PanelManager;
import fr.pulsedev.appbuilder.event.EventsRegisters;
import fr.pulsedev.appbuilder.event.events.blockEvents.BlockDraggedEvent;
import fr.pulsedev.appbuilder.utils.Components;
import fr.pulsedev.appbuilder.utils.Coordinates;
import fr.pulsedev.appbuilder.visualeditor.Block;

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
    private Block<?> toDrag;
    private boolean shouldClone = true;

    public DnD(JComponent component, ArrayList<Component> toNotDrag, Container parent){
        this.component = component;
        this.toNotDrag =toNotDrag;
        this.parent = parent;
    }

    public DnD(JComponent component, ArrayList<Component> toNotDrag, Container parent, boolean shouldClone){
        this.component = component;
        this.toNotDrag =toNotDrag;
        this.parent = parent;
        this.shouldClone = shouldClone;
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
                if(componentToDrag == null || toNotDrag.contains(componentToDrag))return;
                if(!(componentToDrag instanceof Block<?>)) return;
                if(shouldClone){
                    toDrag = (Block<?>) Components.cloneComponent(componentToDrag);
                }
                else{
                    toDrag = (Block<?>) componentToDrag;
                }
                if(!Arrays.asList(component.getComponents()).contains(toDrag))
                    component.add(toDrag, (int) toDrag.getTagsByName("layer").get(0).getValue());
            }

            @Override
            public void mouseReleased(MouseEvent e){
                if (toDrag == null) return;
                if (shouldClone)
                    component.remove(toDrag);
                Container parentLocal = PanelManager.EDITOR.window.getContentPane();
                Component[] components = parentLocal.getComponents();
                if (parentLocal.getComponentAt(toDrag.getLocation()) != toDrag) {
                    Main.blocksInWindow.remove(toDrag);
                    PanelManager.EDITOR.window.remove(toDrag);
                    EditorPanel.getRightSlider().setClickedBlock(null);
                    return;
                }
                if (toDrag.getX() <= component.getWidth() && Arrays.asList(components).contains(toDrag)) {
                    if (shouldClone)
                        parentLocal.remove(toDrag);
                }
                if (parentLocal instanceof EditorPanel) {
                    if (!Main.blocksInWindow.contains(toDrag))
                        Main.blocksInWindow.add(toDrag);
                }
                toDrag = null;
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (toDrag == null) return;

                BlockDraggedEvent event = new BlockDraggedEvent((Coordinates) toDrag.getTagsByName("coordinates").get(0).getValue(), new Coordinates(e.getX(), e.getY()), toDrag);
                try {
                    EventsRegisters.callListener(BlockDraggedEvent.class, event);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                toDrag.editTag("coordinates", new Coordinates(e.getX(), e.getY()));
                if (e.getX() > component.getWidth() - toDrag.getWidth()) {
                    Container parentLocal = PanelManager.EDITOR.window.getContentPane();
                    if (parentLocal != null) {
                        if (!Main.blocksInWindow.contains(toDrag))
                            Main.blocksInWindow.add(toDrag);

                        if (toDrag.getLocation().getX() < parentLocal.getLocation().getX()
                                && toDrag.getLocation().getY() < parentLocal.getLocation().getY())
                            parentLocal.add(toDrag, (int) toDrag.getTagsByName("layer").get(0).getValue());

                        toDrag.editTag("coordinates", new Coordinates(e.getX(), e.getY()));
                    }
                }
                super.mouseDragged(e);
            }
        };
    }

}
