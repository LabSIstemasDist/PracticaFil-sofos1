package com.Innova4d.DP;
import java.awt.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * El problema de los filósofos (Esta versión causa un Deadlock).
 * @author Jose Alonso Zavala Pulido <jose.zavalapo@udlap.mx>
 * @version 1.0
 */
public class CenaFilosofos {

	// El número de filósofos...
	private static final int NUM_FILOSOFOS = 3;
	
	/**
	 * Una prueba de los filósofos.
	 * @param args Not used
	 */
	public static void main (String[] args) {
		/*
		 *  Cada tenedor es un recurso compartido.
		 *  Los recursos compartidos en Java se definen como tipo Lock.
		 *  
		 */
		ReentrantLock[] tenedores = new ReentrantLock[NUM_FILOSOFOS];
		for (int i = 0; i < NUM_FILOSOFOS; i++) {
			tenedores[i] = new ReentrantLock();
		}
		/*
		 *   
		 *  En la siguiente parte se implementa un código para inicializar un Thread con varios filósofos,
		 *  y se crea el arreglo de filosofos con su respectivo inicio de hilo
		 */
		Filosofo [] filosofo = new Filosofo[NUM_FILOSOFOS];
		for (int i = 0; i < NUM_FILOSOFOS; i++) {
			filosofo[i] = new Filosofo(i, tenedores[i], tenedores[(i+1)%NUM_FILOSOFOS]);
			new Thread(filosofo[i]).start();
		}
	}
}