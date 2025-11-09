package br.com.familiaeduca.ui.util;

import javax.swing.*;
import java.awt.*;

public class UiConstants {

    public static final Color BLUE = new Color(0x00, 0x33, 0x66);

    public static void styleButton(JButton b) {
        b.setBackground(BLUE);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
