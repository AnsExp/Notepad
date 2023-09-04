package com.notepad.view.components.menu;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import com.notepad.model.ConstantesLang;
import com.notepad.model.properties.NotepadLang;
import com.notepad.view.components.Observador;

public class MenuArchivo extends MenuAbstracto {

    @Override
    public void update() {
        setText(NotepadLang.getLang(ConstantesLang.FILE));
        menu.keySet().forEach(x -> menu.get(x).setText(NotepadLang.getLang(x)));
    }

    @Override
    protected void initComponents() {
        setText(NotepadLang.getLang(ConstantesLang.FILE));

        menu.put(ConstantesLang.NEW, new JMenuItem(NotepadLang.getLang(ConstantesLang.NEW)));
        menu.put(ConstantesLang.EXIT, new JMenuItem(NotepadLang.getLang(ConstantesLang.EXIT)));
        menu.put(ConstantesLang.OPEN, new JMenuItem(NotepadLang.getLang(ConstantesLang.OPEN)));
        menu.put(ConstantesLang.SAVE, new JMenuItem(NotepadLang.getLang(ConstantesLang.SAVE)));
        menu.put(ConstantesLang.SAVE_AS, new JMenuItem(NotepadLang.getLang(ConstantesLang.SAVE_AS)));
        menu.put(ConstantesLang.SETTINGS, new JMenuItem(NotepadLang.getLang(ConstantesLang.SETTINGS)));
        menu.put(ConstantesLang.NEW_WINDOW, new JMenuItem(NotepadLang.getLang(ConstantesLang.NEW_WINDOW)));

        Observador.addComponent(this);
    }

    @Override
    protected void initPositions() {
        add(menu.get(ConstantesLang.NEW));
        add(menu.get(ConstantesLang.NEW_WINDOW));
        add(new JSeparator());
        add(menu.get(ConstantesLang.OPEN));
        add(menu.get(ConstantesLang.SAVE));
        add(menu.get(ConstantesLang.SAVE_AS));
        add(new JSeparator());
        add(menu.get(ConstantesLang.SETTINGS));
        add(menu.get(ConstantesLang.EXIT));
    }

    @Override
    protected void initAcelerators() {
        menu.get(ConstantesLang.NEW).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        menu.get(ConstantesLang.OPEN).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        menu.get(ConstantesLang.SAVE).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        menu.get(ConstantesLang.EXIT).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
        menu.get(ConstantesLang.SAVE_AS).setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
        menu.get(ConstantesLang.NEW_WINDOW).setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
    }
}
