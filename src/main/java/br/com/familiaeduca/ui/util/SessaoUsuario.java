package br.com.familiaeduca.ui.util;

import br.com.familiaeduca.ui.dto.TokenDto;
import br.com.familiaeduca.ui.dto.UsuarioDto;

public class SessaoUsuario {
    private static SessaoUsuario instancia;
    private UsuarioDto usuarioLogado;
    private TokenDto token;

    private SessaoUsuario() {}

    public static SessaoUsuario getInstance() {
        if (instancia == null) {
            instancia = new SessaoUsuario();
        }
        return instancia;
    }

    public void setSessao(UsuarioDto usuario, TokenDto token) {
        this.usuarioLogado = usuario;
        this.token = token;
    }

    public UsuarioDto getUsuarioLogado() { return usuarioLogado; }
    public String getToken() { return token != null ? token.getToken() : null; }

    public boolean isAluno() {
        return usuarioLogado != null && "Aluno".equalsIgnoreCase(usuarioLogado.getTipo());
    }

    public void logout() {
        this.usuarioLogado = null;
        this.token = null;
    }
}