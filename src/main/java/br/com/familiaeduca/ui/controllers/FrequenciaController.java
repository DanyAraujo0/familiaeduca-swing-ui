package br.com.familiaeduca.ui.controllers;

import br.com.familiaeduca.ui.dto.FrequenciaDto;
import br.com.familiaeduca.ui.dto.TurmaDto;
import br.com.familiaeduca.ui.service.FamiliaEducaApiClient;
import br.com.familiaeduca.ui.util.SessaoUsuario;
import br.com.familiaeduca.ui.view.sistema.FrequenciaPanel;

import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.UUID;

public class FrequenciaController {

    private final FrequenciaPanel view;
    private final FamiliaEducaApiClient apiClient;

    public FrequenciaController(FrequenciaPanel view) {
        this.view = view;
        this.apiClient = new FamiliaEducaApiClient();
    }

    public void inicializar() {
        configurarListeners();
        carregarTurmas();
    }

    private void configurarListeners() {
        view.getBtnSalvarChamada().addActionListener(e -> registrarChamada());

        view.getCbTurma().addActionListener(e -> {
            // Agora pegamos o item objeto, não string
            TurmaItem item = (TurmaItem) view.getCbTurma().getSelectedItem();
            if (item != null) {
                carregarFrequenciaDaTurma(item.getId());
            }
        });
    }

    private void carregarTurmas() {
        try {
            view.limparTurmas();
            TurmaDto.TurmaResumeResponse[] turmas = apiClient.listarTurmas();

            for (TurmaDto.TurmaResumeResponse t : turmas) {
                // TRUQUE: Adicionamos o Objeto, não a String
                view.getCbTurma().addItem(new TurmaItem(t.nome(), UUID.fromString(t.id())));
            }

            if (turmas.length == 0) {
                view.exibirMensagem("Nenhuma turma encontrada.");
            }

        } catch (Exception ex) {
            view.exibirMensagem("Erro ao carregar turmas: " + ex.getMessage());
        }
    }

    public void carregarFrequenciaDaTurma(UUID idTurma) {
        try {
            DefaultTableModel model = view.getTabelaModel();
            model.setRowCount(0);

            TurmaDto.TurmaResponse turma = apiClient.buscarTurmaPorId(idTurma);

            if (turma.alunos() != null) {
                for (TurmaDto.AlunoResumeResponse aluno : turma.alunos()) {
                    model.addRow(new Object[]{
                            aluno.matricula(),
                            aluno.nome(),
                            LocalDate.now().toString(),
                            false
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.exibirMensagem("Erro ao carregar alunos: " + e.getMessage());
        }
    }

    public void registrarChamada() {
        try {
            DefaultTableModel model = view.getTabelaModel();
            TurmaItem item = (TurmaItem) view.getCbTurma().getSelectedItem();

            if (item == null) {
                view.exibirMensagem("Selecione uma turma.");
                return;
            }

            int registros = 0;
            for (int i = 0; i < model.getRowCount(); i++) {
                Boolean presente = (Boolean) model.getValueAt(i, 3);
                // Garante que não seja nulo
                if (presente == null) presente = false;

                Integer matricula = (Integer) model.getValueAt(i, 0);

                FrequenciaDto dto = new FrequenciaDto();
                dto.setData(LocalDate.now());
                dto.setPresente(presente);
                dto.setMatriculaAluno(matricula);
                dto.setIdTurma(item.getId());
                dto.setIdProfessor(UUID.fromString(SessaoUsuario.getInstance().getUsuarioLogado().getId()));

                apiClient.registrarFrequencia(dto);
                registros++;
            }
            view.exibirMensagem("Frequência registrada para " + registros + " alunos.");

        } catch (Exception e) {
            view.exibirMensagem("Erro ao registrar chamada: " + e.getMessage());
        }
    }

    // === CLASSE AUXILIAR PARA O COMBOBOX FICAR BONITO ===
    public static class TurmaItem {
        private String nome;
        private UUID id;

        public TurmaItem(String nome, UUID id) {
            this.nome = nome;
            this.id = id;
        }

        public UUID getId() { return id; }

        @Override
        public String toString() {
            return nome; // O ComboBox usa esse método para mostrar o texto
        }
    }
}