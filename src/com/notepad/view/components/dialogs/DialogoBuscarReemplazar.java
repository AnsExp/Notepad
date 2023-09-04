package com.notepad.view.components.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

import com.notepad.model.ConstantesLang;
import com.notepad.model.properties.NotepadLang;
import com.notepad.view.components.Boton;
import com.notepad.view.components.Editor;
import com.notepad.view.components.Observable;
import com.notepad.view.components.Observador;

public class DialogoBuscarReemplazar extends JDialog implements Observable {

    private Editor editor;
    private final JTextField textSearch = new JTextField();
    private final JTextField textReplace = new JTextField();
    private final JLabel search = new JLabel(NotepadLang.getLang(ConstantesLang.SEARCH));
    private final JLabel replace = new JLabel(NotepadLang.getLang(ConstantesLang.REPLACE));
    private final Boton replaceAll = new Boton(NotepadLang.getLang(ConstantesLang.SEARCH_REPLACE));
    private final JPanel paneSearch = new JPanel(new BorderLayout());
    private final JPanel paneReplace = new JPanel(new BorderLayout());

    public DialogoBuscarReemplazar(Frame frame) {
        super(frame);
        initComponents(frame);
    }

    private void initComponents(Frame frame) {
        setTitle(NotepadLang.getLang(ConstantesLang.SEARCH_REPLACE));
        setLayout(new GridLayout(3, 1, 5, 5));
        setSize(350, 200);
        setLocationRelativeTo(frame);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Observador.addComponent(this);

        add(paneSearch);
        add(paneReplace);
        add(replaceAll);

        paneSearch.add(search, BorderLayout.WEST);
        paneReplace.add(replace, BorderLayout.WEST);
        paneSearch.add(textSearch, BorderLayout.CENTER);
        paneReplace.add(textReplace, BorderLayout.CENTER);
        search.setPreferredSize(new Dimension(75, search.getHeight()));
        replace.setPreferredSize(new Dimension(75, replace.getHeight()));
        search.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
        replace.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));

        replaceAll.setID(ConstantesLang.SEARCH_REPLACE);
        replaceAll.addActionListener(new ControlDialogoBuscarReemplazar());
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void update() {
        Observador.updateTheme(this);
        setTitle(NotepadLang.getLang(ConstantesLang.SEARCH_REPLACE));
        search.setText(NotepadLang.getLang(ConstantesLang.SEARCH));
        replace.setText(NotepadLang.getLang(ConstantesLang.REPLACE));
        replaceAll.setText(NotepadLang.getLang(ConstantesLang.SEARCH_REPLACE));
    }

    private class ControlDialogoBuscarReemplazar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String search = textSearch.getText();
            String replace = textReplace.getText();

            int offs = 0;
            int lenSearch = search.length();
            int lenReplace = replace.length();

            while (offs + lenSearch <= editor.getText().length()) {

                try {

                    if (editor.getText(offs, lenSearch).equals(search)) {
                        editor.replaceRange(replace, offs, offs + lenSearch);
                        offs += lenReplace - 1;
                    }
                } catch (BadLocationException e1) {
                    e1.printStackTrace(System.out);
                }
                offs++;
            }
        }
    }
}
