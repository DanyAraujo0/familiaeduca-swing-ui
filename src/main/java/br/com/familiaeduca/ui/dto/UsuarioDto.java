package br.com.familiaeduca.ui.dto;

public class UsuarioDto {
    private Long id;
    private String nome;
    private String username;
    private String tipo; // Ex: "Aluno", "Colaborador", "Admin"

    // Criar get e set
    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
}
