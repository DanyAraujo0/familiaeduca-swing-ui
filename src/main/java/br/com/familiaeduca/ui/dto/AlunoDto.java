package br.com.familiaeduca.ui.dto;

import java.time.LocalDate;
import java.util.UUID;

public class AlunoDto {

    // DTO Principal para resposta da API
    public record AlunoResponse(
            Integer matricula,
            String nome,
            LocalDate dataNascimento,
            String laudo,
            String alergias,
            TurmaResumeDto turma,
            ResponsavelResumeDto responsavel
    ) {}

    public record TurmaResumeDto(
            UUID id,
            String nome
    ) {
        @Override
        public String toString() {
            return nome;
        }
    }

    public record ResponsavelResumeDto(
            UUID id,
            String nome
    ) {}

    public record CreateAlunoRequest(
            String nome,
            LocalDate dataNascimento,
            String laudo,
            String alergias,
            UUID idTurma,
            UUID idResponsavel
    ) {}
}