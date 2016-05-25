/*
	Programaçao Concorrente e Distribuida 
 */
package concorrente;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class Principal extends Object {

    public static void main(String args[]) throws Exception {
        int n = 1;//numero de requisiçoes para sessao critica
        int porcoes = 5;//quantidade de recurso 
        int selvagens = 3;//quantidade de processos que disputara a sessao critica

        Semaphore semaphore = new Semaphore(n, false); //Instância do objeto semáforo, passando a quantidade e a permissão.
        Caldeirao caldeirao = new Caldeirao(porcoes); //Instância do calderão
        System.out.println("O caldeirão foi iniciado com:  " + caldeirao.getPorcoes() + " porções.\n");

        Cozinheiro cozinheiro = new Cozinheiro(caldeirao, porcoes); //Instanciando o objeto da classe cozinheiro.
        cozinheiro.setName("Cozinheiro"); //Atribuindo nome ao cozinheiro.

        Selvagem[] selvagem = new Selvagem[selvagens]; //Declarando o array com a quantidade de selvagens existentes.

        for (int i = 0; i < selvagem.length; i++) { //Iteração do laço
            selvagem[i] = new Selvagem(i, semaphore, caldeirao, cozinheiro); //Instanciando os objetos da classe selvagem.
            selvagem[i].setName("Selvagem_" + i); //Atribuindo nome aos selvagens. 
            selvagem[i].start(); //Início starter_track.
        }// Fim for.

        cozinheiro.start();


        //cronometro executa o programa em ate 2 minutos depois 
        // para a execução
        int tempo = (1000*8) * 15;//2 minutos
        int p = 1; //quantidade de vezes a ser executado
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(
                new TimerTask() {
            public void run() {
                System.exit(0);
            }
        }, tempo, p);

    }
}
