package fr.pulsedev.appbuilder.UI.panels.bar;

import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.UI.panels.MainPanel;
import fr.pulsedev.appbuilder.UI.panels.bar.enums.MenuItem;
import fr.pulsedev.appbuilder.UI.panels.bar.enums.MenuSubItem;
import fr.pulsedev.appbuilder.UI.panels.bar.enums.MenuTypes;
import fr.pulsedev.appbuilder.UI.panels.enums.PanelManager;
import fr.pulsedev.appbuilder.settings.Theme;
import fr.pulsedev.appbuilder.utils.Coordinates;
import fr.pulsedev.appbuilder.utils.UiUtils;
import fr.pulsedev.appbuilder.visualeditor.Block;
import fr.pulsedev.appbuilder.visualeditor.blocks.BlockManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicMenuUI;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


public class HorizontalBar extends JPanel {
    public static List<JMenuItem> blockList = new ArrayList<>();
    private final JMenuBar MENU_BAR = new JMenuBar();
    private final JButton CLOSE_BUTTON = new JButton(new ImageIcon(UiUtils.resize(UiUtils.imageIconToBufferedImage(new ImageIcon(Main.RESOURCES_PATH + "images/close_2.png")), MainPanel.horizontalBarHeight - 10, MainPanel.horizontalBarHeight - 10)));
    private final JButton FULL_BUTTON = new JButton(new ImageIcon(UiUtils.resize(UiUtils.imageIconToBufferedImage(new ImageIcon(Main.RESOURCES_PATH + "images/full_screen.png")), MainPanel.horizontalBarHeight - 10, MainPanel.horizontalBarHeight - 10)));
    private final JButton MINIM_BUTTON = new JButton(new ImageIcon(UiUtils.resize(UiUtils.imageIconToBufferedImage(new ImageIcon(Main.RESOURCES_PATH + "images/minimize.png")), MainPanel.horizontalBarHeight - 10, MainPanel.horizontalBarHeight - 10)));

    private Rectangle oldWindowsrect;
    private Image logo;

