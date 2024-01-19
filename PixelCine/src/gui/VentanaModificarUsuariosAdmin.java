package gui;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import db.BD;
import domain.Cliente;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class VentanaModificarUsuariosAdmin extends JFrame {

	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldDni;
	private JTextField textFieldEdad;
	private JTextField textFieldEmail;
	private JTextField textFieldNombreUsuario;
	private JTextField textField;
	private JFrame ventanaActual = new JFrame();
	private JList<String> list;
	private static final Logger logger = Logger.getLogger(VentanaDetalle.class.getName());

	public VentanaModificarUsuariosAdmin() {

		setResizable(false);
		ventanaActual = this;
		ventanaActual.setSize(625, 325);
		ventanaActual.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblTitulo = new JLabel("Modificar Usuario");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 27));
		panel.add(lblTitulo);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);

		JButton btnVolver = new JButton("volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volverAVentanaAdmin();
			}
		});
		panel_1.add(btnVolver);

		JButton btnGuardarCambios = new JButton("Guardar Cambios");
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarCambios();
			}
		});
		panel_1.add(btnGuardarCambios);

		JButton btnEliminarUsuario = new JButton("Eliminar Usuario");
		btnEliminarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarUsuario();
			}
		});
		panel_1.add(btnEliminarUsuario);

		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.WEST);

		DefaultListModel<String> listModel = new DefaultListModel<>();
		BD.cargarUsuariosDesdeBaseDeDatos(listModel);

		list = new JList<>(listModel);
		list.setPreferredSize(new Dimension(200, list.getPreferredSize().height));
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					String nombreUsuarioSeleccionado = list.getSelectedValue();
					Cliente cliente = BD.obtenerDatosClientePorNombreUsuario(nombreUsuarioSeleccionado);
					llenarDatosEnTextFields(cliente);
				}
			}
		});

		panel_2.add(list);

		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new MigLayout("", "[60.00][][][17.00][left][grow,right]", "[][][][][][][]"));

		JLabel lblDNI = new JLabel("DNI:");
		panel_3.add(lblDNI, "cell 4 0,alignx trailing");

		textFieldDni = new JTextField();
		panel_3.add(textFieldDni, "cell 5 0,growx");
		textFieldDni.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");
		panel_3.add(lblNombre, "cell 4 1,alignx trailing");

		textFieldNombre = new JTextField();
		panel_3.add(textFieldNombre, "cell 5 1,growx");
		textFieldNombre.setColumns(10);

		JLabel lblApellido = new JLabel("Apellido:");
		panel_3.add(lblApellido, "cell 4 2,alignx trailing");

		textFieldApellido = new JTextField();
		panel_3.add(textFieldApellido, "cell 5 2,growx");
		textFieldApellido.setColumns(10);

		JLabel lblEdad = new JLabel("Edad:");
		panel_3.add(lblEdad, "cell 4 3,alignx trailing");

		textFieldEdad = new JTextField();
		panel_3.add(textFieldEdad, "cell 5 3,growx");
		textFieldEdad.setColumns(10);

		JLabel lblEmail = new JLabel("Email:");
		panel_3.add(lblEmail, "cell 4 4,alignx trailing");

		textFieldEmail = new JTextField();
		panel_3.add(textFieldEmail, "cell 5 4,growx");
		textFieldEmail.setColumns(10);

		JLabel lblNombreUsuario = new JLabel("Nombre Usuario:");
		panel_3.add(lblNombreUsuario, "cell 4 5,alignx trailing");

		textFieldNombreUsuario = new JTextField();
		panel_3.add(textFieldNombreUsuario, "cell 5 5,growx");
		textFieldNombreUsuario.setColumns(10);

		JLabel lblContrasenya = new JLabel("Contrasenya:");
		panel_3.add(lblContrasenya, "cell 4 6,alignx trailing");

		textField = new JTextField();
		panel_3.add(textField, "cell 5 6,growx");
		textField.setColumns(10);
	}

	private void llenarDatosEnTextFields(Cliente cliente) {
		if (cliente != null) {
			textFieldDni.setText(cliente.getDNI());
			textFieldNombre.setText(cliente.getNombre());
			textFieldApellido.setText(cliente.getApellidos());
			textFieldEdad.setText(String.valueOf(cliente.getEdad()));
			textFieldEmail.setText(cliente.getEmail());
			textFieldNombreUsuario.setText(cliente.getUsuario());
			textField.setText(cliente.getContrasenia());
		}
	}

	private void limpiarCampos() {
		textFieldDni.setText("");
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldEdad.setText("");
		textFieldEmail.setText("");
		textFieldNombreUsuario.setText("");
		textField.setText("");
	}

	private void volverAVentanaAdmin() {
		dispose();
		VentanaAdministrador ventanaAdmin = new VentanaAdministrador();
		ventanaAdmin.setVisible(true);
	}

	private void guardarCambios() {
		String dni = textFieldDni.getText();
		String nombre = textFieldNombre.getText();
		String apellidos = textFieldApellido.getText();
		int edad = Integer.parseInt(textFieldEdad.getText());
		String email = textFieldEmail.getText();
		String nombreUsuario = textFieldNombreUsuario.getText();
		String contrasenia = textField.getText();
		Cliente clienteModificado = new Cliente(dni, nombre, apellidos, edad, email, nombreUsuario, contrasenia);

		BD.actualizarDatosCliente(clienteModificado);

		DefaultListModel<String> newListModel = new DefaultListModel<>();
		BD.cargarUsuariosDesdeBaseDeDatos(newListModel);
		list.setModel(newListModel);
		logger.info("Datos Cambiados Correctamente");
	}

	private void eliminarUsuario() {
		String nombreUsuarioSeleccionado = list.getSelectedValue();
		if (nombreUsuarioSeleccionado != null) {
			BD.eliminarUsuarioPorNombre(nombreUsuarioSeleccionado);

			limpiarCampos();

			DefaultListModel<String> newListModel = new DefaultListModel<>();
			BD.cargarUsuariosDesdeBaseDeDatos(newListModel);
			list.setModel(newListModel);
			logger.info("Usuario eliminado Correctamente");
		}
	}

}
