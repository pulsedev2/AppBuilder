package fr.pulsedev.appbuilder.UI.panels.bar.enums;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public enum MenuSubItem {
    ;

    private final MenuItem ITEM;
    private final boolean IS_ITEM;
    private final String TEXT;
    private final ActionListener ACTION_LISTENER;

    MenuSubItem(MenuItem ITEM, boolean IS_ITEM, String TEXT, ActionListener ACTION_LISTENER) {
        this.ITEM = ITEM;
        this.IS_ITEM = IS_ITEM;
        this.TEXT = TEXT;
        this.ACTION_LISTENER = ACTION_LISTENER;
    }

    public MenuItem getIS_ITEM() {
        return ITEM;
    }

    public boolean getITEM() {
        return IS_ITEM;
    }

    public String getTEXT() {
        return TEXT;
    }

    public ActionListener getACTION_LISTENER() {
        return ACTION_LISTENER;
    }

    public static List<MenuSubItem> getByItem(MenuItem item) {
        List<MenuSubItem> menuSubItems = new ArrayList<>();
        for (MenuSubItem menuSubItem : MenuSubItem.values())
            if (menuSubItem.getIS_ITEM() == item)
                menuSubItems.add(menuSubItem);

        return menuSubItems;
    }
}
