package controller;
import java.util.concurrent.Semaphore;

public class ThreadMultiprocessamento extends Thread{
	
	private Semaphore BD;
	private int idThread;
	private static int posCalculo;
	private double tempoCalculo = 0.0;
	private static int saiuBD;
	
	public ThreadMultiprocessamento(int idThread, Semaphore BD) {
		
		this.idThread = idThread;
		this.BD = BD;
	}
	
	public void run() {
		
		calculos();
		try {
			BD.acquire();
			transacaoBD();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			BD.release();
			concluido();
		}
		
	}
	
	public void calculos() {
		
		if (idThread % 3 == 1) {
			
			tempoCalculo = ((Math.random() * 1001.0) + 200.0);
		} else if (idThread % 3 == 2) {
			
			tempoCalculo = ((Math.random() * 1501.0) + 500.0);
		} else {
			
			tempoCalculo = ((Math.random() * 2001.0) + 1000.0);
		}
		try {
			sleep((long) tempoCalculo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		posCalculo++;
		System.out.println("#" + idThread + "| foi a " + posCalculo + " a calcular");
		
		
		
	}
	
	public void transacaoBD() {
		
		System.out.println("#" + idThread + "| executando a transação de BD");
		if (idThread % 3 == 1) {

			tempoCalculo = 1001.0;
		} else if (idThread % 3 == 2) {

			tempoCalculo = 1501.0;
		} else {

			tempoCalculo = 1501.0;
		}
		try {
			sleep((long) tempoCalculo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void concluido() {
		
		saiuBD++;
		System.out.println("#" + idThread + "| foia a " + saiuBD +" a concluir a transação do BD!");
	}
	
	
	
	
	
	
	
}
