package com.notepad.controler;

import java.awt.event.ActionEvent;

import com.notepad.model.ConstantesLang;
import com.notepad.model.ConstantesSystem;
import com.notepad.model.properties.NotepadProperties;

public class ControlVista extends ControlAbstracto {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.get(ConstantesLang.ALWAYS_TOP)) {

            ventana.setAlwaysOnTop(menu.get(ConstantesLang.ALWAYS_TOP).isSelected());

            NotepadProperties.setProperty(ConstantesSystem.ALWAYS_TOP,
                    menu.get(ConstantesLang.ALWAYS_TOP).isSelected());

        } else if (e.getSource() == menu.get(ConstantesLang.LINE_WRAP)) {

            editor.setLineWrap(menu.get(ConstantesLang.LINE_WRAP).isSelected());

            NotepadProperties.setProperty(ConstantesSystem.LINE_WRAP,
                    menu.get(ConstantesLang.LINE_WRAP).isSelected());

        } else if (e.getSource() == menu.get(ConstantesLang.MENU_FONT)) {

            fuente.setVisible(menu.get(ConstantesLang.MENU_FONT).isSelected());

            NotepadProperties.setProperty(ConstantesSystem.MENU_FONT,
                    menu.get(ConstantesLang.MENU_FONT).isSelected());
        }
    }
}
