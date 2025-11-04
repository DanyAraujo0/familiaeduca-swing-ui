package br.com.familiaeduca.ui.util;

import javax.swing.*;
import java.awt.*;

public class UiConstants {

    public static final Color BLUE = new Color(0x00, 0x33, 0x66);

    public static void styleButton(JButton b) {
        b.setBackground(BLUE);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Arial", Font.PLAIN, 12));
        b.setFocusPainted(false);
    }
}
