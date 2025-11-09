package br.com.familiaeduca.ui.view.sistema;

import br.com.familiaeduca.ui.util.UiConstants;

import javax.swing.*;
import java.awt.*;

public class ReunioesPanel extends JPanel {

    private JTable tblReunioes;
    private JButton btnAceitarReuniao;
    private JButton btnSolicitarReuniao;
    private JPanel mainPanel;

    // private ReunioesController controller;

    public ReunioesPanel() {
        // 2. Configura o layout deste painel para BorderLayout
        setLayout(new BorderLayout());

        if (mainPanel != null) {
            add(mainPanel, BorderLayout.CENTER);
        } else {
            // Fallback caso algo dê errado na configuração do IDE
            add(new JLabel("Erro: Não foi possível carregar o design do .form"), BorderLayout.CENTER);
        }

        if (btnAceitarReuniao != null) {
            UiConstants.styleButton(btnAceitarReuniao);
        }
        if (btnSolicitarReuniao != null) {
            UiConstants.styleButton(btnSolicitarReuniao);
        }
        if (tblReunioes != null) {
            UiConstants.styleTable(tblReunioes);
        }
        // 4. Inicializa o controlador (depois que os componentes já existem)
        // this.controller = new ReunioesController(this);
        // this.controller.inicializar(); // Se tiver esse método
    }

    // Getters para o controlador usar
    public JTable getTblReunioes() { return tblReunioes; }
    public JButton getBtnAceitarReuniao() { return btnAceitarReuniao; }
    public JButton getBtnSolicitarReuniao() { return btnSolicitarReuniao; }
}
