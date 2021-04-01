package fr.pulsedev.appbuilder.utils;

import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.UI.panels.MainPanel;
import fr.pulsedev.appbuilder.UI.panels.editor.EditorPanel;
import fr.pulsedev.appbuilder.settings.Theme;
import fr.pulsedev.appbuilder.themes.Themes;
import fr.pulsedev.appbuilder.visualeditor.Block;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class UiUtils {

    public static void makeJButtonTransparent(JButton button){
        button.setBackground(null);
        button.setBorderPainted(false);
        button.setBorder(null);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }

    public static void changeColor(BufferedImage img, Color old, Color new_) {
        final int oldRGB = old.getRGB();
        final int newRGB = new_.getRGB();
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                if (img.getRGB(x, y) == oldRGB && img.getRGB(x, y) < 0)
                    img.setRGB(x, y, newRGB);
            }
        }
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimG = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimG.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimG;
    }

    public static BufferedImage imageIconToBufferedImage(ImageIcon icon){
        BufferedImage bi = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        icon.paintIcon(null, g, 0,0);
        g.dispose();
        return bi;
    }

    public static BufferedImage generateBackgroundImage(Themes theme){
        BufferedImage bufferedImage = new BufferedImage(7680, 4320, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                if (x % 10 == 0 && y % 10 == 0) {
                    bufferedImage.setRGB(x, y, theme.themesInterface.getLIGHTER_BACKGROUND().getRGB());
                } else {
                    bufferedImage.setRGB(x, y, theme.themesInterface.getBACKGROUND().getRGB());
                }
            }
        }

        return bufferedImage;
    }

    public static Color getContrastColor(Color color) {
        double y = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000d;
        return y >= 128 ? Color.black : Color.white;
    }

    public static String lowCaseApartTheFirstChar(String string) {
        String result = string.toLowerCase();

        return result.substring(0, 1).toUpperCase() + result.substring(1);
    }

    public static JButton getCloseButton() {
        return getCloseButton(null);
    }

    public static JButton getCloseButton(@Nullable Container parent){
        JButton close = new JButton();

        close.addActionListener(e -> {
            if(e.getSource() == close){
                if(parent == null){
                    System.exit(0);
                }else{
                    parent.setVisible(false);
                }
            }
        });

        close.addMouseListener(getAdapter(close));

        UiUtils.makeJButtonTransparent(close);

        // Close image computing
        BufferedImage closeImage = UiUtils.imageIconToBufferedImage(new ImageIcon(Main.RESOURCES_PATH + "images/close.png"));
        UiUtils.changeColor(closeImage, Color.BLACK, Theme.USER.themesInterface.getTEXT());
        closeImage = UiUtils.resize(closeImage, 15, 15);
        close.setIcon(new ImageIcon(closeImage));

        close.setFocusable(false);

        return close;
    }

    private static MouseAdapter getAdapter(JButton component){
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                component.setContentAreaFilled(true);
                component.setBackground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                UiUtils.makeJButtonTransparent(component);
            }
        };
    }

    public static class FrameDragListener extends MouseAdapter {
        private final JFrame FRAME;
        private Point deltaMouse = null;

        public FrameDragListener(JFrame FRAME) {
            this.FRAME = FRAME;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            deltaMouse = e.getPoint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            deltaMouse = null;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            Point mouseLoc = e.getLocationOnScreen();
            FRAME.setLocation(mouseLoc.x - deltaMouse.x, mouseLoc.y - deltaMouse.y);
        }
    }

    public static void numberOnly(JTextComponent jTextComponent, boolean decimal, boolean negative) {
        jTextComponent.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                jTextComponent.setEditable((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || (ke.getKeyChar() == '.' && decimal) || (ke.getKeyChar() == '-' && negative) || (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE));
            }
        });
    }

    public static void removeBlock(Block<?> block) {
        Main.blocksInWindow.remove(block);
        MainPanel.getEditorPanel().remove(block);
        EditorPanel.getRightSlider().setClickedBlock(null);
    }
}
