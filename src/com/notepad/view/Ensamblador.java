package com.notepad.view;

import java.awt.BorderLayout;

import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.notepad.controler.ControlAbstracto;
import com.notepad.controler.ControlArchivo;
import com.notepad.controler.ControlEdicion;
import com.notepad.controler.ControlVista;
import com.notepad.model.ConstantesSystem;
import com.notepad.model.properties.NotepadProperties;
import com.notepad.view.components.Contenedor;
import com.notepad.view.components.Editor;
import com.notepad.view.components.MenuFuente;
import com.notepad.view.components.Ventana;
import com.notepad.view.components.dialogs.DialogoBuscar;
import com.notepad.view.components.dialogs.DialogoBuscarReemplazar;
import com.notepad.view.components.dialogs.DialogoConfiguracion;
import com.notepad.view.components.menu.MenuAbstracto;
import com.notepad.view.components.menu.MenuArchivo;
import com.notepad.view.components.menu.MenuEdicion;
import com.notepad.view.components.menu.MenuVista;

public class Ensamblador implements Runnable {

    private final Editor editor = new Editor();
    private final JMenuBar menu = new JMenuBar();
    private final MenuFuente font = new MenuFuente();
    private final Ventana ventana = new Ventana();
    private final Contenedor contenedor = new Contenedor();

    private final MenuAbstracto menuVista = new MenuVista();
    private final MenuAbstracto menuArchivo = new MenuArchivo();
    private final MenuAbstracto menuEdicion = new MenuEdicion();

    private final ControlAbstracto controlVista = new ControlVista();
    private final ControlAbstracto controlArchivo = new ControlArchivo();
    private final ControlAbstracto controlEdicion = new ControlEdicion();

    private final DialogoBuscar dialogoBuscar = new DialogoBuscar(ventana);
    private final DialogoConfiguracion dialogoConfiguracion = new DialogoConfiguracion(ventana);
    private final DialogoBuscarReemplazar dialogoBuscarReeemplazar = new DialogoBuscarReemplazar(ventana);

    static {
        try {
            UIManager.setLookAndFeel(NotepadProperties.StringProperty(ConstantesSystem.THEME));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace(System.out);
        }
    }

    public Ensamblador() {
        initComponents();
        initListeners();
    }

    public Ensamblador(Ventana ventana) {
        this.ventana.setLocationRelativeTo(ventana);
        initComponents();
        initListeners();
    }

    private void initComponents() {
        ventana.add(font, BorderLayout.SOUTH);
        ventana.add(contenedor, BorderLayout.CENTER);
        contenedor.setViewportView(editor);
        ventana.setJMenuBar(menu);
        font.setEditor(editor);
        dialogoBuscar.setEditor(editor);
        dialogoBuscarReeemplazar.setEditor(editor);

        menu.add(menuArchivo);
        menu.add(menuEdicion);
        menu.add(menuVista);
    }

    private void initListeners() {
        controlVista.setMenuFuente(font);
        controlVista.setEditor(editor);
        controlArchivo.setEditor(editor);
        controlEdicion.setEditor(editor);
        controlVista.setVentana(ventana);
        controlArchivo.setVentana(ventana);
        controlEdicion.setVentana(ventana);
        controlEdicion.setBuscar(dialogoBuscar);
        controlArchivo.setConfiguracion(dialogoConfiguracion);
        controlEdicion.setBuscarReemplazar(dialogoBuscarReeemplazar);
        menuVista.initListeners(controlVista);
        menuArchivo.initListeners(controlArchivo);
        menuEdicion.initListeners(controlEdicion);
        controlVista.setMenu(menuVista.getMenu());
        controlArchivo.setMenu(menuArchivo.getMenu());
        controlEdicion.setMenu(menuEdicion.getMenu());
    }

    @Override
    public void run() {
        ventana.setVisible(true);
    }
}
