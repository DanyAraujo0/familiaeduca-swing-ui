package br.com.familiaeduca.ui.util;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class UiTheme {

    public static void setup() {
        try {
            FlatLightLaf.setup();

            // Fontes padrão
            Font defaultFont = new Font("Segoe UI", Font.PLAIN, 13);
            UIManager.put("defaultFont", defaultFont);

            // Aplicar cores e bordas suaves (pode ajustar conforme seu gosto)
            UIManager.put("Button.arc", 12);
            UIManager.put("Component.arc", 10);
            UIManager.put("TextComponent.arc", 8);
            UIManager.put("Button.font", new javax.swing.plaf.FontUIResource(defaultFont));
            UIManager.put("Label.font", new javax.swing.plaf.FontUIResource(defaultFont));
            UIManager.put("Component.focusWidth", 1);

            // Integra com UiConstants para aplicar a cor principal (azul do seu sistema)
            UIManager.put("Button.background", UiConstants.BLUE);
            UIManager.put("Button.foreground", Color.WHITE);
            UIManager.put("TabbedPane.selectedBackground", UiConstants.BLUE);
            UIManager.put("TabbedPane.selectedForeground", Color.WHITE);
            UIManager.put("TabbedPane.foreground", new Color(60, 60, 60));
            UIManager.put("TabbedPane.background", Color.WHITE);

        } catch (Exception e) {
            System.err.println("Erro ao aplicar tema: " + e.getMessage());
        }
    }
}
