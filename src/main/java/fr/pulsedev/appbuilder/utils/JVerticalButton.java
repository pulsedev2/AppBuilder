package fr.pulsedev.appbuilder.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class JVerticalButton extends JButton {

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (this.isOpaque()) {
            g2.setColor(getBackground());
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
        g2.setColor(getForeground());
        AffineTransform af = this.getFont().getTransform();
        af.rotate(Math.toRadians(-90));
        g2.setFont(this.getFont().deriveFont(af));
        FontRenderContext ctx = g2.getFontRenderContext();
        TextLayout layout = new TextLayout(this.getText(), g2.getFont(), ctx);
        Rectangle2D bounds = layout.getBounds();
        int x = (this.getWidth() / 2) - (int) bounds.getCenterX();
        int y = (this.getHeight() / 2) - (int) bounds.getCenterY();
        g2.drawString(this.getText(), x, y);
    }
}