/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabri
 */
public class conexion {
	
	static String timeZone = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static String dbName = "empleados";
	static String url = "jdbc:mysql://localhost:3307/" + dbName + timeZone;
	static String user = "root";
	static String password = "manolo";
	static String driver = "com.mysql.cj.jdbc.Driver";
    
    static Connection conexion;
    static Statement consulta;
    static ResultSet resultado;
    
    public static void Conectar(){
    	try {
           Class.forName(driver) ;
           conexion = DriverManager.getConnection(url, user, password);
           System.out.println("Base de datos situada en :\n "+url);
           consulta = conexion.createStatement();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static ResultSet EjecutarSentencia(String Sentencia){
        try {
        	resultado = consulta.executeQuery(Sentencia);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return resultado;
    }
      
    public static void EjecutarUpdate(String Sentencia){
    	try{
    		consulta.executeUpdate(Sentencia);
    		System.out.println("Done.");
    	}catch(SQLException e){
    		JOptionPane.showMessageDialog(null, e.getMessage());
    	} 
    }
    
    public static Connection getConexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conexion;
	}

    
    public static void CerrarConexion(){
        try{
        	consulta.close();
        }catch(Exception e){}
    }
    
}