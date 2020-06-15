package Controlador;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import beans.User;

public class Login {
	
	public void insertUser(String user, String password) {
		new Modelo.Users().insertUser(user,password);
	}
	
	public void updateUser(String user, String password) {
		new Modelo.Users().insertUser(user,password);
	}
	
	public void deleteUser(String user) {
		new Modelo.Users().deleteUser(user);
	}
	
	public void checkUser(String user, String password) {
		// Recoger los usuarios
		boolean check = new Modelo.Users().checkUserAndPass(user, password);
		if(check) {
			JOptionPane.showMessageDialog(null, "Usuario correcto");
		}else { // Sino error
			JOptionPane.showMessageDialog(null, "Usuario no encontrado");
		}
	}
	
	public boolean checkUserOld(String user, String password) {
		// Recoger los usuarios
		ArrayList<User> usuarios = new Modelo.Users().getAllUsers();
		// Comprobar si usuario y contrase�a son correctos
		for(User usuario : usuarios) {
			if(user.equals(usuario.getUser()) && password.equals(usuario.getPassword())) {
				JOptionPane.showMessageDialog(null, "Usuario correcto");
			}else { // Sino error
				JOptionPane.showMessageDialog(null, "Usuario no encontrado");
			}
		}
		return true;
	}
	
}