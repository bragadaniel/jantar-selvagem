package concorrente;

/*
 * Classe Caldeirão.
 * Utilizida para controle e manipulação do recipiente a qual os processos disputarão acesso.
 * Única variável inteira de nome porções.
 * Métodos: Get, Set, toString.
 * 
 */

import java.util.concurrent.Semaphore;
public class Caldeirao {

    private int porcoes;

    public Caldeirao(int porcoes) {
        this.porcoes = porcoes;
    }

    public int getPorcoes() {
        return porcoes;
    }

    public void setPorcoes(int porcoes) {
        this.porcoes = porcoes;
    }

    @Override
    public String toString() {
        return "" + porcoes;
    }
}
