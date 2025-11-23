package br.com.familiaeduca.ui.dto;

import com.google.gson.annotations.SerializedName;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public class AvisoDto {

    // ==========================================
    // RESPONSE (Blindado com SerializedName)
    // ==========================================
    public static class AvisoResponse {
        @SerializedName("id")
        private String id;

        @SerializedName("titulo")
        private String titulo;

        @SerializedName("mensagem")
        private String mensagem;

        // O backend pode enviar como "dataPublicacao" ou "data_publicacao"
        @SerializedName(value = "dataPublicacao", alternate = {"data_publicacao", "data"})
        private LocalDate dataPublicacao;

        // Getters
        public String getId() { return id; }
        public String getTitulo() { return titulo; }
        public String getMensagem() { return mensagem; }
        public LocalDate getDataPublicacao() { return dataPublicacao; }

        @Override
        public String toString() {
            return titulo;
        }
    }

    // ==========================================
    // REQUEST (Create)
    // ==========================================
    public static class CreateAvisoRequest {
        private String titulo;
        private String mensagem;
        private UUID idDiretor;
        private Set<UUID> idProfessores;
        private Set<UUID> idResponsaveis;

        public CreateAvisoRequest(String titulo, String mensagem, UUID idDiretor) {
            this.titulo = titulo;
            this.mensagem = mensagem;
            this.idDiretor = idDiretor;
            this.idProfessores = Set.of(); // Lista vazia
            this.idResponsaveis = Set.of(); // Lista vazia
        }
    }
}