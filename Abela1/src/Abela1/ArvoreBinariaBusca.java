package Abela1;

class ArvoreBinariaBusca {
    private No raiz;

    public ArvoreBinariaBusca() {
        this.raiz = null;
    }

    public void inserir(Livro livro) {
        raiz = inserirRecursivo(raiz, livro);
    }

    private No inserirRecursivo(No no, Livro livro) {
        if (no == null) {
            return new No(livro);
        }
        if (livro.getAutor().compareToIgnoreCase(no.livro.getAutor()) < 0) {
            no.esquerda = inserirRecursivo(no.esquerda, livro);
        } else {
            no.direita = inserirRecursivo(no.direita, livro);
        }
        return no;
    }

    public void listarLivrosEmOrdem() {
        listarEmOrdemRecursivo(raiz);
    }

    private void listarEmOrdemRecursivo(No no) {
        if (no != null) {
            listarEmOrdemRecursivo(no.esquerda);
            System.out.println(no.livro);
            listarEmOrdemRecursivo(no.direita);
        }
    }

    public Livro buscarPorAutor(String autor) {
        return buscarRecursivo(raiz, autor);
    }

    private Livro buscarRecursivo(No no, String autor) {
        if (no == null) {
            return null;
        }
        if (no.livro.getAutor().equalsIgnoreCase(autor)) {
            return no.livro;
        }
        Livro resultado = buscarRecursivo(no.esquerda, autor);
        if (resultado != null) {
            return resultado;
        }
        return buscarRecursivo(no.direita, autor);
    }
}
