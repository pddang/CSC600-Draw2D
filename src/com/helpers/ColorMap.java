package com.helpers;

import com.UI.Main;
import javafx.scene.paint.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ColorMap {
    public static Map<String, Color> getJavaFXColorMap() {
        Field[] declareFields = Color.class.getDeclaredFields();
        Map<String, Color> colors = new HashMap<>();
        for (Field field : declareFields) {
            if (Modifier.isStatic(field.getModifiers()) && Modifier.isPublic(field.getModifiers())) {
                try {
                    colors.put(field.getName(), (Color) field.get(null));

                } catch (SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return colors;
    }
}