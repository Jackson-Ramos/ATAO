package Abela1;

import javax.swing.*;
import java.awt.*;

public class InterfaceUsuario {
    private GerenciadorDeLivros gerenciadorDeLivros;

    public InterfaceUsuario(GerenciadorDeLivros gerenciadorDeLivros) {
        this.gerenciadorDeLivros = gerenciadorDeLivros;
    }

    public void criarInterface() {
        JFrame frame = new JFrame("Sistema de Livros");
        frame.setSize(720, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel formPanel = criarFormularioAdicionarLivro();
        JPanel buttonsPanel = criarPainelBotoesOrdenacao();

        JPanel listaLivrosPanel = new JPanel();
        listaLivrosPanel.setLayout(new BoxLayout(listaLivrosPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(listaLivrosPanel);

        frame.add(formPanel, BorderLayout.NORTH);
        frame.add(buttonsPanel, BorderLayout.SOUTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        gerenciadorDeLivros.listarLivros(); // Atualizar a lista de livros ao iniciar

        frame.setVisible(true);
    }

    private JPanel criarFormularioAdicionarLivro() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Adicionar Novo Livro"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel tituloLabel = new JLabel("Título:");
        JTextField tituloField = new JTextField(20);
        JLabel autorLabel = new JLabel("Autor:");
        JTextField autorField = new JTextField(20);
        JLabel anoLabel = new JLabel("Ano de Publicação:");
        JTextField anoField = new JTextField(5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(tituloLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(tituloField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(autorLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(autorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(anoLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(anoField, gbc);

        JButton adicionarButton = new JButton("Adicionar Livro");
        adicionarButton.setBackground(new Color(46, 204, 113));
        adicionarButton.setForeground(Color.WHITE);

        adicionarButton.addActionListener(e -> {
            String titulo = tituloField.getText().trim();
            String autor = autorField.getText().trim();

            try {
                int ano = Integer.parseInt(anoField.getText().trim());
                if (titulo.isEmpty() || autor.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                } else {
                    gerenciadorDeLivros.adicionarLivro(titulo, autor, ano);
                    tituloField.setText("");
                    autorField.setText("");
                    anoField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um ano de publicação válido (número inteiro).");
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(adicionarButton, gbc);

        return formPanel;
    }

    private JPanel criarPainelBotoesOrdenacao() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton ordenarTituloButton = new JButton("Ordenar por Título");
        ordenarTituloButton.setBackground(new Color(52, 152, 219));
        ordenarTituloButton.setForeground(Color.WHITE);

        JButton ordenarAutorButton = new JButton("Ordenar por Autor");
        ordenarAutorButton.setBackground(new Color(155, 89, 182));
        ordenarAutorButton.setForeground(Color.WHITE);

        buttonsPanel.add(ordenarTituloButton);
        buttonsPanel.add(ordenarAutorButton);

        ordenarTituloButton.addActionListener(e -> gerenciadorDeLivros.ordenarPorTitulo());
        ordenarAutorButton.addActionListener(e -> gerenciadorDeLivros.ordenarPorAutor());

        return buttonsPanel;
    }
}
