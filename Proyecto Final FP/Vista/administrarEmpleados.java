package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Esta vista se usa para administrar a los empleados
 * 
 * @author: Carlos Garc�a Acevedo
 *
 */

public class administrarEmpleados extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodEmpleado;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtPuesto;
	private JTextField txtSueldo;
	private JTextField txtHoras;

	PreparedStatement ps;
	ResultSet rs;

	/**
	 * M�todo que vac�a los campos de texto
	 */
	private void limpiarCajas() {
		txtCodEmpleado.setText(null);
		txtNombre.setText(null);
		txtApellido.setText(null);
		txtPuesto.setText(null);
		txtSueldo.setText(null);
		txtHoras.setText(null);
	}

	/**
	 * Constructor del frame para administrar empleados
	 */
	public administrarEmpleados() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(administrarEmpleados.class.getResource("/imagenes/home.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 570, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("C\u00F3digo Empleado:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(41, 24, 111, 23);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(101, 97, 67, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblApellido.setBounds(92, 128, 60, 14);
		contentPane.add(lblApellido);

		JLabel lblPuesto = new JLabel("Puesto:");
		lblPuesto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPuesto.setBounds(101, 159, 83, 14);
		contentPane.add(lblPuesto);

		JLabel lblNewLabel_2 = new JLabel("Sueldo:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(102, 190, 59, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblHoras = new JLabel("Horas:");
		lblHoras.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHoras.setBounds(101, 224, 93, 14);
		contentPane.add(lblHoras);

		txtCodEmpleado = new JTextField();
		/**
		 * Evento que obliga a que solo podamos meter numeros del 0 al 9
		 */
		txtCodEmpleado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c < '0' || c > '9')
					e.consume();
			}
		});
		txtCodEmpleado.setBounds(162, 25, 86, 20);
		contentPane.add(txtCodEmpleado);
		txtCodEmpleado.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(178, 94, 195, 20);
		contentPane.add(txtNombre);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(178, 125, 195, 20);
		contentPane.add(txtApellido);

		txtPuesto = new JTextField();
		txtPuesto.setColumns(10);
		txtPuesto.setBounds(178, 156, 195, 20);
		contentPane.add(txtPuesto);

		txtSueldo = new JTextField();
		txtSueldo.setColumns(10);
		txtSueldo.setBounds(178, 187, 195, 20);
		contentPane.add(txtSueldo);

		txtHoras = new JTextField();
		txtHoras.setColumns(10);
		txtHoras.setBounds(178, 221, 195, 20);
		contentPane.add(txtHoras);

		/**
		 * Bot�n que sirve para guardar los datos
		 */
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon(administrarEmpleados.class.getResource("/imagenes/guardar.png")));
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;

				try {
					con = conexion.getConexion();
					ps = con.prepareStatement(
							"INSERT INTO empleado(nombre, apellido, puesto, sueldo, horas) VALUES (?,?,?,?,?)");
					ps.setString(1, txtNombre.getText());
					ps.setString(2, txtApellido.getText());
					ps.setString(3, txtPuesto.getText());
					ps.setInt(4, Integer.parseInt(txtSueldo.getText()));
					ps.setInt(5, Integer.parseInt(txtHoras.getText()));

					int res = ps.executeUpdate();

					if (res > 0) {
						JOptionPane.showMessageDialog(null, "Empleado Guardado");
						limpiarCajas();
					} else {
						JOptionPane.showMessageDialog(null, "Error al Guardar Empleado");
						limpiarCajas();
					}
					con.close();
				} catch (Exception err) {
					System.err.println(err);
				}
			}
		});
		btnGuardar.setBounds(29, 287, 111, 23);
		contentPane.add(btnGuardar);
		/**
		 * Bot�n que sirve para modificar los datos
		 */
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(administrarEmpleados.class.getResource("/imagenes/modificar.png")));
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;

				try {
					con = conexion.getConexion();
					ps = con.prepareStatement(
							"UPDATE empleado SET nombre=?, apellido=?, puesto=?, sueldo=?, horas=? WHERE cod_empleado=?");
					ps.setString(1, txtNombre.getText());
					ps.setString(2, txtApellido.getText());
					ps.setString(3, txtPuesto.getText());
					ps.setInt(4, Integer.parseInt(txtSueldo.getText()));
					ps.setInt(5, Integer.parseInt(txtHoras.getText()));
					ps.setInt(6, Integer.parseInt(txtCodEmpleado.getText()));

					int res = ps.executeUpdate();

					if (res > 0) {
						JOptionPane.showMessageDialog(null, "Empleado Modificado");
						limpiarCajas();
					} else {
						JOptionPane.showMessageDialog(null, "Error al Modificar Empleado");
						limpiarCajas();
					}
					con.close();
				} catch (Exception err) {
					System.err.println(err);
				}
			}
		});
		btnModificar.setBounds(150, 287, 114, 23);
		contentPane.add(btnModificar);
		/**
		 * Bot�n que sirve para eliminar los datos
		 */
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(administrarEmpleados.class.getResource("/imagenes/eliminar.png")));
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;

				try {
					con = conexion.getConexion();
					ps = con.prepareStatement("DELETE FROM empleado WHERE cod_empleado=?");
					ps.setInt(1, Integer.parseInt(txtCodEmpleado.getText()));

					int res = ps.executeUpdate();

					if (res > 0) {
						JOptionPane.showMessageDialog(null, "Empleado Eliminado");
						limpiarCajas();
					} else {
						JOptionPane.showMessageDialog(null, "Error al Eliminar Empleado");
						limpiarCajas();
					}
					con.close();
				} catch (Exception err) {
					System.err.println(err);
				}
			}
		});
		btnEliminar.setBounds(274, 287, 123, 23);
		contentPane.add(btnEliminar);
		/**
		 * Bot�n que sirve para eliminar los datos de los campos de texto
		 */
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setIcon(new ImageIcon(administrarEmpleados.class.getResource("/imagenes/limpiar.png")));
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCajas();
			}
		});
		btnLimpiar.setBounds(407, 287, 111, 23);
		contentPane.add(btnLimpiar);
		/**
		 * Bot�n que sirve para buscar los datos por id
		 */
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(administrarEmpleados.class.getResource("/imagenes/Buscar.png")));
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;

				try {
					con = conexion.getConexion();
					ps = con.prepareStatement(
							"SELECT nombre,apellido,puesto,sueldo,horas FROM empleado WHERE cod_empleado=?");
					ps.setString(1, txtCodEmpleado.getText());

					rs = ps.executeQuery();

					if (rs.next()) {
						txtNombre.setText(rs.getString("nombre"));
						txtApellido.setText(rs.getString("apellido"));
						txtPuesto.setText(rs.getString("puesto"));
						txtSueldo.setText(rs.getString("sueldo"));
						txtHoras.setText(rs.getString("horas"));
					} else {
						JOptionPane.showMessageDialog(null,
								"No existe un empleado con ese c�digo, porfavor introduce uno v�lido");
					}
				} catch (Exception err) {
					System.err.println(err);
				}
			}
		});
		btnBuscar.setBounds(258, 24, 105, 23);
		contentPane.add(btnBuscar);

		JLabel label = new JLabel("Introduzca un c\u00F3digo");
		label.setFont(new Font("Tahoma", Font.ITALIC, 10));
		label.setBounds(386, 24, 146, 14);
		contentPane.add(label);

		JLabel lblParaAdministrarEse = new JLabel("para administrar ese empleado.");
		lblParaAdministrarEse.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblParaAdministrarEse.setBounds(386, 33, 146, 14);
		contentPane.add(lblParaAdministrarEse);
	}
}