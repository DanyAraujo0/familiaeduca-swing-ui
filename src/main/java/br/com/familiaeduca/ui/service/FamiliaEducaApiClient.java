 package br.com.familiaeduca.ui.service;

import br.com.familiaeduca.ui.dto.*;
import br.com.familiaeduca.ui.service.exception.*;
import br.com.familiaeduca.ui.util.SessaoUsuario;
import com.google.gson.*;
import java.net.URI;
import java.net.http.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.UUID;

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

    private HttpRequest.Builder getRequestAutenticada(String uri) {
        UsuarioDto user = SessaoUsuario.getInstance().getUsuarioLogado();
        String senha = SessaoUsuario.getInstance().getSenhaUsuario();

        if (user == null || senha == null) {
            System.out.println("ALERTA: Tentando requisição sem usuário/senha na sessão!");
            return HttpRequest.newBuilder().uri(URI.create(BASE_URL + uri));
        }

        String auth = user.getEmail() + ":" + senha;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        return HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + uri))
                .header("Authorization", "Basic " + encodedAuth)
                .header("Content-Type", "application/json");
    }

    private String extrairMensagemDeErro(String body) {
        try {
            JsonObject json = JsonParser.parseString(body).getAsJsonObject();
            if (json.has("message")) return json.get("message").getAsString();
            if (json.has("error")) return json.get("error").getAsString();
        } catch (Exception e) { return body; }
        return body;
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
            throw new ApiBadRequestException("\n Erro ao processar a requisição. Verifique os dados preenchidos.");
        }
        else {
            String mensagem = extrairMensagemDeErro(response.body());
            throw new ApiServiceException("Erro no login: " + mensagem);
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
            throw new ApiBadRequestException("\n Erro ao processar a requisição. Verifique os dados preenchidos.");
        }
        else if (status == 409) {
            throw new ApiBadRequestException("\n Erro ao processar a requisição. Verifique os dados preenchidos.");
        }
        else {
            String mensagem = extrairMensagemDeErro(response.body());
            throw new ApiServiceException("Erro ao cadastrar diretor: " + mensagem);
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
            throw new ApiBadRequestException("\n\n Erro ao processar a requisição. Verifique os dados preenchidos.");
        }
        else {
            String mensagem = extrairMensagemDeErro(response.body());
            throw new ApiServiceException("Erro ao cadastrar professor: " + mensagem);
        }
    }

    // ============================================================
    // RESPONSÁVEL
    // ============================================================
    public UsuarioDto cadastrarResponsavel(ResponsavelDto.CreateResponsavelRequest dto) throws Exception {

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
            throw new ApiBadRequestException("\n Erro ao processar a requisição. Verifique os dados preenchidos.");
        }
        else {
            String mensagem = extrairMensagemDeErro(response.body());
            throw new ApiServiceException("Erro ao cadastrar responsável: " + mensagem);
        }
    }

    public ResponsavelDto.ResponsavelResumeResponse[] listarResponsaveis() throws Exception {
        HttpRequest request = getRequestAutenticada("/responsaveis")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Teste para saber se a comunicação esta bem sucedida
//        System.out.println("STATUS RESPONSAVEL = " + response.statusCode());
//        System.out.println("BODY RESPONSAVEL = " + response.body());

        if (response.statusCode() == 200) {
            return gson.fromJson(response.body(), ResponsavelDto.ResponsavelResumeResponse[].class);
        }

        if (response.statusCode() == 404) {
            return new ResponsavelDto.ResponsavelResumeResponse[0];
        }

        if (response.statusCode() == 403) {
            throw new ApiServiceException("Acesso Negado ao listar responsáveis. Verifique as permissões no Backend.");
        }

        throw new ApiServiceException("Erro ao listar responsáveis (Status " + response.statusCode() + "): " + extrairMensagemDeErro(response.body()));
    }

    // ============================================================
    // PERFIL
    // ============================================================
    public UsuarioDto getMeuPerfil() throws Exception {
        // MUDANÇA: Usando o helper autenticado
        HttpRequest request = getRequestAutenticada("/usuarios/me")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // Teste para saber se a comunicação esta bem sucedida
        System.out.println("STATUS AVISO = " + response.statusCode());
        System.out.println("BODY AVISO = " + response.body());
        if (response.statusCode() == 200) {
            return gson.fromJson(response.body(), UsuarioDto.class);
        }
        throw new ApiServiceException("Erro perfil: " + extrairMensagemDeErro(response.body()));
    }

    // ============================================================
    // ALUNO
    // ============================================================
    public AlunoDto.AlunoResponse[] listarAlunos() throws Exception {
        HttpRequest request = getRequestAutenticada("/alunos")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return gson.fromJson(response.body(), AlunoDto.AlunoResponse[].class);
        }
        throw new ApiServiceException("Erro ao listar alunos.");
    }

    public AlunoDto.AlunoResponse criarAluno(AlunoDto.CreateAlunoRequest req) throws Exception {
        String jsonBody = gson.toJson(req);

        HttpRequest request = getRequestAutenticada("/alunos")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 201 || response.statusCode() == 200) {
            return gson.fromJson(response.body(), AlunoDto.AlunoResponse.class);
        }
        throw new ApiServiceException("Erro ao criar aluno: " + extrairMensagemDeErro(response.body()));
    }

    // ============================================================
    // TURMAS
    // ============================================================
    public TurmaDto.TurmaResumeResponse[] listarTurmas() throws Exception {
        HttpRequest request = getRequestAutenticada("/turmas")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // Teste para saber se a comunicação esta bem sucedida
        //System.out.println("STATUS TURMAS = " + response.statusCode());
        //System.out.println("BODY TURMAS = " + response.body());

        if (response.statusCode() == 200) {
            return gson.fromJson(response.body(), TurmaDto.TurmaResumeResponse[].class);
        }

        if (response.statusCode() == 403) {
            throw new ApiServiceException("Acesso Negado. Verifique se seu usuário tem permissão.");
        }

        if (response.statusCode() == 404) {
            return new TurmaDto.TurmaResumeResponse[0];
        }

        throw new Exception("Erro ao carregar turmas: " + response.body());
    }

    public TurmaDto.TurmaResponse buscarTurmaPorId(UUID id) throws Exception {
        HttpRequest request = getRequestAutenticada("/turmas/" + id)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return gson.fromJson(response.body(), TurmaDto.TurmaResponse.class);
        }
        throw new ApiServiceException("Erro ao buscar turma.");
    }

    // ============================================================
    // FREQUÊNCIA
    // ============================================================
    public FrequenciaDto registrarFrequencia(FrequenciaDto dto) throws Exception {
        String jsonBody = gson.toJson(dto);

        HttpRequest request = getRequestAutenticada("/frequencias")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // Teste para saber se a comunicação esta bem sucedida
//        System.out.println("STATUS AVISOS = " + response.statusCode());
//        System.out.println("BODY AVISOS = " + response.body());

        if (response.statusCode() == 201 || response.statusCode() == 200) {
            return gson.fromJson(response.body(), FrequenciaDto.class);
        }
        throw new ApiServiceException("Erro frequência: " + extrairMensagemDeErro(response.body()));
    }

    // ============================================================
    // NOTA
    // ============================================================

    // ============================================================
    // AVISOS
    // ============================================================
    public AvisoDto.AvisoResponse[] listarAvisos() throws Exception {
        HttpRequest request = getRequestAutenticada("/avisos")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // Teste para saber se a comunicação esta bem sucedida
//        System.out.println("STATUS AVISOS = " + response.statusCode());
//        System.out.println("BODY AVISOS = " + response.body());
        if (response.statusCode() == 200) {
            return gson.fromJson(response.body(), AvisoDto.AvisoResponse[].class);
        }

        throw new ApiServiceException("Erro ao listar avisos (" + response.statusCode() + ")");
    }

    public void criarAviso(AvisoDto.CreateAvisoRequest dto) throws Exception {
        String jsonBody = gson.toJson(dto);

        HttpRequest request = getRequestAutenticada("/avisos")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 201 && response.statusCode() != 200) {
            throw new ApiServiceException("Erro ao criar aviso: " + extrairMensagemDeErro(response.body()));
        }
    }

    // ============================================================
    // REUNIÕES
    // ============================================================

    // ============================================================
    // CHECKLIST
    // ============================================================
}