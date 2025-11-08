package br.com.familiaeduca.ui.service;

import br.com.familiaeduca.ui.dto.*; // Importa todos os DTOs
import com.google.gson.*;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List; // O import que faltava!

public class FamiliaEducaApiClient {

    // URL base da vossa API (Backend Spring Boot)
    private static final String BASE_URL = "http://localhost:8080"; // Removi o /api base para ficar mais flexível
    private final HttpClient client;
    private final Gson gson;

    public FamiliaEducaApiClient() {
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        // Configura Gson para entender LocalDate (yyyy-MM-dd)
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
                    @Override
                    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
                        return new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE));
                    }
                })
                .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
                    @Override
                    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE);
                    }
                })
                .create();
    }

    // =================================================================================
    // AUTENTICAÇÃO & PERFIL
    // =================================================================================

    public TokenDto fazerLogin(String email, String senha) throws Exception {
        LoginDto loginDto = new LoginDto(email, senha);
        String jsonBody = gson.toJson(loginDto);

        // NOTA: Ajuste a URL se a sua API usar /api/auth/login ou só /login
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return gson.fromJson(response.body(), TokenDto.class);
        } else {
            throw new RuntimeException("Falha no login: " + response.statusCode());
        }
    }

    // Assumindo que existe um endpoint que retorna os dados do usuário logado
    public UsuarioDto getMeuPerfil(String token) throws Exception {
        // NOTA: Ajuste se a API usar outro endpoint para isso (ex: /usuarios/me)
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/usuarios/me"))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return gson.fromJson(response.body(), UsuarioDto.class);
        } else {
            // Se não houver endpoint /me, você terá que pegar os dados do Token JWT
            throw new RuntimeException("Erro ao buscar perfil: " + response.statusCode());
        }
    }

    // =================================================================================
    // FREQUÊNCIA (Aluno: visualiza | Professor: edita)
    // =================================================================================

    public List<FrequenciaDto> getFrequencia(String token) throws Exception {
        // [PESSOA A] Implementar chamada GET para /frequencias (ou /alunos/{id}/frequencia)
        System.out.println("CHAMADA FAKE: getFrequencia");
        return Collections.emptyList();
    }

    public void adicionarFrequencia(String token, FrequenciaDto dto) throws Exception {
        // [PESSOA A] Implementar chamada POST para /frequencias
        System.out.println("CHAMADA FAKE: adicionarFrequencia: " + gson.toJson(dto));
    }

    // =================================================================================
    // NOTAS (Aluno: visualiza | Professor: edita)
    // =================================================================================

    // Você precisará criar a classe NotaDto.java
    public List<Object> getNotas(String token) throws Exception {
        // [PESSOA A] Implementar chamada GET para /notas
        System.out.println("CHAMADA FAKE: getNotas");
        return Collections.emptyList();
    }

    public void adicionarNota(String token, Object notaDto) throws Exception {
        // [PESSOA A] Implementar chamada POST para /notas
        System.out.println("CHAMADA FAKE: adicionarNota");
    }

    // =================================================================================
    // MATRÍCULA (Aluno/Professor: visualiza)
    // =================================================================================

    // Você precisará criar a classe MatriculaDto.java
    public Object getMatricula(String token) throws Exception {
        // [PESSOA A] Implementar chamada GET para /matriculas/minha (ou similar)
        System.out.println("CHAMADA FAKE: getMatricula");
        return null;
    }

    // =================================================================================
    // CHECKLIST (Aluno: altera | Professor: visualiza e dá retorno)
    // =================================================================================

    // Você precisará criar a classe ChecklistDto.java
    public List<Object> getChecklist(String token) throws Exception {
        // [PESSOA A] Implementar chamada GET para /checklist
        System.out.println("CHAMADA FAKE: getChecklist");
        return Collections.emptyList();
    }

    public void atualizarChecklist(String token, Object checklistDto) throws Exception {
        // [PESSOA A] Implementar chamada PUT para /checklist/{id}
        System.out.println("CHAMADA FAKE: atualizarChecklist");
    }

    // =================================================================================
    // REUNIÕES (Aluno/Professor: solicita e aceita)
    // =================================================================================

    // Você precisará criar a classe ReuniaoDto.java
    public List<Object> getReunioes(String token) throws Exception {
        // [PESSOA A] Implementar chamada GET para /reunioes
        System.out.println("CHAMADA FAKE: getReunioes");
        return Collections.emptyList();
    }

    public void solicitarReuniao(String token, Object reuniaoDto) throws Exception {
        // [PESSOA A] Implementar chamada POST para /reunioes
        System.out.println("CHAMADA FAKE: solicitarReuniao");
    }

    public void aceitarReuniao(String token, Long reuniaoId) throws Exception {
        // [PESSOA A] Implementar chamada PATCH/PUT para /reunioes/{id}/aceitar
        System.out.println("CHAMADA FAKE: aceitarReuniao ID: " + reuniaoId);
    }

    // =================================================================================
    // AVISOS (Aluno: visualiza | Professor: edita)
    // =================================================================================

    // Você precisará criar a classe AvisoDto.java
    public List<Object> getAvisos(String token) throws Exception {
        // [PESSOA A] Implementar chamada GET para /avisos
        System.out.println("CHAMADA FAKE: getAvisos");
        return Collections.emptyList();
    }

    public void criarAviso(String token, Object avisoDto) throws Exception {
        // [PESSOA A] Implementar chamada POST para /avisos
        System.out.println("CHAMADA FAKE: criarAviso");
    }

}