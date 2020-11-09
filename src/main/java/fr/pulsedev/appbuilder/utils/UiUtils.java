package fr.pulsedev.appbuilder.utils;

import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.settings.Settings;

import javax.swing.*;
import java.awt.*;
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
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
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

    public static JButton getCloseButton(){
        JButton close = new JButton();

        close.addActionListener(e -> {
            if(e.getSource() == close){
                System.exit(0);
            }
        });

        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                close.setContentAreaFilled(true);
                close.setBackground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                UiUtils.makeJButtonTransparent(close);
            }
        });


        UiUtils.makeJButtonTransparent(close);

        // Close image computing
        BufferedImage closeImage = UiUtils.imageIconToBufferedImage(new ImageIcon(Main.RESOURCES_PATH + "images/close.png"));
        UiUtils.changeColor(closeImage, Color.BLACK, Settings.getSettingsFromJSon().getTheme().getThemes().themesInterface.getTEXT());
        closeImage = UiUtils.resize(closeImage, 15, 15);
        close.setIcon(new ImageIcon(closeImage));

        return close;
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
}
