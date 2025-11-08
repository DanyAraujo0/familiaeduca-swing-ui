package br.com.familiaeduca.ui.dto;

public class UsuarioDto {
    private Long id;
    private String nome;
    private String email;
    // Perfis esperados: "PROFESSOR", "DIRETOR", "RESPONSAVEL"
    private String perfil;

    // Construtor vazio (necessário para bibliotecas JSON como Gson)
    public UsuarioDto() {
    }

    // Construtor completo (opcional, útil para testes)
    public UsuarioDto(Long id, String nome, String email, String perfil) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.perfil = perfil;
    }

    // --- GETTERS ---
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getPerfil() {
        return perfil;
    }

    // --- SETTERS ---
    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    // --- MÉTODOS AUXILIARES DE VERIFICAÇÃO ---
    // Estes métodos tornam o código nos Controllers muito mais limpo.
    // Exemplo de uso: if (usuario.isProfessor()) { ... }

    public boolean isProfessor() {
        return "PROFESSOR".equalsIgnoreCase(perfil);
    }

    public boolean isDiretor() {
        return "DIRETOR".equalsIgnoreCase(perfil);
    }

    public boolean isResponsavel() {
        return "RESPONSAVEL".equalsIgnoreCase(perfil);
    }

    // --- TOSTRING (Para Debug) ---
    @Override
    public String toString() {
        return "UsuarioDto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", perfil='" + perfil + '\'' +
                '}';
    }
}