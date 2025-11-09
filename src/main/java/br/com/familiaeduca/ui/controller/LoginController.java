package br.com.familiaeduca.ui.controller;

import br.com.familiaeduca.ui.dto.TokenDto;
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
            String usuario = view.getUsuario();
            String senha = view.getSenha();

            if (usuario.isEmpty() || senha.isEmpty()) {
                view.exibirMensagem("Por favor, preencha todos os campos antes de continuar.");
                return;
            }

            // 1. Chama a API para obter o Token
            TokenDto tokenDto = apiClient.fazerLogin(usuario, senha);

            // 2. Usa o Token para obter os dados do utilizador
            UsuarioDto usuarioDto = apiClient.getMeuPerfil(tokenDto.token());

            // 3. Salva na sessão global
            SessaoUsuario.getInstance().iniciarSessao(usuarioDto, tokenDto);

            // 4. Abre a tela principal e fecha a de login
            SwingUtilities.invokeLater(() -> {
                SistemaFrame sistemaFrame = new SistemaFrame();
                sistemaFrame.setVisible(true);

                if (ownerFrame != null) {
                    ownerFrame.dispose();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            String msgErro = "Erro ao conectar. Verifique se o servidor está a correr.";
            if (e.getMessage() != null && (e.getMessage().contains("403") || e.getMessage().contains("401"))) {
                msgErro = "Email ou senha incorretos.";
            }
            view.exibirMensagem(msgErro);
        }
    }
}