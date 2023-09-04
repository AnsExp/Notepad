package com.notepad.model.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import com.notepad.model.ConstantesSystem;

public final class NotepadLang {

    private static final Properties LANG = new Properties();

    private NotepadLang() {
    }

    static {
        loadLang();
    }

    public static String getLang(String key) {
        return LANG.getProperty(key);
    }

    public static void loadLang() {

        LANG.clear();

        try {
            LANG.load(
                    new InputStreamReader(
                            new FileInputStream(
                                    NotepadProperties.StringProperty(ConstantesSystem.LANG)),
                            StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
