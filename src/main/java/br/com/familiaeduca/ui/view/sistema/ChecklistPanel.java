package br.com.familiaeduca.ui.view.sistema;

import br.com.familiaeduca.ui.util.UiConstants;

import javax.swing.*;
import java.awt.*;

public class ChecklistPanel extends JPanel {

    private JComboBox cbAlunoChecklist;
    private JTable tblChecklist;
    private JButton btnSalvarChecklist;
    private JPanel mainPanel;

    public ChecklistPanel() {
        // 2. Configura o layout deste painel para BorderLayout
        setLayout(new BorderLayout());

        if (mainPanel != null) {
            add(mainPanel, BorderLayout.CENTER);
        } else {
            // Fallback caso algo dê errado na configuração do IDE
            add(new JLabel("Erro: Não foi possível carregar o design do .form"), BorderLayout.CENTER);
        }

        if (btnSalvarChecklist != null) {
            UiConstants.styleButton(btnSalvarChecklist);
        }

        if (tblChecklist != null) {
            UiConstants.styleTable(tblChecklist);
        }

        if (cbAlunoChecklist != null) {
            UiConstants.styleField(cbAlunoChecklist);
        }

    }
        // Getters para o controlador usar
    public JComboBox getCbAlunoChecklist() { return cbAlunoChecklist; }
    public JTable getTblChecklist() { return tblChecklist; }
    public JButton getBtnSalvarChecklist() { return btnSalvarChecklist; }

}
