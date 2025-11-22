package br.com.familiaeduca.ui.dto;

import java.util.List;
import java.util.UUID;

public class TurmaDto {
    public record TurmaResumeResponse(
            UUID id,
            String nome,
            String nomeProfessor
    ) {
        @Override
        public String toString() {
            return nome + " (" + nomeProfessor + ")";
        }
    }
    public record TurmaResponse(
            UUID id,
            String nome,
            ProfessorResumeResponse professor,
            List<AlunoResumeResponse> alunos
    ) {}

    public record ProfessorResumeResponse(
            UUID id,
            String nome
    ) {}

    public record AlunoResumeResponse(
            Integer matricula,
            String nome
    ) {}

    public record TurmaRequest(
            String nome,
            UUID idProfessor,
            String turno,
            Integer anoLetivo
    ) {}
}