package com.notepad.view.components;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import com.notepad.model.properties.NotepadLang;

public class Boton extends JButton implements Observable {

    private String ID;

    public Boton(String text) {
        super(text);
        initComponents();
    }

    private void initComponents() {
        setBackground(null);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(100, 50));
        setBorder(BorderFactory.createEmptyBorder());
        Observador.addComponent(this);
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public void update() {
        setText(NotepadLang.getLang(ID));
        setBackground(null);
    }
}
