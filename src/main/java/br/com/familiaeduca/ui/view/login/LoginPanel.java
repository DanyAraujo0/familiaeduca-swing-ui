package br.com.familiaeduca.ui.view.login;

import br.com.familiaeduca.ui.controller.LoginController;
import br.com.familiaeduca.ui.util.UiConstants;
import javax.swing.*;

public class LoginPanel extends JPanel {
    private JPanel mainPanel;
    private JTextField txtUsuario;
    private JPasswordField pwdSenha;
    private JButton btnEntrar;

    private LoginController controller;

    public LoginPanel(LoginFrame ownerFrame) {
        this.controller = new LoginController(this);

        // Adiciona o conteúdo principal do .form dentro deste JPanel
        setLayout(new java.awt.BorderLayout());
        add(mainPanel, java.awt.BorderLayout.CENTER);

        btnEntrar.addActionListener(e -> controller.realizarLogin());
        UiConstants.styleButton(btnEntrar);
    }

    public String getUsuario() { return txtUsuario.getText().trim(); }
    public String getSenha() { return new String(pwdSenha.getPassword()).trim(); }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
}
