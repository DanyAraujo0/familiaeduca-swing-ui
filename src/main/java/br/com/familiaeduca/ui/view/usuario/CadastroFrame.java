package br.com.familiaeduca.ui.view.usuario;

import br.com.familiaeduca.ui.view.sistema.SistemaFrame;
import javax.swing.*;
import java.awt.*;

public class CadastroFrame extends JFrame {

    public CadastroFrame(JPanel painelCadastro) {
        setTitle("Família Educa - Cadastro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(painelCadastro, BorderLayout.CENTER);

        setVisible(true);
    }

    public void voltarAoSistema() {
        dispose(); // fecha a tela atual
    }
}
