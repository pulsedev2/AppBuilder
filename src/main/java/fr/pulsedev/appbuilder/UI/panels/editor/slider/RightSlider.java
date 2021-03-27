package fr.pulsedev.appbuilder.UI.panels.editor.slider;

import com.bric.colorpicker.ColorPicker;
import fr.pulsedev.appbuilder.UI.panels.editor.slider.listeners.UpdateCoordinatesListener;
import fr.pulsedev.appbuilder.UI.panels.enums.PanelManager;
import fr.pulsedev.appbuilder.event.EventsRegisters;
import fr.pulsedev.appbuilder.languages.Lang;
import fr.pulsedev.appbuilder.settings.Language;
import fr.pulsedev.appbuilder.settings.Theme;
import fr.pulsedev.appbuilder.utils.Coordinates;
import fr.pulsedev.appbuilder.utils.UiUtils;
import fr.pulsedev.appbuilder.visualeditor.Block;
import fr.pulsedev.appbuilder.visualeditor.Tag;
import fr.pulsedev.appbuilder.visualeditor.blocks.JBlock;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RightSlider extends JScrollPane {
    private final JPanel panel;
    private final JPanel subPanel = new JPanel();
    private Block<?> clickedBlock;
    private boolean alreadyPaint = false;
    private UpdateCoordinatesListener updateCoordinatesListener;

    public RightSlider(JPanel panel) {
        super(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.panel = panel;

        setBorder(null);
        setBackground(null);
        setVisible(true);

        this.panel.setLayout(new GridLayout(1, 1));
        this.panel.setBackground(null);
        this.panel.setBorder(null);
        this.panel.add(subPanel);
        this.panel.setBounds(0, 0, getWidth(), getHeight());

        subPanel.setBackground(Theme.USER.themesInterface.getLIGHTER_BACKGROUND());
        subPanel.setBorder(null);
        subPanel.setLayout(null);
    }

    public MouseAdapter getMouseAdapter() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Component jComponent = PanelManager.EDITOR.window.getContentPane().getComponentAt(e.getX(), e.getY());

                if (jComponent instanceof Block) {
                    if (jComponent != clickedBlock) {
                        subPanel.removeAll();
                        alreadyPaint = false;
                    }

                    clickedBlock = (Block<?>) jComponent;
                } else {
                    clickedBlock = null;
                }
            }
        };
    }

    public void repaint() {
        if (this.clickedBlock != null
                && !this.alreadyPaint) {
            this.alreadyPaint = true;
            List<Tag<?>> tagList = clickedBlock.getTags();


            for (Tag<?> tag : tagList) {
                JLabel label = new JLabel(tag.getName().replaceFirst(".", (tag.getName().charAt(0) + "").toUpperCase()));
                label.setBackground(null);
                label.setForeground(Theme.USER.themesInterface.getTEXT());

                this.subPanel.add(label);
                label.setBounds(10, 50 * tagList.indexOf(tag) + 10, getWidth() / 2, 40);

                Rectangle rectangle = new Rectangle(getWidth() / 2, 50 * tagList.indexOf(tag) + 10, getWidth() / 2 - 26, 40);

                switch (tag.getType().getSimpleName()) {
                    case "String" -> {
                        JTextField textField = new JTextField((String) tag.getValue());
                        textField.setBackground(Theme.USER.themesInterface.getBACKGROUND());
                        textField.setForeground(Theme.USER.themesInterface.getTEXT());
                        textField.setFont(textField.getFont().deriveFont(16F));
                        textField.setBorder(null);

                        Runnable runnable = () -> {
                            while (textField.hasFocus()) {
                                clickedBlock.editTag(tag.getName(), textField.getText());
                            }
                        };

                        textField.addFocusListener(new FocusAdapter() {
                            @Override
                            public void focusGained(FocusEvent e) {
                                super.focusGained(e);

                                new Thread(runnable).start();
                            }
                        });

                        this.subPanel.add(textField);
                        textField.setBounds(rectangle);
                    }

                    case "Color" -> {
                        final Color[] color = {(Color) tag.getValue()};

                        JBlock block = new JBlock();
                        block.setBackground(color[0]);
                        block.setBorder(new LineBorder(Color.WHITE));

                        block.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                JPopupMenu popupMenu = new JPopupMenu();
                                popupMenu.setBackground(Theme.USER.themesInterface.getLIGHTER_BACKGROUND());
                                popupMenu.setBorderPainted(false);

                                ColorPicker colorPicker = new ColorPicker(true, false);
                                colorPicker.setColor(color[0]);

                                Lang.Interface language = Language.USER.interface_;
                                List<String> list = new ArrayList<>(Arrays.asList(language.getString("color_picker.tint")
                                        , language.getString("color_picker.saturation")
                                        , language.getString("color_picker.brightness")
                                        , language.getString("color_picker.red")
                                        , language.getString("color_picker.green")
                                        , language.getString("color_picker.blue")
                                        , "Hex"
                                        , ""));
                                AtomicInteger number = new AtomicInteger();
                                Arrays.stream(colorPicker.getExpertControls().getComponents()).forEach((component) -> {
                                    if (component instanceof JPanel)
                                        Arrays.stream(((JPanel) component).getComponents()).forEach(component1 -> {
                                            if (component1 instanceof JLabel) {
                                                component1.setForeground(Theme.USER.themesInterface.getTEXT());
                                                ((JLabel) component1).setText(list.get(number.get()));

                                                number.getAndIncrement();
                                            }
                                        });
                                    System.out.println(component);
                                });

                                colorPicker.getColorPanel().addChangeListener(e1 -> {
                                    color[0] = colorPicker.getColor();
                                    block.setBackground(color[0]);
                                    clickedBlock.editTag(tag.getName(), color[0]);
                                    clickedBlock.update();
                                });

                                popupMenu.add(colorPicker);
                                colorPicker.setBounds(0, 0, popupMenu.getWidth(), popupMenu.getHeight());

                                popupMenu.show(subPanel, e.getX(), e.getY());
                            }
                        });

                        this.subPanel.add(block);
                        block.setBounds(rectangle);
                    }

                    case "Boolean" -> {
                        JCheckBox checkBox = new JCheckBox();
                        checkBox.setSelected((boolean) tag.getValue());
                        checkBox.setBackground(null);
                        checkBox.setBorder(null);

                        checkBox.addActionListener(e -> {
                            JCheckBox checkBox1 = (JCheckBox) e.getSource();
                            this.clickedBlock.editTag(tag.getName(), checkBox1.isSelected());
                            this.clickedBlock.update();
                        });

                        this.subPanel.add(checkBox);
                        checkBox.setBounds(rectangle);
                    }

                    case "Coordinates" -> {
                        JLabel xLabel = new JLabel("x : ");
                        xLabel.setBackground(null);
                        xLabel.setForeground(Theme.USER.themesInterface.getTEXT());

                        subPanel.add(xLabel);
                        xLabel.setBounds(rectangle.x, rectangle.y, (int) (rectangle.x * 0.11), rectangle.height);

                        JTextField xTextField = new JTextField();
                        xTextField.setText(((Coordinates) tag.getValue()).getX() + "");
                        xTextField.setBackground(Theme.USER.themesInterface.getBACKGROUND());
                        xTextField.setForeground(Theme.USER.themesInterface.getTEXT());
                        xTextField.setFont(xTextField.getFont().deriveFont(16F));
                        xTextField.setBorder(null);

                        Runnable xRunnable = () -> {
                            while (xTextField.hasFocus() && clickedBlock != null) {
                                if (!xTextField.getText().equals("")) {
                                    clickedBlock.editTag(tag.getName(), new Coordinates(Integer.parseInt(xTextField.getText()), ((Coordinates) tag.getValue()).getY()));
                                    clickedBlock.update();
                                }
                            }
                        };

                        xTextField.addFocusListener(new FocusAdapter() {
                            @Override
                            public void focusGained(FocusEvent e) {
                                new Thread(xRunnable).start();
                            }
                        });

                        UiUtils.numberOnly(xTextField, false, false);

                        subPanel.add(xTextField);
                        xTextField.setBounds((int) (rectangle.x + rectangle.x * 0.11), rectangle.y, (int) (rectangle.x * 0.30), rectangle.height);

                        JLabel yLabel = new JLabel("y : ");
                        yLabel.setBackground(null);
                        yLabel.setForeground(Theme.USER.themesInterface.getTEXT());

                        subPanel.add(yLabel);
                        yLabel.setBounds((int) (rectangle.x + rectangle.x * 0.46), rectangle.y, (int) (rectangle.x * 0.11), rectangle.height);

                        JTextField yTextField = new JTextField();
                        yTextField.setText(((Coordinates) tag.getValue()).getY() + "");
                        yTextField.setBackground(Theme.USER.themesInterface.getBACKGROUND());
                        yTextField.setForeground(Theme.USER.themesInterface.getTEXT());
                        yTextField.setFont(yTextField.getFont().deriveFont(16F));
                        yTextField.setBorder(null);

                        Runnable yRunnable = () -> {
                            while (yTextField.hasFocus() && clickedBlock != null) {
                                if (!yTextField.getText().equals("")) {
                                    clickedBlock.editTag(tag.getName(), new Coordinates(((Coordinates) tag.getValue()).getX(), Integer.parseInt(yTextField.getText())));
                                    clickedBlock.update();
                                }
                            }
                        };

                        yTextField.addFocusListener(new FocusAdapter() {
                            @Override
                            public void focusGained(FocusEvent e) {
                                new Thread(yRunnable).start();
                            }
                        });

                        UiUtils.numberOnly(xTextField, false, false);

                        subPanel.add(yTextField);
                        yTextField.setBounds((int) (rectangle.x + rectangle.x * 0.57), rectangle.y, (int) (rectangle.x * 0.30), rectangle.height);

                        updateCoordinatesListener = new UpdateCoordinatesListener(xTextField, yTextField);
                        EventsRegisters.addListener(updateCoordinatesListener);
                    }

                    case "Dimension" -> {
                        JLabel xLabel = new JLabel("w : ");
                        xLabel.setBackground(null);
                        xLabel.setForeground(Theme.USER.themesInterface.getTEXT());

                        subPanel.add(xLabel);
                        xLabel.setBounds(rectangle.x, rectangle.y, (int) (rectangle.x * 0.11), rectangle.height);

                        JTextField xTextField = new JTextField();
                        xTextField.setText(((Dimension) tag.getValue()).getWidth() + "");
                        xTextField.setBackground(Theme.USER.themesInterface.getBACKGROUND());
                        xTextField.setForeground(Theme.USER.themesInterface.getTEXT());
                        xTextField.setFont(xTextField.getFont().deriveFont(16F));
                        xTextField.setBorder(null);

                        Runnable xRunnable = () -> {
                            while (xTextField.hasFocus() && clickedBlock != null) {
                                if (!xTextField.getText().equals("")) {
                                    clickedBlock.editTag(tag.getName(), new Dimension((int) Double.parseDouble(xTextField.getText()), (int) ((Dimension) tag.getValue()).getHeight()));
                                    clickedBlock.update();
                                }
                            }
                        };

                        xTextField.addFocusListener(new FocusAdapter() {
                            @Override
                            public void focusGained(FocusEvent e) {
                                new Thread(xRunnable).start();
                            }
                        });


                        UiUtils.numberOnly(xTextField, true, false);

                        subPanel.add(xTextField);
                        xTextField.setBounds((int) (rectangle.x + rectangle.x * 0.11), rectangle.y, (int) (rectangle.x * 0.30), rectangle.height);

                        JLabel yLabel = new JLabel("h : ");
                        yLabel.setBackground(null);
                        yLabel.setForeground(Theme.USER.themesInterface.getTEXT());

                        subPanel.add(yLabel);
                        yLabel.setBounds((int) (rectangle.x + rectangle.x * 0.46), rectangle.y, (int) (rectangle.x * 0.11), rectangle.height);

                        JTextField yTextField = new JTextField();
                        yTextField.setText(((Dimension) tag.getValue()).getHeight() + "");
                        yTextField.setBackground(Theme.USER.themesInterface.getBACKGROUND());
                        yTextField.setForeground(Theme.USER.themesInterface.getTEXT());
                        yTextField.setFont(yTextField.getFont().deriveFont(16F));
                        yTextField.setBorder(null);

                        Runnable yRunnable = () -> {
                            while (yTextField.hasFocus() && clickedBlock != null) {
                                if (!yTextField.getText().equals("")) {
                                    clickedBlock.editTag(tag.getName(), new Dimension((int) ((Dimension) tag.getValue()).getWidth(), (int) Double.parseDouble(yTextField.getText())));
                                    clickedBlock.update();
                                }
                            }
                        };

                        yTextField.addFocusListener(new FocusAdapter() {
                            @Override
                            public void focusGained(FocusEvent e) {
                                new Thread(yRunnable).start();
                            }
                        });

                        UiUtils.numberOnly(yTextField, true, false);

                        subPanel.add(yTextField);
                        yTextField.setBounds((int) (rectangle.x + rectangle.x * 0.57), rectangle.y, (int) (rectangle.x * 0.30), rectangle.height);
                    }

                    case "Integer" -> {
                        JTextField textField = new JTextField();
                        textField.setText(tag.getValue() + "");
                        textField.setBackground(Theme.USER.themesInterface.getBACKGROUND());
                        textField.setForeground(Theme.USER.themesInterface.getTEXT());
                        textField.setFont(textField.getFont().deriveFont(16F));
                        textField.setBorder(null);

                        UiUtils.numberOnly(textField, false, false);

                        Runnable runnable = () -> {
                            while (textField.hasFocus() && clickedBlock != null) {
                                if (!textField.getText().equals("")) {
                                    clickedBlock.editTag(tag.getName(), Integer.parseInt(textField.getText()));
                                    clickedBlock.update();
                                }
                            }
                        };

                        textField.addFocusListener(new FocusAdapter() {
                            @Override
                            public void focusGained(FocusEvent e) {
                                new Thread(runnable).start();
                            }
                        });

                        subPanel.add(textField);
                        textField.setBounds(rectangle);
                    }
                }
            }
        } else if (this.clickedBlock == null
                && this.alreadyPaint) {
            this.subPanel.removeAll();
            this.alreadyPaint = false;
            updateCoordinatesListener = null;
        }
    }
}
