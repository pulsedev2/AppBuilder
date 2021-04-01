package fr.pulsedev.appbuilder.UI.panels.editor;

import fr.pulsedev.appbuilder.Main;
import fr.pulsedev.appbuilder.UI.panels.editor.slider.DnD;
import fr.pulsedev.appbuilder.UI.panels.editor.slider.LeftSlider;
import fr.pulsedev.appbuilder.UI.panels.editor.slider.RightSlider;
import fr.pulsedev.appbuilder.settings.Theme;
import fr.pulsedev.appbuilder.utils.UiUtils;
import fr.pulsedev.appbuilder.visualeditor.Block;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditorPanel extends JLayeredPane {

    private static LeftSlider leftSlider;
    private static RightSlider rightSlider;
    BufferedImage bg = UiUtils.generateBackgroundImage(Theme.USER);

    public EditorPanel() {
        this.setLayout(null);
        this.setFocusable(false);

        leftSlider = new LeftSlider();
        this.add(leftSlider);

        rightSlider = new RightSlider(new JPanel());
        this.add(rightSlider);

        JLabel title = new JLabel("Elements");
        MouseAdapter mouseAdapter = new DnD(this, new ArrayList<>() {{
            add(title);
        }}, this, false).getDnD();
        this.addMouseMotionListener(mouseAdapter);
        this.addMouseListener(mouseAdapter);

        MouseAdapter mouseAdapter1 = rightSlider.getMouseAdapter();
        this.addMouseMotionListener(mouseAdapter1);
        this.addMouseListener(mouseAdapter1);
    }

    public static LeftSlider getLeftSlider() {
        return leftSlider;
    }

    public static RightSlider getRightSlider() {
        return rightSlider;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(new ImageIcon(this.bg.getSubimage(0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight())).getImage(), 0, 0, null);

        /*PointerInfo a = MouseInfo.getPointerInfo();
        Point b = new Point(a.getLocation());
        SwingUtilities.convertPointFromScreen(b, SwingUtilities.getWindowAncestor(this));
        int x1 = (int) b.getX();
        int y1 = (int) b.getY();
        CubicCurve2D cubCurve = new CubicCurve2D.Float(x1, y1, y1 + 200, y1 - 115, 400 - 200, 400 + 115, 400, 400);
        g2d.draw(cubCurve);*/

        if (!Arrays.asList(this.getComponents()).contains(leftSlider))
            this.add(leftSlider);

        leftSlider.repaint(this);
        leftSlider.setBounds(0, 0, (this.getWidth() / 5), this.getHeight());
        this.setLayer(leftSlider, Integer.MAX_VALUE);

        if (!Arrays.asList(this.getComponents()).contains(rightSlider))
            this.add(rightSlider);

        rightSlider.repaint();
        rightSlider.setBounds((this.getWidth() - this.getWidth() / 5), 0, this.getWidth() / 5, this.getHeight() - 39);
        this.setLayer(rightSlider, Integer.MAX_VALUE);

        for (Block<?> block : Main.blocksInWindow) {

            if (!Arrays.asList(this.getComponents()).contains(block)) {
                this.add(block, (int) block.getTagsByName("layer").get(0).getValue());
            }

            block.update();
        }

        List<Component> componentList = new ArrayList<>();
        for (Component component : this.getComponents()) {
            if (component instanceof Block<?>
                    && !Main.blocksInWindow.contains(component)) {
                componentList.add(component);
            }
        }

        componentList.forEach(this::remove);
    }
}
