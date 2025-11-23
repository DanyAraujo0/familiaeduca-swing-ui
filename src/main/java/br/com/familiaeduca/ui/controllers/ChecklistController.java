package br.com.familiaeduca.ui.controllers;

import br.com.familiaeduca.ui.dto.AlunoDto;
import br.com.familiaeduca.ui.dto.ChecklistDto;
import br.com.familiaeduca.ui.service.FamiliaEducaApiClient;
import br.com.familiaeduca.ui.util.SessaoUsuario;
import br.com.familiaeduca.ui.view.sistema.ChecklistPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
        aplicarRegrasDePerfil();
    }

    private void aplicarRegrasDePerfil() {
        // Se for Responsável, ele não pode salvar, apenas ver.
        if (SessaoUsuario.getInstance().isResponsavel()) {
            view.getBtnSalvarChecklist().setVisible(false);
            view.getTblChecklist().setEnabled(false); // Bloqueia cliques na tabela
        }
    }

    private void carregarAlunos() {
        try {
            view.getCbAlunoChecklist().removeAllItems();
            // Busca alunos (já filtrado pelo backend para Responsável/Professor)
            AlunoDto.AlunoResponse[] alunos = apiClient.listarAlunos();

            for (AlunoDto.AlunoResponse a : alunos) {
                view.getCbAlunoChecklist().addItem(new AlunoItem(a.getNome(), a.getMatricula()));
            }

            // Se tiver alunos, seleciona o primeiro e carrega os dados
            if (view.getCbAlunoChecklist().getItemCount() > 0) {
                view.getCbAlunoChecklist().setSelectedIndex(0);
                carregarDadosDoChecklist();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configurarEventos() {
        view.getBtnSalvarChecklist().addActionListener(e -> salvarChecklist());

        // Ao trocar de aluno, carrega o checklist dele
        view.getCbAlunoChecklist().addActionListener(e -> carregarDadosDoChecklist());
    }

    private void carregarDadosDoChecklist() {
        AlunoItem item = (AlunoItem) view.getCbAlunoChecklist().getSelectedItem();
        if (item == null) return;

        // 1. Limpa a tabela antes de carregar (reseta tudo para unchecked)
        limparTabela();

        // 2. Busca no servidor
        ChecklistDto.ChecklistResponse checklist = apiClient.buscarChecklistHoje(item.getMatricula());

        if (checklist != null && checklist.getObservacoes() != null) {
            // 3. PREENCHE A TABELA COM BASE NO TEXTO
            // O texto vem assim: "- Sono: SIM (obs)"
            String textoCompleto = checklist.getObservacoes();
            DefaultTableModel model = view.getTabelaModel();

            for (int i = 0; i < model.getRowCount(); i++) {
                String nomeAtividade = (String) model.getValueAt(i, 0);

                // Verifica se no texto tem "Atividade: SIM"
                if (textoCompleto.contains("- " + nomeAtividade + ": SIM")) {
                    model.setValueAt(true, i, 1); // Marca Checkbox
                } else {
                    model.setValueAt(false, i, 1);
                }

                // Opcional: Extrair observação seria mais complexo com string pura,
                // então por enquanto focamos no Checkbox.
            }
        }
    }

    private void limparTabela() {
        for (int i = 0; i < view.getTabelaModel().getRowCount(); i++) {
            view.getTabelaModel().setValueAt(false, i, 1);
            view.getTabelaModel().setValueAt("", i, 2);
        }
    }

    private void salvarChecklist() {
        try {
            AlunoItem alunoSelecionado = (AlunoItem) view.getCbAlunoChecklist().getSelectedItem();
            if (alunoSelecionado == null) return;

            StringBuilder observacoes = new StringBuilder();
            observacoes.append("Rotina do dia ").append(LocalDate.now()).append(":\n");

            for (int i = 0; i < view.getTabelaModel().getRowCount(); i++) {
                String item = (String) view.getTabelaModel().getValueAt(i, 0);
                Boolean status = (Boolean) view.getTabelaModel().getValueAt(i, 1);
                String obs = (String) view.getTabelaModel().getValueAt(i, 2);

                String statusTexto = (status != null && status) ? "SIM" : "NÃO";
                observacoes.append("- ").append(item).append(": ").append(statusTexto);
                if (obs != null && !obs.isEmpty()) observacoes.append(" (").append(obs).append(")");
                observacoes.append("\n");
            }

            String idProf = SessaoUsuario.getInstance().getUsuarioLogado().getId();

            ChecklistDto.CreateChecklistProfessorRequest req = new ChecklistDto.CreateChecklistProfessorRequest(
                    LocalDate.now(),
                    observacoes.toString(),
                    alunoSelecionado.getMatricula(),
                    UUID.fromString(idProf)
            );

            apiClient.criarChecklistProfessor(req);
            JOptionPane.showMessageDialog(view, "Checklist salvo com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro ao salvar: " + e.getMessage());
        }
    }

    private static class AlunoItem {
        String nome; Integer matricula;
        public AlunoItem(String nome, Integer matricula) { this.nome = nome; this.matricula = matricula; }
        public Integer getMatricula() { return matricula; }
        @Override public String toString() { return nome; }
    }
}