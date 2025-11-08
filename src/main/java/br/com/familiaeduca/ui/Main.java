package br.com.familiaeduca.ui;

import br.com.familiaeduca.ui.view.login.LoginFrame;
import com.formdev.flatlaf.FlatLightLaf; // Importe o FlatLaf
// import com.formdev.flatlaf.FlatDarkLaf; // Se preferirem o tema escuro

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // 1. ATIVAR O LOOK AND FEEL (TEMA)
        try {
            // Opção 1: Tema Claro (FlatLightLaf) - Recomendado para começar
            UIManager.setLookAndFeel(new FlatLightLaf());

            // Opção 2: Tema Escuro (FlatDarkLaf) - Muito popular
            // UIManager.setLookAndFeel(new FlatDarkLaf());

            // Opção 3: Tema IntelliJ (semelhante ao IDE)
            // UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatIntelliJLaf());

        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Falha ao inicializar o FlatLaf Look and Feel");
        }

        // 2. INICIAR A APLICAÇÃO
        // A primeira tela a aparecer deve ser o LoginFrame
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}