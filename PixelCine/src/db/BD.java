package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;

import domain.Cliente;

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

	public static boolean insertarCliente(Connection con, String DNI, String nombre, String apellidos, int edad,
			String email, String usuario, String contrasenia) {
		String sql = "INSERT INTO Cliente VALUES('" + DNI + "','" + nombre + "','" + apellidos + "','" + edad + "','"
				+ email + "','" + usuario + "','" + contrasenia + "')";
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

	public static boolean insertarReserva(Connection con, String usuario, String pelicula, String fechayhora,
			int asiento) {
		String sql = "INSERT INTO Reservas VALUES('" + usuario + "','" + pelicula + "','" + fechayhora + "','" + asiento
				+ "')";
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
		Connection con = initBD("resources/db/pixelcine.db");
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

	public static void cargarUsuariosDesdeBaseDeDatos(DefaultListModel<String> listModel) {
		try {
			Connection con = initBD("resources/db/pixelcine.db");

			String consulta = "SELECT usuario FROM Cliente";
			PreparedStatement statement = con.prepareStatement(consulta);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				String nombreUsuario = rs.getString("usuario");
				listModel.addElement(nombreUsuario);
			}

			rs.close();
			statement.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Cliente obtenerDatosClientePorNombreUsuario(String nombreUsuario) {
		Cliente cliente = null;
		try {
			Connection con = initBD("resources/db/pixelcine.db");
			String consulta = "SELECT * FROM Cliente WHERE usuario=?";
			try (PreparedStatement statement = con.prepareStatement(consulta)) {
				statement.setString(1, nombreUsuario);
				ResultSet rs = statement.executeQuery();

				if (rs.next()) {
					cliente = new Cliente();
					cliente.setDNI(rs.getString("dni"));
					cliente.setNombre(rs.getString("nombre"));
					cliente.setApellidos(rs.getString("apellidos"));
					cliente.setEdad(rs.getInt("edad"));
					cliente.setEmail(rs.getString("email"));
					cliente.setUsuario(rs.getString("usuario"));
					cliente.setContrasenia(rs.getString("contrasenia"));
				}

				rs.close();
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return cliente;
	}

	public static void eliminarUsuarioPorNombre(String nombreUsuario) {
		try {
			Connection con = initBD("resources/db/pixelcine.db");
			String consulta = "DELETE FROM cliente WHERE usuario=?";
			try (PreparedStatement statement = con.prepareStatement(consulta)) {
				statement.setString(1, nombreUsuario);
				statement.executeUpdate();
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void actualizarDatosCliente(Cliente clienteModificado) {
		try {
			Connection con = initBD("resources/db/pixelcine.db");
			String consulta = "UPDATE cliente SET dni=?, nombre=?, apellidos=?, edad=?, email=?, usuario=?, contrasenia=? WHERE usuario=?";

			try (PreparedStatement statement = con.prepareStatement(consulta)) {
				statement.setString(1, clienteModificado.getDNI());
				statement.setString(2, clienteModificado.getNombre());
				statement.setString(3, clienteModificado.getApellidos());
				statement.setInt(4, clienteModificado.getEdad());
				statement.setString(5, clienteModificado.getEmail());
				statement.setString(6, clienteModificado.getUsuario());
				statement.setString(7, clienteModificado.getContrasenia());
				statement.setString(8, clienteModificado.getUsuario()); // WHERE nombre_usuario=?

				statement.executeUpdate();
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
