package br.com.familiaeduca.ui.view.sistema;

import br.com.familiaeduca.ui.controllers.FrequenciaController;
import br.com.familiaeduca.ui.util.UiConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer; // Import necessário para centralizar
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FrequenciaPanel extends JPanel {

    private JPanel mainPanel;
    private JTable tblFrequencia;
    private JScrollPane scrollPaneTabela;
    private JButton salvarChamadaButton;
    private JComboBox<Object> cbTurma; // JComboBox de Object para guardar o Item

    private FrequenciaController controller;
    private DefaultTableModel tableModel;

    public FrequenciaPanel() {
        setLayout(new BorderLayout());
        inicializarComponentes();

        if (mainPanel == null) {
            mainPanel = new JPanel(new BorderLayout());
            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            topPanel.add(new JLabel("Turma:"));
            topPanel.add(cbTurma);
            topPanel.add(salvarChamadaButton);
            topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            mainPanel.add(topPanel, BorderLayout.NORTH);
            mainPanel.add(scrollPaneTabela, BorderLayout.CENTER);
        }

        add(mainPanel, BorderLayout.CENTER);
        this.controller = new FrequenciaController(this);
        this.controller.inicializar();
    }

    private void inicializarComponentes() {
        if (cbTurma == null) cbTurma = new JComboBox<>();
        if (salvarChamadaButton == null) salvarChamadaButton = new JButton("Salvar Chamada");
        if (tblFrequencia == null) tblFrequencia = new JTable();

        configurarTabela(); // <--- A MÁGICA ACONTECE AQUI

        if (scrollPaneTabela == null) {
            scrollPaneTabela = new JScrollPane(tblFrequencia);
        } else {
            scrollPaneTabela.setViewportView(tblFrequencia);
        }

        UiConstants.styleButton(salvarChamadaButton);
        UiConstants.styleTable(tblFrequencia);
        UiConstants.styleField(cbTurma);

        // Estilo do Cabeçalho
        tblFrequencia.getTableHeader().setVisible(true);
        tblFrequencia.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
    }

    private void configurarTabela() {
        String[] colunas = {"Matrícula", "Nome do Aluno", "Data", "Presença"};

        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 3) return Boolean.class;
                return String.class;
            }
            @Override
            public boolean isCellEditable(int row, int col) {
                return col == 3;
            }
        };

        tblFrequencia.setModel(tableModel);
        tblFrequencia.setRowHeight(30);
        tblFrequencia.setFillsViewportHeight(true);

        // =======================================================
        // CENTRALIZANDO O TEXTO
        // =======================================================
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Aplica o centralizador nas colunas 0, 1 e 2 (Matricula, Nome, Data)
        if (tblFrequencia.getColumnCount() > 0) {
            tblFrequencia.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Matrícula
            tblFrequencia.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // Nome
            tblFrequencia.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); // Data

            // Ajuste de larguras
            tblFrequencia.getColumnModel().getColumn(0).setMaxWidth(100);
            tblFrequencia.getColumnModel().getColumn(3).setMaxWidth(80);
        }
    }

    // Getters
    public void exibirMensagem(String msg) { JOptionPane.showMessageDialog(this, msg); }
    public void limparTurmas() { cbTurma.removeAllItems(); }
    public JButton getBtnSalvarChamada() { return salvarChamadaButton; }
    public JComboBox<Object> getCbTurma() { return cbTurma; }
    public DefaultTableModel getTabelaModel() { return tableModel; }
}