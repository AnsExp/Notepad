package com.notepad.view.components;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

public class Contenedor extends JScrollPane implements Observable {

    public Contenedor() {
        initComponents();
    }

    private void initComponents() {
        setBackground(null);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        Observador.addComponent(this);
    }

    @Override
    public void update() {
        setBackground(null);
    }
}
