package br.com.familiaeduca.ui.service;

import br.com.familiaeduca.ui.dto.*;
import java.util.List;

// [PESSOA A] Esta é a sua classe principal.
// Deve usar HttpClient, Gson/Jackson para implementar estes métodos.
public class FamiliaEducaApiClient {

    private final String API_BASE_URL = "http://localhost:8080/api";

    public TokenDto fazerLogin(LoginDto loginDto) {
        System.out.println("SERVICE: A chamar API de Login para " + loginDto);
        // [PESSOA A]: Implementar POST para /usuarios/login
        // Se o login for sucesso, retorne um TokenDto
        // Se falhar, retorne null ou lance uma ApiServiceException

        // Mock (simulação) de sucesso:
        if ("admin".equals(loginDto.getUsername())) {
            TokenDto token = new TokenDto();
            token.setToken("fake-admin-token-123");
            return token;
        }
        return null;
    }

    public UsuarioDto getMeuPerfil(String token) {
        System.out.println("SERVICE: A chamar API para buscar perfil com token " + token);
        // [PESSOA A]: Implementar GET para /usuarios/perfil (ou similar)
        // passando o token no Header "Authorization: Bearer " + token

        // Mock (simulação):
        UsuarioDto user = new UsuarioDto();
        user.setNome("Administrador");
        user.setTipo("Colaborador"); // ou "Aluno"
        return user;
    }

    // --- Métodos que substituem o DataBase.lerCSV ---

    public List<FrequenciaDto> getFrequencia(String token) {
        System.out.println("SERVICE: A chamar API para buscar Frequencia");
        // [PESSOA A]: Implementar GET para /frequencias
        // A API deve filtrar os dados (retornar só do aluno ou todos)
        return List.of(); // Retornar lista vazia por enquanto
    }

    public List<NotasDto> getNotas(String token) {
        System.out.println("SERVICE: A chamar API para buscar Notas");
        // [PESSOA A]: Implementar GET para /notas
        return List.of();
    }

    // ... criar métodos getChecklist(), getMatriculas() ...

    // --- Métodos que substituem o DataBase.adicionarLinha ---

    public FrequenciaDto addFrequencia(String token, FrequenciaDto novaFrequencia) {
        System.out.println("SERVICE: A chamar API para ADICIONAR Frequencia");
        // [PESSOA A]: Implementar POST para /frequencias
        return null;
    }

    // ... criar métodos addNota(), addChecklist(), addMatricula() ...
}