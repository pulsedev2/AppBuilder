package fr.pulsedev.appbuilder.UI.panels.editor;

import fr.pulsedev.appbuilder.settings.Settings;
import fr.pulsedev.appbuilder.settings.Theme;
import fr.pulsedev.appbuilder.utils.UiUtils;
import fr.pulsedev.appbuilder.visualeditor.blocks.TextArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.CubicCurve2D;
import java.awt.image.BufferedImage;
import java.util.Set;
import java.util.TimerTask;

public class EditorPanel extends JPanel {

    BufferedImage bg = UiUtils.generateBackgroundImage(Theme.USER);

    public EditorPanel(){

        Component component = this;

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                component.repaint();
            }
        };

        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
               component.repaint();
            }
        }, 0, 1);


        this.addMouseListener(mouseAdapter);
        LeftSlider leftSlider = new LeftSlider();
        this.add(leftSlider);

        leftSlider.setBounds(0,0, 100, 500);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(new ImageIcon(this.bg.getSubimage(0,0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight())).getImage(), 0, 0, null);

        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = new Point(a.getLocation());
        SwingUtilities.convertPointFromScreen(b, SwingUtilities.getWindowAncestor(this));
        int x1 = (int) b.getX();
        int y1 = (int) b.getY();
        CubicCurve2D cubCurve = new CubicCurve2D.Float(x1, y1, y1 + 200, y1 - 115, 400 - 200, 400 + 115, 400, 400);
        g2d.draw(cubCurve);
    }


}
