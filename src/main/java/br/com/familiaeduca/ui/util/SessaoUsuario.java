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

    // Renomeei de 'setSessao' para 'iniciarSessao' para ficar igual ao LoginController
    public void iniciarSessao(UsuarioDto usuario, TokenDto tokenDto) {
        this.usuarioLogado = usuario;
        this.token = tokenDto;
    }

    public UsuarioDto getUsuarioLogado() { return usuarioLogado; }

    // Corrigido para usar .token() porque TokenDto é um Record
    public String getToken() { return token != null ? token.token() : null; }

    // --- Novos métodos de verificação baseados no UsuarioDto ---
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
        this.token = null;
    }
}