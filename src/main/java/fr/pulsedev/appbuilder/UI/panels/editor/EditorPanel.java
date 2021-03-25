package fr.pulsedev.appbuilder.UI.panels.editor;

import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.UI.panels.editor.slider.DnD;
import fr.pulsedev.appbuilder.UI.panels.editor.slider.LeftSlider;
import fr.pulsedev.appbuilder.UI.panels.enums.PanelManager;
import fr.pulsedev.appbuilder.settings.Theme;
import fr.pulsedev.appbuilder.utils.UiUtils;
import fr.pulsedev.appbuilder.visualeditor.Block;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.util.*;

public class EditorPanel extends JLayeredPane {

    BufferedImage bg = UiUtils.generateBackgroundImage(Theme.USER);
    LeftSlider leftSlider;

    public EditorPanel(){

        Component component = this;

        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
               component.repaint();
            }
        }, 0, 1);
        this.setLayout(null);
        this.setFocusable(false);

        leftSlider = new LeftSlider();
        this.add(leftSlider, -1);

        JLabel title = new JLabel("Elements");
        MouseAdapter mouseAdapter = new DnD(this, new ArrayList<>(){{add(title);}}, this, false).getDnD();
        this.addMouseMotionListener(mouseAdapter);
        this.addMouseListener(mouseAdapter);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(new ImageIcon(this.bg.getSubimage(0,0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight())).getImage(), 0, 0, null);

        /*PointerInfo a = MouseInfo.getPointerInfo();
        Point b = new Point(a.getLocation());
        SwingUtilities.convertPointFromScreen(b, SwingUtilities.getWindowAncestor(this));
        int x1 = (int) b.getX();
        int y1 = (int) b.getY();
        CubicCurve2D cubCurve = new CubicCurve2D.Float(x1, y1, y1 + 200, y1 - 115, 400 - 200, 400 + 115, 400, 400);
        g2d.draw(cubCurve);*/

        if(!Arrays.asList(this.getComponents()).contains(leftSlider))
            this.add(leftSlider, -1);
        leftSlider.repaint(this);
        int width = SwingUtilities.getWindowAncestor(this).getWidth();
        int height = SwingUtilities.getWindowAncestor(this).getHeight();
        leftSlider.setBounds(0,0,(width/5),height);

        for (Block<?> block : Main.blocksInWindow) {

            if(!Arrays.asList(this.getComponents()).contains(block)){
                this.add(block, (int) block.getTagsByName("layer").get(0).getValue());
            }

            block.update();
        }

        if(!PanelManager.EDITOR.window.isFocusOwner()){
            PanelManager.EDITOR.window.requestFocusInWindow();
        }

    }


}
