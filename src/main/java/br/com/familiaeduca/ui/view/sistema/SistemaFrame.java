package br.com.familiaeduca.ui.view.sistema;

import br.com.familiaeduca.ui.dto.UsuarioDto;
import br.com.familiaeduca.ui.util.SessaoUsuario;
import br.com.familiaeduca.ui.util.UiConstants;
import br.com.familiaeduca.ui.util.UiImages;
import br.com.familiaeduca.ui.view.login.LoginFrame;
import br.com.familiaeduca.ui.view.usuario.CadastroFrame;
import br.com.familiaeduca.ui.view.usuario.CadastroProfessorPanel;
import br.com.familiaeduca.ui.view.usuario.CadastroResponsavelPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class SistemaFrame extends JFrame {

    private JPanel contentPanel;
    private CardLayout cardLayout;
    private JPanel sideMenu;

    public SistemaFrame() {
        UsuarioDto user = SessaoUsuario.getInstance().getUsuarioLogado();
        String userProfile = (user != null) ? user.getPerfil() : "Visitante";
        String userName = (user != null) ? user.getNome() : "Usuário Teste";

        setTitle("Família Educa - Sistema [" + userProfile + "]");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- 1. Painel de Conteúdo (CENTRO) ---
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(new Color(245, 248, 250));
        add(contentPanel, BorderLayout.CENTER);

        // --- 2. Menu Lateral (ESQUERDA) ---
        sideMenu = new JPanel();
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        sideMenu.setBackground(UiConstants.BLUE);
        sideMenu.setPreferredSize(new Dimension(250, 0)); // Um pouco mais largo para a logo
        sideMenu.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(sideMenu, BorderLayout.WEST);

        // --- Logo Branca no Menu ---
        URL logoUrl = getClass().getResource(UiImages.WHITE_LOGO);
        if (logoUrl != null) {
            ImageIcon logoIcon = new ImageIcon(logoUrl);
            // Redimensiona a logo se for muito grande para o menu
            Image img = logoIcon.getImage().getScaledInstance(180, -1, Image.SCALE_SMOOTH);
            JLabel lblLogo = new JLabel(new ImageIcon(img));
            lblLogo.setAlignmentX(Component.LEFT_ALIGNMENT);
            sideMenu.add(lblLogo);
        } else {
            // Fallback se a imagem não for encontrada
            JLabel lblLogoTexto = new JLabel("Família Educa");
            lblLogoTexto.setFont(new Font("Segoe UI", Font.BOLD, 24));
            lblLogoTexto.setForeground(Color.WHITE);
            lblLogoTexto.setAlignmentX(Component.LEFT_ALIGNMENT);
            sideMenu.add(lblLogoTexto);
        }

        sideMenu.add(Box.createRigidArea(new Dimension(0, 20)));

        // --- Informações do Usuário ---
        JLabel lblWelcome = new JLabel("Olá,");
        lblWelcome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblWelcome.setForeground(new Color(200, 220, 255));
        lblWelcome.setAlignmentX(Component.LEFT_ALIGNMENT);
        sideMenu.add(lblWelcome);

        JLabel lblUser = new JLabel(userName);
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblUser.setForeground(Color.WHITE);
        lblUser.setAlignmentX(Component.LEFT_ALIGNMENT);
        sideMenu.add(lblUser);

        JLabel lblProfile = new JLabel(userProfile);
        lblProfile.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblProfile.setForeground(new Color(150, 180, 220));
        lblProfile.setAlignmentX(Component.LEFT_ALIGNMENT);
        sideMenu.add(lblProfile);

        sideMenu.add(Box.createRigidArea(new Dimension(0, 40)));

        // --- 3. Adicionando as Telas ao Menu ---
        // Adiciona TODAS as opções para teste, mas na versão final use os 'if' de perfil
        adicionarOpcaoMenu("Mural de Avisos", new AvisosPanel(), true);
        adicionarOpcaoMenu("Checklist Diário", new ChecklistPanel(), false);
        adicionarOpcaoMenu("Reuniões", new ReunioesPanel(), false);

        adicionarSeparadorMenu("GESTÃO ACADÊMICA");
        adicionarOpcaoMenu("Frequência", new FrequenciaPanel(), false);
        adicionarOpcaoMenu("Notas e Avaliações", new NotasPanel(), false);

        // Verifica permissões reais para Matrícula e Cadastro (para não quebrar a lógica de negócio)
        if (user == null || user.isProfessor() || user.isDiretor()) {
            adicionarOpcaoMenu("Painel de Matrículas", new MatriculaPanel(), false);
        }

        if (user == null || user.isDiretor()) {
            adicionarSeparadorMenu("ADMINISTRAÇÃO");

            JButton btnCadastroProfessor = criarBotaoMenu("Cadastrar Professor");
            btnCadastroProfessor.addActionListener(e -> new CadastroFrame(new CadastroProfessorPanel(null)));
            sideMenu.add(btnCadastroProfessor);
            sideMenu.add(Box.createRigidArea(new Dimension(0, 8)));

            JButton btnCadastroResponsavel = criarBotaoMenu("Cadastrar Responsável");
            btnCadastroResponsavel.addActionListener(e -> new CadastroFrame(new CadastroResponsavelPanel(null)));
            sideMenu.add(btnCadastroResponsavel);
            sideMenu.add(Box.createRigidArea(new Dimension(0, 8)));
        }

        // --- Botão de Sair ---
        sideMenu.add(Box.createVerticalGlue());
        JButton btnSair = criarBotaoMenu("Sair do Sistema");
        btnSair.setBackground(new Color(220, 53, 69));
        btnSair.setForeground(Color.WHITE);
        btnSair.addActionListener(e -> fazerLogout());
        sideMenu.add(btnSair);
    }

    // ... (manter os métodos auxiliares adicionarOpcaoMenu, criarBotaoMenu, etc. iguais ao anterior) ...
    // Vou repeti-los aqui para garantir que o arquivo fica completo e funcional

    private void adicionarOpcaoMenu(String titulo, JPanel panel, boolean isDefault) {
        contentPanel.add(panel, titulo);
        JButton btn = criarBotaoMenu(titulo);
        btn.addActionListener(e -> cardLayout.show(contentPanel, titulo));
        sideMenu.add(btn);
        sideMenu.add(Box.createRigidArea(new Dimension(0, 8)));
        if (isDefault) {
            cardLayout.show(contentPanel, titulo);
        }
    }

    private void adicionarSeparadorMenu(String texto) {
        sideMenu.add(Box.createRigidArea(new Dimension(0, 15)));
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lbl.setForeground(new Color(150, 180, 220));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        sideMenu.add(lbl);
        sideMenu.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    private JButton criarBotaoMenu(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(0x00, 0x44, 0x88)); // Azul ligeiramente diferente para os botões
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        return btn;
    }

    private void fazerLogout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja sair?", "Sair", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            SessaoUsuario.getInstance().logout();
            this.dispose();
            SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
        }
    }
    private void abrirJanelaCadastroUsuarios() {
        // Aqui criamos uma janela principal de cadastro (com abas ou menus)
        JFrame frame = new JFrame("Família Educa - Cadastros");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(this);
        frame.setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnProfessor = new JButton("Cadastrar Professor");
        JButton btnResponsavel = new JButton("Cadastrar Responsável");
        JButton btnFechar = new JButton("Fechar");

        btnProfessor.addActionListener(e -> new CadastroFrame(new CadastroProfessorPanel(null)));
        btnResponsavel.addActionListener(e -> new CadastroFrame(new CadastroResponsavelPanel(null)));
        btnFechar.addActionListener(e -> frame.dispose());

        frame.add(btnProfessor);
        frame.add(btnResponsavel);
        frame.add(btnFechar);

        frame.setVisible(true);
    }

    // Método main para teste
    public static void main(String[] args) {
        try { com.formdev.flatlaf.FlatLightLaf.setup(); } catch (Exception e) { }
        SwingUtilities.invokeLater(() -> new SistemaFrame().setVisible(true));
    }

}