package br.com.familiaeduca.ui.controller;

import br.com.familiaeduca.ui.dto.DiretorDto;
import br.com.familiaeduca.ui.dto.UsuarioDto;
import br.com.familiaeduca.ui.service.FamiliaEducaApiClient;
import br.com.familiaeduca.ui.view.login.CadastroDiretorPanel;

import javax.swing.*;

public class CadastroDiretorController {

    private final CadastroDiretorPanel view;
    private final FamiliaEducaApiClient apiClient;

    public CadastroDiretorController(CadastroDiretorPanel view) {
        this.view = view;
        this.apiClient = new FamiliaEducaApiClient();
    }

    public void cadastrar() {
        try {
            DiretorDto dto = new DiretorDto();
            dto.setNome(view.getNome());
            dto.setEmail(view.getEmail());
            dto.setSenha(view.getSenha());
            dto.setTelefone(view.getTelefone());

            UsuarioDto usuario = apiClient.cadastrarDiretor(dto);
            JOptionPane.showMessageDialog(null,
                    "Diretor cadastrado com sucesso: " + usuario.getNome(),
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Erro ao cadastrar diretor: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
