package br.com.familiaeduca.ui.controller;

import br.com.familiaeduca.ui.dto.UsuarioDto;
import br.com.familiaeduca.ui.service.FamiliaEducaApiClient;
import br.com.familiaeduca.ui.util.SessaoUsuario;
import br.com.familiaeduca.ui.view.login.LoginPanel;
import br.com.familiaeduca.ui.view.sistema.SistemaFrame;

import javax.swing.*;

public class LoginController {

    private final LoginPanel view;
    private final JFrame ownerFrame;
    private final FamiliaEducaApiClient apiClient;

    public LoginController(LoginPanel view, JFrame ownerFrame) {
        this.view = view;
        this.ownerFrame = ownerFrame;
        this.apiClient = new FamiliaEducaApiClient();
    }
    public void realizarLogin() {
        try {
            String email = view.getUsuario();
            String senha = view.getSenha();

            if (email.isEmpty() || senha.isEmpty()) {
                view.exibirMensagem("Preencha todos os campos.");
                return;
            }

            // backend já retorna UsuarioResponse
            UsuarioDto usuarioDto = apiClient.fazerLogin(email, senha);

            // salva na sessão
            SessaoUsuario.getInstance().iniciarSessao(usuarioDto);

            SwingUtilities.invokeLater(() -> {
                SistemaFrame frame = new SistemaFrame();
                frame.setVisible(true);
                if (ownerFrame != null) ownerFrame.dispose();
            });

        } catch (Exception e) {
            e.printStackTrace();
            view.exibirMensagem("Email ou senha incorretos.");
        }
    }
}