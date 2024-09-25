package Abela1;

class No {
    Livro livro;
    No esquerda;
    No direita;

    No(Livro livro) {
        this.livro = livro;
        this.esquerda = null;
        this.direita = null;
    }
}
