# Família Educa - Desktop Client (Swing UI)

Este é o aplicativo Desktop do **Família Educa**, criado para oferecer uma interface simples e prática para diretores, professores e responsáveis acompanharem o dia a dia escolar.  
Ele funciona como o cliente gráfico do sistema, consumindo a API oficial.

> **Importante:** Este projeto é só a interface. Para funcionar, o Back-end precisa estar rodando.  
> 🔗 **API (Back-end):** [caroluiza-n/projeto-familiaeduca](https://github.com/caroluiza-n/projeto-familiaeduca)

---

## ✨ O que o sistema oferece

O sitema adapta automaticamente menus e permissões de acordo com o perfil do usuário (**Diretor**, **Professor** ou **Responsável**), garantindo uma navegação personalizada.

### 👥 Administração e Usuários
- **Cadastro de Usuários:** Diretores podem registrar novos Professores e Responsáveis.
- **Controle de Permissões:** Cada perfil acessa apenas o que é permitido, mantendo segurança e organização.

### 🎓 Área Acadêmica
- **Matrículas:** Cadastro de alunos com seleção dinâmica de turmas e responsáveis.
- **Frequência:** Professores registram a presença dos alunos de forma rápida e visual.
- **Notas:** Lançamento de avaliações por disciplina e aluno.

### 📝 Rotina e Acompanhamento
- **Checklist Diário:** Professores registram rotina do aluno e responsáveis acompanham pelo painel.
- **Mural de Avisos:** Espaço para comunicados oficiais da escola.

### 📅 Comunicação
- **Agendamento de Reuniões:**
    - Diretores e Professores podem solicitar reuniões.
    - Responsáveis acompanham seus agendamentos e status.

---

## 🛠️ Tecnologias utilizadas

- **Linguagem:** Java 21
- **Interface:** Java Swing
- **Tema:** [FlatLaf](https://www.formdev.com/flatlaf/)
- **HTTP Client:** `java.net.http.HttpClient`
- **JSON:** Gson
- **Build:** Maven

---

## 🏛️ Arquitetura

O projeto segue o padrão **MVC**, mantendo organização e facilitando manutenção:

1. **View (`ui.view`)** – Onde ficam as telas, botões, tabelas e layouts.
2. **Controller (`ui.controller`)** – Recebe interações da tela, valida dados e envia requisições.
3. **Service/Client (`ui.service`)** – Responsável pela comunicação com a API (requisições, autenticação, erros).
4. **DTO (`ui.dto`)** – Estruturas usadas para enviar e receber dados JSON.

---

## 🚀 Como rodar o projeto

### Pré-requisitos
Antes de iniciar este Front-end, você **precisa** ter o Back-end rodando.

1. Siga as instruções do repositório [caroluiza-n/projeto-familiaeduca](https://github.com/caroluiza-n/projeto-familiaeduca) para subir a API e o Banco de Dados Docker.
2. Certifique-se de que a API está online em `http://localhost:8080`.

### Executando o Cliente Swing

1. **Clone este repositório:**
    ```bash
    git clone [https://github.com/DanyAraujo0/familiaeduca-swing-ui.git](https://github.com/DanyAraujo0/familiaeduca-swing-ui.git)
    cd familiaeduca-swing-ui
    ```

2. **Instale as dependências (Maven):**
    ```bash
    mvn clean install
    ```

3. **Execute a aplicação:**
    - Pela IDE (IntelliJ/Eclipse): Localize a classe `Main.java` e clique em **Run**.
    - Pelo terminal:
        ```bash
        mvn exec:java -Dexec.mainClass="br.com.familiaeduca.ui.Main"
        ```

---
