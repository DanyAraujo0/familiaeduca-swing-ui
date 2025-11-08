package br.com.familiaeduca.ui.view.login;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JPanel mainPanel; // Você PRECISA de um painel raiz no .form
    private JTabbedPane tabs;

    public LoginFrame() {
        // NÃO CHAME initComponents(); O IntelliJ faz isso sozinho.

        // É CRUCIAL dizer ao JFrame qual é o seu conteúdo principal.
        // O 'mainPanel' deve ser o painel raiz que você criou no GUI Designer.
        setContentPane(mainPanel);

        setTitle("Família Educa - Portal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);
        setLocationRelativeTo(null);

        // Agora sim, pode adicionar coisas às abas
        // (Certifique-se que LoginPanel existe e aceita 'this' no construtor)
        tabs.add("Login", new LoginPanel(this));

        setVisible(true);
    }

    // O IntelliJ pode pedir para criar este método se você escolher
    // a opção "Generate main method" nas configurações do GUI Designer,
    // mas por padrão, não precisa dele aqui.
}