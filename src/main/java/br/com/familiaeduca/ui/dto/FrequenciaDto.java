package br.com.familiaeduca.ui.dto;

import java.time.LocalDate;
import java.util.UUID;

public class FrequenciaDto {
    private LocalDate data;
    private Boolean presente;
    private Integer matriculaAluno;
    private UUID idTurma;
    private UUID idProfessor;

    // getters e setters
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public Boolean getPresente() { return presente; }
    public void setPresente(Boolean presente) { this.presente = presente; }

    public Integer getMatriculaAluno() { return matriculaAluno; }
    public void setMatriculaAluno(Integer matriculaAluno) { this.matriculaAluno = matriculaAluno; }

    public UUID getIdTurma() { return idTurma; }
    public void setIdTurma(UUID idTurma) { this.idTurma = idTurma; }

    public UUID getIdProfessor() { return idProfessor; }
    public void setIdProfessor(UUID idProfessor) { this.idProfessor = idProfessor; }
}
