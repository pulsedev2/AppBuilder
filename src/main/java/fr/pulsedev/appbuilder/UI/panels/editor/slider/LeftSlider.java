package fr.pulsedev.appbuilder.UI.panels.editor.slider;

import fr.pulsedev.appbuilder.settings.Theme;
import fr.pulsedev.appbuilder.utils.UiUtils;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TimerTask;

public class LeftSlider extends JDesktopPane {

    private final JInternalFrame internalFrame = new JInternalFrame("Test frame", false, true, false,false);

    public LeftSlider(){
        this.setName("Test");
        this.add(internalFrame);
        System.out.println(SwingUtilities.getWindowAncestor(this));
        internalFrame.setBounds(0, 0, this.getWidth(), this.getHeight());
        JLabel test = new JLabel("test");
        JPanel panel = new JPanel();
        JButton close = UiUtils.getCloseButton();
        panel.setBackground(Theme.USER.themesInterface.getLIGHTER_BACKGROUND());

        ((BasicInternalFrameUI) internalFrame.getUI()).setNorthPane(null);

        test.setForeground(Theme.USER.themesInterface.getTEXT());


        // Internal Frame assets
        internalFrame.setBorder(null);
        internalFrame.add(panel);
        internalFrame.setVisible(true);

        // Panel assets
        panel.add(test);
        panel.add(close);
        test.setBounds(100,0,10,50);
        close.setBounds(500 - 30,0,30,30);
    }

    public JInternalFrame getInternalFrame() {
        return internalFrame;
    }

    public void repaint(Component component){
        assert SwingUtilities.getWindowAncestor(this) != null;

        if(internalFrame.isClosed()){
            this.setVisible(false);
            return;
        }

        internalFrame.setBounds(0,0,(SwingUtilities.getWindowAncestor(component).getWidth()/5),SwingUtilities.getWindowAncestor(component).getHeight());

    }

}
