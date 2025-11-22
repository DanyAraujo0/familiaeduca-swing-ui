package br.com.familiaeduca.ui.controllers;

import br.com.familiaeduca.ui.util.SessaoUsuario;
import br.com.familiaeduca.ui.view.usuario.CadastroUsuariosPanel;
import br.com.familiaeduca.ui.view.usuario.CadastroFrame;
import br.com.familiaeduca.ui.view.usuario.CadastroResponsavelPanel;
import br.com.familiaeduca.ui.view.usuario.CadastroProfessorPanel;

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
        view.getBtnCadastrarProfessor().setVisible(isDiretor);
        view.getBtnCadastrarResponsavel().setVisible(true);
    }

    private void configurarAcoes() {
        view.getBtnCadastrarProfessor().addActionListener(e -> abrirCadastro("PROFESSOR"));
        view.getBtnCadastrarResponsavel().addActionListener(e -> abrirCadastro("RESPONSAVEL"));
    }

    private void abrirCadastro(String tipo) {
        switch (tipo) {
            case "RESPONSAVEL":
                new CadastroFrame(new CadastroResponsavelPanel(null)).setVisible(true);
                break;
            case "PROFESSOR":
                new CadastroFrame(new CadastroProfessorPanel(null)).setVisible(true);
                break;
        }
    }
}
