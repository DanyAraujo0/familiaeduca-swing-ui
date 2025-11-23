package br.com.familiaeduca.ui.dto;

import com.google.gson.annotations.SerializedName;
import java.time.LocalDate;
import java.util.UUID;

public class ChecklistDto {

    // ==========================================
    // REQUEST (Para o Professor Criar)
    // ==========================================
    public static class CreateChecklistProfessorRequest {
        private LocalDate dataChecklist;
        private String observacoes;
        private Integer matriculaAluno;
        private UUID idProfessor;

        public CreateChecklistProfessorRequest(LocalDate dataChecklist, String observacoes, Integer matriculaAluno, UUID idProfessor) {
            this.dataChecklist = dataChecklist;
            this.observacoes = observacoes;
            this.matriculaAluno = matriculaAluno;
            this.idProfessor = idProfessor;
        }
    }

    // ==========================================
    // RESPONSE (Para Listar na Tabela)
    // ==========================================
    public static class ChecklistResponse {
        @SerializedName("id")
        private String id;

        @SerializedName("dataChecklist")
        private LocalDate dataChecklist;

        @SerializedName("observacoes")
        private String observacoes;

        // Se o backend retornar o objeto aluno, precisamos mapear
        @SerializedName("aluno")
        private AlunoDto.AlunoResponse aluno;

        // Getters
        public LocalDate getDataChecklist() { return dataChecklist; }
        public String getObservacoes() { return observacoes; }
        public String getNomeAluno() { return (aluno != null) ? aluno.getNome() : "---"; }
    }
}