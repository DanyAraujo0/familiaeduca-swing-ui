package br.com.familiaeduca.ui.view.sistema;

import br.com.familiaeduca.ui.controllers.FrequenciaController;
import br.com.familiaeduca.ui.util.UiConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FrequenciaPanel extends JPanel {

    // Componentes
    private JPanel mainPanel;
    private JTable tblFrequencia;
    private JScrollPane scrollPaneTabela; // Importante para o cabeçalho
    private JButton salvarChamadaButton;
    private JComboBox<String> cbTurma;

    private FrequenciaController controller;
    private DefaultTableModel tableModel;

    public FrequenciaPanel() {
        setLayout(new BorderLayout());

        // 1. Inicialização dos componentes visuais
        inicializarComponentes();

        // 2. Adiciona ao painel principal
        // Se você usa GUI Builder (.form), o mainPanel já vem instanciado.
        // Se for manual, instanciamos aqui:
        if (mainPanel == null) {
            mainPanel = new JPanel(new BorderLayout());

            // Painel superior para o combo e botão
            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            topPanel.add(cbTurma);
            topPanel.add(salvarChamadaButton);

            mainPanel.add(topPanel, BorderLayout.NORTH);

            // ADICIONANDO O SCROLLPANE (Isso faz o cabeçalho aparecer)
            mainPanel.add(scrollPaneTabela, BorderLayout.CENTER);
        }

        add(mainPanel, BorderLayout.CENTER);

        // 3. Controller
        this.controller = new FrequenciaController(this);
        this.controller.inicializar();
    }

    private void inicializarComponentes() {
        cbTurma = new JComboBox<>();
        salvarChamadaButton = new JButton("Salvar Chamada");

        // Configuração da Tabela
        tblFrequencia = new JTable();
        configurarTabela(); // Define colunas e modelo

        // *** O SEGREDO DO CABEÇALHO ***
        // A tabela deve ser "envelopada" por um JScrollPane
        scrollPaneTabela = new JScrollPane(tblFrequencia);

        // Estilos (Opcional)
        UiConstants.styleButton(salvarChamadaButton);
        UiConstants.styleTable(tblFrequencia);
        UiConstants.styleField(cbTurma);
    }

    private void configurarTabela() {
        // CORREÇÃO: Títulos exatos que você pediu
        // Removemos "Matrícula" para simplificar conforme seu pedido: "id nome data e Presente?"
        String[] colunas = {"ID", "Nome", "Data", "Presente?"};

        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // A coluna 3 (a quarta coluna) é Boolean -> vira Checkbox
                if (columnIndex == 3) return Boolean.class;
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int col) {
                // Apenas a coluna "Presente?" é editável
                return col == 3;
            }
        };

        tblFrequencia.setModel(tableModel);
        tblFrequencia.setRowHeight(30);

        // Ajuste de largura (opcional)
        tblFrequencia.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID pequeno
        tblFrequencia.getColumnModel().getColumn(1).setPreferredWidth(300); // Nome grande
        tblFrequencia.getColumnModel().getColumn(3).setPreferredWidth(80);  // Checkbox pequeno
    }

    // --- Getters e Helpers ---

    public void exibirMensagem(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void adicionarTurma(String turma) {
        cbTurma.addItem(turma);
    }

    public void limparTurmas() {
        cbTurma.removeAllItems();
    }

    public String getTurmaSelecionada() {
        return (String) cbTurma.getSelectedItem();
    }

    public JButton getBtnSalvarChamada() {
        return salvarChamadaButton;
    }

    public JComboBox<String> getCbTurma() {
        return cbTurma;
    }

    public DefaultTableModel getTabelaModel() {
        return tableModel;
    }
}