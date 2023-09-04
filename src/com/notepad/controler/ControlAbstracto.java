package com.notepad.controler;

import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JMenuItem;

import com.notepad.view.components.Editor;
import com.notepad.view.components.MenuFuente;
import com.notepad.view.components.Ventana;

public abstract class ControlAbstracto implements ActionListener {

    protected Editor editor;
    protected JDialog buscar;
    protected JDialog configuracion;
    protected JDialog buscarReemplazar;
    protected Ventana ventana;
    protected MenuFuente fuente;
    protected HashMap<String, JMenuItem> menu;

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public void setVentana(Ventana ventana) {
        this.ventana = ventana;
    }

    public void setMenuFuente(MenuFuente fuente) {
        this.fuente = fuente;
    }

    public void setBuscar(JDialog buscar) {
        this.buscar = buscar;
    }

    public void setConfiguracion(JDialog configuracion) {
        this.configuracion = configuracion;
    }

    public void setBuscarReemplazar(JDialog buscarReemplazar) {
        this.buscarReemplazar = buscarReemplazar;
    }

    public void setMenu(HashMap<String, JMenuItem> menu) {
        this.menu = menu;
    }

    protected void searchWeb() {

        String text = editor.getSelectedText();

        if (text == null)
            return;

        try {

            String encodedText = java.net.URLEncoder.encode(text, "UTF-8");
            String searchUrl = "https://www.google.com/search?q=" + encodedText;

            if (Desktop.isDesktopSupported()
                    && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
                Desktop.getDesktop().browse(new URI(searchUrl));

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace(System.out);
        }
    }
}
