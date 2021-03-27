package fr.pulsedev.appbuilder.event.events.blockEvents;

import fr.pulsedev.appbuilder.event.Events;
import fr.pulsedev.appbuilder.visualeditor.Block;

public abstract class BlockEvents extends Events {
    public Block<?> block;

    public BlockEvents(Block<?> block, String eventName) {
        super(BlockEvents.class.getSimpleName() + " " + eventName);
        this.block = block;
    }
}
