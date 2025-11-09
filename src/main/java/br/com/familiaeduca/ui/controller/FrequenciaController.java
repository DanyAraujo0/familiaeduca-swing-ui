package br.com.familiaeduca.ui.controller;

import br.com.familiaeduca.ui.dto.FrequenciaDto;
import br.com.familiaeduca.ui.service.FamiliaEducaApiClient;
import br.com.familiaeduca.ui.util.SessaoUsuario;
import br.com.familiaeduca.ui.view.sistema.FrequenciaPanel;

import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class FrequenciaController{

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
        //carregarDadosTabela();
        configurarAcoes();
    }

    // Lógica de permissão (habilita/desabilita botões)
    private void configurarPermissoes() {
        view.getBtnAddPresenca().setEnabled(!sessao.isProfessor());
    }

    // Lógica de carregamento (substitui loadCSVIntoModel)
/*    public void carregarDadosTabela() {
        DefaultTableModel model = view.getTabelaFrequenciaModel();
        model.setRowCount(0); // Limpa a tabela

        try {
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
*/
    // Lógica de Ação (substitui o ActionListener)
    private void configurarAcoes() {
        view.getBtnAddPresenca().addActionListener(e -> adicionarPresenca());
    }

    private void adicionarPresenca() {
        String matriculaStr = view.perguntar("Matrícula do aluno:");
        String dataStr = view.perguntar("Data (yyyy-mm-dd):");
        String presenteStr = view.perguntar("Presente? (Sim/Não):");
        String turmaIdStr = view.perguntar("ID da turma:");

        if (matriculaStr != null && dataStr != null && presenteStr != null && turmaIdStr != null) {
            try {
                int matricula = Integer.parseInt(matriculaStr);
                LocalDate data = LocalDate.parse(dataStr);
                boolean presente = presenteStr.equalsIgnoreCase("sim");
                UUID turmaId = UUID.fromString(turmaIdStr);

                // Cria DTO compatível com o backend
                FrequenciaDto dto = new FrequenciaDto(matricula, data, presente, turmaId);

                // Chama o backend
                apiClient.addFrequencia(sessao.getToken(), dto);

                view.exibirMensagem("Presença adicionada com sucesso!");
               // carregarDadosTabela();

            } catch (Exception e) {
                view.exibirMensagem("Erro ao adicionar presença: " + e.getMessage());
            }
        }
    }

}