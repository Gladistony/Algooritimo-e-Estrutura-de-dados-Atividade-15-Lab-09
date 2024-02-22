package tree;

import estrut.Tree;

public class BinarySearchTree implements Tree{

    private NoArvore raiz;

    @Override
    public boolean buscaElemento(int valor) {
        if (raiz == null) {
            return false;
        } else {
            return recursivobuscaElemento(raiz, valor);
        }
    }

    private boolean recursivobuscaElemento(NoArvore no,int valor) {
        if (no == null) {
            return false;
        } else if (no.getValor() == valor) {
            return true;
        } else if (valor < no.getValor()) {
            return recursivobuscaElemento(no.getEsquerda(), valor);
        } else {
            return recursivobuscaElemento(no.getDireita(), valor);
        }
    }

    @Override
    public int minimo() {
        if (raiz == null) {
            return 0;
        } else {
            return recursivominimo(raiz);
        }
    }

    private int recursivominimo(NoArvore raiz2) {
        if (raiz2.getEsquerda() == null) {
            return raiz2.getValor();
        } else {
            return recursivominimo(raiz2.getEsquerda());
        }
    }

    @Override
    public int maximo() {
        if (raiz == null) {
            return 0;
        } else {
            return recursivomaximo(raiz);
        }
    }

    private int recursivomaximo(NoArvore raiz2) {
        if (raiz2.getDireita() == null) {
            return raiz2.getValor();
        } else {
            return recursivomaximo(raiz2.getDireita());
        }
    }

    @Override
    public void insereElemento(int valor) {
        return;
    }

    @Override
    public void remove(int valor) {
        return;
    }

    @Override
    public int[] preOrdem() {
        return new int[]{1,2};
    }

    @Override
    public int[] emOrdem() {
        return new int[]{1,2};
    }

    @Override
    public int[] posOrdem() {
        return new int[]{1,2};
    }

}
