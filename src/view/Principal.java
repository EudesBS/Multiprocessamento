package view;
import controller.ThreadMultiprocessamento; 
import java.util.concurrent.Semaphore;


public class Principal {
	public static void main(String[] args) {
		
		int processos = 1;
		Semaphore BD = new Semaphore(processos);
		
		for (int idThread = 1; idThread <= 21; idThread++) {
			
			Thread multiprocessamento = new ThreadMultiprocessamento(idThread, BD);
			multiprocessamento.start();
		}
		
		
		
	}
}
