package br.com.familiaeduca.ui.view.sistema;

import br.com.familiaeduca.ui.controller.CadastroUsuariosController;
import javax.swing.*;

public class CadastroUsuariosPanel extends JPanel {

    private JButton btnCadastrarDiretor;
    private JButton btnCadastrarProfessor;
    private JButton btnCadastrarResponsavel;
    // Adicione outros componentes (tabelas, campos) conforme necessário

    private CadastroUsuariosController controller;

    public CadastroUsuariosPanel() {
        // Lembre-se: SEM initComponents() aqui!

        this.controller = new CadastroUsuariosController(this);
        this.controller.inicializar();
    }

    // Getters para o Controller
    public JButton getBtnCadastrarDiretor() { return btnCadastrarDiretor; }
    public JButton getBtnCadastrarProfessor() { return btnCadastrarProfessor; }
    public JButton getBtnCadastrarResponsavel() { return btnCadastrarResponsavel; }

}