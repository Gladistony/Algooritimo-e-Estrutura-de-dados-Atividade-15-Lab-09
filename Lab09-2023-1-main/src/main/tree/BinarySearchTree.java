package tree;

import java.util.ArrayList;
import java.util.List;

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
        // Cria uma lista auxiliar para armazenar os valores
        List<Integer> lista = new ArrayList<>();
        // Chama o método recursivo passando a lista como parâmetro
        preOrdemRecursivo(raiz, lista);
        // Converte a lista em um array de inteiros e retorna
        return lista.stream().mapToInt(i -> i).toArray();
    }

    // Método privado que percorre a árvore em pré-ordem de forma recursiva
    private void preOrdemRecursivo(NoArvore no, List<Integer> lista) {
        // Se o nó não é nulo, adiciona o valor do nó na lista, depois visita a subárvore esquerda, depois a subárvore direita
        if (no != null) {
        lista.add(no.getValor());
        preOrdemRecursivo(no.getEsquerda(), lista);
        preOrdemRecursivo(no.getDireita(), lista);
        }
    }

    @Override
    public int[] emOrdem() {
        // Cria uma lista auxiliar para armazenar os valores
        List<Integer> lista = new ArrayList<>();
        // Chama o método recursivo passando a lista como parâmetro
        emOrdemRecursivo(raiz, lista);
        // Converte a lista em um array de inteiros e retorna
        return lista.stream().mapToInt(i -> i).toArray();
    }

    // Método privado que percorre a árvore em ordem de forma recursiva
    private void emOrdemRecursivo(NoArvore no, List<Integer> lista) {
        // Se o nó não é nulo, visita a subárvore esquerda, depois adiciona o valor do nó na lista, depois visita a subárvore direita
        if (no != null) {
        emOrdemRecursivo(no.getEsquerda(), lista);
        lista.add(no.getValor());
        emOrdemRecursivo(no.getDireita(), lista);
        }
    }

    @Override
    public int[] posOrdem() {
        // Cria uma lista auxiliar para armazenar os valores
        List<Integer> lista = new ArrayList<>();
        // Chama o método recursivo passando a lista como parâmetro
        posOrdemRecursivo(raiz, lista);
        // Converte a lista em um array de inteiros e retorna
        return lista.stream().mapToInt(i -> i).toArray();
    }

    // Método privado que percorre a árvore em pós-ordem de forma recursiva
    private void posOrdemRecursivo(NoArvore no, List<Integer> lista) {
        // Se o nó não é nulo, visita a subárvore esquerda, depois a subárvore direita, depois adiciona o valor do nó na lista
        if (no != null) {
        posOrdemRecursivo(no.getEsquerda(), lista);
        posOrdemRecursivo(no.getDireita(), lista);
        lista.add(no.getValor());
        }
    }
}
