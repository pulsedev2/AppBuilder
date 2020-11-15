package fr.pulsedev.appbuilder.UI.panels.editor.slider;

import fr.pulsedev.appbuilder.UI.panels.enums.PanelManager;
import fr.pulsedev.appbuilder.blueprints.Block;
import fr.pulsedev.appbuilder.blueprints.Component.JBlock;
import fr.pulsedev.appbuilder.settings.Theme;
import fr.pulsedev.appbuilder.utils.Coordinates;
import fr.pulsedev.appbuilder.utils.UiUtils;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class LeftSlider extends JDesktopPane {

    private final JInternalFrame internalFrame = new JInternalFrame("Test frame", false, true, false,false);
    private final JPanel panel = new JPanel();
    private Component toDrag;
    private Component component;

    public LeftSlider(){
        this.setName("Test");
        this.add(internalFrame);
        internalFrame.setBounds(0, 0, this.getWidth(), this.getHeight());
        JLabel title = new JLabel("Elements");
        title.setFont(new Font("Dialog", Font.BOLD, 20));
        title.setForeground(Theme.USER.themesInterface.getTEXT());
        JLabel test = new JLabel("test");
        JButton close = UiUtils.getCloseButton(this);
        JBlock block = new JBlock(new Color(150,15,15), new Block(new Coordinates(100,100)));
        panel.setLayout(null);
        panel.setBackground(Theme.USER.themesInterface.getLIGHTER_BACKGROUND());

        ((BasicInternalFrameUI) internalFrame.getUI()).setNorthPane(null);

        test.setForeground(Theme.USER.themesInterface.getTEXT());


        MouseAdapter mouseAdapter = new DnD(panel, new ArrayList<>(){{add(title);}}, PanelManager.EDITOR.window.getContentPane()).getDnD();
        panel.addMouseMotionListener(mouseAdapter);
        panel.addMouseListener(mouseAdapter);

        // Internal Frame assets
        internalFrame.setBorder(null);
        internalFrame.add(panel);
        internalFrame.setVisible(true);

        // Panel assets
        panel.add(test);
        panel.add(title);
        panel.add(block);
        panel.add(close);
        test.setBounds(300, 300,100,50);
        title.setBounds(150, 0,150, 70);
        block.setBounds(50,150,250,187);
        close.setBounds(0,0,30,30);

        System.out.println(block.getX());
    }

    public JInternalFrame getInternalFrame() {
        return internalFrame;
    }

    public void repaint(Component component){
        assert SwingUtilities.getWindowAncestor(this) != null;
        this.component = this;
        if(internalFrame.isClosed()){
            this.setVisible(false);
            return;
        }

        internalFrame.setBounds(0,0,(SwingUtilities.getWindowAncestor(component).getWidth()/5),SwingUtilities.getWindowAncestor(component).getHeight());

    }
}
