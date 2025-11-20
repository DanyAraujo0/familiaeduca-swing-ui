package br.com.familiaeduca.ui.dto;

import java.time.LocalDate;
import java.util.UUID;

public class FrequenciaDto {
    private LocalDate data;
    private Boolean presente;
    private Integer matriculaAluno;
    private UUID idTurma;
    private UUID idProfessor;

    public FrequenciaDto(LocalDate data, Boolean presente, Integer matriculaAluno, UUID idTurma, UUID idProfessor) {
        this.data = data;
        this.presente = presente;
        this.matriculaAluno = matriculaAluno;
        this.idTurma = idTurma;
        this.idProfessor = idProfessor;
    }

    // getters e setters

}
