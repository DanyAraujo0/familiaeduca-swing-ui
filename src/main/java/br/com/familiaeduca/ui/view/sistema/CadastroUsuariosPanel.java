package br.com.familiaeduca.ui.view.sistema;

import br.com.familiaeduca.ui.controller.CadastroUsuariosController;
import br.com.familiaeduca.ui.util.UiConstants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CadastroUsuariosPanel extends JPanel {

    private JButton btnCadastrarDiretor;
    private JButton btnCadastrarProfessor;
    private JButton btnCadastrarResponsavel;
    private CadastroUsuariosController controller;

    public CadastroUsuariosPanel() {
        setLayout(new BorderLayout(0, 20));
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(30, 30, 30, 30));

        // --- 1. Título ---
        JLabel lblTitulo = new JLabel("Gestão de Usuários");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(UiConstants.BLUE);
        add(lblTitulo, BorderLayout.NORTH);

        // --- 2. Painel de Botões (Centro) ---
        JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre botões
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20; // Botões mais altos

        // Inicializa os botões manualmente
        btnCadastrarDiretor = criarBotaoMenu("Cadastrar Novo Diretor", new Color(0x2ecc71)); // Verde
        btnCadastrarProfessor = criarBotaoMenu("Cadastrar Novo Professor", UiConstants.BLUE);
        btnCadastrarResponsavel = criarBotaoMenu("Cadastrar Novo Responsável", new Color(0xe67e22)); // Laranja

        // Adiciona ao painel central
        menuPanel.add(btnCadastrarDiretor, gbc);
        gbc.gridy++;
        menuPanel.add(btnCadastrarProfessor, gbc);
        gbc.gridy++;
        menuPanel.add(btnCadastrarResponsavel, gbc);

        // Adiciona um painel vazio para empurrar tudo para cima
        gbc.gridy++;
        gbc.weighty = 1.0;
        menuPanel.add(Box.createGlue(), gbc);

        add(menuPanel, BorderLayout.CENTER);

        // --- 3. Controlador (Último passo) ---
        this.controller = new CadastroUsuariosController(this);
        this.controller.inicializar();
    }

    private JButton criarBotaoMenu(String texto, Color cor) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(300, 50)); // Tamanho fixo para uniformidade
        return btn;
    }

    // --- Getters para o Controller ---
    public JButton getBtnCadastrarDiretor() { return btnCadastrarDiretor; }
    public JButton getBtnCadastrarProfessor() { return btnCadastrarProfessor; }
    public JButton getBtnCadastrarResponsavel() { return btnCadastrarResponsavel; }
}