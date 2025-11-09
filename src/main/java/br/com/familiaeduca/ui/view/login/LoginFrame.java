package br.com.familiaeduca.ui.view.login;

import javax.swing.*;
import java.awt.*;
import br.com.familiaeduca.ui.view.login.LoginPanel;
import br.com.familiaeduca.ui.view.login.CadastroDiretorPanel;

public class LoginFrame extends JFrame {

    private JPanel currentPanel;

    public LoginFrame() {
        setTitle("Família Educa - Acesso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        mostrarLoginPanel();
    }

    // Mostra o painel de login
    public void mostrarLoginPanel() {
        trocarPainel(new LoginPanel(this));
    }

    // Mostra o painel de cadastro de diretor
    public void mostrarCadastroDiretorPanel() {
        trocarPainel(new CadastroDiretorPanel(this));
    }

    private void trocarPainel(JPanel novoPainel) {
        if (currentPanel != null) remove(currentPanel);
        currentPanel = novoPainel;
        add(currentPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
/* // Testando individualmente
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }*/
}
