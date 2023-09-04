package com.notepad.view.components.menu;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.KeyStroke;

import com.notepad.model.ConstantesLang;
import com.notepad.model.properties.NotepadLang;
import com.notepad.model.properties.NotepadProperties;
import com.notepad.view.components.Observador;

public class MenuVista extends MenuAbstracto {

    @Override
    public void update() {
        setText(NotepadLang.getLang(ConstantesLang.VIEW));
        menu.keySet().forEach(x -> menu.get(x).setText(NotepadLang.getLang(x)));
    }

    @Override
    protected void initComponents() {
        setText(NotepadLang.getLang(ConstantesLang.VIEW));

        menu.put(ConstantesLang.LINE_WRAP, new JCheckBoxMenuItem(NotepadLang.getLang(ConstantesLang.LINE_WRAP)));
        menu.put(ConstantesLang.MENU_FONT, new JCheckBoxMenuItem(NotepadLang.getLang(ConstantesLang.MENU_FONT)));
        menu.put(ConstantesLang.ALWAYS_TOP, new JCheckBoxMenuItem(NotepadLang.getLang(ConstantesLang.ALWAYS_TOP)));

        menu.get(ConstantesLang.LINE_WRAP).setSelected(NotepadProperties.BooleanProperty(ConstantesLang.LINE_WRAP));
        menu.get(ConstantesLang.MENU_FONT).setSelected(NotepadProperties.BooleanProperty(ConstantesLang.MENU_FONT));
        menu.get(ConstantesLang.ALWAYS_TOP).setSelected(NotepadProperties.BooleanProperty(ConstantesLang.ALWAYS_TOP));

        Observador.addComponent(this);
    }

    @Override
    protected void initPositions() {
        add(menu.get(ConstantesLang.LINE_WRAP));
        add(menu.get(ConstantesLang.ALWAYS_TOP));
        add(menu.get(ConstantesLang.MENU_FONT));
    }

    @Override
    protected void initAcelerators() {
        menu.get(ConstantesLang.LINE_WRAP)
                .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK));
        menu.get(ConstantesLang.ALWAYS_TOP)
                .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
        menu.get(ConstantesLang.MENU_FONT)
                .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
    }
}
