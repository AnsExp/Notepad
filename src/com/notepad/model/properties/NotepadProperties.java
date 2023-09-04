package com.notepad.model.properties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.notepad.model.ConstantesSystem;

public final class NotepadProperties {

    private static final Properties PROPERTIES = new Properties();

    static {
        try {
            PROPERTIES.load(new FileInputStream(ConstantesSystem.URL_PROP));
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private NotepadProperties() {
    }

    public static String StringProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static Integer IntegerProperty(String key) {
        return Integer.valueOf(PROPERTIES.getProperty(key));
    }

    public static Boolean BooleanProperty(String key) {
        return Boolean.valueOf(PROPERTIES.getProperty(key));
    }

    public static void setProperty(String key, String value) {
        PROPERTIES.setProperty(key, value);
    }

    public static void setProperty(String key, Integer value) {
        PROPERTIES.setProperty(key, String.valueOf(value));
    }

    public static void setProperty(String key, Boolean value) {
        PROPERTIES.setProperty(key, String.valueOf(value));
    }

    public static void storeProperties() {
        try {
            PROPERTIES.store(new FileOutputStream(ConstantesSystem.URL_PROP), "File Update");
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
