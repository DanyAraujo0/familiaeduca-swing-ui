package br.com.familiaeduca.ui.dto;

import com.google.gson.annotations.SerializedName;
import java.time.LocalDate;
import java.util.UUID;

public class ReuniaoDto {
    public static class CreateReuniaoRequest {
        private LocalDate data;
        private String motivo;
        private UUID idResponsavel;

        public CreateReuniaoRequest(LocalDate data, String motivo, UUID idResponsavel) {
            this.data = data;
            this.motivo = motivo;
            this.idResponsavel = idResponsavel;
        }
    }

    public static class ReuniaoResponse {
        private String id;
        private LocalDate data;
        private String motivo;
        private String status;

        @SerializedName("responsavel")
        private ResponsavelDto.ResponsavelResumeResponse responsavel;

        public String getId() { return id; }
        public LocalDate getData() { return data; }
        public String getMotivo() { return motivo; }
        public String getStatus() { return status; }

        public String getNomeResponsavel() {
            return (responsavel != null) ? responsavel.getNome() : "---";
        }
    }
}