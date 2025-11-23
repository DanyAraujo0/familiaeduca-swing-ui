package br.com.familiaeduca.ui.view.sistema;

import br.com.familiaeduca.ui.controllers.AlunoController;
import br.com.familiaeduca.ui.util.UiConstants;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;

public class MatriculaPanel extends JPanel {

    private JTextField txtNomeAluno;
    private JFormattedTextField txtDataNasc; // Campo formatado
    private JComboBox<Object> cbTurmaMatricula;
    private JComboBox<Object> cbResponsavel;
    private JButton btnMatricular;
    private JPanel mainPanel;

    public MatriculaPanel() {
        setLayout(new BorderLayout());
        inicializarComponentes();

        if (mainPanel != null) {
            add(mainPanel, BorderLayout.CENTER);
        } else {
            construirLayoutManual();
        }

        styleComponents();
        // Inicia o controller
        new AlunoController(this);
    }

    private void inicializarComponentes() {
        if (txtNomeAluno == null) txtNomeAluno = new JTextField(20);
        if (cbTurmaMatricula == null) cbTurmaMatricula = new JComboBox<>();
        if (cbResponsavel == null) cbResponsavel = new JComboBox<>();
        if (btnMatricular == null) btnMatricular = new JButton("Realizar Matrícula");

        // --- CORREÇÃO AQUI ---
        // 1. Se o campo não existir, cria.
        if (txtDataNasc == null) {
            txtDataNasc = new JFormattedTextField();
        }

        // 2. FORÇA a aplicação da máscara no campo existente
        configurarMascaraData(txtDataNasc);
    }

    // Método que injeta a máscara no campo
    private void configurarMascaraData(JFormattedTextField campo) {
        try {
            MaskFormatter mask = new MaskFormatter("##/##/####");
            mask.setPlaceholderCharacter('_'); // Isso faz aparecer __/__/____
            mask.setValidCharacters("0123456789");
            mask.setValueContainsLiteralCharacters(true); // Garante que o texto tenha as barras

            // Aplica a máscara no campo usando a Factory padrão
            campo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mask));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =======================================================
    // MÉTODO QUE CRIA A MÁSCARA VISUAL (__/__/____)
    // =======================================================
    private JFormattedTextField criarCampoData() {
        try {
            // Define o formato: 2 números / 2 números / 4 números
            MaskFormatter mask = new MaskFormatter("##/##/####");

            // Define que o espaço vazio será um sublinhado (ou use ' ' para espaço em branco)
            mask.setPlaceholderCharacter('_');

            // Garante que o .getText() retorne as barras junto com os números
            mask.setValueContainsLiteralCharacters(true);

            // Aceita apenas números na digitação
            mask.setValidCharacters("0123456789");

            JFormattedTextField txt = new JFormattedTextField(mask);
            txt.setColumns(10);

            // Foca na primeira posição quando clica
            txt.setFocusLostBehavior(JFormattedTextField.PERSIST);

            return txt;
        } catch (Exception e) {
            e.printStackTrace();
            return new JFormattedTextField();
        }
    }

    private void styleComponents() {
        UiConstants.styleButton(btnMatricular);
        UiConstants.styleField(txtNomeAluno);
        UiConstants.styleField(txtDataNasc);
        UiConstants.styleField(cbTurmaMatricula);
        UiConstants.styleField(cbResponsavel);
    }

    private void construirLayoutManual() {
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("Nome do Aluno:"), gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(txtNomeAluno, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("Nascimento:"), gbc);
        gbc.gridx = 0; gbc.gridy = 3;
        mainPanel.add(txtDataNasc, gbc); // O campo já aparece com __/__/____

        gbc.gridx = 0; gbc.gridy = 4;
        mainPanel.add(new JLabel("Turma:"), gbc);
        gbc.gridx = 0; gbc.gridy = 5;
        mainPanel.add(cbTurmaMatricula, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        mainPanel.add(new JLabel("Responsável:"), gbc);
        gbc.gridx = 0; gbc.gridy = 7;
        mainPanel.add(cbResponsavel, gbc);

        gbc.gridx = 0; gbc.gridy = 8;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(btnMatricular, gbc);

        add(mainPanel, BorderLayout.CENTER);
    }

    public void exibirMensagem(String msg) { JOptionPane.showMessageDialog(this, msg); }

    public JTextField getTxtNomeAluno() { return txtNomeAluno; }
    public JFormattedTextField getTxtDataNasc() { return txtDataNasc; }
    public JComboBox<Object> getCbTurmaMatricula() { return cbTurmaMatricula; }
    public JComboBox<Object> getCbResponsavel() { return cbResponsavel; }
    public JButton getBtnMatricular() { return btnMatricular; }
}