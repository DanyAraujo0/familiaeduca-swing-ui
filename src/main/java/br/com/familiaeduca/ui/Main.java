package br.com.familiaeduca.ui;

import br.com.familiaeduca.ui.view.login.LoginFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Inicia a primeira janela (o Frame de Login)
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}