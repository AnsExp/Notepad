package com.notepad.model;

import com.notepad.view.components.Editor;
import com.notepad.view.components.Ventana;

public final class IOEditor {

    private IOEditor() {
    }

    public static void newDocument(Ventana ventana, Editor editor) {

        if (editor.isChange()) {

            int i = IODocumento.confirmSave(ventana);

            switch (i) {
                case 0:
                    IODocumento.save(ventana, editor);
                    editor.setChange(false);
                    break;
                case 1:
                    resetDocument(editor);
            }

            return;
        }
        resetDocument(editor);
    }

    public static void saveDocument(Ventana ventana, Editor editor) {

        if (editor.getFile() == null) {
            saveAsDocument(ventana, editor);
            return;
        }

        IOTexto.write(editor);
        editor.setChange(false);
    }

    public static void saveAsDocument(Ventana ventana, Editor editor) {
        if (IODocumento.save(ventana, editor)) {
            IOTexto.write(editor);
            editor.setChange(false);
        }
    }

    public static void openDocument(Ventana ventana, Editor editor) {

        if (editor.isChange()) {
            int i = IODocumento.confirmSave(ventana);

            switch (i) {
                case 0:
                    saveDocument(ventana, editor);
                    break;
                case 1:
                    if (IODocumento.open(ventana, editor)) {
                        IOTexto.read(editor);
                        editor.setChange(false);
                    }
                    break;
            }

            return;
        }

        if (IODocumento.open(ventana, editor)) {
            IOTexto.read(editor);
            editor.setChange(false);
        }
    }

    private static void resetDocument(Editor editor) {
        editor.setText(null);
        editor.setFile(null);
        editor.setChange(false);
    }
}
