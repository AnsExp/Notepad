package com.notepad.view.components;

import java.awt.Component;
import java.util.HashSet;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public final class Observador {

    private static final HashSet<Observable> obs = new HashSet<>();

    private Observador() {
    }

    public static void addComponent(Observable obs) {
        Observador.obs.add(obs);
    }

    public static void updateComponents() {
        obs.forEach(x -> x.update());
    }

    public static void applyTheme(String theme) {
        try {
            UIManager.setLookAndFeel(theme);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void updateTheme(Component comp) {
        SwingUtilities.updateComponentTreeUI(comp);
    }
}
