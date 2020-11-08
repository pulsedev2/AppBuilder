package fr.pulsedev.appbuilder.utils;

import javax.swing.*;
import java.awt.*;
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
}
