package fr.pulsedev.appbuilder.UI.panels.bar.enums;

import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.UI.panels.bar.HorizontalBar;
import fr.pulsedev.appbuilder.UI.panels.editor.EditorPanel;
import fr.pulsedev.appbuilder.UI.panels.enums.PanelManager;
import fr.pulsedev.appbuilder.projects.Project;
import fr.pulsedev.appbuilder.projects.errors.ProjectErrors;
import fr.pulsedev.appbuilder.utils.FolderFilter;
import fr.pulsedev.appbuilder.utils.UiUtils;
import fr.pulsedev.appbuilder.visualeditor.Block;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum MenuItem {
    // FILE
    SAVE(MenuTypes.FILE, true, "Save", e -> {
        Main.PROJECT.save();
    }),
    LOAD(MenuTypes.FILE, true, "Load", e -> {
        Main.PROJECT.save();

        File desktop = new File(System.getProperty("user.home"), "Desktop");
        JFileChooser fileChooser = new JFileChooser(desktop);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.resetChoosableFileFilters();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FolderFilter(false));
        int returnedVal = fileChooser.showDialog(PanelManager.EDITOR.window.getPanel(), "Accept");

        if (returnedVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                Main.blocksInWindow.clear();
                Main.PROJECT = Project.open(file);
                PanelManager.EDITOR.window.repaint();
            } catch (ProjectErrors projectErrors) {
                projectErrors.printStackTrace();
            }
        }
    }),
    RESTART(MenuTypes.FILE, true, "Restart", e -> {
        PanelManager.RESTART.window.run();
    }),

    // EDIT
    NEW(MenuTypes.EDIT, false, "New", null, HorizontalBar.blockList),
    REMOVE(MenuTypes.EDIT, true, "Remove", e -> {
        Block<?> block = EditorPanel.getRightSlider().clickedBlock;
        if (block != null)
            UiUtils.removeBlock(block);
    });

    private final MenuTypes TYPE;
    private final boolean IS_ITEM;
    private final String TEXT;
    private final ActionListener ACTION_LISTENER;
    private final List<?> SUB_ITEM;

    MenuItem(MenuTypes TYPE, boolean IS_ITEM, String TEXT, ActionListener ACTION_LISTENER, List<?> SUB_ITEM) {
        this.TYPE = TYPE;
        this.IS_ITEM = IS_ITEM;
        this.TEXT = TEXT;
        this.ACTION_LISTENER = ACTION_LISTENER;
        this.SUB_ITEM = SUB_ITEM;
    }

    MenuItem(MenuTypes TYPE, boolean IS_ITEM, String TEXT, ActionListener ACTION_LISTENER, Class<?> SUB_ITEM) {
        this(TYPE, IS_ITEM, TEXT, ACTION_LISTENER, new ArrayList<>(Collections.singletonList(SUB_ITEM)));
    }

    MenuItem(MenuTypes TYPE, boolean IS_ITEM, String TEXT, ActionListener ACTION_LISTENER) {
        this(TYPE, IS_ITEM, TEXT, ACTION_LISTENER, new ArrayList<>());
    }

    public static List<MenuItem> getByType(MenuTypes type) {
        List<MenuItem> menuItems = new ArrayList<>();
        for (MenuItem menuItem : MenuItem.values())
            if (menuItem.getTYPE() == type)
                menuItems.add(menuItem);

        return menuItems;
    }

    public MenuTypes getTYPE() {
        return TYPE;
    }

    public boolean isIS_ITEM() {
        return IS_ITEM;
    }

    public String getTEXT() {
        return TEXT;
    }

    public ActionListener getACTION_LISTENER() {
        return ACTION_LISTENER;
    }

    public List<?> getSUB_ITEM() {
        return SUB_ITEM;
    }
}
