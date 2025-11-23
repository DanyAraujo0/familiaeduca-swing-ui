package br.com.familiaeduca.ui.view.sistema;

import br.com.familiaeduca.ui.controllers.ReunioesController;
import br.com.familiaeduca.ui.util.UiConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReunioesPanel extends JPanel {

    private JTable tblReunioes;
    private JButton btnSolicitarReuniao;
    private JButton btnAtualizar;
    private DefaultTableModel tableModel;

    private ReunioesController controller;

    public ReunioesPanel() {
        setLayout(new BorderLayout());
        inicializarComponentes();
        construirLayout();

        this.controller = new ReunioesController(this);
        this.controller.inicializar();
    }

    private void inicializarComponentes() {
        btnSolicitarReuniao = new JButton("Agendar Reunião (+)");
        btnAtualizar = new JButton("Atualizar Lista");

        tblReunioes = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"Data", "Motivo", "Responsável", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblReunioes.setModel(tableModel);
        tblReunioes.setRowHeight(30);
        tblReunioes.setFillsViewportHeight(true);

        UiConstants.styleButton(btnSolicitarReuniao);
        UiConstants.styleTable(tblReunioes);
    }

    private void construirLayout() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(btnAtualizar);
        topPanel.add(btnSolicitarReuniao);

        JScrollPane scroll = new JScrollPane(tblReunioes);
        scroll.setBorder(BorderFactory.createTitledBorder("Agenda de Reuniões"));

        add(topPanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    // Getters
    public JTable getTblReunioes() { return tblReunioes; }
    public DefaultTableModel getTabelaModel() { return tableModel; }
    public JButton getBtnSolicitarReuniao() { return btnSolicitarReuniao; }
    public JButton getBtnAtualizar() { return btnAtualizar; }
}