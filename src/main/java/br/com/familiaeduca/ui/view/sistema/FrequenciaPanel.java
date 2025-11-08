// No ficheiro .java
package br.com.familiaeduca.ui.view.sistema;

import br.com.familiaeduca.ui.controller.FrequenciaController;
import br.com.familiaeduca.ui.util.UiConstants;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FrequenciaPanel extends JPanel {

    // Pessoas B/C: Criem esta classe como "GUI Form (JPanel)"
    // Adicionem os componentes no GUI Designer e deem estes nomes:
    private JTable tblFrequencia;
    private JButton btnAddPresenca;

    private FrequenciaController controller;
    private DefaultTableModel tableModel;

    public FrequenciaPanel() {
        // REMOVIDO: initComponents();

        configurarTabela();

        // Liga esta View ao Controller
        this.controller = new FrequenciaController(this);

        // O Controller é quem vai carregar os dados e configurar os botões
        this.controller.inicializar();
    }

    private void configurarTabela() {
        // Define as colunas (o Controller só adiciona as linhas)
        String[] cols = {"ID", "Aluno", "Data", "Presente"};
        tableModel = new DefaultTableModel(cols, 0);
        tblFrequencia.setModel(tableModel);
    }

    // Métodos "Get" para o Controller
    public DefaultTableModel getTabelaFrequenciaModel() { return tableModel; }
    public JButton getBtnAddPresenca() { return btnAddPresenca; }

    // Métodos de feedback para o Controller
    public void exibirMensagem(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
    public String perguntar(String msg) {
        return JOptionPane.showInputDialog(this, msg);
    }

    // ... (initComponents() gerado pelo IntelliJ) ...
    // ... (não se esqueça de aplicar styleButton(btnAddPresenca)) ...
}