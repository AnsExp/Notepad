package com.notepad.controler;

import java.awt.event.ActionEvent;

import com.notepad.model.ConstantesLang;

public class ControlEdicion extends ControlAbstracto {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.get(ConstantesLang.CUT))
            editor.cut();
        else if (e.getSource() == menu.get(ConstantesLang.COPY))
            editor.copy();
        else if (e.getSource() == menu.get(ConstantesLang.PASTE))
            editor.paste();
        else if (e.getSource() == menu.get(ConstantesLang.DELETE))
            editor.replaceSelection(null);
        else if (e.getSource() == menu.get(ConstantesLang.SEARCH))
            buscar.setVisible(true);
        else if (e.getSource() == menu.get(ConstantesLang.SEARCH_WEB))
            searchWeb();
        else if (e.getSource() == menu.get(ConstantesLang.SEARCH_REPLACE))
            buscarReemplazar.setVisible(true);
    }
}
