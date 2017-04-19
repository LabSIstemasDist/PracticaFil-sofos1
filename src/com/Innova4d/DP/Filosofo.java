package com.Innova4d.DP;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * La clase Filosofo que utiliza Runnable para poder ser implementada por un thread.
 * @author Jose Alonso Zavala Pulido <jose.zavalapo@udlap.mx>
 * @version 1.0
 */
class Filosofo implements Runnable {

	// Tiempo en que come un filósofo
	private Random numGenerator = new Random();
	// El Id del filosofo
	private int id;

	/* 
	 * Definir los cubiertos del filosofo.
	 * En Java los recursos compartidos se definen como Locks.
	 */
	private Lock cubiertoIzquierdo = new ReentrantLock();
	private Lock cubiertoDerecho = new ReentrantLock();

	/**
	 * El constructor del filósofo
	 * @param id el id del filósofo
	 * @param cubiertoIzquierdo cubierto izquierdo
	 * @param cubiertoDerecho cubierto derecho
	 */
	public Filosofo (int id, Lock cubiertoIzquierdo, Lock cubiertoDerecho) {
		this.id = id;
		this.cubiertoIzquierdo = cubiertoIzquierdo;
		this.cubiertoDerecho = cubiertoDerecho;
	}

	/**
	 * Ejecuta el thread, piensa, levanta cubiertos, come.
	 */
	public void run() {
		try {
			while (true) {
				piensa();
				levantaCubiertoIzquierdo();
				levantaCubiertoDerecho();
				come();
				bajaCubiertos();
			}
		} catch (InterruptedException e) {
			System.out.println("El Filósofo " + id + " fue interrumpido.\n");			
		}
	}

	/** 
	 * El filósofo está pensando
	 */
	private void piensa() throws InterruptedException {
		System.out.println("El Filósofo " + id + " está pensando.\n");
		Thread.sleep (numGenerator.nextInt(10));
		/*
		 * Cuando el filósofo está pensando debemos "dormir el hilo por un tiempo aleatorio"
		 */
	}

	/** 
	 * El filósofo ocupa el cubierto (Recurso compartido)
	 */
	private void levantaCubiertoIzquierdo() {
		cubiertoIzquierdo.lock();
		System.out.println("El filósofo " + id + " tiene en la mano el cubierto izquierdo.\n");
	}

	/** 
	 * El filósofo ocupa el cubierto (Recurso compartido)
	 */
	private void levantaCubiertoDerecho() {
		cubiertoDerecho.lock();
		System.out.println("El filósofo " + id + " tiene en la mano el cubierto derecho.\n");
		/*
		 * Cuando el filósofo levanta el cubierto derecho el recurso es ocupado "Locked".
		 */
	}

	/**
	 * El filósofo come...
	 * @throws InterruptedException
	 */
	private void come() throws InterruptedException {
		System.out.println("El filósofo " + id + " está comiendo.\n");
		Thread.sleep (numGenerator.nextInt(10));
	}

	/**
	 * El filósofo baja los cubiertos
	 */
	private void bajaCubiertos() {
		cubiertoIzquierdo.unlock();
		cubiertoDerecho.unlock();
		System.out.println("El filósofo " + id + " baja ambos cubiertos.\n");
		/*
		 * Cuando el filósofo desocupa 
		 * ambos cubiertos los recursos deben ser desbloqueados "Unlocked".
		 */
	}
}