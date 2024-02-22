package tree;

public class NoArvore {
    private int valor;
    private NoArvore esquerda;
    private NoArvore direita;
    private NoArvore pai;
    private int altura;
    //Construtor
    public NoArvore(int valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
        this.pai = null;
        this.altura = 0;
    }
    //Metodos Get
    public int getValor() {
        return valor;
    }
    public NoArvore getEsquerda() {
        return esquerda;
    }
    public NoArvore getDireita() {
        return direita;
    }
    //Metodos Set
    public void setEsquerda(NoArvore noArvore) {
        this.esquerda = noArvore;
    }
    public void setDireita(NoArvore noArvore) {
        this.direita = noArvore;
    }
    public void setValor(int minimo) {
        this.valor = minimo;
    }

}
