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
        if (raiz == null) {
            raiz = new NoArvore(valor);
        } else {
            recursivoinsereElemento(raiz, valor);
        }
    }

    private void recursivoinsereElemento(NoArvore raiz2, int valor) {
        if (valor < raiz2.getValor()) {
            if (raiz2.getEsquerda() == null) {
                raiz2.setEsquerda(new NoArvore(valor));
            } else {
                recursivoinsereElemento(raiz2.getEsquerda(), valor);
            }
        } else {
            if (raiz2.getDireita() == null) {
                raiz2.setDireita(new NoArvore(valor));
            } else {
                recursivoinsereElemento(raiz2.getDireita(), valor);
            }
        }
    }

    @Override
    public void remove(int valor) {
        if (raiz != null) {
            raiz = recursivoremove(raiz, valor);
        }
    }

    private NoArvore recursivoremove(NoArvore raiz2, int valor) {
        if (raiz2 == null) {
            return null;
        } else if (raiz2.getValor() == valor) {
            if (raiz2.getEsquerda() == null && raiz2.getDireita() == null) {
                return null;
            } else if (raiz2.getEsquerda() == null) {
                return raiz2.getDireita();
            } else if (raiz2.getDireita() == null) {
                return raiz2.getEsquerda();
            } else {
                int minimo = recursivominimo(raiz2.getDireita());
                raiz2.setValor(minimo);
                raiz2.setDireita(recursivoremove(raiz2.getDireita(), minimo));
                return raiz2;
            }
        } else if (valor < raiz2.getValor()) {
            raiz2.setEsquerda(recursivoremove(raiz2.getEsquerda(), valor));
            return raiz2;
        } else {
            raiz2.setDireita(recursivoremove(raiz2.getDireita(), valor));
            return raiz2;
        }
    }

    @Override
    public int[] preOrdem() {
        int[] resultado;
        if (raiz == null) {
            resultado = new int[0];
        } else {
            resultado = new int[1];
            resultado[0] = raiz.getValor();
        }
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
