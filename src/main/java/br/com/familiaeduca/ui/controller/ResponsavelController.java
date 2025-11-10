package br.com.familiaeduca.ui.controller;

import br.com.familiaeduca.ui.dto.ResponsavelDto;
import br.com.familiaeduca.ui.dto.UsuarioDto;
import br.com.familiaeduca.ui.service.FamiliaEducaApiClient;
import br.com.familiaeduca.ui.util.SessaoUsuario;
import br.com.familiaeduca.ui.view.usuario.CadastroResponsavelPanel;

import javax.swing.*;

public class ResponsavelController {

    private final CadastroResponsavelPanel view;
    private final FamiliaEducaApiClient apiClient;
    private final SessaoUsuario sessao;

    public ResponsavelController(CadastroResponsavelPanel view) {
        this.view = view;
        this.apiClient = new FamiliaEducaApiClient();
        this.sessao = SessaoUsuario.getInstance();
    }

    public void cadastrar() {
        try {
            // Cria DTO com dados da tela
            ResponsavelDto dto = new ResponsavelDto();
            dto.setNome(view.getNome());
            dto.setEmail(view.getEmail());
            dto.setSenha(view.getSenha());
            dto.setTelefone(view.getTelefone());
            dto.setEndereco(view.getEndereco());

            // Pega o token da sessão
            String token = sessao.getToken();

            if (token == null) {
                JOptionPane.showMessageDialog(null,
                        "Sessão expirada. Faça login novamente.",
                        "Erro de autenticação",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Envia para API
            UsuarioDto usuario = apiClient.cadastrarResponsavel(dto, token);

            JOptionPane.showMessageDialog(null,
                    "Responsável cadastrado com sucesso: " + usuario.getNome(),
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Erro ao cadastrar responsável: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}