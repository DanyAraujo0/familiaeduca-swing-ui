package br.com.familiaeduca.ui.controllers;

import br.com.familiaeduca.ui.dto.AlunoDto;
import br.com.familiaeduca.ui.dto.ChecklistDto;
import br.com.familiaeduca.ui.service.FamiliaEducaApiClient;
import br.com.familiaeduca.ui.util.SessaoUsuario;
import br.com.familiaeduca.ui.view.sistema.ChecklistPanel;

import javax.swing.*;
import java.time.LocalDate;
import java.util.UUID;

public class ChecklistController {

    private final ChecklistPanel view;
    private final FamiliaEducaApiClient apiClient;

    public ChecklistController(ChecklistPanel view) {
        this.view = view;
        this.apiClient = new FamiliaEducaApiClient();
    }

    public void inicializar() {
        carregarAlunos();
        configurarEventos();

        if (!SessaoUsuario.getInstance().isProfessor()) {
            view.getBtnSalvarChecklist().setEnabled(false);
            view.getBtnSalvarChecklist().setText("Apenas Professores podem salvar");
            view.getBtnSalvarChecklist().setToolTipText("Você está logado como Diretor/Responsável.");
        }
    }

    private void carregarAlunos() {
        try {
            view.getCbAlunoChecklist().removeAllItems();
            AlunoDto.AlunoResponse[] alunos = apiClient.listarAlunos();

            for (AlunoDto.AlunoResponse a : alunos) {
                view.getCbAlunoChecklist().addItem(new AlunoItem(a.getNome(), a.getMatricula()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configurarEventos() {
        view.getBtnSalvarChecklist().addActionListener(e -> salvarChecklist());
    }

    private void salvarChecklist() {
        try {
            AlunoItem alunoSelecionado = (AlunoItem) view.getCbAlunoChecklist().getSelectedItem();
            if (alunoSelecionado == null) {
                JOptionPane.showMessageDialog(view, "Selecione um aluno.");
                return;
            }

            // Captura os dados da tabela
            StringBuilder observacoes = new StringBuilder();
            observacoes.append("Rotina Diária:\n");

            for (int i = 0; i < view.getTabelaModel().getRowCount(); i++) {
                String item = (String) view.getTabelaModel().getValueAt(i, 0);
                Boolean status = (Boolean) view.getTabelaModel().getValueAt(i, 1);
                String obs = (String) view.getTabelaModel().getValueAt(i, 2);

                String statusTexto = (status != null && status) ? "SIM" : "NÃO";
                observacoes.append("- ").append(item).append(": ").append(statusTexto);
                if (obs != null && !obs.isEmpty()) {
                    observacoes.append(" (").append(obs).append(")");
                }
                observacoes.append("\n");
            }

            // Pega ID do professor logado
            String idProf = SessaoUsuario.getInstance().getUsuarioLogado().getId();

            ChecklistDto.CreateChecklistProfessorRequest req = new ChecklistDto.CreateChecklistProfessorRequest(
                    LocalDate.now(),
                    observacoes.toString(),
                    alunoSelecionado.getMatricula(),
                    UUID.fromString(idProf)
            );

            apiClient.criarChecklistProfessor(req);

            JOptionPane.showMessageDialog(view, "Checklist salvo com sucesso!");

            // Limpa a tabela
            for (int i = 0; i < view.getTabelaModel().getRowCount(); i++) {
                view.getTabelaModel().setValueAt(false, i, 1);
                view.getTabelaModel().setValueAt("", i, 2);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao salvar: " + e.getMessage());
        }
    }

    // Classe Auxiliar para o ComboBox
    private static class AlunoItem {
        String nome;
        Integer matricula;

        public AlunoItem(String nome, Integer matricula) {
            this.nome = nome;
            this.matricula = matricula;
        }
        public Integer getMatricula() { return matricula; }
        @Override
        public String toString() { return nome; }
    }
}