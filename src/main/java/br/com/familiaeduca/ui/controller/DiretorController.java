package br.com.familiaeduca.ui.controller;

import br.com.familiaeduca.ui.dto.DiretorDto;
import br.com.familiaeduca.ui.dto.UsuarioDto;
import br.com.familiaeduca.ui.service.FamiliaEducaApiClient;
import br.com.familiaeduca.ui.view.usuario.CadastroDiretorPanel;

import javax.swing.*;

public class DiretorController {

    private final CadastroDiretorPanel view;
    private final FamiliaEducaApiClient apiClient;

    public DiretorController(CadastroDiretorPanel view) {
        this.view = view;
        this.apiClient = new FamiliaEducaApiClient();
    }

    public void cadastrar() {
        // LIMPA ERROS ANTES DE VALIDAR
        view.limparErros();

        String nome = view.getNome();
        String email = view.getEmail();
        String senha = view.getSenha();
        String telefone = view.getTelefone();
        // Remove tudo que não for número
        String telefoneNumerico = telefone.replaceAll("\\D", "");

        boolean temErro = false;

        // ==========================
        // VALIDAÇÕES
        // ==========================

        if (nome.isEmpty()) {
            view.setErroNome("O nome é obrigatório.");
            temErro = true;
        } else if (nome.length() < 5) {
            view.setErroNome("O nome deve ter pelo menos 5 caracteres.");
            temErro = true;
        }

        if (email.isEmpty()) {
            view.setErroEmail("O e-mail é obrigatório.");
            temErro = true;
        } else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            view.setErroEmail("O e-mail deve ser válido.");
            temErro = true;
        }

        if (senha.isEmpty()) {
            view.setErroSenha("A senha é obrigatória.");
            temErro = true;
        } else if (senha.length() < 8 || !senha.matches(".*[A-Za-z].*") || !senha.matches(".*\\d.*")) {
            view.setErroSenha("A senha deve ter pelo menos 8 caracteres, contendo letras e números.");
            temErro = true;
        }

        if (telefoneNumerico.isEmpty()) {
            view.setErroTelefone("O telefone é obrigatório.");
            temErro = true;
        } else if (!telefoneNumerico.matches("^\\d{10,11}$")) {
            view.setErroTelefone("O telefone deve ter 10 ou 11 dígitos.");
            temErro = true;
        }

        // SE ALGUM CAMPO ESTÁ INVALIDO → NÃO CHAMA A API
        if (temErro) return;

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
