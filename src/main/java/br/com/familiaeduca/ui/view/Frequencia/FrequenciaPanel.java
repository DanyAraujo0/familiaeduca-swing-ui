package br.com.familiaeduca.ui.view.Frequencia;

public class FrequenciaPanel {
    // No construtor de view/frequencia/FrequenciaPanel.java
    private FrequenciaController controller;

    public FrequenciaPanel() {
        // ... código do IntelliJ (initComponents) ...

        this.controller = new FrequenciaController(this);
        this.controller.inicializar(); // O controller faz o resto
    }

    // Métodos de ajuda para o controller
    public DefaultTableModel getTabelaFrequenciaModel() {
        return (DefaultTableModel) tblFrequencia.getModel();
    }
    public JButton getBtnAddPresenca() {
        return btnAddPresenca;
    }
    public void exibirMensagem(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
    public String perguntar(String msg) {
        return JOptionPane.showInputDialog(this, msg);
    }
}
