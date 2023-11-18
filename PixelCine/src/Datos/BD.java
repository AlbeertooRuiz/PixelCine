package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
			String sql = "CREATE TABLE IF NOT EXISTS Cliente (DNI String, nombre String, apellidos String, edad int, email String, usuario String, contrasenia String)";
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

		
		public static boolean insertarCliente(Connection con, String DNI, String nombre, String apellidos, int edad, String email, String usuario, String contrasenia) {
			String sql = "INSERT INTO Cliente VALUES('" + DNI + "','" + nombre + "','" + apellidos + "','" + edad + "','" + email + "','" + usuario + "','" + contrasenia + "')";
			try {
				Statement st = con.createStatement();
				st.executeUpdate(sql);
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return false;
			}
		}
		
		public static Cliente obtenerDatosCliente(Connection con, String usuario) {
			String sql = "SELECT * FROM Cliente WHERE usuario='" + usuario + "'";
			Cliente cliente = null;
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				if (rs.next()) {
					String u = rs.getString("usuario");
					String contr = rs.getString("contrasenia");
					cliente = new Cliente(u, contr);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
			return cliente;
		}

		public static void connect() {
			// TODO Auto-generated method stub
			
		}
		
}
