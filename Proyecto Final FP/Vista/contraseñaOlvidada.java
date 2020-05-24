package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.conexion;
import Modelo.hash;
import Modelo.recuperarPass;

import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

/**
 * Esta vista se usa para restablecer la contrase�a
 * 
 * @author Carlos Garc�a Acevedo
 *
 */

public class contrase�aOlvidada extends JFrame {

	private JPanel contentPane;

	/**
	 * Constructor del frame para restablecer la clave
	 */
	public contrase�aOlvidada() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(contrase�aOlvidada.class.getResource("/imagenes/olvidarPass.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JTextPane txtpnCorreo = new JTextPane();
		txtpnCorreo.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnCorreo.setEditable(false);
		txtpnCorreo.setBackground(UIManager.getColor("Button.background"));
		txtpnCorreo.setText("Correo:");
		txtpnCorreo.setBounds(42, 105, 53, 20);
		panel.add(txtpnCorreo);

		JTextPane Correo = new JTextPane();
		Correo.setBounds(105, 105, 217, 20);
		panel.add(Correo);

		JTextPane txtpnRecuperarContrasea = new JTextPane();
		txtpnRecuperarContrasea.setFont(new Font("Tw Cen MT", Font.BOLD, 22));
		txtpnRecuperarContrasea.setEditable(false);
		txtpnRecuperarContrasea.setForeground(SystemColor.desktop);
		txtpnRecuperarContrasea.setBackground(UIManager.getColor("Button.background"));
		txtpnRecuperarContrasea.setText("Recuperar Contrase\u00F1a");
		txtpnRecuperarContrasea.setBounds(105, 38, 217, 46);
		panel.add(txtpnRecuperarContrasea);

		/**
		 * Bot�n que sirve para restablecer la clave
		 */
		JButton btnRecuperarContrasea = new JButton("Recuperar contrase\u00F1a");
		btnRecuperarContrasea.setIcon(new ImageIcon(contrase�aOlvidada.class.getResource("/imagenes/olvidarPass.png")));
		btnRecuperarContrasea.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRecuperarContrasea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nuevaCon = null;
				try {
					nuevaCon = recuperarPass.RecuperarCon(Correo.getText());

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (nuevaCon.equals("0")) {
					JOptionPane.showMessageDialog(null, "Correo incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE);
				} else {
					// Cifra la clave
					String nuevoPass = hash.sha1(nuevaCon);
					// Imprime por consola la clave
					System.out.println(nuevaCon);
					// Imprime por JOptionPane la clave
					JOptionPane.showMessageDialog(null, "Tu nueva contrase�a es " + nuevaCon, " Contrase�a",
							JOptionPane.INFORMATION_MESSAGE);

					PreparedStatement ps = null;
					conexion conn = new conexion();
					Connection con = conn.getConexion();
					/**
					 * Ejecutamos la sql para insertar la nueva clave en la Base de datos
					 */
					String sql = "UPDATE usuarios SET password='" + nuevoPass + "' where correo='" + Correo.getText()
							+ "'";
					try {
						ps = con.prepareStatement(sql);
						int rs = ps.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnRecuperarContrasea.setBounds(121, 163, 185, 23);
		panel.add(btnRecuperarContrasea);
	}
}