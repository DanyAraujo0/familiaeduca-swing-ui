// No ficheiro .java (a lógica)
package br.com.familiaeduca.ui.view.login;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    // Pessoas B/C: Criem esta classe como "GUI Form (JFrame)"
    // No "GUI Designer" do IntelliJ, adicione um JTabbedPane (nome: tabs)

    private JTabbedPane tabs; // Este deve ser o nome do componente no GUI Designer

    public LoginFrame() {
        initComponents(); // O IntelliJ cria este método

        setTitle("Família Educa - Portal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);
        setLocationRelativeTo(null);

        // Adiciona os painéis (que também vamos criar) às abas
        tabs.add("Login", new LoginPanel(this)); // Passa a si mesmo para o painel
        tabs.add("Quem Somos", new QuemSomosPanel());
        tabs.add("Fale Conosco", new FaleConoscoPanel());

        // Nota: O add(tabs) já é feito pelo GUI designer se o JTabbedPane
        // for o componente principal do vosso JFrame.

        setVisible(true);
    }

    // ... (initComponents() gerado pelo IntelliJ) ...
}