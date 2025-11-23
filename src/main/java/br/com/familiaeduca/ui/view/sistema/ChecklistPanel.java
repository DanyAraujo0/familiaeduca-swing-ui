package br.com.familiaeduca.ui.view.sistema;

import br.com.familiaeduca.ui.controllers.ChecklistController;
import br.com.familiaeduca.ui.util.UiConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ChecklistPanel extends JPanel {

    private JComboBox<Object> cbAlunoChecklist;
    private JTable tblChecklist;
    private JButton btnSalvarChecklist;
    private DefaultTableModel tableModel;

    private ChecklistController controller;

    public ChecklistPanel() {
        setLayout(new BorderLayout());
        inicializarComponentes();
        construirLayout();

        this.controller = new ChecklistController(this);
        this.controller.inicializar();
    }

    private void inicializarComponentes() {
        cbAlunoChecklist = new JComboBox<>();
        btnSalvarChecklist = new JButton("Salvar Checklist Diário");

        tblChecklist = new JTable();

        // Cria modelo com colunas
        tableModel = new DefaultTableModel(new Object[]{"Atividade", "Realizado?", "Observação Rápida"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if(columnIndex == 1) return Boolean.class;
                return String.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };

        tblChecklist.setModel(tableModel);
        tblChecklist.setRowHeight(30);

        // Adiciona itens fixos na tabela
        adicionarItensPadrao();

        UiConstants.styleButton(btnSalvarChecklist);
        UiConstants.styleField(cbAlunoChecklist);
        UiConstants.styleTable(tblChecklist);
    }

    private void adicionarItensPadrao() {
        tableModel.addRow(new Object[]{"Alimentação (Lanche)", false, ""});
        tableModel.addRow(new Object[]{"Alimentação (Almoço)", false, ""});
        tableModel.addRow(new Object[]{"Sono / Soneca", false, ""});
        tableModel.addRow(new Object[]{"Troca de Fralda / Banheiro", false, ""});
        tableModel.addRow(new Object[]{"Participação nas Atividades", false, ""});
        tableModel.addRow(new Object[]{"Humor / Comportamento", false, ""});
    }

    private void construirLayout() {
        // Painel Topo
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Selecione o Aluno:"));
        topPanel.add(cbAlunoChecklist);

        // Painel Centro (Tabela)
        JScrollPane scrollPane = new JScrollPane(tblChecklist);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Itens de Rotina"));

        // Painel Baixo (Botão)
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(btnSalvarChecklist);

        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 40, 20));

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Getters
    public JComboBox<Object> getCbAlunoChecklist() { return cbAlunoChecklist; }
    public DefaultTableModel getTabelaModel() { return tableModel; }
    public JButton getBtnSalvarChecklist() { return btnSalvarChecklist; }
    public JTable getTblChecklist() { return tblChecklist; }
}