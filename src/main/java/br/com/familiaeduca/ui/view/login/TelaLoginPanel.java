package br.com.familiaeduca.ui.view.login;

import br.com.familiaeduca.ui.controller.LoginController;
import br.com.familiaeduca.ui.util.UiConstants;
import javax.swing.*;

public class TelaLoginPanel extends JPanel {
    // Pessoas B/C: Criem esta classe como "GUI Form (JPanel)"
    // Adicionem os componentes no GUI Designer e deem estes nomes:
    private JTextField txtUsuario;
    private JPasswordField pwdSenha;
    private JButton btnEntrar;

    private LoginController controller;

    public TelaLoginPanel(LoginFrame ownerFrame) {
        initComponents(); // Gerado pelo IntelliJ

        // Liga esta View ao Controller
        this.controller = new LoginController(this, ownerFrame);

        // Delega a ação do botão ao controller
        btnEntrar.addActionListener(e -> controller.realizarLogin());

        // Aplica o estilo
        UiConstants.styleButton(btnEntrar);
    }

    // Métodos "Get" para o Controller ler os dados
    public String getUsuario() { return txtUsuario.getText().trim(); }
    public String getSenha() { return new String(pwdSenha.getPassword()).trim(); }

    // Método para o Controller mostrar feedback
    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    // ... (initComponents() gerado pelo IntelliJ) ...
}
