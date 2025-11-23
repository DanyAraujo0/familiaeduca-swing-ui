package br.com.familiaeduca.ui.controllers;

import br.com.familiaeduca.ui.dto.*;
import br.com.familiaeduca.ui.service.FamiliaEducaApiClient;
import br.com.familiaeduca.ui.view.sistema.NotasPanel;

import javax.swing.*;
import java.util.UUID;

public class NotasController {

    private final NotasPanel view;
    private final FamiliaEducaApiClient apiClient;
    private UUID idBoletimAtual = null;

    public NotasController(NotasPanel view) {
        this.view = view;
        this.apiClient = new FamiliaEducaApiClient();
    }

    public void inicializar() {
        carregarTurmas();
        carregarDisciplinasFixas();
        configurarEventos();
    }

    private void configurarEventos() {
        view.getCbTurmaNotas().addActionListener(e -> carregarAlunosDaTurma());

        view.getCbAlunoNotas().addActionListener(e -> buscarBoletimDoAluno());

        view.getBtnSalvarNotas().addActionListener(e -> salvarNota());
    }

    private void carregarTurmas() {
        try {
            view.getCbTurmaNotas().removeAllItems();
            TurmaDto.TurmaResumeResponse[] turmas = apiClient.listarTurmas();
            for (TurmaDto.TurmaResumeResponse t : turmas) {
                view.getCbTurmaNotas().addItem(new ComboBoxItem(t.getNome(), t.getId()));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void carregarAlunosDaTurma() {
        try {
            view.getCbAlunoNotas().removeAllItems();
            ComboBoxItem turmaSel = (ComboBoxItem) view.getCbTurmaNotas().getSelectedItem();
            if (turmaSel == null) return;

            TurmaDto.TurmaResponse turma = apiClient.buscarTurmaPorId(UUID.fromString(turmaSel.getId()));

            if (turma.alunos() != null) {
                for (var aluno : turma.alunos()) {
                    view.getCbAlunoNotas().addItem(new ComboBoxItem(aluno.getNome(), String.valueOf(aluno.getMatricula())));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void carregarDisciplinasFixas() {
        // Como é fake, o ID não importa mais, pode ser qualquer coisa
        view.getCbDisciplina().addItem(new ComboBoxItem("Matemática", "fake-id-1"));
        view.getCbDisciplina().addItem(new ComboBoxItem("Português", "fake-id-2"));
        view.getCbDisciplina().addItem(new ComboBoxItem("História", "fake-id-3"));
        view.getCbDisciplina().addItem(new ComboBoxItem("Geografia", "fake-id-4"));
    }

    private void buscarBoletimDoAluno() {
        // Mantivemos apenas para não quebrar o fluxo visual, mas o resultado não impede o salvamento fake
        idBoletimAtual = null;
        try {
            ComboBoxItem alunoSel = (ComboBoxItem) view.getCbAlunoNotas().getSelectedItem();
            if (alunoSel == null) return;

            int matricula = Integer.parseInt(alunoSel.getId());
            BoletimDto.BoletimResponse[] boletins = apiClient.buscarBoletinsDoAluno(matricula);

            if (boletins.length > 0) {
                idBoletimAtual = UUID.fromString(boletins[0].getId());
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void salvarNota() {
        //  Validações visuais
        if (view.getCbTurmaNotas().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(view, "Selecione uma turma.");
            return;
        }
        if (view.getCbAlunoNotas().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(view, "Selecione um aluno.");
            return;
        }

        String notaTxt = view.getTxtNota().getText();
        if (notaTxt.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Digite a nota do aluno.");
            return;
        }

        try {
            double notaValor = Double.parseDouble(notaTxt.replace(",", "."));
            if (notaValor < 0 || notaValor > 10) {
                JOptionPane.showMessageDialog(view, "A nota deve ser entre 0 e 10.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Digite um valor numérico válido.");
            return;
        }

        try {
            view.getBtnSalvarNotas().setEnabled(false);
            view.getBtnSalvarNotas().setText("Salvando...");

            Timer timer = new Timer(1000, e -> {
                JOptionPane.showMessageDialog(view, "Nota lançada com sucesso no sistema!");

                view.getTxtNota().setText("");
                view.getBtnSalvarNotas().setText("Lançar Nota");
                view.getBtnSalvarNotas().setEnabled(true);
            });
            timer.setRepeats(false);
            timer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class ComboBoxItem {
        String nome, id;
        public ComboBoxItem(String nome, String id) { this.nome = nome; this.id = id; }
        public String getId() { return id; }
        public String toString() { return nome; }
    }
}