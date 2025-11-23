package br.com.familiaeduca.ui.dto;

import com.google.gson.annotations.SerializedName;
import java.util.UUID;

public class ResponsavelDto {

    // ===== REQUEST (Cadastro) =====
    public static class CreateResponsavelRequest {
        private String nome;
        private String email;
        private String senha;
        private String telefone;
        private String endereco;

        // Getters e setters
        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }

        public String getTelefone() { return telefone; }
        public void setTelefone(String telefone) { this.telefone = telefone; }

        public String getEndereco() { return endereco; }
        public void setEndereco(String endereco) { this.endereco = endereco; }
    }

    // ===== RESPONSE (Lista)  =====
    public static class ResponsavelResumeResponse {
        @SerializedName(value = "id", alternate = {"identificador", "uuid"})
        private String id;

        @SerializedName("nome")
        private String nome;

        @SerializedName("email")
        private String email;

        public String getId() { return id; }
        public String getNome() { return nome; }
        public String getEmail() { return email; }

        public String id() { return id; }
        public String nome() { return nome; }

        @Override
        public String toString() {
            return nome;
        }
    }
}