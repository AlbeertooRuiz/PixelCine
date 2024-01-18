package Ventanas;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;

import Datos.BD;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaModificarUsuariosAdmin extends JFrame{
	
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldDni;
	private JTextField textFieldEdad;
	private JTextField textFieldEmail;
	private JTextField textFieldNombreUsuario;
	private JTextField textField;
	private JFrame ventanaActual = new JFrame();
	
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
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.WEST);

		
		DefaultListModel<String> listModel = new DefaultListModel<>();
		BD.cargarUsuariosDesdeBaseDeDatos(listModel);
		
		JList<String> list = new JList<>(listModel);
		list.setPreferredSize(new Dimension(200, list.getPreferredSize().height));
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
	 private void volverAVentanaAdmin() {
	        dispose();  
	        VentanaAdministrador ventanaAdmin = new VentanaAdministrador();
	        ventanaAdmin.setVisible(true);
	    }

}
