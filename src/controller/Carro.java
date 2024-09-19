package controller;
import java.util.concurrent.Semaphore;

public class Carro extends Thread {
	
	private Semaphore semaforo;
	private final int id = (int)threadId();
	private String[] direcao = {"Norte","Sul","Leste","Oeste"};
	private String[] carro = new String[2];
	
	public Carro(Semaphore semaforo, int i) {
		this.semaforo = semaforo;
		carro[1] = Integer.toString(id);
		carro[0] = direcao[i];
	}
	
	private void andando() {
		for(int i = 0; i < 4; i++) {
			System.out.println("Carro " + carro[1] + " está vindo da direção " + carro[0]);
			try {
				sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Carro " + carro[1] + " chegou no cruzamento");
	}
	
	private void cruzamento() {
		try {
			System.out.println("O carro " + carro[1] + " está passando pelo cruzamento" );
			sleep(1500);
			System.out.println("O carro " + carro[1] + " saiu do cruzamento" );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		andando();
		
		try {
			semaforo.acquire();
			cruzamento();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			semaforo.release();
		}
	}
}
