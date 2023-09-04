package com.notepad.view.components;

import java.awt.Font;
import java.io.File;

import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.notepad.model.ConstantesSystem;
import com.notepad.model.properties.NotepadProperties;

public class Editor extends JTextArea {

    private File file;
    private boolean change;

    public Editor() {
        initComponents();
    }

    private void initComponents() {
        setBackground(null);
        setWrapStyleWord(true);
        getDocument().addDocumentListener(new ControlEditor());
        setLineWrap(NotepadProperties.BooleanProperty(ConstantesSystem.LINE_WRAP));
        setFont(new Font(
                NotepadProperties.StringProperty(ConstantesSystem.FONT_FAMILY),
                NotepadProperties.IntegerProperty(ConstantesSystem.FONT_STYLE),
                NotepadProperties.IntegerProperty(ConstantesSystem.FONT_SIZE)));
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public boolean isChange() {
        return change;
    }

    public void setChange(boolean change) {
        this.change = change;
    }

    public void setFontFamily(String fontFamyly) {
        NotepadProperties.setProperty(ConstantesSystem.FONT_FAMILY, fontFamyly);
        setFont(new Font(
                fontFamyly,
                NotepadProperties.IntegerProperty(ConstantesSystem.FONT_STYLE),
                NotepadProperties.IntegerProperty(ConstantesSystem.FONT_SIZE)));
    }

    public void setFontStyle(int fontStyle) {
        NotepadProperties.setProperty(ConstantesSystem.FONT_STYLE, fontStyle);
        setFont(new Font(
                NotepadProperties.StringProperty(ConstantesSystem.FONT_FAMILY),
                fontStyle,
                NotepadProperties.IntegerProperty(ConstantesSystem.FONT_SIZE)));
    }

    public void setFontSize(int fontSize) {
        NotepadProperties.setProperty(ConstantesSystem.FONT_SIZE, fontSize);
        setFont(new Font(
                NotepadProperties.StringProperty(ConstantesSystem.FONT_FAMILY),
                NotepadProperties.IntegerProperty(ConstantesSystem.FONT_STYLE),
                fontSize));
    }

    private class ControlEditor implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            change = true;
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            change = true;
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            change = true;
        }
    }
}
