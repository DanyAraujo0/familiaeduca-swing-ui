package br.com.familiaeduca.ui.view.login;

import br.com.familiaeduca.ui.controllers.LoginController;
import br.com.familiaeduca.ui.util.UiConstants;
import br.com.familiaeduca.ui.util.UiImages;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private JTextField txtUsuario;
    private JPasswordField pwdSenha;
    private JButton btnEntrar;
    private JButton btnCadastrarDiretor;
    private LoginController controller;
    private JLabel lblErroEmail;

    public LoginPanel(LoginFrame frame) {
        this.controller = new LoginController(this, frame);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // --- Logo (Esquerda) ---
        JLabel logoLabel = new JLabel(new ImageIcon(getClass().getResource(UiImages.LOGO)));
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setOpaque(true);
        logoLabel.setBackground(UiConstants.BLUE);
        logoLabel.setPreferredSize(new Dimension(380, 0));
        add(logoLabel, BorderLayout.WEST);

        // --- Formulário (Centro) ---
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        // Margens menores (40) para deixar os campos mais largos
        formPanel.setBorder(BorderFactory.createEmptyBorder(50, 40, 50, 40));

/*
        //JLabel sombra = new JLabel("Seja bem-vindo!");
        sombra.setFont(new Font("Segoe UI", Font.BOLD, 22));
        sombra.setForeground(new Color(0,0,0,100));
        sombra.setBounds(102, 102, 300, 40);
*/
        JLabel lblTitulo = new JLabel("Seja bem-vindo!");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(0x2568CC));
        lblTitulo.setBounds(100, 100, 300, 40);
        /*
        panel.setLayout(null);
        panel.add(sombra);
        panel.add(lblTitulo);*/

        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(UiConstants.BLUE);
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtUsuario = new JTextField();
        txtUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        txtUsuario.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblErroEmail = new JLabel(" ");
        lblErroEmail.setForeground(Color.RED);
        lblErroEmail.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblErroEmail.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setAlignmentX(Component.LEFT_ALIGNMENT);

        pwdSenha = new JPasswordField();
        pwdSenha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        pwdSenha.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnEntrar = new JButton("Entrar");
        UiConstants.styleButton(btnEntrar);
        btnEntrar.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnCadastrarDiretor = new JButton("Cadastrar Usuário");
        UiConstants.styleButton(btnCadastrarDiretor);

        // Botão secundário com cor diferente (opcional, mas ajuda a distinguir)
        btnCadastrarDiretor.setBackground(Color.WHITE);
        btnCadastrarDiretor.setForeground(UiConstants.BLUE);
        btnCadastrarDiretor.setAlignmentX(Component.LEFT_ALIGNMENT);

        // --- Montagem ---
        formPanel.add(Box.createVerticalGlue());
        //formPanel.add(sombra);
        formPanel.add(lblTitulo);
        formPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        formPanel.add(lblUsuario);
        formPanel.add(txtUsuario);
        formPanel.add(lblErroEmail);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        formPanel.add(lblSenha);
        formPanel.add(pwdSenha);
        formPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        formPanel.add(btnEntrar);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(btnCadastrarDiretor);
        formPanel.add(Box.createVerticalGlue());

        add(formPanel, BorderLayout.CENTER);

        // --- Eventos ---
        btnEntrar.addActionListener(e -> controller.realizarLogin());
        btnCadastrarDiretor.addActionListener(e -> frame.mostrarCadastroDiretorPanel());
    }
    // Getters para a controller
    public String getUsuario() { return txtUsuario.getText().trim(); }
    public String getSenha() { return new String(pwdSenha.getPassword()).trim(); }
    public void exibirMensagem(String msg) { JOptionPane.showMessageDialog(this, msg); }

    // Setters para captura de erros
    public void setErroEmail(String msg) {
        lblErroEmail.setText(msg);
    }
    public void limparErros() {
        lblErroEmail.setText(" ");
    }
}