package br.com.familiaeduca.ui.view.usuario;

import javax.swing.*;
import java.awt.*;
import br.com.familiaeduca.ui.view.sistema.SistemaFrame;

public class CadastroUsuariosPanel extends JPanel {

    private final SistemaFrame sistemaFrame;

    private JButton btnCadastrarProfessor;
    private JButton btnCadastrarResponsavel;

    public CadastroUsuariosPanel(SistemaFrame sistemaFrame) {
        this.sistemaFrame = sistemaFrame;
        inicializar();
    }

    private void inicializar() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("Gerenciamento de Usuários");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo, BorderLayout.NORTH);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 50));
        btnCadastrarProfessor = new JButton("Cadastrar Professor");
        btnCadastrarResponsavel = new JButton("Cadastrar Responsável");

        botoes.add(btnCadastrarProfessor);
        botoes.add(btnCadastrarResponsavel);
        add(botoes, BorderLayout.CENTER);
    }

    public JButton getBtnCadastrarProfessor() {
        return btnCadastrarProfessor;
    }

    public JButton getBtnCadastrarResponsavel() {
        return btnCadastrarResponsavel;
    }
}
