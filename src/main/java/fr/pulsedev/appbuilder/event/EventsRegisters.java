package fr.pulsedev.appbuilder.event;

import fr.pulsedev.appbuilder.event.annotations.EventHandler;
import fr.pulsedev.appbuilder.event.annotations.EventsListeners;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventsRegisters {
    private static final List<Object> listenersList = new ArrayList<>();

    public static void addListener(Object clazz) {
        if (clazz.getClass().isAnnotationPresent(EventsListeners.class)) {
            listenersList.add(clazz);
        }
    }

    public static void callListener(Class<? extends Events> type, Events event) throws Exception {
        listenersList.removeIf(Objects::isNull);

        for (Object listener : listenersList) {
            for (Method method : listener.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(EventHandler.class)
                        && method.getParameterCount() == 1
                        && method.getParameterTypes()[0] == type) {
                    method.setAccessible(true);
                    method.invoke(listener, event);
                }
            }
        }
    }
}
