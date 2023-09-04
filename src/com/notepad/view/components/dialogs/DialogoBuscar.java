package com.notepad.view.components.dialogs;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

import com.notepad.model.ConstantesLang;
import com.notepad.model.properties.NotepadLang;
import com.notepad.view.components.Boton;
import com.notepad.view.components.Editor;
import com.notepad.view.components.Observable;
import com.notepad.view.components.Observador;

public class DialogoBuscar extends JDialog implements Observable {

    private Editor editor;
    private final JTextField field = new JTextField();
    private final JPanel pane = new JPanel(new GridLayout(1, 2));
    private final Boton pos = new Boton(NotepadLang.getLang(ConstantesLang.SEARCH_POS));
    private final Boton prev = new Boton(NotepadLang.getLang(ConstantesLang.SEARCH_PREV));

    public DialogoBuscar(Frame frame) {
        super(frame);
        initComponents(frame);
    }

    private void initComponents(Frame frame) {
        setTitle(NotepadLang.getLang(ConstantesLang.SEARCH));
        setLayout(new GridLayout(2, 1));
        setSize(350, 140);
        setLocationRelativeTo(frame);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Observador.addComponent(this);
        field.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        add(field);
        add(pane);

        pane.add(prev);
        pane.add(pos);

        pos.addActionListener(new ControlBusqueda("pos"));
        prev.addActionListener(new ControlBusqueda("prev"));
        pos.setID(ConstantesLang.SEARCH_POS);
        prev.setID(ConstantesLang.SEARCH_PREV);
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void update() {
        Observador.updateTheme(this);
        setTitle(NotepadLang.getLang(ConstantesLang.SEARCH));
        prev.setText(NotepadLang.getLang(ConstantesLang.SEARCH_PREV));
        pos.setText(NotepadLang.getLang(ConstantesLang.SEARCH_POS));
    }

    private class ControlBusqueda implements ActionListener {

        private final String orientation;
        private int offs;
        private int len;

        public ControlBusqueda(String orientation) {
            this.orientation = orientation;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (field.getText().isEmpty())
                return;

            if (field.getText().length() > editor.getText().length())
                return;

            len = field.getText().length();
            offs = editor.getSelectedText() == null ? editor.getCaretPosition() : editor.getSelectionStart();

            if (offs + len > editor.getText().length())
                return;

            String result;

            while (offs >= 0 && offs + len <= editor.getText().length()) {

                if (orientation.equals("pos"))
                    offs++;
                else if (orientation.equals("prev"))
                    offs--;

                try {

                    result = editor.getText(offs, len);

                    if (result.equals(field.getText())) {

                        editor.setSelectionStart(offs);
                        editor.setSelectionEnd(offs + len);
                        break;
                    }
                } catch (BadLocationException ex) {
                    ex.printStackTrace(System.out);
                }
            }
        }
    }
}
