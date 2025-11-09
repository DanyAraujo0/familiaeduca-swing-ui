package br.com.familiaeduca.ui.dto;

import java.time.LocalDate;
import java.util.UUID;

public class FrequenciaDto {
    private Integer matricula;
    private LocalDate data;
    private boolean presente;
    private UUID turmaId;

    public FrequenciaDto(Integer matricula, LocalDate data, boolean presente, UUID turmaId) {
        this.matricula = matricula;
        this.data = data;
        this.presente = presente;
        this.turmaId = turmaId;
    }

    public Integer getMatricula() { return matricula; }
    public void setMatricula(Integer matricula) { this.matricula = matricula; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public boolean isPresente() { return presente; }
    public void setPresente(boolean presente) { this.presente = presente; }

    public UUID getTurmaId() { return turmaId; }
    public void setTurmaId(UUID turmaId) { this.turmaId = turmaId; }
}
