package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {
		public static Connection initBD(String nombreBD) {
			Connection con = null;
			try {
				Class.forName("org.sqlite.JDBC");
				con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
			} catch (Exception e) {
				// TODO Auto-generated catch block
			} 

			return con;
		}

		public static void closeBD(Connection con) {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
				}
			}
		}

		public static boolean crearTablaCliente(Connection con) {
			String sql = "CREATE TABLE IF NOT EXISTS Cliente (DNI String, nombre String, apellidos String, usuario String, contrasenia String)";
			try {
				Statement st = con.createStatement();
				st.executeUpdate(sql);
				st.close();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return false;
			}
		}

		
		public static boolean insertarCliente(Connection con, String DNI, String nombre, String apellidos, String usuario, String contrasenia) {
			String sql = "INSERT INTO Cliente VALUES('" + DNI + "','" + nombre + "','" + apellidos + "','" + usuario + "','" + contrasenia + "')";
			try {
				Statement st = con.createStatement();
				st.executeUpdate(sql);
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return false;
			}
		}
}
