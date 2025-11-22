package br.com.familiaeduca.ui.controllers;

import br.com.familiaeduca.ui.view.sistema.FrequenciaPanel;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FrequenciaController {

    private final FrequenciaPanel view;

    public FrequenciaController(FrequenciaPanel view) {
        this.view = view;
    }

    public void inicializar() {
        configurarListeners();
        carregarTurmas();
    }

    private void configurarListeners() {
        view.getBtnSalvarChamada().addActionListener(e -> registrarChamada());

        // Listener que detecta a troca de turma
        view.getCbTurma().addActionListener(e -> {
            String turmaSelecionada = view.getTurmaSelecionada();
            if (turmaSelecionada != null) {
                carregarFrequenciaDaTurma(turmaSelecionada);
            }
        });
    }

    private void carregarTurmas() {
        view.limparTurmas();
        // Adiciona as turmas disponíveis
        List<String> turmas = List.of("Turma A", "Turma B", "Turma C");
        for (String t : turmas) {
            view.adicionarTurma(t);
        }
        // Seleciona a primeira para disparar o evento e carregar a tabela
        if (!turmas.isEmpty()) view.getCbTurma().setSelectedIndex(0);
    }

    /**
     * CORREÇÃO: Agora verificamos QUAL turma é para carregar alunos diferentes
     */
    public void carregarFrequenciaDaTurma(String turma) {
        try {
            DefaultTableModel model = view.getTabelaModel();
            model.setRowCount(0); // Limpa a tabela anterior

            List<String> alunos = new ArrayList<>();

            // Simulação de banco de dados: Alunos diferentes por turma
            switch (turma) {
                case "Turma A":
                    alunos = List.of("Maria", "João", "Pedro", "Ana");
                    break;
                case "Turma B":
                    alunos = List.of("Carlos", "Beatriz", "Fernanda");
                    break;
                case "Turma C":
                    alunos = List.of("Ricardo", "Sofia");
                    break;
                default:
                    alunos = List.of();
            }

            int idFicticio = 1;
            String dataHoje = LocalDate.now().toString();

            for (String nomeAluno : alunos) {
                model.addRow(new Object[]{
                        idFicticio,     // ID
                        nomeAluno,      // Nome
                        dataHoje,       // Data
                        false           // Presente? (Começa desmarcado)
                });
                idFicticio++;
            }

        } catch (Exception e) {
            view.exibirMensagem("Erro ao carregar lista: " + e.getMessage());
        }
    }

    public void registrarChamada() {
        // (Mantenha o código de registrar chamada igual ao anterior)
        try {
            DefaultTableModel model = view.getTabelaModel();
            int totalPresentes = 0;

            System.out.println("Registrando chamada para: " + view.getTurmaSelecionada());

            for (int i = 0; i < model.getRowCount(); i++) {
                Boolean isPresente = (Boolean) model.getValueAt(i, 3); // Coluna 3 é o checkbox
                String nome = (String) model.getValueAt(i, 1);

                if (isPresente != null && isPresente) {
                    System.out.println("Presente: " + nome);
                    totalPresentes++;
                }
            }
            view.exibirMensagem("Chamada salva! Total de presentes: " + totalPresentes);
        } catch (Exception e) {
            view.exibirMensagem("Erro: " + e.getMessage());
        }
    }
}