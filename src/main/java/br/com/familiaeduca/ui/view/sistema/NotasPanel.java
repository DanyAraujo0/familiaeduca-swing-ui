package br.com.familiaeduca.ui.view.sistema;

import br.com.familiaeduca.ui.controllers.NotasController;
import br.com.familiaeduca.ui.util.UiConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class NotasPanel extends JPanel {

    private JComboBox<Object> cbDisciplina;
    private JComboBox<Object> cbTurmaNotas;
    private JComboBox<Object> cbAlunoNotas; // Importante!
    private JTextField txtNota; // Campo para digitar a nota
    private JButton btnSalvarNotas;
    private JTable tblNotas; // Apenas visual por enquanto

    private NotasController controller;

    public NotasPanel() {
        setLayout(new BorderLayout());
        inicializarComponentes();
        construirLayout();

        this.controller = new NotasController(this);
        this.controller.inicializar();
    }

    private void inicializarComponentes() {
        cbDisciplina = new JComboBox<>();
        cbTurmaNotas = new JComboBox<>();
        cbAlunoNotas = new JComboBox<>();
        txtNota = new JTextField();
        btnSalvarNotas = new JButton("Lançar Nota");
        tblNotas = new JTable();

        UiConstants.styleButton(btnSalvarNotas);
        UiConstants.styleField(cbDisciplina);
        UiConstants.styleField(cbTurmaNotas);
        UiConstants.styleField(cbAlunoNotas);
        UiConstants.styleField(txtNota);
    }

    private void construirLayout() {
        // Painel de Formulário
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Lançamento de Notas"));

        formPanel.add(new JLabel("Selecione a Turma:"));
        formPanel.add(cbTurmaNotas);

        formPanel.add(new JLabel("Selecione o Aluno:"));
        formPanel.add(cbAlunoNotas);

        formPanel.add(new JLabel("Disciplina:"));
        formPanel.add(cbDisciplina);

        formPanel.add(new JLabel("Nota (0-10):"));
        formPanel.add(txtNota);

        formPanel.add(new JLabel("")); // Espaço vazio
        formPanel.add(btnSalvarNotas);

        // Adiciona ao topo (North) para não esticar
        JPanel containerNorte = new JPanel(new BorderLayout());
        containerNorte.add(formPanel, BorderLayout.NORTH);

        add(containerNorte, BorderLayout.CENTER);
    }

    // Getters
    public JComboBox<Object> getCbDisciplina() { return cbDisciplina; }
    public JComboBox<Object> getCbTurmaNotas() { return cbTurmaNotas; }
    public JComboBox<Object> getCbAlunoNotas() { return cbAlunoNotas; }
    public JTextField getTxtNota() { return txtNota; }
    public JButton getBtnSalvarNotas() { return btnSalvarNotas; }
}