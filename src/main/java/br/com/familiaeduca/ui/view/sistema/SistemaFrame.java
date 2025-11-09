// No ficheiro .java
package br.com.familiaeduca.ui.view.sistema;

import br.com.familiaeduca.ui.dto.UsuarioDto;
import br.com.familiaeduca.ui.util.SessaoUsuario;
import javax.swing.*;
import java.awt.*;

public class SistemaFrame extends JFrame {

    private JTabbedPane tabs; // Componente criado no GUI Designer

    // No construtor de SistemaFrame.java
    public SistemaFrame() {
        // ... (configurações iniciais) ...

        UsuarioDto user = SessaoUsuario.getInstance().getUsuarioLogado();

        // Abas comuns a todos (Responsável, Professor, Diretor)
        tabs.add("Avisos", new AvisosPanel());
        tabs.add("Checklist", new ChecklistPanel());
        tabs.add("Reuniões", new ReunioesPanel());

        // Abas restritas (Apenas Professor e Diretor)
        if (user.isProfessor() || user.isDiretor()) {
            tabs.add("Notas", new NotasPanel());
            tabs.add("Frequência", new FrequenciaPanel());
            tabs.add("Matrícula", new MatriculaPanel());
            tabs.add("Cadastros", new CadastroUsuariosPanel());
        }

        // Aba administrativa (Apenas Diretor)
        if (user.isDiretor()) {
            tabs.add("Cadastros", new CadastroUsuariosPanel());
            tabs.add("Matrícula", new MatriculaPanel());
        }
    }


}