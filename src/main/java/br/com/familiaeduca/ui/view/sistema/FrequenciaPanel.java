package br.com.familiaeduca.ui.view.sistema;

import br.com.familiaeduca.ui.controller.FrequenciaController;
import br.com.familiaeduca.ui.util.UiConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FrequenciaPanel extends JPanel {

    private JPanel mainPanel;
    private JTable tblFrequencia;
    private JButton salvarChamadaButton;
    private JComboBox<String> cbTurma;

    private FrequenciaController controller;
    private DefaultTableModel tableModel;

    public FrequenciaPanel() {

        setLayout(new BorderLayout());
        if (mainPanel != null) {
            add(mainPanel, BorderLayout.CENTER);
        } else {
            add(new JLabel("Erro: mainPanel não encontrado no .form"), BorderLayout.CENTER);
        }

        configurarTabela();

        // Estilos
        if (salvarChamadaButton != null)
            UiConstants.styleButton(salvarChamadaButton);

        if (tblFrequencia != null)
            UiConstants.styleTable(tblFrequencia);

        if (cbTurma != null)
            UiConstants.styleField(cbTurma);

        // Controller
        this.controller = new FrequenciaController(this);
        this.controller.inicializar();

        // EVENTO: ao trocar a turma, recarregar tabela
        if (cbTurma != null) {
            cbTurma.addActionListener(e -> controller.carregarFrequenciaDaTurma());
        }
    }

    /** CONFIGURA AS COLUNAS DA TABELA */
    private void configurarTabela() {
        String[] colunas = {"ID", "Matrícula", "Aluno", "Data", "Presente"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 4) return Boolean.class; // checkbox
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int col) {
                return col == 4; // só a coluna "Presente" pode ser editada
            }
        };

        if (tblFrequencia != null) {
            tblFrequencia.setModel(tableModel);
            tblFrequencia.setRowHeight(28);
        }
    }

    /** GETTERS PARA O CONTROLLER */
    public JButton getBtnSalvarChamada() {
        return salvarChamadaButton;
    }

    public DefaultTableModel getTabelaModel() {
        return tableModel;
    }

    public JTable getTabela() {
        return tblFrequencia;
    }

    public String getTurmaSelecionada() {
        return (cbTurma != null) ? (String) cbTurma.getSelectedItem() : null;
    }

    public void exibirMensagem(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public String perguntar(String msg) {
        return JOptionPane.showInputDialog(this, msg);
    }
    public void adicionarTurma(String turma) {
        if (cbTurma != null) {
            cbTurma.addItem(turma);
        }
    }
}