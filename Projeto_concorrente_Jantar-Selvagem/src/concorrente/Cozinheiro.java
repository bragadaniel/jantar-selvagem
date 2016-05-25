package concorrente;

import static concorrente.Cozinheiro.TEMPO_PREPARO;

/* 
 * Classe Cozinheiro.
 * Utilizida para controle do caldeirão, tem como objetivo encher o caldeirão toda vez que este estiver vazio. 
 * Variáveis:
 *      TEMPO_PREPARO - Estática . Define o tempo que o cozinheiro
 *      porções - Inteira. Atribui a quantiade de poções existentes no caldeirão.
 *      verifica - Boolean. Utilizada mais para controle, verificando a disponibilidade.
 * Métodos: Get e Set das variáveis, void:encher_caldeirao(), void:preparar(), void:dormir(), void:run().
 * 
 */
public class Cozinheiro extends Thread {

    final static int TEMPO_PREPARO = 5000;
    private Caldeirao caldeirao;
    public int porcoes;
    private boolean verifica = false;

    public Cozinheiro() {
    } //Construtor padrão da classe.

    public Cozinheiro(Caldeirao caldeirao, int porcao) { //Construtor da classe.
        this.caldeirao = caldeirao;
        this.porcoes = porcao;
    }

    public void preparar() {  //Método de preparado
        try {
            Thread.sleep(TEMPO_PREPARO);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void encher_caldeirao() throws InterruptedException { // Método que enche o caldeirão com porções de comida.
        synchronized (this) {
           // if (getCaldeirao().getPorcoes() == 0) { //Verifica se o caldeirão está vazio.
            //Impressão de debug na tela.
            //System.out.println("O caldeirão possui " + getCaldeirao().getPorcoes() + ", por tanto está vazio.");
            caldeirao.setPorcoes(5); //Atribuindo valores (Enchendo novamente) ao caldeirão.
            System.out.println("\n"+this.getName() + " enche Caldeirão com (" + caldeirao.getPorcoes() + ") porções. ");
            this.notify();
            this.wait();
            
       // }

        }

    }
    public void dormir() throws InterruptedException { //Thread que sincroniza o cozinheiro.
        synchronized (this) {
            System.out.println("Cozinheiro volta a dormir!\n");
            this.notify();
            this.wait();
        }
        //this.interrupt();
    }//fim metodo dormir    

    public void run() { // declaração do método que roda a thread.
        
        while(true){
        try {
           preparar(); // Coloca a thread para esperar pelo tempo detarminado na declaração da variável.
          //  while (getCaldeirao().getPorcoes() == 0) {
                
                //encher_caldeirao(); //Chamada do método para encher a caldeira.
         
                if (getCaldeirao().getPorcoes() == 5) {
                    System.out.println("Caldeirão agora possui: ("+getCaldeirao().getPorcoes()+")");
                    dormir();
       
                    this.isInterrupted();
                   
                }
//                else{
//                    encher_caldeirao();
//                }
           // }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        }

    }//fim método run.

    //Declaração dos Gets e Sets das variáveis.
    public int getPorcoes() {
        return porcoes;
    }

    public void setPorcoes(int porcoes) {
        this.porcoes = porcoes;
    }

    public boolean getVerifica() {
        return verifica;
    }

    public void setVerifica(boolean verifica) {
        this.verifica = verifica;
    }

    public Caldeirao getCaldeirao() {
        return caldeirao;
    }

    public void setCaldeirao(Caldeirao caldeirao) {
        this.caldeirao = caldeirao;
    }
}//fim classe
