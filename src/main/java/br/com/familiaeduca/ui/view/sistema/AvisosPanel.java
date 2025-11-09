package br.com.familiaeduca.ui.view.sistema;

import br.com.familiaeduca.ui.util.UiConstants;

import javax.swing.*;
import java.awt.*;

// import br.com.familiaeduca.ui.controller.AvisosController;

public class AvisosPanel extends JPanel {

    private JPanel mainPanel;
    private JTable tblAvisos;
    private JTextArea txtDetalheAviso;
    private JButton btnNovoAviso;

    // private AvisosController controller;

    public AvisosPanel() {
        // 2. Configura o layout deste painel para BorderLayout
        setLayout(new BorderLayout());

        if (mainPanel != null) {
            add(mainPanel, BorderLayout.CENTER);
        } else {
            // Fallback caso algo dê errado na configuração do IDE
            add(new JLabel("Erro: Não foi possível carregar o design do .form"), BorderLayout.CENTER);
        }

        if (btnNovoAviso != null) {
            UiConstants.styleButton(btnNovoAviso);
        }
        if (tblAvisos != null) {
            UiConstants.styleTable(tblAvisos);
        }
        if (txtDetalheAviso != null) {
            UiConstants.styleField(txtDetalheAviso);
        }

        // 4. Inicializa o controlador (depois que os componentes já existem)
        // this.controller = new AvisosController(this);
        // this.controller.inicializar(); // Se tiver esse método
    }

    // Getters para o controlador usar
    public JTable getTblAvisos() { return tblAvisos; }
    public JTextArea getTxtDetalheAviso() { return txtDetalheAviso; }
    public JButton getBtnNovoAviso() { return btnNovoAviso; }
}