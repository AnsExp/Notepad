package com.notepad.view.components;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.notepad.model.ConstantesSystem;
import com.notepad.model.properties.NotepadProperties;

public class Ventana extends JFrame implements Observable {

    private static int ID;

    public Ventana() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setTitle(ConstantesSystem.NAME);
        setIconImage(new ImageIcon(ConstantesSystem.ICON).getImage());
        setAlwaysOnTop(NotepadProperties.BooleanProperty(ConstantesSystem.ALWAYS_TOP));
        setSize(NotepadProperties.IntegerProperty(ConstantesSystem.SIZE_X),
                NotepadProperties.IntegerProperty(ConstantesSystem.SIZE_Y));
        setLocation(NotepadProperties.IntegerProperty(ConstantesSystem.LOCATION_X),
                NotepadProperties.IntegerProperty(ConstantesSystem.LOCATION_Y));
        addWindowListener(new ControlVentana());
        Observador.addComponent(this);
    }

    private class ControlVentana implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {
            ID++;
        }

        @Override
        public void windowClosing(WindowEvent e) {
            if (ID == 1) {
                NotepadProperties.setProperty(ConstantesSystem.SIZE_X, getSize().width);
                NotepadProperties.setProperty(ConstantesSystem.SIZE_Y, getSize().height);
                NotepadProperties.setProperty(ConstantesSystem.LOCATION_X, getLocation().x);
                NotepadProperties.setProperty(ConstantesSystem.LOCATION_Y, getLocation().y);
                NotepadProperties.setProperty(ConstantesSystem.ALWAYS_TOP, isAlwaysOnTop());
                NotepadProperties.storeProperties();
            }
            setDefaultCloseOperation(ID == 1 ? JFrame.EXIT_ON_CLOSE : JFrame.DISPOSE_ON_CLOSE);
            ID--;
        }

        @Override
        public void windowClosed(WindowEvent e) {
        }

        @Override
        public void windowIconified(WindowEvent e) {
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
        }

        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
        }
    }

    @Override
    public void update() {
        Observador.updateTheme(this);
    }
}
