package br.com.familiaeduca.ui.view.sistema;

import br.com.familiaeduca.ui.controllers.AvisosController;
import br.com.familiaeduca.ui.util.UiConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AvisosPanel extends JPanel {

    // Componentes da tela
    private JTable tblAvisos;
    private JTextArea txtDetalheAviso;
    private JButton btnNovoAviso;
    private JButton btnAtualizar;
    private DefaultTableModel tableModel;

    // Controlador
    private AvisosController controller;

    public AvisosPanel() {
        // 1. Define o Layout Principal como BorderLayout (Norte, Sul, Leste, Oeste, Centro)
        setLayout(new BorderLayout());

        // 2. Inicializa os componentes visuais
        inicializarComponentes();

        // 3. Constrói a tela manualmente (Ignorando o .form para evitar erros)
        construirLayout();

        // 4. Inicia o Controlador
        this.controller = new AvisosController(this);
        this.controller.inicializar();
    }

    private void inicializarComponentes() {
        // Botões
        btnNovoAviso = new JButton("Novo Aviso (+)");
        btnAtualizar = new JButton("Atualizar");

        // Área de Texto (Detalhes)
        txtDetalheAviso = new JTextArea(5, 20); // 5 linhas, 20 colunas
        txtDetalheAviso.setEditable(false);
        txtDetalheAviso.setLineWrap(true);
        txtDetalheAviso.setWrapStyleWord(true);
        txtDetalheAviso.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtDetalheAviso.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tabela
        tblAvisos = new JTable();

        // Configura o Modelo da Tabela (Colunas)
        tableModel = new DefaultTableModel(new Object[]{"Data", "Título", "Mensagem", "ID"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Bloqueia edição
            }
        };
        tblAvisos.setModel(tableModel);

        // Visual da Tabela
        tblAvisos.setRowHeight(30);
        tblAvisos.setFillsViewportHeight(true); // Estica a tabela até o fim
        tblAvisos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Cabeçalho da Tabela
        tblAvisos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblAvisos.getTableHeader().setBackground(new Color(230, 230, 230));

        // Esconde a coluna ID (coluna 3)
        if(tblAvisos.getColumnCount() > 3) {
            tblAvisos.getColumnModel().getColumn(3).setMinWidth(0);
            tblAvisos.getColumnModel().getColumn(3).setMaxWidth(0);
            tblAvisos.getColumnModel().getColumn(3).setWidth(0);
        }

        // Estilos Gerais
        UiConstants.styleButton(btnNovoAviso);
    }

    private void construirLayout() {
        // --- PAINEL SUPERIOR (Botões) ---
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(btnAtualizar);
        topPanel.add(btnNovoAviso);

        // --- PAINEL CENTRAL (Tabela com Scroll) ---
        // O ScrollPane é OBRIGATÓRIO para a tabela aparecer
        JScrollPane scrollTabela = new JScrollPane(tblAvisos);
        scrollTabela.setBorder(BorderFactory.createTitledBorder("Mural de Avisos"));

        // --- PAINEL INFERIOR (Detalhes) ---
        JScrollPane scrollDetalhes = new JScrollPane(txtDetalheAviso);
        scrollDetalhes.setBorder(BorderFactory.createTitledBorder("Detalhes do Aviso"));
        scrollDetalhes.setPreferredSize(new Dimension(0, 120)); // Altura fixa pro detalhe

        // --- ADICIONA TUDO AO PAINEL PRINCIPAL ---
        add(topPanel, BorderLayout.NORTH);
        add(scrollTabela, BorderLayout.CENTER); // A tabela vai preencher todo o espaço branco
        add(scrollDetalhes, BorderLayout.SOUTH);
    }

    // Getters
    public JTable getTblAvisos() { return tblAvisos; }
    public DefaultTableModel getTabelaModel() { return tableModel; }
    public JTextArea getTxtDetalheAviso() { return txtDetalheAviso; }
    public JButton getBtnNovoAviso() { return btnNovoAviso; }
    public JButton getBtnAtualizar() { return btnAtualizar; }
}