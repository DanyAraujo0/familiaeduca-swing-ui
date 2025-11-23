package br.com.familiaeduca.ui.controllers;

import br.com.familiaeduca.ui.dto.AvisoDto;
import br.com.familiaeduca.ui.service.FamiliaEducaApiClient;
import br.com.familiaeduca.ui.util.SessaoUsuario;
import br.com.familiaeduca.ui.view.sistema.AvisosPanel;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class AvisosController {

    private final AvisosPanel view;
    private final FamiliaEducaApiClient apiClient;

    public AvisosController(AvisosPanel view) {
        this.view = view;
        this.apiClient = new FamiliaEducaApiClient();
    }

    public void inicializar() {
        carregarAvisos();
        configurarEventos();

        if (!SessaoUsuario.getInstance().isDiretor()) {
            view.getBtnNovoAviso().setVisible(false);
        }
    }

    private void configurarEventos() {
        view.getBtnNovoAviso().addActionListener(e -> criarNovoAviso());

        // Configura o botão Atualizar
        view.getBtnAtualizar().addActionListener(e -> {
            System.out.("Botão Atualizar Clicado!");
            carregarAvisos();
        });

        view.getTblAvisos().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarDetalhesAviso();
            }
        });
    }

    private void carregarAvisos() {
        System.out.("Controller: Chamando carregarAvisos()...");
        try {
            view.getTabelaModel().setRowCount(0); // Limpa a tabela

            AvisoDto.AvisoResponse[] avisos = apiClient.listarAvisos();
            System.out.("Controller: Avisos recebidos: " + avisos.length);

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (AvisoDto.AvisoResponse a : avisos) {
                view.getTabelaModel().addRow(new Object[]{
                        (a.getDataPublicacao() != null) ? a.getDataPublicacao().format(fmt) : "",
                        a.getTitulo(),
                        a.getMensagem(),
                        a.getId()
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao carregar avisos: " + ex.getMessage());
        }
    }

    private void mostrarDetalhesAviso() {
        int row = view.getTblAvisos().getSelectedRow();
        if (row >= 0) {
            String mensagem = (String) view.getTabelaModel().getValueAt(row, 2);
            String titulo = (String) view.getTabelaModel().getValueAt(row, 1);
            view.getTxtDetalheAviso().setText("--- " + titulo + " ---\n\n" + mensagem);
        }
    }

    private void criarNovoAviso() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JTextField txtTitulo = new JTextField();
        JTextArea txtMsg = new JTextArea(5, 20);

        panel.add(new JLabel("Título:"));
        panel.add(txtTitulo);
        panel.add(new JLabel("Mensagem:"));
        panel.add(new JScrollPane(txtMsg));

        int result = JOptionPane.showConfirmDialog(view, panel, "Novo Aviso",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String titulo = txtTitulo.getText();
            String msg = txtMsg.getText();

            if (titulo.isEmpty() || msg.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Preencha todos os campos.");
                return;
            }
            enviarAvisoParaApi(titulo, msg);
        }
    }

    private void enviarAvisoParaApi(String titulo, String mensagem) {
        try {
            String idDiretorStr = SessaoUsuario.getInstance().getUsuarioLogado().getId();

            AvisoDto.CreateAvisoRequest req = new AvisoDto.CreateAvisoRequest(
                    titulo,
                    mensagem,
                    UUID.fromString(idDiretorStr)
            );

            apiClient.criarAviso(req);
            JOptionPane.showMessageDialog(view, "Aviso publicado!");
            carregarAvisos();
            view.getTxtDetalheAviso().setText("");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro: " + e.getMessage());
        }
    }
}