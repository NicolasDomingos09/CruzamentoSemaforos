package view;
import controller.Carro;
import java.util.concurrent.Semaphore;

public class Main {
	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		
		for(int i = 0; i < 4; i++) {
			Thread t = new Carro(semaforo, i);
			t.start();
		}
	}
}
