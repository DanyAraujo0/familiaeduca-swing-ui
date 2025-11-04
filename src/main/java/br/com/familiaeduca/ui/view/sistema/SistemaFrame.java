// No ficheiro .java
package br.com.familiaeduca.ui.view.sistema;

import br.com.familiaeduca.ui.dto.UsuarioDto;
import br.com.familiaeduca.ui.util.SessaoUsuario;
import javax.swing.*;
import java.awt.*;

public class SistemaFrame extends JFrame {

    private JTabbedPane tabs; // Componente criado no GUI Designer

    public SistemaFrame() {
        initComponents(); // Gerado pelo IntelliJ

        UsuarioDto user = SessaoUsuario.getInstance().getUsuarioLogado();

        setTitle("Sistema - " + user.getTipo() + " (" + user.getNome() + ")");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        // Adiciona os painéis das abas
        tabs.add("Checklist", new ChecklistPanel());
        tabs.add("Frequência", new FrequenciaPanel());
        tabs.add("Notas", new NotasPanel());
        tabs.add("Matrícula", new MatriculaPanel());
    }

    // ... (initComponents() gerado pelo IntelliJ) ...
}