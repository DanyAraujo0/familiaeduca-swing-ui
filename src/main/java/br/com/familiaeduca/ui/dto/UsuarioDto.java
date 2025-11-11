package br.com.familiaeduca.ui.dto;

import com.google.gson.annotations.SerializedName;

public class UsuarioDto {
    private String id; // Mudei para String pois seu JSON usa UUIDs como strings
    private String nome;
    private String email;
    private String telefone;

    // O Gson vai ler "funcao" do JSON e jogar dentro da variável "perfil"
    @SerializedName("funcao")
    private String perfil;

    // Getters
    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getPerfil() { return perfil; }

    // Métodos auxiliares (mantidos iguais)
    public boolean isProfessor() { return "Professor".equalsIgnoreCase(perfil); }
    // Note que no seu JSON está "Diretor" (singular), ajustei aqui:
    public boolean isDiretor() { return "Diretor".equalsIgnoreCase(perfil); }
    public boolean isResponsavel() { return "Responsavel".equalsIgnoreCase(perfil); }

    @Override
    public String toString() {
        return nome + " (" + perfil + ")";
    }
}