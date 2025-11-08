package br.com.familiaeduca.ui.controller;

import br.com.familiaeduca.ui.util.SessaoUsuario;
import br.com.familiaeduca.ui.view.sistema.CadastroUsuariosPanel;

public class CadastroUsuariosController {

    private final CadastroUsuariosPanel view;
    private final SessaoUsuario sessao;

    public CadastroUsuariosController(CadastroUsuariosPanel view) {
        this.view = view;
        this.sessao = SessaoUsuario.getInstance();
    }

    public void inicializar() {
        configurarPermissoes();
        configurarAcoes();
    }

    private void configurarPermissoes() {
        boolean isDiretor = sessao.isDiretor();

        // SE for Diretor -> Vê tudo (true)
        // SE NÃO for Diretor (é Professor) -> Não vê (false)
        view.getBtnCadastrarDiretor().setVisible(isDiretor);
        view.getBtnCadastrarProfessor().setVisible(isDiretor);

        // Responsável todos (Diretor e Professor) podem ver
        view.getBtnCadastrarResponsavel().setVisible(true);
    }

    private void configurarAcoes() {
        view.getBtnCadastrarDiretor().addActionListener(e -> abrirTelaCadastro("DIRETOR"));
        view.getBtnCadastrarProfessor().addActionListener(e -> abrirTelaCadastro("PROFESSOR"));
        view.getBtnCadastrarResponsavel().addActionListener(e -> abrirTelaCadastro("RESPONSAVEL"));
    }

    private void abrirTelaCadastro(String tipoUsuario) {
        // Lógica para abrir um JDialog ou mudar de tela para o formulário de cadastro
        System.out.println("Abrindo cadastro para: " + tipoUsuario);
        // Exemplo: new FormularioUsuarioDialog(tipoUsuario).setVisible(true);
    }
}