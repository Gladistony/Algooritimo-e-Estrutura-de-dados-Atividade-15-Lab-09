package tree;

public class NoArvore {
    private int valor;
    private NoArvore esquerda;
    private NoArvore direita;
    private NoArvore pai;
    private int altura;
    private int balanceamento; // novo atributo para armazenar o fator de balanceamento
    //Construtor
    public NoArvore(int valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
        this.pai = null;
        this.altura = 0;
        this.balanceamento = 0;
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
    public void setPai(NoArvore raiz2) {
        this.pai = raiz2;
    }
    public NoArvore getPai() {
        return pai;
    }
    public void setBalanceamento(int i) {
        this.balanceamento = i;
    }
    public int getBalanceamento() {
        return balanceamento;
    }

}
