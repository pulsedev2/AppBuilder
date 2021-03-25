package fr.pulsedev.appbuilder.UI.panels.editor.slider;

import fr.pulsedev.appbuilder.UI.panels.enums.PanelManager;
import fr.pulsedev.appbuilder.visualeditor.blocks.JBlock;
import fr.pulsedev.appbuilder.settings.Theme;
import fr.pulsedev.appbuilder.utils.Coordinates;
import fr.pulsedev.appbuilder.utils.UiUtils;
import fr.pulsedev.appbuilder.visualeditor.blocks.JTextArea;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class LeftSlider extends JDesktopPane {

    private final JInternalFrame internalFrame = new JInternalFrame("Test frame", false, true, false,false);
    private final JPanel panel = new JPanel();
    private Component component;
    JBlock block = new JBlock();
    JTextArea text = new JTextArea();

    public LeftSlider(){
        this.setName("Test");
        this.add(internalFrame);
        internalFrame.setBounds(0, 0, this.getWidth(), this.getHeight());
        JLabel title = new JLabel("Elements");
        title.setFont(new Font("Dialog", Font.BOLD, 20));
        title.setForeground(Theme.USER.themesInterface.getTEXT());
        JButton close = UiUtils.getCloseButton(this);
        // Update block
        block.editTag("coordinates", new Coordinates(100, 100));
        block.editTag("color", new Color(150,15,15));
        // Update text area
        text.editTag("coordinates", new Coordinates(100, 400));
        text.editTag("text", "Text Area");
        //
        panel.setLayout(null);
        panel.setBackground(Theme.USER.themesInterface.getLIGHTER_BACKGROUND());

        ((BasicInternalFrameUI) internalFrame.getUI()).setNorthPane(null);

        MouseAdapter mouseAdapter = new DnD(panel, new ArrayList<>(){{add(title);}}, PanelManager.EDITOR.window.getContentPane()).getDnD();
        panel.addMouseMotionListener(mouseAdapter);
        panel.addMouseListener(mouseAdapter);

        // Internal Frame assets
        internalFrame.setBorder(null);
        internalFrame.add(panel);
        internalFrame.setVisible(true);

        this.setFocusable(false);
        internalFrame.setFocusable(false);
        panel.setFocusable(false);
        // Panel assets
        panel.add(text);
        panel.add(title);
        panel.add(block);
        panel.add(close);
        title.setBounds(150, 0,150, 70);
        block.setBounds(50,150,250,187);
        close.setBounds(0,0,30,30);
        text.setBounds(50, 400, 250, 187);
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
        block.update();
        text.update();

        internalFrame.setBounds(0,0,(SwingUtilities.getWindowAncestor(component).getWidth()/5),SwingUtilities.getWindowAncestor(component).getHeight());

    }
}
