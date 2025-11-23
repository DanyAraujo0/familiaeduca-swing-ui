package br.com.familiaeduca.ui.dto;

import java.time.LocalDate;
import java.util.UUID;

public class AlunoDto {

    public record AlunoResponse(
            Integer matricula,
            String nome,
            LocalDate dataNascimento,
            String laudo,
            String alergias,
            TurmaResumeDto turma,
            ResponsavelResumeDto responsavel
    ) {
        public Integer getMatricula() {
            return matricula;
        }
        public String getNome() {
            return nome;
        }
        public LocalDate getDataNascimento() {
            return dataNascimento;
        }
        public String getLaudo() {
            return laudo;
        }
        public String getAlergias() {
            return alergias;
        }
        public TurmaResumeDto getTurma() {
            return turma;
        }
        public ResponsavelResumeDto getResponsavel() {
            return responsavel;
        }
    }

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