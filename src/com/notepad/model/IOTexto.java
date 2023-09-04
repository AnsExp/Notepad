package com.notepad.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.notepad.view.components.Editor;

public final class IOTexto {

    private static FileReader fr;
    private static FileWriter fw;
    private static BufferedReader br;
    private static BufferedWriter bw;

    private IOTexto() {
    }

    public static void write(Editor editor) {
        try {
            fw = new FileWriter(editor.getFile());
            bw = new BufferedWriter(fw);
            bw.write(editor.getText());
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void read(Editor editor) {
        StringBuilder sb = new StringBuilder();
        String s;

        try {
            fr = new FileReader(editor.getFile());
            br = new BufferedReader(fr);
            while ((s = br.readLine()) != null)
                sb.append(s).append("\n");
            br.close();
            fr.close();
            editor.setText(sb.toString());
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
