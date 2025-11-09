package br.com.familiaeduca.ui;

import br.com.familiaeduca.ui.view.login.LoginFrame;
import br.com.familiaeduca.ui.util.UiTheme;
import com.formdev.flatlaf.FlatLightLaf; // Importe o FlatLaf

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // 1. ATIVAR O LOOK AND FEEL (TEMA)
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Falha ao inicializar o FlatLaf Look and Feel");
        }
        // Aplica temas globais
        UiTheme.setup();

        // A primeira tela a aparecer deve ser o LoginFrame
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}