package fr.pulsedev.appbuilder.event;

public abstract class Events {
    String eventName = "";

    public Events(String eventName) {
        this.eventName = eventName;
    }
}
