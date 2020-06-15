package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bbdd.conexion;
import Modelo.empleado;

	/**
	 * Esta clase realiza consultas a la BBDD
	 * 
	 * @author Carlos García Acevedo
	 *
	 */

	public class sqlEmpleados extends conexion {

		/**
		 * Inserta datos en la BBDD
		 * 
		 * @param usr
		 * @return boolean
		 */

		public boolean registrar(empleado emp) {

			/**
			 * Creamos el insert sql para poder registrar a los empleados
			 */
			PreparedStatement ps = null;
			Connection con = getConexion();

			String sql = "INSERT INTO empleado(cod_empleado,nombre,apellido,puesto, sueldo, horas) VALUES(?,?,?,?,?,?)";

			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, emp.getCod_empleado());
				ps.setString(2, emp.getNombre());
				ps.setString(3, emp.getApellido());
				ps.setString(4, emp.getPuesto());
				ps.setDouble(5, emp.getSueldo());
				ps.setInt(6,emp.getHoras());
				ps.execute();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
}
