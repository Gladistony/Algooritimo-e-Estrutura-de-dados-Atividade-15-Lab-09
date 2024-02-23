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
                raiz2.getEsquerda().setPai(raiz2); // seta o pai do nó inserido
                balancear(raiz2); // chama o método de balanceamento
            } else {
                recursivoinsereElemento(raiz2.getEsquerda(), valor);
            }
        } else {
            if (raiz2.getDireita() == null) {
                raiz2.setDireita(new NoArvore(valor));
                raiz2.getDireita().setPai(raiz2); // seta o pai do nó inserido
                balancear(raiz2); // chama o método de balanceamento
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
                if (raiz2.getPai() == null) { // se for a raiz
                    raiz = null;
                } else {
                    NoArvore pai = raiz2.getPai();
                    if (pai.getEsquerda() == raiz2) { // se for filho esquerdo
                        pai.setEsquerda(null);
                    } else { // se for filho direito
                        pai.setDireita(null);
                    }
                    balancear(pai); // chama o método de balanceamento
                }
                return null;
            } else if (raiz2.getEsquerda() == null) {
                NoArvore pai = raiz2.getPai();
                if (pai == null) { // se for a raiz
                    raiz = raiz2.getDireita();
                } else {
                    if (pai.getEsquerda() == raiz2) { // se for filho esquerdo
                        pai.setEsquerda(raiz2.getDireita());
                    } else { // se for filho direito
                        pai.setDireita(raiz2.getDireita());
                    }
                    raiz2.getDireita().setPai(pai);
                    balancear(pai); // chama o método de balanceamento
                }
                return raiz2.getDireita();
            } else if (raiz2.getDireita() == null) {
                NoArvore pai = raiz2.getPai();
                if (pai == null) { // se for a raiz
                    raiz = raiz2.getEsquerda();
                } else {
                    if (pai.getEsquerda() == raiz2) { // se for filho esquerdo
                        pai.setEsquerda(raiz2.getEsquerda());
                    } else { // se for filho direito
                        pai.setDireita(raiz2.getEsquerda());
                    }
                    raiz2.getEsquerda().setPai(pai);
                    balancear(pai); // chama o método de balanceamento
                }
                return raiz2.getEsquerda();
            } else {
                // caso em que o nó tem dois filhos
                // usa o sucessor ou o antecessor do nó para substituí-lo
                // neste exemplo, usamos o sucessor
                NoArvore sucessor = sucessor(raiz2);
                raiz2.setValor(sucessor.getValor()); // copia o valor do sucessor
                raiz2.setDireita(recursivoremove(raiz2.getDireita(), sucessor.getValor())); // remove o sucessor da subárvore direita
                balancear(raiz2); // chama o método de balanceamento
                return raiz2;
            }
        } else if (valor < raiz2.getValor()) {
            raiz2.setEsquerda(recursivoremove(raiz2.getEsquerda(), valor));
            balancear(raiz2); // chama o método de balanceamento
            return raiz2;
        } else {
            raiz2.setDireita(recursivoremove(raiz2.getDireita(), valor));
            balancear(raiz2); // chama o método de balanceamento
            return raiz2;
        }
    }

    private NoArvore sucessor(NoArvore no) {
        // se o nó tem um filho direito, o sucessor é o menor valor da subárvore direita
        if (no.getDireita() != null) {
            return minValue(no.getDireita());
        }

        // se o nó não tem um filho direito, o sucessor é o primeiro ancestral que tem o nó como descendente da sua subárvore esquerda
        NoArvore pai = no.getPai();
        while (pai != null && no == pai.getDireita()) {
            no = pai;
            pai = pai.getPai();
        }
        return pai;
    }

    // método para encontrar o menor valor em uma subárvore binária
    private NoArvore minValue(NoArvore no) {
        // se o nó for nulo, retorna um valor infinito
        if (no == null) {
            return null;
        }
        // se o nó não tiver um filho esquerdo, retorna o valor do nó
        if (no.getEsquerda() == null) {
            return no;
        }
        // se o nó tiver um filho esquerdo, chama a função minValue() para o filho esquerdo
        return minValue(no.getEsquerda());
    }


    // método para calcular a altura de um nó
    private int calcularAltura(NoArvore no) {
        if (no == null) {
            return -1;
        } else {
            return 1 + Math.max(calcularAltura(no.getEsquerda()), calcularAltura(no.getDireita()));
        }
    }

    // método para calcular o balanceamento de um nó
    private void calcularBalanceamento(NoArvore no) {
        if (no != null) {
            no.setBalanceamento(calcularAltura(no.getEsquerda()) - calcularAltura(no.getDireita()));
        }
    }

    // método para fazer uma rotação simples à direita
    private NoArvore rotacaoDireita(NoArvore no) {
        NoArvore esquerda = no.getEsquerda();
        NoArvore pai = no.getPai();

        no.setEsquerda(esquerda.getDireita());

        if (no.getEsquerda() != null) {
            no.getEsquerda().setPai(no);
        }

        esquerda.setDireita(no);
        esquerda.setPai(pai);

        no.setPai(esquerda);

        if (pai != null) {
            if (pai.getEsquerda() == no) {
                pai.setEsquerda(esquerda);
            } else {
                pai.setDireita(esquerda);
            }
        }

        calcularBalanceamento(no);
        calcularBalanceamento(esquerda);

        System.out.println("Rotação simples à direita realizada"); // aviso de rotação

        return esquerda;
    }

    // método para fazer uma rotação simples à esquerda
    private NoArvore rotacaoEsquerda(NoArvore no) {
        NoArvore direita = no.getDireita();
        NoArvore pai = no.getPai();

        no.setDireita(direita.getEsquerda());

        if (no.getDireita() != null) {
            no.getDireita().setPai(no);
        }

        direita.setEsquerda(no);
        direita.setPai(pai);

        no.setPai(direita);

        if (pai != null) {
            if (pai.getEsquerda() == no) {
                pai.setEsquerda(direita);
            } else {
                pai.setDireita(direita);
            }
        }

        calcularBalanceamento(no);
        calcularBalanceamento(direita);

        System.out.println("Rotação simples à esquerda realizada"); // aviso de rotação

        return direita;
    }

    // método para fazer uma rotação dupla à direita
    private NoArvore rotacaoDuplaDireita(NoArvore no) {
        no.setEsquerda(rotacaoEsquerda(no.getEsquerda()));
        return rotacaoDireita(no);
    }

    // método para fazer uma rotação dupla à esquerda
    private NoArvore rotacaoDuplaEsquerda(NoArvore no) {
        no.setDireita(rotacaoDireita(no.getDireita()));
        return rotacaoEsquerda(no);
    }

        // método para balancear um nó
    private void balancear(NoArvore no) {
        calcularBalanceamento(no);

        if (no.getBalanceamento() == -2) {
            if (calcularAltura(no.getDireita().getDireita()) >= calcularAltura(no.getDireita().getEsquerda())) {
                no = rotacaoEsquerda(no);
            } else {
                no = rotacaoDuplaEsquerda(no);
            }
        } else if (no.getBalanceamento() == 2) {
            if (calcularAltura(no.getEsquerda().getEsquerda()) >= calcularAltura(no.getEsquerda().getDireita())) {
                no = rotacaoDireita(no);
            } else {
                no = rotacaoDuplaDireita(no);
            }
        }

        if (no.getPai() != null) {
            balancear(no.getPai());
        } else {
            raiz = no;
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
