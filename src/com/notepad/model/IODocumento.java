package com.notepad.model;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.notepad.model.properties.NotepadLang;
import com.notepad.view.components.Editor;
import com.notepad.view.components.Ventana;

public final class IODocumento {

    private static final JFileChooser fileChooser = new JFileChooser();

    private IODocumento() {
    }

    static {
        fileChooser.setFileFilter(new FileNameExtensionFilter("text(.txt)", "txt"));
    }

    public static int confirmSave(Ventana ventana) {
        return JOptionPane.showOptionDialog(
                ventana,
                NotepadLang.getLang(ConstantesLang.SAVE_TEXT),
                NotepadLang.getLang(ConstantesLang.SAVE_TITLE),
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[] {
                        NotepadLang.getLang(ConstantesLang.YES),
                        NotepadLang.getLang(ConstantesLang.NO),
                        NotepadLang.getLang(ConstantesLang.CANCEL) },
                0);
    }

    public static boolean save(Ventana ventana, Editor editor) {
        if (fileChooser.showSaveDialog(ventana) == JFileChooser.APPROVE_OPTION) {

            if (!fileChooser.getSelectedFile().getAbsolutePath().endsWith(".txt"))
                fileChooser.setSelectedFile(new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt"));

            editor.setFile(fileChooser.getSelectedFile());
            return true;
        }
        return false;
    }

    public static boolean open(Ventana ventana, Editor editor) {
        if (fileChooser.showOpenDialog(ventana) == JFileChooser.APPROVE_OPTION) {

            editor.setFile(fileChooser.getSelectedFile());
            return true;
        }
        return false;
    }
}
