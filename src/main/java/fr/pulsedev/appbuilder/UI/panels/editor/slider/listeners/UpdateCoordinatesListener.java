package fr.pulsedev.appbuilder.UI.panels.editor.slider.listeners;

import fr.pulsedev.appbuilder.event.annotations.EventHandler;
import fr.pulsedev.appbuilder.event.annotations.EventsListeners;
import fr.pulsedev.appbuilder.event.events.blockEvents.BlockDraggedEvent;

import javax.swing.text.JTextComponent;

@EventsListeners
public class UpdateCoordinatesListener {
    private final JTextComponent xComponent;
    private final JTextComponent yComponent;

    public UpdateCoordinatesListener(JTextComponent xComponent, JTextComponent yComponent) {
        this.xComponent = xComponent;
        this.yComponent = yComponent;
    }

    @EventHandler
    public void onBlockMove(BlockDraggedEvent event) {
        this.xComponent.setText(String.valueOf(event.getNewCoordinates().getX()));
        this.yComponent.setText(String.valueOf(event.getNewCoordinates().getY()));
    }
}
