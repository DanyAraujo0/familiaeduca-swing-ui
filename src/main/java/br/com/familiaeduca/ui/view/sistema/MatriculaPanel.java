package br.com.familiaeduca.ui.view.sistema;

import br.com.familiaeduca.ui.util.UiConstants;

import javax.swing.*;
import java.awt.*;

public class MatriculaPanel extends JPanel {

    private JTextField txtNomeAluno;
    private JFormattedTextField txtDataNasc;
    private JComboBox cbTurmaMatricula;
    private JComboBox cbResponsavel;
    private JButton btnMatricular;
    private JPanel mainPanel;

    // private MatriculaController controller;

    public MatriculaPanel() {
        // 2. Configura o layout deste painel para BorderLayout
        setLayout(new BorderLayout());

        if (mainPanel != null) {
            add(mainPanel, BorderLayout.CENTER);
        } else {
            // Fallback caso algo dê errado na configuração do IDE
            add(new JLabel("Erro: Não foi possível carregar o design do .form"), BorderLayout.CENTER);
        }

        if (btnMatricular != null) {
            UiConstants.styleButton(btnMatricular);
        }
        if (txtNomeAluno != null) {
            UiConstants.styleField(txtNomeAluno);
        }
        if (txtDataNasc != null) {
            UiConstants.styleField(txtDataNasc);
        }
        if (cbTurmaMatricula != null) {
            UiConstants.styleField(cbTurmaMatricula);
        }
        if (cbResponsavel != null) {
            UiConstants.styleField(cbResponsavel);
        }
        // 4. Inicializa o controlador (depois que os componentes já existem)
        // this.controller = new MatriculaPanel(this);
        // this.controller.inicializar(); // Se tiver esse método
    }

    // Getters para o controlador usar
    public JTextField getTxtNomeAluno() { return txtNomeAluno; }
    public JFormattedTextField getTxtDataNasc() { return txtDataNasc; }
    public JComboBox getCbTurmaMatricula() { return cbTurmaMatricula; }
    public JComboBox getCbResponsavel() { return cbResponsavel; }
    public JButton getBtnMatricular() { return btnMatricular; }
}