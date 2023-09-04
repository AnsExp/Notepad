package com.notepad.view.components.dialogs;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.notepad.model.ConstantesArchivos;
import com.notepad.model.ConstantesLang;
import com.notepad.model.ConstantesSystem;
import com.notepad.model.properties.NotepadLang;
import com.notepad.model.properties.NotepadProperties;
import com.notepad.view.components.Observable;
import com.notepad.view.components.Observador;

public class DialogoConfiguracion extends JDialog implements Observable {

    private final JLabel lang = new JLabel(NotepadLang.getLang(ConstantesLang.LANGUAGE));
    private final OpcionMultiple langOptions = new OpcionMultiple();
    private final JPanel langPane = new JPanel(new GridLayout(1, 2));

    private final JLabel theme = new JLabel(NotepadLang.getLang(ConstantesLang.LOOK_AND_FEEL));
    private final OpcionMultiple themeOptions = new OpcionMultiple();
    private final JPanel themePane = new JPanel(new GridLayout(1, 2));

    public DialogoConfiguracion(Frame frame) {
        super(frame);
        initComponents(frame);
    }

    private void initComponents(Frame frame) {
        setTitle(NotepadLang.getLang(ConstantesLang.SETTINGS));
        setLayout(new GridLayout(2, 1));
        setSize(300, 300);
        setLocationRelativeTo(frame);
        setResizable(false);
        Observador.addComponent(this);

        langPane.add(lang);
        langPane.add(langOptions);

        themePane.add(theme);
        themePane.add(themeOptions);

        lang.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        theme.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        lang.setHorizontalAlignment(JLabel.RIGHT);
        theme.setHorizontalAlignment(JLabel.RIGHT);

        langOptions.addOption(new Opcion("Español", ConstantesSystem.LANG, ConstantesArchivos.FILE_ES));
        langOptions.addOption(new Opcion("English", ConstantesSystem.LANG, ConstantesArchivos.FILE_EN));
        langOptions.addOption(new Opcion("Portugës", ConstantesSystem.LANG, ConstantesArchivos.FILE_PR));
        langOptions.addOption(new Opcion("\u65E5\u672C\u8A9E", ConstantesSystem.LANG, ConstantesArchivos.FILE_JP));

        themeOptions.addOption(new Opcion("Light", ConstantesSystem.THEME, ConstantesArchivos.FILE_LIGHT));
        themeOptions.addOption(new Opcion("Dark", ConstantesSystem.THEME, ConstantesArchivos.FILE_DARK));
        themeOptions.addOption(new Opcion("Light Mac", ConstantesSystem.THEME, ConstantesArchivos.FILE_LIGHT_MAC));
        themeOptions.addOption(new Opcion("Dark Mac", ConstantesSystem.THEME, ConstantesArchivos.FILE_DARK_MAC));

        langOptions.setOptionSelected(NotepadProperties.StringProperty(ConstantesSystem.LANG));
        themeOptions.setOptionSelected(NotepadProperties.StringProperty(ConstantesSystem.THEME));

        add(langPane);
        add(themePane);
    }

    @Override
    public void update() {
        Observador.updateTheme(this);
        setTitle(NotepadLang.getLang(ConstantesLang.SETTINGS));
        lang.setText(NotepadLang.getLang(ConstantesLang.LANGUAGE));
        theme.setText(NotepadLang.getLang(ConstantesLang.LOOK_AND_FEEL));
    }

    private class OpcionMultiple extends JComponent {

        private final ButtonGroup group = new ButtonGroup();
        private final ArrayList<Opcion> ops = new ArrayList<>();

        private OpcionMultiple() {
            initComponents();
        }

        private void initComponents() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        }

        private void addOption(Opcion o) {
            add(o);
            ops.add(o);
            group.add(o);
            o.addActionListener(new ControlOpcion(o));
        }

        private void setOptionSelected(String url) {

            for (Opcion o : ops) {

                if (o.getUrl().equals(url)) {
                    o.setSelected(true);
                    break;
                }
            }
        }
    }

    private class Opcion extends JRadioButton {

        private String field;
        private String url;

        private Opcion(String text, String field, String url) {
            super(text);
            this.field = field;
            this.url = url;
        }

        private String getField() {
            return field;
        }

        private String getUrl() {
            return url;
        }
    }

    private class ControlOpcion implements ActionListener {

        private final Opcion o;

        public ControlOpcion(Opcion o) {
            this.o = o;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            NotepadProperties.setProperty(o.getField(), o.getUrl());
            if (o.getField().equals(ConstantesSystem.THEME)) {
                Observador.applyTheme(o.getUrl());
            } else {
                NotepadLang.loadLang();
            }
            Observador.updateComponents();
        }
    }
}
