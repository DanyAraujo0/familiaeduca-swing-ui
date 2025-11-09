package br.com.familiaeduca.ui.view.login;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JPanel mainPanel;
    private JTabbedPane tabs;

    public LoginFrame() {
        setContentPane(mainPanel); // já é suficiente

        setTitle("Família Educa - Portal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);
        setLocationRelativeTo(null);

        // Exemplo: adicionando a aba de login
        tabs.add("Login", new LoginPanel(this));

        // Exemplo: depois adicionaremos a aba de cadastro
        // tabs.add("Cadastro", new CadastroUsuarioPanel());

        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            com.formdev.flatlaf.FlatLightLaf.setup();
        } catch(Exception e) {
            e.printStackTrace();
        }

        javax.swing.SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}
