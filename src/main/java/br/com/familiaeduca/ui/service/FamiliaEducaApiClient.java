package br.com.familiaeduca.ui.service;

import br.com.familiaeduca.ui.dto.*;
import br.com.familiaeduca.ui.service.exception.*;
import com.google.gson.*;
import java.net.URI;
import java.net.http.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class FamiliaEducaApiClient {

    private static final String BASE_URL = "http://localhost:8080";
    private final HttpClient client;
    private final Gson gson;

    public FamiliaEducaApiClient() {
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>)
                        (src, typeOfSrc, ctx) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE)))
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>)
                        (json, typeOfT, ctx) -> LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE))
                .create();
    }

    // ============================================================
    // HELPER — Extrai mensagem do backend
    // ============================================================
    private String extractMessage(String json) {
        try {
            Map<String, String> map = gson.fromJson(json, Map.class);
            if (map.containsKey("mensagem")) return map.get("mensagem");
            if (map.containsKey("message")) return map.get("message");
            return json;
        } catch (Exception e) {
            return json;
        }
    }

    // ============================================================
    // LOGIN
    // ============================================================
    public UsuarioDto fazerLogin(String email, String senha) throws Exception {

        LoginDto dto = new LoginDto(email, senha);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/usuarios/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(dto)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int status = response.statusCode();

        if (status == 200) {
            return gson.fromJson(response.body(), UsuarioDto.class);
        }
        else if (status == 401) {
            throw new ApiUnauthorizedException("Credenciais inválidas.");
        }
        else if (status == 400) {
            throw new ApiBadRequestException(extractMessage(response.body()));
        }
        else {
            throw new ApiServiceException("Erro no login: " + extractMessage(response.body()));
        }
    }

    // ============================================================
    // DIRETOR
    // ============================================================
    public UsuarioDto cadastrarDiretor(DiretorDto dto) throws Exception {

        String jsonBody = gson.toJson(dto);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/diretores"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int status = response.statusCode();

        if (status == 201 || status == 200) {
            return gson.fromJson(response.body(), UsuarioDto.class);
        }
        else if (status == 400) {
            throw new ApiBadRequestException(extractMessage(response.body()));
        }
        else if (status == 409) {
            throw new ApiBadRequestException("Conflito: " + extractMessage(response.body()));
        }
        else {
            throw new ApiServiceException("Erro ao cadastrar diretor: " + extractMessage(response.body()));
        }
    }

    // ============================================================
    // PROFESSOR
    // ============================================================
    public UsuarioDto cadastrarProfessor(ProfessorDto dto) throws Exception {

        String jsonBody = gson.toJson(dto);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/professores"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int status = response.statusCode();

        if (status == 201 || status == 200) {
            return gson.fromJson(response.body(), UsuarioDto.class);
        }
        else if (status == 400) {
            throw new ApiBadRequestException(extractMessage(response.body()));
        }
        else {
            throw new ApiServiceException("Erro ao cadastrar professor: " + extractMessage(response.body()));
        }
    }

    // ============================================================
    // RESPONSÁVEL
    // ============================================================
    public UsuarioDto cadastrarResponsavel(ResponsavelDto dto) throws Exception {

        String jsonBody = gson.toJson(dto);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/responsaveis"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int status = response.statusCode();

        if (status == 201 || status == 200) {
            return gson.fromJson(response.body(), UsuarioDto.class);
        }
        else if (status == 400) {
            throw new ApiBadRequestException(extractMessage(response.body()));
        }
        else {
            throw new ApiServiceException("Erro ao cadastrar responsável: " + extractMessage(response.body()));
        }
    }

    // ============================================================
    // PERFIL
    // ============================================================
    public UsuarioDto getMeuPerfil() throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/usuarios/me"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int status = response.statusCode();

        if (status == 200) {
            return gson.fromJson(response.body(), UsuarioDto.class);
        }
        else if (status == 404) {
            throw new ApiNotFoundException("Usuário não encontrado.");
        }
        else {
            throw new ApiServiceException("Erro ao buscar perfil: " + extractMessage(response.body()));
        }
    }

    // ============================================================
    // FREQUÊNCIA
    // ============================================================
    public void addFrequencia(FrequenciaDto dto) throws Exception {

        String jsonBody = gson.toJson(dto);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/frequencias"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int status = response.statusCode();

        if (status != 201 && status != 200) {
            throw new ApiServiceException("Erro ao adicionar frequência: " + extractMessage(response.body()));
        }
    }
}
