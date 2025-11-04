package br.com.familiaeduca.ui.controller;

import br.com.familiaeduca.ui.dto.LoginDto;
import br.com.familiaeduca.ui.dto.TokenDto;
import br.com.familiaeduca.ui.dto.UsuarioDto;
import br.com.familiaeduca.ui.service.FamiliaEducaApiClient;
import br.com.familiaeduca.ui.util.SessaoUsuario;
import br.com.familiaeduca.ui.view.login.LoginFrame;
import br.com.familiaeduca.ui.view.login.LoginPanel;
import br.com.familiaeduca.ui.view.sistema.SistemaFrame; // A nossa próxima janela

public class LoginController {

    private final LoginPanel view;
    private final LoginFrame ownerFrame; // O JFrame que contém o painel
    private final FamiliaEducaApiClient apiClient;

    public LoginController(LoginPanel view, LoginFrame ownerFrame) {
        this.view = view;
        this.ownerFrame = ownerFrame;
        this.apiClient = new FamiliaEducaApiClient(); // Instancia o serviço
    }

    public void realizarLogin() {
        String usuario = view.getUsuario();
        String senha = view.getSenha();

        if (usuario.isEmpty() || senha.isEmpty()) {
            view.exibirMensagem("Preencha usuário e senha.");
            return;
        }

        try {
            LoginDto loginDto = new LoginDto(usuario, senha);

            // [PESSOA A] O apiClient.fazerLogin deve ser implementado
            TokenDto token = apiClient.fazerLogin(loginDto);

            if (token != null) {
                // Login OK, agora buscar os dados do utilizador
                // [PESSOA A] O apiClient.getMeuPerfil deve ser implementado
                UsuarioDto usuarioLogado = apiClient.getMeuPerfil(token.getToken());

                if (usuarioLogado != null) {
                    // 1. Guardar a sessão global
                    SessaoUsuario.getInstance().setSessao(usuarioLogado, token);

                    // 2. Abrir a nova janela do sistema
                    new SistemaFrame().setVisible(true);

                    // 3. Fechar a janela de login
                    ownerFrame.dispose();
                } else {
                    view.exibirMensagem("Erro ao obter perfil do utilizador.");
                }
            } else {
                view.exibirMensagem("Usuário ou senha inválidos.");
            }
        } catch (Exception e) {
            // Tratar exceções da API (ex: 500 Internal Server Error)
            view.exibirMensagem("Erro de comunicação com o servidor: " + e.getMessage());
        }
    }
}
