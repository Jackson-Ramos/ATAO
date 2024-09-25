package Abela1;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List; // Ensure this is imported for List<Livro>
import java.awt.event.ActionEvent;


public class Main {
    private static GerenciadorDeLivros gerenciadorDeLivros;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::criarInterface);
    }

    private static void criarInterface() {
        JFrame frame = new JFrame("Sistema de Livros");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // Crie o painel para listar livros
        JPanel listaLivrosPanel = new JPanel();
        listaLivrosPanel.setLayout(new BoxLayout(listaLivrosPanel, BoxLayout.Y_AXIS));
        listaLivrosPanel.setBackground(new Color(255, 255, 255)); // Fundo branco

        // Inicialize o gerenciador de livros
        gerenciadorDeLivros = new GerenciadorDeLivros(100, listaLivrosPanel);

        // Painel de formulário
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(240, 240, 240)); // Fundo cinza claro
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2), 
                "Adicionar Novo Livro", TitledBorder.CENTER, TitledBorder.TOP, 
                new Font("Arial", Font.BOLD, 14), new Color(70, 130, 180))); // Bordas arredondadas

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

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
        adicionarButton.setBackground(new Color(70, 130, 180)); // Cor do botão
        adicionarButton.setForeground(Color.WHITE);
        adicionarButton.setFocusPainted(false);
        adicionarButton.setBorderPainted(false);
        adicionarButton.setFont(new Font("Arial", Font.BOLD, 14));

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(adicionarButton, gbc);

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

        // Painel de busca
        JPanel buscaPanel = new JPanel();
        buscaPanel.setLayout(new BoxLayout(buscaPanel, BoxLayout.X_AXIS)); // Mudança para BoxLayout horizontal
        buscaPanel.setBackground(new Color(240, 240, 240)); // Fundo cinza claro
        buscaPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2), 
                "Buscar Livro por Autor", TitledBorder.CENTER, TitledBorder.TOP, 
                new Font("Arial", Font.BOLD, 14), new Color(70, 130, 180)));

        JTextField buscaAutorField = new JTextField(15);
        buscaAutorField.setPreferredSize(new Dimension(200, 30)); // Aumenta a largura do campo
        JButton buscarButton = new JButton("Buscar");
        buscarButton.setBackground(new Color(70, 130, 180)); // Cor do botão
        buscarButton.setForeground(Color.WHITE);
        buscarButton.setFocusPainted(false);
        buscarButton.setBorderPainted(false);
        buscarButton.setFont(new Font("Arial", Font.BOLD, 14));

        buscaPanel.add(new JLabel("Autor:"));
        buscaPanel.add(buscaAutorField);
        buscaPanel.add(buscarButton);

        buscarButton.addActionListener(e -> {
            String autor = buscaAutorField.getText().trim();
            if (!autor.isEmpty()) {
                List<Livro> livrosEncontrados = gerenciadorDeLivros.buscarPorAutor(autor);
                if (!livrosEncontrados.isEmpty()) {
                    StringBuilder resultado = new StringBuilder("Livros encontrados:\n");
                    for (Livro livro : livrosEncontrados) {
                        resultado.append(livro).append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, resultado.toString());
                } else {
                    JOptionPane.showMessageDialog(frame, "Nenhum livro encontrado para o autor: " + autor);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, insira o nome do autor.");
            }
        });
        

        // Botões de ordenação
        JButton ordenarPorAutorButton = new JButton("Ordenar por Autor");
        JButton ordenarPorTituloButton = new JButton("Ordenar por Título");
        ordenarPorAutorButton.setBackground(new Color(70, 130, 180)); // Cor do botão
        ordenarPorAutorButton.setForeground(Color.WHITE);
        ordenarPorAutorButton.setFocusPainted(false);
        ordenarPorAutorButton.setBorderPainted(false);
        ordenarPorAutorButton.setFont(new Font("Arial", Font.BOLD, 14));

        ordenarPorTituloButton.setBackground(new Color(70, 130, 180)); // Cor do botão
        ordenarPorTituloButton.setForeground(Color.WHITE);
        ordenarPorTituloButton.setFocusPainted(false);
        ordenarPorTituloButton.setBorderPainted(false);
        ordenarPorTituloButton.setFont(new Font("Arial", Font.BOLD, 14));

        ordenarPorAutorButton.addActionListener(e -> {
            gerenciadorDeLivros.ordenarPorAutor();
        });

        ordenarPorTituloButton.addActionListener(e -> {
            gerenciadorDeLivros.ordenarPorTitulo();
        });

        // Adicionando o painel de busca e a lista de livros no layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(listaLivrosPanel);

        // Adicionando botões de ordenação ao painel de lista
        JPanel botaoPanel = new JPanel();
        botaoPanel.setLayout(new BoxLayout(botaoPanel, BoxLayout.Y_AXIS));
        botaoPanel.add(ordenarPorAutorButton);
        botaoPanel.add(ordenarPorTituloButton);

        mainPanel.add(scrollPane, BorderLayout.CENTER);  // Lista de livros à esquerda
        mainPanel.add(buscaPanel, BorderLayout.SOUTH);  // Painel de busca na parte inferior
        mainPanel.add(botaoPanel, BorderLayout.WEST); // Painel de botões à esquerda

        frame.add(formPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER); // Adiciona o painel principal ao centro

        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela
    }
}
