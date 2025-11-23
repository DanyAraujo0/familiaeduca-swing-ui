package br.com.familiaeduca.ui.controllers;

import br.com.familiaeduca.ui.dto.ResponsavelDto;
import br.com.familiaeduca.ui.dto.TurmaDto;
import br.com.familiaeduca.ui.dto.AlunoDto;
import br.com.familiaeduca.ui.view.sistema.MatriculaPanel;
import br.com.familiaeduca.ui.service.FamiliaEducaApiClient;

import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class AlunoController {

    private final MatriculaPanel view;
    private final FamiliaEducaApiClient apiClient;

    public AlunoController(MatriculaPanel view) {
        this.view = view;
        this.apiClient = new FamiliaEducaApiClient();

        // Carrega a primeira vez
        carregarListas();

        configurarEvento();
    }

    private void carregarListas() {
        try {
            // Guarda o item que estava selecionado antes de limpar
            Object turmaSelecionada = view.getCbTurmaMatricula().getSelectedItem();
            Object respSelecionado = view.getCbResponsavel().getSelectedItem();

            // ATUALIZA TURMAS
            view.getCbTurmaMatricula().removeAllItems();
            TurmaDto.TurmaResumeResponse[] turmas = apiClient.listarTurmas();
            for (TurmaDto.TurmaResumeResponse t : turmas) {
                view.getCbTurmaMatricula().addItem(new ComboBoxItem(t.getNome(), t.getId()));
            }

            // ATUALIZA RESPONSÁVEIS
            view.getCbResponsavel().removeAllItems();
            ResponsavelDto.ResponsavelResumeResponse[] responsaveis = apiClient.listarResponsaveis();

            for (ResponsavelDto.ResponsavelResumeResponse r : responsaveis) {
                view.getCbResponsavel().addItem(new ComboBoxItem(r.getNome(), r.getId()));
            }

        } catch (Exception ex) {
            System.out.println("Erro ao atualizar listas em segundo plano: " + ex.getMessage());
        }
    }

    private void configurarEvento() {
        view.getBtnMatricular().addActionListener(e -> matricular());
        view.addAncestorListener(new AncestorListener() {

            @Override
            public void ancestorAdded(AncestorEvent event) {
                carregarListas();
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {}

            @Override
            public void ancestorMoved(AncestorEvent event) {}
        });
    }

    private void matricular() {
        try {
            String nome = view.getTxtNomeAluno().getText();
            String dataTxt = view.getTxtDataNasc().getText();

            if (dataTxt.contains("_") || dataTxt.trim().isEmpty()) {
                view.exibirMensagem("Preencha a data corretamente (DD/MM/AAAA).");
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate nascimento = LocalDate.parse(dataTxt, formatter);

            ComboBoxItem turmaItem = (ComboBoxItem) view.getCbTurmaMatricula().getSelectedItem();
            ComboBoxItem respItem = (ComboBoxItem) view.getCbResponsavel().getSelectedItem();

            if (turmaItem == null || respItem == null) {
                view.exibirMensagem("Selecione a Turma e o Responsável.");
                return;
            }

            AlunoDto.CreateAlunoRequest req = new AlunoDto.CreateAlunoRequest(
                    nome,
                    nascimento,
                    null,
                    null,
                    UUID.fromString(turmaItem.getId()),
                    UUID.fromString(respItem.getId())
            );

            apiClient.criarAluno(req);

            view.exibirMensagem("Aluno " + nome + " matriculado com sucesso!");

            view.getTxtNomeAluno().setText("");
            view.getTxtDataNasc().setValue(null);
            view.getTxtDataNasc().setText("");

        } catch (Exception ex) {
            ex.printStackTrace();
            view.exibirMensagem("Erro ao matricular: " + ex.getMessage());
        }
    }

    public static class ComboBoxItem {
        private String nome;
        private String id;

        public ComboBoxItem(String nome, String id) {
            this.nome = nome;
            this.id = id;
        }
        public String getId() { return id; }
        @Override
        public String toString() { return nome; }
    }
}