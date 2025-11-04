package br.com.familiaeduca.ui.dto;

public class TokenDto {
    private String token;
    // ... outros campos que a API retornar (ex: "tipo", "expiracao")

    // Criar getters e setters

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
