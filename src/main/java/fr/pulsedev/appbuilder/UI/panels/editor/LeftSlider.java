package fr.pulsedev.appbuilder.UI.panels.editor;

import javax.swing.*;

public class LeftSlider extends JScrollPane {

    public LeftSlider(){
        this.setLayout(null);
        JButton test = new JButton("test");
        this.add(test);
        test.setBounds(0,0, 50,50);
    }
}
