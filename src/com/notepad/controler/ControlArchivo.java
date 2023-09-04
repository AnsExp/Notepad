package com.notepad.controler;

import java.awt.event.ActionEvent;

import com.notepad.model.ConstantesLang;
import com.notepad.model.IOEditor;
import com.notepad.view.Ensamblador;

public class ControlArchivo extends ControlAbstracto {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.get(ConstantesLang.NEW_WINDOW))
            new Ensamblador(ventana).run();
        else if (e.getSource() == menu.get(ConstantesLang.EXIT))
            ventana.dispose();
        else if (e.getSource() == menu.get(ConstantesLang.NEW))
            IOEditor.newDocument(ventana, editor);
        else if (e.getSource() == menu.get(ConstantesLang.OPEN))
            IOEditor.openDocument(ventana, editor);
        else if (e.getSource() == menu.get(ConstantesLang.SAVE))
            IOEditor.saveDocument(ventana, editor);
        else if (e.getSource() == menu.get(ConstantesLang.SAVE_AS))
            IOEditor.saveAsDocument(ventana, editor);
        else if (e.getSource() == menu.get(ConstantesLang.SETTINGS))
            configuracion.setVisible(true);
    }
}
