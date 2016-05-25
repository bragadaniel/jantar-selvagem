package concorrente;

import java.util.concurrent.Semaphore;

/* 
 * Classe Selvagem.
 * Utilizida para controle do caldeirão, tem como objetivo encher o caldeirão toda vez que este estiver vazio. 
 * Variáveis:
 *      TEMPO_SERVIR - Estática . Define o tempo que o processo vai estar esperando acesso à sessão crítica.
 *      TEMPO_COMER - Estática. Define o tempo que o processo vai estar usando à sessão crítica.   
 *      identifica - Inteira. Usada para verificação do status do caldeirão.
 * Métodos: Get e Set das variáveis, void:comer(), void:servir(), void:run().
 * 
 */
public class Selvagem extends Thread {

    final static int TEMPO_SERVIR = 1000;
    final static int TEMPO_COMER = 3000;
    private int identifica;
    Semaphore selvagemSemaforo;
    Caldeirao caldeirao;
    Cozinheiro cozinheiro;

    public Selvagem() {
    } // Construtor padrão da classe.

    //Construtor completo da classe, atribuição de valores.
    public Selvagem(int identifica, Semaphore selvagemSemaforo, Caldeirao caldeirao, Cozinheiro cozinheiro) {
        this.identifica = identifica;
        this.selvagemSemaforo = selvagemSemaforo;
        this.caldeirao = caldeirao;
        this.cozinheiro = cozinheiro;

    }

    public void comer() throws InterruptedException { //Thread que sincroniza o selvagem ao uso da sessão crítica.
        synchronized (this) {
            System.out.println(this.getName() + "   ~> Comendo, porção de nº:(" + getCaldeirao().getPorcoes() + ")");
            this.notify();
        }
    }//fim metodo

    public void servir() throws InterruptedException {
        synchronized (this) {

            //Verificando a quantidade de poções no caldeirão para decidir de quem vai ser a vez.
            if (getCaldeirao().getPorcoes() == 0) { // Se estiver vazio.
                //System.out.println("\nAcabou a comida, acordando o cozinheiro.");
                //System.out.println("\nAcabou a comida, acordando o cozinheiro."); //Impressão de Debug na tela.
                cozinheiro.encher_caldeirao(); // Atribuindo valor(Colando comida) ao calderã novamente.  


            } else {  // Se ainda possuir porções de comida.



                getCaldeirao().setPorcoes(getCaldeirao().getPorcoes() - 1);
                System.out.println(this.getName() + "   ~> Servindo, porção de nº:(" + getCaldeirao().getPorcoes() + ")");

                Thread.sleep(TEMPO_COMER);
                comer();


            }
            this.notify();

        }

    } // Fim do método servir.

    public void run() {
        while (true) {
            try { // Execução do método e tratamento de execeção.

                selvagemSemaforo.acquire();
                Thread.sleep(TEMPO_SERVIR);
                servir();


            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally { //Definido o término da execução.
                selvagemSemaforo.release();

            }
        }
    }

    //Declaração dos Gets e Sets das variáveis.
    public int getIdentifica() {
        return identifica;
    }

    public void setIdentifica(int identifica) {
        this.identifica = identifica;
    }

    public Semaphore getAux() {
        return selvagemSemaforo;
    }

    public void setAux(Semaphore aux) {
        this.selvagemSemaforo = aux;
    }

    public Caldeirao getCaldeirao() {
        return caldeirao;
    }

    public void setCaldeirao(Caldeirao caldeirao) {
        this.caldeirao = caldeirao;
    }
}//fim da classe Selvagem.
