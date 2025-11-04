package br.com.familiaeduca.ui.controller;

import br.com.familiaeduca.ui.dto.FrequenciaDto;
import br.com.familiaeduca.ui.service.FamiliaEducaApiClient;
import br.com.familiaeduca.ui.util.SessaoUsuario;
import br.com.familiaeduca.ui.view.sistema.FrequenciaPanel;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class FrequenciaController {

    private final FrequenciaPanel view;
    private final FamiliaEducaApiClient apiClient;
    private final SessaoUsuario sessao;

    public FrequenciaController(FrequenciaPanel view) {
        this.view = view;
        this.apiClient = new FamiliaEducaApiClient();
        this.sessao = SessaoUsuario.getInstance();
    }

    // Chamado pela View assim que ela é criada
    public void inicializar() {
        configurarPermissoes();
        carregarDadosTabela();
        configurarAcoes();
    }

    // Lógica de permissão (habilita/desabilita botões)
    private void configurarPermissoes() {
        view.getBtnAddPresenca().setEnabled(!sessao.isAluno());
    }

    // Lógica de carregamento (substitui loadCSVIntoModel)
    public void carregarDadosTabela() {
        DefaultTableModel model = view.getTabelaFrequenciaModel();
        model.setRowCount(0); // Limpa a tabela

        try {
            // [PESSOA A] O apiClient.getFrequencia deve ser implementado
            // A API já deve filtrar os dados (só do aluno, ou todos)
            List<FrequenciaDto> frequencias = apiClient.getFrequencia(sessao.getToken());

            for (FrequenciaDto freq : frequencias) {
                model.addRow(new Object[]{
                        freq.getId(),
                        freq.getAlunoNome(),
                        freq.getData(),
                        freq.isPresente() ? "Sim" : "Não"
                });
            }
        } catch (Exception e) {
            view.exibirMensagem("Erro ao carregar frequência: " + e.getMessage());
        }
    }

    // Lógica de Ação (substitui o ActionListener)
    private void configurarAcoes() {
        view.getBtnAddPresenca().addActionListener(e -> adicionarPresenca());
    }

    private void adicionarPresenca() {
        String aluno = view.perguntar("Nome do aluno:");
        String data = view.perguntar("Data (yyyy-mm-dd):");
        String presente = view.perguntar("Presente? (Sim/Não):");

        if (aluno != null && data != null && presente != null) {

            // [PESSOA A] Criar o FrequenciaDto e o método addFrequencia
            // FrequenciaDto novoDto = new FrequenciaDto(aluno, data, presente);
            // apiClient.addFrequencia(sessao.getToken(), novoDto);

            view.exibirMensagem("Presença adicionada com sucesso!");
            carregarDadosTabela(); // Recarrega a tabela
        }
    }
}