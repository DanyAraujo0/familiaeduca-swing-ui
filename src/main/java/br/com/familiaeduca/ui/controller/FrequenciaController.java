package br.com.familiaeduca.ui.controller;

import br.com.familiaeduca.ui.view.sistema.FrequenciaPanel;

import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.List;

public class FrequenciaController {

    private final FrequenciaPanel view;

    public FrequenciaController(FrequenciaPanel view) {
        this.view = view;
    }

    // Método chamado pelo panel
    public void inicializar() {
        carregarTurmas();
        configurarBtnSalvar();
    }

    /**
     * Carrega as turmas no comboBox
     */
    private void carregarTurmas() {
        try {
            List<String> turmas = List.of("Turma A", "Turma B", "Turma C");

            for (String t : turmas) {
                view.adicionarTurma(t);   // <-- corrigido (agora usa método público)
            }

        } catch (Exception e) {
            view.exibirMensagem("Erro ao carregar turmas: " + e.getMessage());
        }
    }

    /**
     * Configura o botão para registrar chamada
     */
    private void configurarBtnSalvar() {
        view.getBtnAddPresenca().addActionListener(e -> registrarChamada());
    }

    /**
     * Carrega a frequência da turma
     */
    public void carregarFrequenciaDaTurma(String turma) {
        try {
            DefaultTableModel model = view.getTabelaFrequenciaModel();
            model.setRowCount(0);

            List<String> alunos = List.of("Maria", "João", "Pedro");

            for (String a : alunos) {
                model.addRow(new Object[]{
                        1,
                        a,
                        LocalDate.now().toString(),
                        "Presente"
                });
            }

        } catch (Exception e) {
            view.exibirMensagem("Erro ao carregar lista: " + e.getMessage());
        }
    }

    /**
     * Salva o registro da chamada
     */
    public void registrarChamada() {
        try {
            String turma = view.getTurmaSelecionada();

            if (turma == null || turma.isEmpty()) {
                view.exibirMensagem("Selecione uma turma.");
                return;
            }

            DefaultTableModel model = view.getTabelaFrequenciaModel();

            if (model.getRowCount() == 0) {
                view.exibirMensagem("Nenhum aluno carregado.");
                return;
            }

            String confirmacao = view.perguntar(
                    "Confirmar registro de presença para esta turma? (Digite 'sim')"
            );

            if (confirmacao == null || !confirmacao.equalsIgnoreCase("sim")) {
                view.exibirMensagem("Registro cancelado.");
                return;
            }

            for (int i = 0; i < model.getRowCount(); i++) {
                Integer id = (Integer) model.getValueAt(i, 0);
                String aluno = (String) model.getValueAt(i, 1);
                String data = (String) model.getValueAt(i, 2);
                String status = (String) model.getValueAt(i, 3);

                System.out.println("Salvando: " + id + " - " + aluno + " - " + data + " - " + status);
            }

            view.exibirMensagem("Frequência registrada com sucesso!");

        } catch (Exception e) {
            view.exibirMensagem("Erro ao salvar chamada: " + e.getMessage());
        }
    }
}
