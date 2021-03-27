package fr.pulsedev.appbuilder.event.events.blockEvents;

import fr.pulsedev.appbuilder.utils.Coordinates;
import fr.pulsedev.appbuilder.visualeditor.Block;

public class BlockDraggedEvent extends BlockEvents {

    public Coordinates oldCoordinates;
    public Coordinates newCoordinates;

    public BlockDraggedEvent(Coordinates oldCoordinates, Coordinates newCoordinates, Block<?> block) {
        super(block, BlockDraggedEvent.class.getSimpleName());

        this.oldCoordinates = oldCoordinates;
        this.newCoordinates = newCoordinates;
    }

    public Coordinates getOldCoordinates() {
        return oldCoordinates;
    }

    public Coordinates getNewCoordinates() {
        return newCoordinates;
    }
}
