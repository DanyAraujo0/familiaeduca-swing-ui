package br.com.familiaeduca.ui.service;

import br.com.familiaeduca.ui.dto.*;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FamiliaEducaApiClient {

    private static final String BASE_URL = "http://localhost:8080";
    private final HttpClient client;
    private final Gson gson;

    public FamiliaEducaApiClient() {
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        // Configura Gson para suportar LocalDate
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>)
                        (src, typeOfSrc, context) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE)))
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>)
                        (json, typeOfT, context) -> LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE))
                .create();
    }

    // =================================================================================
    // LOGIN
    // =================================================================================
    // TAVA FUNCIONANDO MAS PRECISA DE AUTENTICAÇÃO
        public TokenDto fazerLogin(String email, String senha) throws Exception {
            String jsonBody = gson.toJson(new LoginDto(email, senha));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/usuarios"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                TokenDto tokenDto = gson.fromJson(response.body(), TokenDto.class);
                return tokenDto;
            } else {
                throw new RuntimeException("Falha no login: " + response.statusCode() + " - " + response.body());
            }
        }
    // =================================================================================
    // DIRETORES
    // =================================================================================

    public UsuarioDto cadastrarDiretor(DiretorDto dto) throws Exception {
        String jsonBody = gson.toJson(dto);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/diretores"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 201 || response.statusCode() == 200) {
            return gson.fromJson(response.body(), UsuarioDto.class);
        } else {
            throw new RuntimeException("Erro ao cadastrar diretor: " + response.statusCode() + "\n" + response.body());
        }
    }

    // =================================================================================
    // PROFESSORES
    // =================================================================================

    public UsuarioDto cadastrarProfessor(ProfessorDto dto, String token) throws Exception {
        String jsonBody = gson.toJson(dto);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/professores"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 201 || response.statusCode() == 200) {
            return gson.fromJson(response.body(), UsuarioDto.class);
        } else {
            throw new RuntimeException("Erro ao cadastrar professor: " + response.statusCode() + "\n" + response.body());
        }
    }

    // =================================================================================
    // RESPONSÁVEIS
    // =================================================================================

    public UsuarioDto cadastrarResponsavel(ResponsavelDto dto, String token) throws Exception {
        String jsonBody = gson.toJson(dto);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/responsaveis"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 201 || response.statusCode() == 200) {
            return gson.fromJson(response.body(), UsuarioDto.class);
        } else {
            throw new RuntimeException("Erro ao cadastrar responsável: " + response.statusCode() + "\n" + response.body());
        }
    }

    // =================================================================================
    // PERFIL
    // =================================================================================

    public UsuarioDto getMeuPerfil(String token) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/usuarios/me"))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return gson.fromJson(response.body(), UsuarioDto.class);
        } else {
            throw new RuntimeException("Erro ao buscar perfil: " + response.statusCode() + " - " + response.body());
        }
    }

    public void addFrequencia(String token, FrequenciaDto dto) throws Exception {
        String jsonBody = gson.toJson(dto);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/frequencias"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 201 && response.statusCode() != 200) {
            throw new RuntimeException("Erro ao adicionar frequência: " + response.statusCode() + " - " + response.body());
        }
    }

}