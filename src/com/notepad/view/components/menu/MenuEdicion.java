package com.notepad.view.components.menu;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import com.notepad.model.ConstantesLang;
import com.notepad.model.properties.NotepadLang;
import com.notepad.view.components.Observador;

public class MenuEdicion extends MenuAbstracto {

    @Override
    public void update() {
        setText(NotepadLang.getLang(ConstantesLang.EDIT));
        menu.keySet().forEach(x -> menu.get(x).setText(NotepadLang.getLang(x)));
    }

    @Override
    protected void initComponents() {
        setText(NotepadLang.getLang(ConstantesLang.EDIT));

        menu.put(ConstantesLang.CUT, new JMenuItem(NotepadLang.getLang(ConstantesLang.CUT)));
        menu.put(ConstantesLang.COPY, new JMenuItem(NotepadLang.getLang(ConstantesLang.COPY)));
        menu.put(ConstantesLang.PASTE, new JMenuItem(NotepadLang.getLang(ConstantesLang.PASTE)));
        menu.put(ConstantesLang.DELETE, new JMenuItem(NotepadLang.getLang(ConstantesLang.DELETE)));
        menu.put(ConstantesLang.SEARCH, new JMenuItem(NotepadLang.getLang(ConstantesLang.SEARCH)));
        menu.put(ConstantesLang.SEARCH_WEB, new JMenuItem(NotepadLang.getLang(ConstantesLang.SEARCH_WEB)));
        menu.put(ConstantesLang.SEARCH_REPLACE, new JMenuItem(NotepadLang.getLang(ConstantesLang.SEARCH_REPLACE)));

        Observador.addComponent(this);
    }

    @Override
    protected void initPositions() {

        add(menu.get(ConstantesLang.COPY));
        add(menu.get(ConstantesLang.CUT));
        add(menu.get(ConstantesLang.PASTE));
        add(menu.get(ConstantesLang.DELETE));
        add(new JSeparator());
        add(menu.get(ConstantesLang.SEARCH));
        add(menu.get(ConstantesLang.SEARCH_REPLACE));
        add(menu.get(ConstantesLang.SEARCH_WEB));
    }

    @Override
    protected void initAcelerators() {
        menu.get(ConstantesLang.SEARCH).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
        menu.get(ConstantesLang.DELETE).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        menu.get(ConstantesLang.CUT).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        menu.get(ConstantesLang.COPY).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        menu.get(ConstantesLang.PASTE).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        menu.get(ConstantesLang.SEARCH_WEB)
                .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, InputEvent.CTRL_DOWN_MASK));
        menu.get(ConstantesLang.SEARCH_REPLACE)
                .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, InputEvent.ALT_DOWN_MASK));
    }
}
