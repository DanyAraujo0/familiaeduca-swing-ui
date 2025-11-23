package br.com.familiaeduca.ui.dto;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class TurmaDto {

    // ==================================================================
    // 1. CLASSE RESUMIDA (Usada no ComboBox e Listas)
    // ==================================================================
    public static class TurmaResumeResponse {

        @SerializedName("id")
        private String id;

        @SerializedName("nome")
        private String nome;

        // Se quiser ler o professor que apareceu no seu JSON
        @SerializedName("nomeProfessor")
        private String nomeProfessor;

        // --- Métodos que o seu Controller espera (estilo Record) ---
        public String id() { return id; }
        public String nome() { return nome; }

        // --- Getters padrão (caso precise) ---
        public String getId() { return id; }
        public String getNome() { return nome; }

        @Override
        public String toString() {
            return nome; // Isso ajuda o ComboBox a mostrar o nome se der erro na formatação
        }
    }

    // ==================================================================
    // 2. CLASSE DETALHADA (Usada quando clica na turma)
    // ==================================================================
    public static class TurmaResponse {
        @SerializedName("id")
        private String id;

        @SerializedName("nome")
        private String nome;

        @SerializedName("alunos")
        private List<AlunoResumeResponse> alunos;

        // Métodos estilo Record
        public String id() { return id; }
        public String nome() { return nome; }
        public List<AlunoResumeResponse> alunos() { return alunos; }
    }

    // ==================================================================
    // 3. CLASSE ALUNO (Dentro da Turma)
    // ==================================================================
    public static class AlunoResumeResponse {
        @SerializedName("matricula")
        private Integer matricula;

        @SerializedName("nome")
        private String nome;

        // Métodos estilo Record
        public Integer matricula() { return matricula; }
        public String nome() { return nome; }

        public Integer getMatricula() { return matricula; }
        public String getNome() { return nome; }
    }
}