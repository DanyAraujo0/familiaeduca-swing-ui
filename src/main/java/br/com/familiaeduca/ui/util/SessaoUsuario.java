package br.com.familiaeduca.ui.util;

import br.com.familiaeduca.ui.dto.UsuarioDto;

public class SessaoUsuario {
    private static SessaoUsuario instancia;
    private UsuarioDto usuarioLogado;
    private String senhaUsuario;

    private SessaoUsuario() {}

    public static SessaoUsuario getInstance() {
        if (instancia == null) {
            instancia = new SessaoUsuario();
        }
        return instancia;
    }

    public void iniciarSessao(UsuarioDto usuario, String senha) {
        this.usuarioLogado = usuario;
        this.senhaUsuario = senha;
    }

    public UsuarioDto getUsuarioLogado() {
        return usuarioLogado;
    }

    public String getSenhaUsuario() { return senhaUsuario; }

    // Como não existe token no backend, este método pode sempre retornar null:
    public String getToken() {
        return null;
    }

    public boolean isProfessor() {
        return usuarioLogado != null && usuarioLogado.isProfessor();
    }
    public boolean isDiretor() {
        return usuarioLogado != null && usuarioLogado.isDiretor();
    }
    public boolean isResponsavel() {
        return usuarioLogado != null && usuarioLogado.isResponsavel();
    }

    public void logout() {
        this.usuarioLogado = null;
        this.senhaUsuario = null;
    }
}