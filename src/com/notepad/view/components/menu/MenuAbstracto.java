package com.notepad.view.components.menu;

import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.notepad.view.components.Observable;

public abstract class MenuAbstracto extends JMenu implements Observable {

    protected final HashMap<String, JMenuItem> menu = new HashMap<>();

    protected MenuAbstracto() {
        initComponents();
        initPositions();
        initAcelerators();
    }

    public HashMap<String, JMenuItem> getMenu() {
        return menu;
    }

    public void initListeners(ActionListener l) {
        menu.values().forEach(x -> x.addActionListener(l));
    }

    protected abstract void initComponents();

    protected abstract void initPositions();

    protected abstract void initAcelerators();
}