    public HorizontalBar() {
        this.setLayout(null);
        this.setBackground(Theme.USER.themesInterface.getFOREGROUND());

        this.CLOSE_BUTTON.setFocusable(false);
        this.CLOSE_BUTTON.setBackground(null);
        this.CLOSE_BUTTON.setBorderPainted(false);
        this.CLOSE_BUTTON.addActionListener(e -> {
            Main.PROJECT.save();
            System.exit(0);
        });
        this.add(this.CLOSE_BUTTON);

        this.FULL_BUTTON.setFocusable(false);
        this.FULL_BUTTON.setBackground(null);
        this.FULL_BUTTON.setBorderPainted(false);
        this.FULL_BUTTON.addActionListener(e -> {
            if (oldWindowsrect == null) {
                oldWindowsrect = PanelManager.EDITOR.window.getBounds();

                Rectangle rectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
                PanelManager.EDITOR.window.setLocation(rectangle.getLocation());
                PanelManager.EDITOR.window.setSize(new Dimension(rectangle.width, rectangle.height));
            } else {
                PanelManager.EDITOR.window.setBounds(oldWindowsrect);
                oldWindowsrect = null;
            }
        });
        this.add(this.FULL_BUTTON);

        this.MINIM_BUTTON.setFocusable(false);
        this.MINIM_BUTTON.setBackground(null);
        this.MINIM_BUTTON.setBorderPainted(false);
        this.MINIM_BUTTON.addActionListener(e -> {
            PanelManager.EDITOR.window.setState(Frame.ICONIFIED);
        });
        this.add(this.MINIM_BUTTON);

        try {
            this.logo = ImageIO.read(new File(Main.RESOURCES_PATH + "images/logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (BlockManager block : BlockManager.values()) {
            JMenuItem jMenuItem = new JMenuItem(block.getClassName());
            jMenuItem.addActionListener(e -> {
                try {
                    Block<?> a = block.getClazz().getConstructor().newInstance();
                    a.editTag("coordinates", new Coordinates(MainPanel.getEditorPanel().getX() + (MainPanel.getEditorPanel().getWidth() / 2), MainPanel.getEditorPanel().getY() + (MainPanel.getEditorPanel().getHeight() / 2)));
                    Main.blocksInWindow.add(a);
                } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException noSuchMethodException) {
                    noSuchMethodException.printStackTrace();
                }

            });

            blockList.add(jMenuItem);
        }

        this.MENU_BAR.setBackground(null);
        this.MENU_BAR.setBorderPainted(false);

        for (MenuTypes menuTypes : MenuTypes.values()) {
            JMenu menu = new JMenu(menuTypes.getTEXT());
            menu.setBorderPainted(false);
            menu.setUI(new CustomMenuUI(Theme.USER.themesInterface.getHOVER_TEXT()));
            menu.setForeground(UiUtils.getContrastColor(Theme.USER.themesInterface.getLIGHTER_BACKGROUND()));
            menu.getPopupMenu().setBackground(Theme.USER.themesInterface.getFOREGROUND());
            menu.getPopupMenu().setBorder(new LineBorder(Theme.USER.themesInterface.getBACKGROUND()));
            this.MENU_BAR.add(menu);

            for (MenuItem fileItem : MenuItem.getByType(menuTypes)) {
                JMenuItem item;
                if (fileItem.isIS_ITEM()) {
                    item = new JMenuItem(fileItem.getTEXT());
                    item.addActionListener(fileItem.getACTION_LISTENER());
                } else {
                    item = new JMenu(fileItem.getTEXT());

                    if (fileItem.getSUB_ITEM().get(0) == MenuSubItem.class) {
                        for (MenuSubItem menuSubItem : MenuSubItem.getByItem(fileItem)) {
                            JMenuItem subItem;

                            if (menuSubItem.getITEM()) {
                                subItem = new JMenuItem(menuSubItem.getTEXT());
                                subItem.addActionListener(menuSubItem.getACTION_LISTENER());
                            } else {
                                subItem = new JMenu(menuSubItem.getTEXT());
                            }
                            item.add(subItem);
                        }
                    } else {
                        ((List<JMenuItem>) fileItem.getSUB_ITEM()).forEach(item::add);
                    }
                }
                item.setBorderPainted(false);
                item.setBackground(null);
                menu.add(item);
            }
        }

        this.add(this.MENU_BAR);
        this.MENU_BAR.updateUI();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(this.logo, 0, 0, this.logo.getWidth(this), MainPanel.horizontalBarHeight, this);

        this.MENU_BAR.setBounds(this.logo.getWidth(this) + 20, 0, this.getWidth() - 100, MainPanel.horizontalBarHeight);

        this.CLOSE_BUTTON.setBounds(this.getWidth() - (this.CLOSE_BUTTON.getIcon().getIconWidth() + 20), (MainPanel.horizontalBarHeight - this.CLOSE_BUTTON.getIcon().getIconHeight()) / 2, this.CLOSE_BUTTON.getIcon().getIconWidth(), this.CLOSE_BUTTON.getIcon().getIconHeight());

        this.FULL_BUTTON.setBounds(this.getWidth() - (this.FULL_BUTTON.getIcon().getIconWidth() + this.CLOSE_BUTTON.getIcon().getIconWidth() + 30), (MainPanel.horizontalBarHeight - this.FULL_BUTTON.getIcon().getIconHeight()) / 2, this.FULL_BUTTON.getIcon().getIconWidth(), this.FULL_BUTTON.getIcon().getIconHeight());

        this.MINIM_BUTTON.setBounds(this.getWidth() - (this.MINIM_BUTTON.getIcon().getIconWidth() + this.FULL_BUTTON.getIcon().getIconWidth() + this.CLOSE_BUTTON.getIcon().getIconWidth() + 40), (MainPanel.horizontalBarHeight - this.MINIM_BUTTON.getIcon().getIconHeight()) / 2, this.MINIM_BUTTON.getIcon().getIconWidth(), this.MINIM_BUTTON.getIcon().getIconHeight());

    }

    static class CustomMenuUI extends BasicMenuUI {
        public CustomMenuUI(Color color) {
            super.selectionForeground = color;
        }
    }
}
