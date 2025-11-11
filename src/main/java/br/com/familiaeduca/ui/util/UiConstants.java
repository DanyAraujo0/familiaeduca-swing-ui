package br.com.familiaeduca.ui.util;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class UiConstants {

    public static final Color BLUE = new Color(0x2568CC);
    public static final Color GRAY_BG = new Color(245, 248, 250);
    public static final Font FONT_REGULAR = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONT_BOLD = new Font("Segoe UI", Font.BOLD, 14);

    // --- Botões ---
    public static void styleButton(JButton b) {
        b.setBackground(BLUE);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.putClientProperty("JButton.buttonType", "roundRect");
    }

    public static void styleButtonSecondary(JButton b) {
        b.setBackground(new Color(230, 230, 230));
        b.setForeground(Color.BLACK);
        b.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    // --- Tabelas (JTable) ---
    public static void styleTable(JTable table) {
        table.setRowHeight(30);
        table.setFont(FONT_REGULAR);
        table.setSelectionBackground(new Color(200, 220, 255));
        table.setSelectionForeground(Color.BLACK);
        table.setGridColor(new Color(230, 230, 230));
        table.setShowVerticalLines(false);

        // Cabeçalho da tabela
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(Color.WHITE);
        header.setForeground(BLUE);
        header.setPreferredSize(new Dimension(0, 35));
    }

    // --- Campos de Texto e Combos ---
    public static void styleField(JComponent field) {
        field.setFont(FONT_REGULAR);
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, 35));
        field.putClientProperty("JComponent.roundRect", true);
    }
}