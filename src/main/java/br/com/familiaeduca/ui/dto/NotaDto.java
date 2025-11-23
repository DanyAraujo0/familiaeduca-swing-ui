package br.com.familiaeduca.ui.dto;
import java.time.LocalDate;
import java.util.UUID;

public class NotaDto {
    public static class CreateNotaRequest {
        private UUID idDisciplina;
        private UUID idBoletim;
        private String nota;
        private LocalDate dataAvaliacao;

        public CreateNotaRequest(UUID idDisciplina, UUID idBoletim, String nota, LocalDate dataAvaliacao) {
            this.idDisciplina = idDisciplina;
            this.idBoletim = idBoletim;
            this.nota = nota;
            this.dataAvaliacao = dataAvaliacao;
        }
    }

    // Response simples para listagem (se precisar no futuro)
    public static class NotaResponse {
        private String id;
        private String nota;
    }
}