package br.com.familiaeduca.ui.view.sistema;

import br.com.familiaeduca.ui.util.UiConstants;

import javax.swing.*;
import java.awt.*;

public class NotasPanel extends JPanel {

    private JPanel mainPanel;
    private JComboBox cbDisciplina;
    private JComboBox cbTurmaNotas;
    private JTable tblNotas;
    private JButton btnSalvarNotas;

    public NotasPanel() {
        // 2. Configura o layout deste painel para BorderLayout
        setLayout(new BorderLayout());

        if (mainPanel != null) {
            add(mainPanel, BorderLayout.CENTER);
        } else {
            // Fallback caso algo dê errado na configuração do IDE
            add(new JLabel("Erro: Não foi possível carregar o design do .form"), BorderLayout.CENTER);
        }

        if (btnSalvarNotas != null) {
            UiConstants.styleButton(btnSalvarNotas);
        }
        if (cbDisciplina != null) {
            UiConstants.styleField(cbDisciplina);
        }
        if (cbTurmaNotas != null) {
            UiConstants.styleField(cbTurmaNotas);
        }
        if (tblNotas != null) {
            UiConstants.styleTable(tblNotas);
        }
        // this.controller = new NotasPanel(this);
        // this.controller.inicializar(); // Se tiver esse método
    }

    // Getters para o controlador usar
    public JComboBox getCbDisciplina() { return cbDisciplina; }
    public JComboBox getCbTurmaNotas() { return cbTurmaNotas; }
    public JTable getTblNotas() { return tblNotas; }
    public JButton getBtnSalvarNotas() { return btnSalvarNotas; }
}
