package br.com.familiaeduca.ui.controllers;

import br.com.familiaeduca.ui.dto.ReuniaoDto;
import br.com.familiaeduca.ui.dto.ResponsavelDto;
import br.com.familiaeduca.ui.service.FamiliaEducaApiClient;
import br.com.familiaeduca.ui.util.SessaoUsuario;
import br.com.familiaeduca.ui.view.sistema.ReunioesPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class ReunioesController {

    private final ReunioesPanel view;
    private final FamiliaEducaApiClient apiClient;

    public ReunioesController(ReunioesPanel view) {
        this.view = view;
        this.apiClient = new FamiliaEducaApiClient();
    }

    public void inicializar() {
        carregarReunioes();
        configurarEventos();
    }

    private void configurarEventos() {
        // Lógica do Botão Agendar/Solicitar
        view.getBtnSolicitarReuniao().addActionListener(e -> {

            // TRATAMENTO QUE VOCÊ PEDIU:
            if (SessaoUsuario.getInstance().isResponsavel()) {
                JOptionPane.showMessageDialog(view,
                        "Solicite pessoalmente com o Professor ou Diretor para agendar uma Reunião.",
                        "Agendamento",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Só abre o popup se for Diretor ou Professor
                abrirPopupSolicitacao();
            }
        });

        view.getBtnAtualizar().addActionListener(e -> carregarReunioes());
    }

    private void carregarReunioes() {
        try {
            view.getTabelaModel().setRowCount(0);
            ReuniaoDto.ReuniaoResponse[] lista;

            // LÓGICA INTELIGENTE DE LISTAGEM
            if (SessaoUsuario.getInstance().isResponsavel()) {
                // Se for Pai/Mãe, busca só as reuniões dele
                String meuId = SessaoUsuario.getInstance().getUsuarioLogado().getId();
                lista = apiClient.listarReunioesPorResponsavel(meuId);
            } else {
                // Se for Diretor, busca todas
                lista = apiClient.listarReunioes();
            }

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (ReuniaoDto.ReuniaoResponse r : lista) {
                view.getTabelaModel().addRow(new Object[]{
                        r.getData().format(fmt),
                        r.getMotivo(),
                        r.getNomeResponsavel(),
                        r.getStatus()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Se der erro (ex: backend offline), não explode na cara do usuário, só loga
            System.out.println("Erro ao carregar reuniões: " + e.getMessage());
        }
    }

    private void abrirPopupSolicitacao() {
        // ... (Este método continua igual, pois só o Diretor vai acessar) ...
        // Cria componentes para o popup
        JTextField txtData = new JTextField();
        JTextField txtMotivo = new JTextField();
        JComboBox<ComboBoxItem> cbResponsavel = new JComboBox<>();

        try {
            ResponsavelDto.ResponsavelResumeResponse[] resps = apiClient.listarResponsaveis();
            for (var r : resps) {
                cbResponsavel.addItem(new ComboBoxItem(r.getNome(), r.getId()));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erro ao carregar responsáveis: " + e.getMessage());
            return;
        }

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Responsável:"));
        panel.add(cbResponsavel);
        panel.add(new JLabel("Data (DD/MM/AAAA):"));
        panel.add(txtData);
        panel.add(new JLabel("Motivo:"));
        panel.add(txtMotivo);

        int result = JOptionPane.showConfirmDialog(view, panel, "Agendar Reunião",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                ComboBoxItem itemResp = (ComboBoxItem) cbResponsavel.getSelectedItem();
                if (itemResp == null) return;

                String dataTxt = txtData.getText();
                DateTimeFormatter fmtBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataReuniao = LocalDate.parse(dataTxt, fmtBR);

                ReuniaoDto.CreateReuniaoRequest req = new ReuniaoDto.CreateReuniaoRequest(
                        dataReuniao,
                        txtMotivo.getText(),
                        UUID.fromString(itemResp.getId())
                );

                apiClient.criarReuniao(req);
                JOptionPane.showMessageDialog(view, "Reunião agendada com sucesso!");
                carregarReunioes();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Erro ao agendar: " + ex.getMessage());
            }
        }
    }

    public static class ComboBoxItem {
        String nome, id;
        public ComboBoxItem(String nome, String id) { this.nome = nome; this.id = id; }
        public String getId() { return id; }
        public String toString() { return nome; }
    }
}