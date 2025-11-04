package br.com.familiaeduca.ui.view.info;

import br.com.familiaeduca.ui.util.UiConstants;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class QuemSomosPanel extends JPanel {

    public QuemSomosPanel() {
        // Pessoas B/C: Podem recriar isto no GUI Designer ou
        // simplesmente copiar/colar este código no construtor

        super(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Quem Somos");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(UiConstants.BLUE);

        JTextArea txt = new JTextArea(
                "Somos uma escola infantil dedicada ao aprendizado e bem-estar das crianças.\n" +
                        "Proporcionamos atividades lúdicas, um ambiente seguro e afeto.\n" +
                        "Nosso compromisso é com o crescimento integral de cada aluno."
        );
        txt.setLineWrap(true);
        txt.setWrapStyleWord(true);
        txt.setEditable(false);
        txt.setFont(new Font("Arial", Font.PLAIN, 12));
        txt.setBackground(Color.WHITE);

        add(title, BorderLayout.NORTH);
        add(new JScrollPane(txt), BorderLayout.CENTER);
    }
}