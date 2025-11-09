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
        // 1. Configura o layout para receber o painel do .form
        setLayout(new BorderLayout());
        if (mainPanel != null) {
            add(mainPanel, BorderLayout.CENTER);
        } else {
            add(new JLabel("Erro: mainPanel não encontrado no .form"), BorderLayout.CENTER);
        }

        // 2. Configura a tabela (se necessário)
        configurarTabela();

        // --- 3. APLICAÇÃO DE ESTILOS (Pós-injeção do .form) ---
        if (salvarChamadaButton != null) {
            UiConstants.styleButton(salvarChamadaButton);
            // salvarChamadaButton.setText("Registrar Frequência"); // Opcional: mudar texto via código
        }

        if (tblFrequencia != null) {
            UiConstants.styleTable(tblFrequencia);
        }

        if (cbTurma != null) {
            UiConstants.styleField(cbTurma);
        }

        // 4. Inicializa o Controlador
        this.controller = new FrequenciaController(this);
        this.controller.inicializar();
    }

    private void configurarTabela() {
        String[] colunas = {"ID", "Aluno", "Data", "Status"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };

        if (tblFrequencia != null) {
            tblFrequencia.setModel(tableModel);
        }
    }

    public JButton getBtnAddPresenca() {
        return salvarChamadaButton;
    }

    public DefaultTableModel getTabelaFrequenciaModel() {
        return (tblFrequencia != null) ? (DefaultTableModel) tblFrequencia.getModel() : tableModel;
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
}