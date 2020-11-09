package fr.pulsedev.appbuilder.UI.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.CubicCurve2D;

public class EditorPanel extends JPanel {

    private CubicCurve2D cubicCurve2D;

    public EditorPanel(){

        Component component = this;

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                component.repaint();
            }
        };

        this.addMouseListener(mouseAdapter);

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = new Point(a.getLocation());
        SwingUtilities.convertPointFromScreen(b, SwingUtilities.getWindowAncestor(this));
        int x1 = (int) b.getX();
        int y1 = (int) b.getY();
        CubicCurve2D cubcurve = new CubicCurve2D.Float(x1,y1,y1+200,y1-115,400-200,400+115,400,400);
        g2d.draw(cubcurve);

    }


}
