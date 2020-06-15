package main;

import java.awt.EventQueue;

import bbdd.conexion;
import Vista.login;

public class Main {
	public static void main(String[] args) {
		/* Conexión con la BBDD */
		conexion.Conectar();
		
		/* LLamada a la vista login */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}