package br.com.familiaeduca.ui.dto;

public class DisciplinaDto {
    private String id;
    private String nome;

    public String getId() { return id; }
    public String getNome() { return nome; }

    @Override
    public String toString() { return nome; }
}