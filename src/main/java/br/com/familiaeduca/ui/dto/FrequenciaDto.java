package br.com.familiaeduca.ui.dto;

import java.time.LocalDate;
import java.util.UUID;

public class FrequenciaDto {
    private UUID id;
    private LocalDate data;
    private boolean presente;
    // O backend provavelmente retorna o ID e/ou o Nome do aluno/turma
    private UUID alunoId;
    private String alunoNome; // Muito útil para mostrar na tabela!
    private UUID turmaId;
    private String turmaNome;

    // Construtor vazio (necessário para o Gson/Jackson)
    public FrequenciaDto() {}

    // Construtor completo (útil para testes ou criação manual)
    public FrequenciaDto(UUID id, LocalDate data, boolean presente, UUID alunoId, String alunoNome, UUID turmaId, String turmaNome) {
        this.id = id;
        this.data = data;
        this.presente = presente;
        this.alunoId = alunoId;
        this.alunoNome = alunoNome;
        this.turmaId = turmaId;
        this.turmaNome = turmaNome;
    }

    // Getters e Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public boolean isPresente() { return presente; }
    public void setPresente(boolean presente) { this.presente = presente; }
    public UUID getAlunoId() { return alunoId; }
    public void setAlunoId(UUID alunoId) { this.alunoId = alunoId; }
    public String getAlunoNome() { return alunoNome; }
    public void setAlunoNome(String alunoNome) { this.alunoNome = alunoNome; }
    public UUID getTurmaId() { return turmaId; }
    public void setTurmaId(UUID turmaId) { this.turmaId = turmaId; }
    public String getTurmaNome() { return turmaNome; }
    public void setTurmaNome(String turmaNome) { this.turmaNome = turmaNome; }
}