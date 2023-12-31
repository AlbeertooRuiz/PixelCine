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
					String n = rs.getString("nombre");
					String ap = rs.getString("apellidos");
					String u = rs.getString("usuario");
					String contr = rs.getString("contrasenia");
					cliente = new Cliente(n, ap, u, contr);
					
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
			return cliente;
		}

		public static void connect() {
			// TODO Auto-generated method stub
			
		}
		
		public static boolean crearTablaReservas(Connection con) {
			String sql = "CREATE TABLE IF NOT EXISTS Reservas (usuario String, pelicula String, fechayhora String, asiento int)";
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
		
		public static boolean insertarReserva(Connection con, String usuario, String pelicula, String fechayhora, int asiento) {
			String sql = "INSERT INTO Reservas VALUES('" + usuario + "','" + pelicula + "','" + fechayhora + "','" + asiento + "')";
			try {
				Statement st = con.createStatement();
				st.executeUpdate(sql);
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return false;
			}
		}
		
		public static boolean existeReserva(String nom, String fecha) {
			boolean resul = false;
			String sql = "SELECT * FROM Reservas WHERE hotel='" + nom + "' AND fecha='" + fecha + "'";
			Connection con = initBD("pixelcine.db");
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				if (rs.next()) {
					resul = true;
				}
				rs.close();
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
			closeBD(con);
			return resul;
		}

		
}
