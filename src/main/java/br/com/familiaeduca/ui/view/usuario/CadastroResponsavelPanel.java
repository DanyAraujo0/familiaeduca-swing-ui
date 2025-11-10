package br.com.familiaeduca.ui.view.usuario;

import br.com.familiaeduca.ui.controller.ResponsavelController;
import br.com.familiaeduca.ui.util.UiConstants;
import br.com.familiaeduca.ui.util.UiImages;

import javax.swing.*;
import java.awt.*;

public class CadastroResponsavelPanel extends JPanel {

    private JTextField txtNome;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JTextField txtTelefone;
    private JTextField txtEndereco;
    private final CadastroFrame frame;
    private final ResponsavelController controller;

    public CadastroResponsavelPanel(CadastroFrame frame) {
        this.frame = frame;
        this.controller = new ResponsavelController(this);
        inicializar();
    }

    private void inicializar() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // --- Painel Azul (Logo) ---
        JLabel logoLabel = new JLabel(new ImageIcon(getClass().getResource(UiImages.LOGO)));
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setOpaque(true);
        logoLabel.setBackground(UiConstants.BLUE);
        logoLabel.setPreferredSize(new Dimension(380, 0));
        add(logoLabel, BorderLayout.WEST);

        // --- Painel de Formulário ---
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        JLabel lblTitulo = new JLabel("Novo Responsável");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(UiConstants.BLUE);
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtNome = criarCampoTexto();
        txtEmail = criarCampoTexto();
        txtSenha = new JPasswordField();
        txtSenha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        txtSenha.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtTelefone = criarCampoTexto();
        txtEndereco = criarCampoTexto();

        JButton btnCadastrar = new JButton("Confirmar Cadastro");
        UiConstants.styleButton(btnCadastrar);
        btnCadastrar.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnVoltar = new JButton("Voltar ao Sistema");
        UiConstants.styleButton(btnVoltar);
        btnVoltar.setBackground(new Color(200, 200, 200));
        btnVoltar.setForeground(Color.BLACK);
        btnVoltar.setAlignmentX(Component.LEFT_ALIGNMENT);

        // --- Montagem ---
        formPanel.add(Box.createVerticalGlue());
        formPanel.add(lblTitulo);
        formPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        adicionarCampo(formPanel, "Nome Completo:", txtNome);
        adicionarCampo(formPanel, "E-mail:", txtEmail);
        adicionarCampo(formPanel, "Senha:", txtSenha);
        adicionarCampo(formPanel, "Telefone:", txtTelefone);
        adicionarCampo(formPanel, "Endereço:", txtEndereco);

        formPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        formPanel.add(btnCadastrar);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(btnVoltar);
        formPanel.add(Box.createVerticalGlue());

        add(formPanel, BorderLayout.CENTER);

        // --- Eventos ---
        btnCadastrar.addActionListener(e -> controller.cadastrar());
        btnVoltar.addActionListener(e -> frame.voltarAoSistema());
    }

    // Métodos auxiliares
    private JTextField criarCampoTexto() {
        JTextField txt = new JTextField();
        txt.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        txt.setAlignmentX(Component.LEFT_ALIGNMENT);
        return txt;
    }

    private void adicionarCampo(JPanel panel, String label, JComponent campo) {
        JLabel lbl = new JLabel(label);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lbl);
        panel.add(campo);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
    }

    // Getters
    public String getNome() { return txtNome.getText().trim(); }
    public String getEmail() { return txtEmail.getText().trim(); }
    public String getSenha() { return new String(txtSenha.getPassword()).trim(); }
    public String getTelefone() { return txtTelefone.getText().trim(); }
    public String getEndereco() { return txtEndereco.getText().trim(); }
}
