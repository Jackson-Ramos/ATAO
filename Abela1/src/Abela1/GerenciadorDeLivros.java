package Abela1;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class GerenciadorDeLivros {
    private Livro[] livros; // Array para armazenar livros
    private int count; // Contador de livros
    private JPanel listaLivrosPanel; // Painel onde os livros serão listados

    public GerenciadorDeLivros(int capacidade, JPanel listaLivrosPanel) {
        this.livros = new Livro[capacidade]; // Inicializa o array de livros
        this.count = 0; // Inicializa o contador de livros
        this.listaLivrosPanel = listaLivrosPanel; // Define o painel de livros
    }

    public void adicionarLivro(String titulo, String autor, int ano) {
        if (count < livros.length) {
            livros[count] = new Livro(titulo, autor, ano); // Adiciona um novo livro
            count++;
            atualizarLista(); // Atualiza a lista após adicionar um livro
        } else {
            JOptionPane.showMessageDialog(null, "Capacidade máxima atingida!"); // Mensagem de erro
        }
    }

    public void removerLivro(int indice) {
        if (indice >= 0 && indice < count) {
            for (int i = indice; i < count - 1; i++) {
                livros[i] = livros[i + 1];  // Shift dos livros
            }
            livros[count - 1] = null; // Limpa a última posição
            count--;
            atualizarLista(); // Atualiza a lista após remover um livro
        }
    }

    public void listarLivros() {
        listaLivrosPanel.removeAll();  // Limpa o painel antes de listar os livros

        if (count == 0) {
            JLabel vazioLabel = new JLabel("Nenhum livro no acervo."); // Mensagem se não houver livros
            listaLivrosPanel.add(vazioLabel);
        } else {
            for (int i = 0; i < count; i++) {
                JPanel livroPanel = new JPanel();  // Cria o painel para cada livro
                JLabel livroLabel = new JLabel(livros[i].toString());  // Mostra o livro
                JButton removerButton = new JButton("Remover");

                // Atualiza o índice do livro dinamicamente para remoção correta
                int index = i;
                removerButton.addActionListener(e -> {
                    removerLivro(index); // Adiciona ação de remoção ao botão
                });

                livroPanel.add(livroLabel); // Adiciona a label do livro ao painel
                livroPanel.add(removerButton); // Adiciona o botão de remover ao painel
                listaLivrosPanel.add(livroPanel);  // Adiciona o painel do livro ao painel principal
            }
        }

        // Atualiza visualmente o painel após a adição
        listaLivrosPanel.revalidate(); // Revalida após adicionar componentes
        listaLivrosPanel.repaint(); // Repaint para garantir que as atualizações apareçam
    }

    // Método para ordenar os livros pelo título
    public void ordenarPorTitulo() {
        quickSort(0, count - 1, true);
        listarLivros();  // Atualiza a lista após ordenação
    }

    // Método para ordenar os livros pelo autor
    public void ordenarPorAutor() {
        quickSort(0, count - 1, false);
        listarLivros();  // Atualiza a lista após ordenação
    }

    // Implementação do QuickSort
    private void quickSort(int inicio, int fim, boolean ordenarPorTitulo) {
        if (inicio < fim) {
            int posicaoPivo = partition(inicio, fim, ordenarPorTitulo);
            quickSort(inicio, posicaoPivo - 1, ordenarPorTitulo);
            quickSort(posicaoPivo + 1, fim, ordenarPorTitulo);
        }
    }

    // Método de particionamento para o QuickSort
    private int partition(int inicio, int fim, boolean ordenarPorTitulo) {
        Livro pivo = livros[fim]; // O último livro é o pivô
        int i = inicio - 1; // Índice do menor elemento
        for (int j = inicio; j < fim; j++) {
            if (ordenarPorTitulo) {
                if (livros[j].getTitulo().compareToIgnoreCase(pivo.getTitulo()) < 0) {
                    i++;
                    trocarLivros(i, j); // Troca os livros
                }
            } else {
                if (livros[j].getAutor().compareToIgnoreCase(pivo.getAutor()) < 0) {
                    i++;
                    trocarLivros(i, j); // Troca os livros
                }
            }
        }
        trocarLivros(i + 1, fim); // Coloca o pivô na posição correta
        return i + 1;
    }

    // Método para buscar livro por autor
    public List<Livro> buscarPorAutor(String autor) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (int i = 0; i < count; i++) { // Itera sobre a lista de livros
            Livro livro = livros[i]; // Obtém o livro
            if (livro != null && livro.getAutor().equalsIgnoreCase(autor)) { // Verifica se o autor coincide
                livrosEncontrados.add(livro); // Adiciona à lista de livros encontrados
            }
        }
        return livrosEncontrados; // Retorna a lista de livros encontrados
    }

    // Troca de posição de dois livros
    private void trocarLivros(int i, int j) {
        Livro temp = livros[i];
        livros[i] = livros[j];
        livros[j] = temp; // Troca os livros
    }

    // Método para atualizar a lista de livros após qualquer operação
    void atualizarLista() {
        listarLivros();
    }
}
