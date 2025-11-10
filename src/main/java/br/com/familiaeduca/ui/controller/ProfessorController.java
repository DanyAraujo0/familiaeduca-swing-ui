package br.com.familiaeduca.ui.controller;

import br.com.familiaeduca.ui.dto.ProfessorDto;
import br.com.familiaeduca.ui.dto.UsuarioDto;
import br.com.familiaeduca.ui.service.FamiliaEducaApiClient;
import br.com.familiaeduca.ui.util.SessaoUsuario;
import br.com.familiaeduca.ui.view.usuario.CadastroProfessorPanel;

import javax.swing.*;

public class ProfessorController {

    private final CadastroProfessorPanel view;
    private final FamiliaEducaApiClient apiClient;
    private final SessaoUsuario sessao;

    public ProfessorController(CadastroProfessorPanel view) {
        this.view = view;
        this.apiClient = new FamiliaEducaApiClient();
        this.sessao = SessaoUsuario.getInstance();
    }

    public void cadastrar() {
        try {
            // Monta o DTO com os dados da tela
            ProfessorDto dto = new ProfessorDto();
            dto.setNome(view.getNome());
            dto.setEmail(view.getEmail());
            dto.setSenha(view.getSenha());
            dto.setTelefone(view.getTelefone());

            // Recupera o token da sessão atual
            String token = sessao.getToken();

            if (token == null) {
                JOptionPane.showMessageDialog(null,
                        "Sessão expirada. Faça login novamente.",
                        "Erro de autenticação",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Envia a requisição para a API
            UsuarioDto usuario = apiClient.cadastrarProfessor(dto, token);

            JOptionPane.showMessageDialog(null,
                    "Professor cadastrado com sucesso: " + usuario.getNome(),
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Erro ao cadastrar professor: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
