package com.notepad.view.components;

import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import com.notepad.model.ConstantesSystem;
import com.notepad.model.properties.NotepadProperties;

public class MenuFuente extends JComponent {

    private Editor editor;

    private final JComboBox<String> fontFamily = new JComboBox<>();
    private final JComboBox<String> fontStyle = new JComboBox<>();
    private final JComboBox<Integer> fontSize = new JComboBox<>();

    public MenuFuente() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setVisible(NotepadProperties.BooleanProperty(ConstantesSystem.MENU_FONT));

        fontFamily.setModel(new DefaultComboBoxModel<String>(
                GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()));

        fontStyle.addItem("Regular");
        fontStyle.addItem("Bold");
        fontStyle.addItem("Italic");
        fontStyle.addItem("Bold Italic");

        for (int i = 2; i < 40; i += 2)
            fontSize.addItem(i);

        for (int i = 0; i < fontFamily.getItemCount(); i++) {
            if (fontFamily.getItemAt(i).equals(NotepadProperties.StringProperty(ConstantesSystem.FONT_FAMILY))) {
                fontFamily.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < fontSize.getItemCount(); i++) {
            if (fontSize.getItemAt(i) == NotepadProperties.IntegerProperty(ConstantesSystem.FONT_SIZE)) {
                fontSize.setSelectedIndex(i);
                break;
            }
        }

        fontStyle.setSelectedIndex(NotepadProperties.IntegerProperty(ConstantesSystem.FONT_STYLE));

        fontFamily.addActionListener(new ControlFuente());
        fontSize.addActionListener(new ControlFuente());
        fontStyle.addActionListener(new ControlFuente());

        add(fontFamily);
        add(fontStyle);
        add(fontSize);
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    private class ControlFuente implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == fontSize) {
                editor.setFontSize((int) fontSize.getSelectedItem());
            }
            if (e.getSource() == fontFamily) {
                editor.setFontFamily((String) fontFamily.getSelectedItem());
            }
            if (e.getSource() == fontStyle) {
                editor.setFontStyle(fontStyle.getSelectedIndex());
            }
        }
    }
}
