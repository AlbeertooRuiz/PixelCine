package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Datos.BD;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JPasswordField;
import javax.swing.SpinnerNumberModel;

public class VentanaRegistro extends JFrame {

	private JPanel contentPane;
	private JLabel lblDNI;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textDNI;
	private JTextField textEmail;
	private JFrame ventanaAnterior, ventanaActual;
	private JTextField textUsuario;
	private JTextField textContraseña;
	/**
	 * Create the frame.
	 */
	public VentanaRegistro(JFrame va) {
		ventanaActual = this;
		ventanaAnterior = va;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanaActual.setBounds(100, 100, 514, 373);
		ventanaActual.setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitlulo = new JPanel();
		contentPane.add(panelTitlulo, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("Registro");
		lblTitulo.setFont(new Font("Serif", Font.BOLD, 23));
		panelTitlulo.add(lblTitulo);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaAnterior.setVisible(true);
				ventanaActual.dispose();
			}
		});
		panelSur.add(btnVolver);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new MigLayout("", "[][][204.00][67.00,left][80.00,grow]", "[][][][][][][][][]"));
		
		JLabel lblNombre = new JLabel("Nombre: ");
		panelCentro.add(lblNombre, "cell 1 1,alignx trailing");
		
		textNombre = new JTextField();
		panelCentro.add(textNombre, "cell 2 1,growx");
		textNombre.setColumns(10);
		
		JLabel lblEdad = new JLabel("Edad :");
		panelCentro.add(lblEdad, "cell 3 1,alignx trailing");
		
		JLabel lblApellidos = new JLabel("Apellidos : ");
		panelCentro.add(lblApellidos, "cell 1 3,alignx trailing");
		
		textApellidos = new JTextField();
		panelCentro.add(textApellidos, "cell 2 3,growx");
		textApellidos.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario :");
		panelCentro.add(lblUsuario, "cell 3 3,alignx trailing");
		
		textUsuario = new JTextField();
		panelCentro.add(textUsuario, "cell 4 3,growx");
		textUsuario.setColumns(10);
		
		lblDNI = new JLabel("DNI : ");
		panelCentro.add(lblDNI, "flowy,cell 1 5,alignx trailing");
		
		JSpinner spinnerEdad = new JSpinner();
		spinnerEdad.setModel(new SpinnerNumberModel(0, 0, 130, 1));
		panelCentro.add(spinnerEdad, "cell 4 1,alignx center");
		
		textDNI = new JTextField();
		panelCentro.add(textDNI, "cell 2 5,growx");
		textDNI.setColumns(10);
		
		JLabel lblContraseña = new JLabel("Contraseña :");
		panelCentro.add(lblContraseña, "cell 3 5,alignx trailing");
		
		textContraseña = new JTextField();
		panelCentro.add(textContraseña, "cell 4 5,growx");
		textContraseña.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email :");
		panelCentro.add(lblEmail, "cell 1 7,alignx trailing");
		
		textEmail = new JTextField();
		panelCentro.add(textEmail, "cell 2 7,growx");
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String erDNI = "[0-9]{8}";
				String dni = textDNI.getText();
				String erNombre = "[A-Za-z]{1,}";
				String nombre = textNombre.getText();
				String erApellidos = "[A-Za-z]{1,}";
				String apellidos = textUsuario.getText();
				int edad = (int) spinnerEdad.getValue();
				String erEmail = "[A-Za-z0-9]{1,}";
				String email = textApellidos.getText();
				String erUsuario = "[A-Za-z0-9]{1,}";
				String usuario = textContraseña.getText();
				String erContrasenia = "[A-Za-z0-9]{1,}";
				String contrasenia = textEmail.getText();
				if(edad != 0 || edad < 0) {
					if(Pattern.matches(erDNI, dni)) {
						if(Pattern.matches(erNombre, nombre)) {
							if(Pattern.matches(erApellidos, apellidos)) {
								if(Pattern.matches(erEmail, email)) {
									if(Pattern.matches(erUsuario, usuario)) {
										if(Pattern.matches(erContrasenia, contrasenia)) {
											//Nos conectamos con la base de datos
											Connection con = BD.initBD("pixelcine.db");
											BD.insertarCliente(con, dni, nombre, apellidos, edad, email, usuario, contrasenia);
											BD.closeBD(con);
											JOptionPane.showMessageDialog(null, "Te has registrado correctamente!!!");
											VentanaLogin vl= new VentanaLogin();
											vl.setVisible(true);
											dispose();
										} else {
											JOptionPane.showMessageDialog(null, "Los datos no cumplen los requisitos(Contraseña - Letras y numeros)", "ERROR", JOptionPane.ERROR_MESSAGE);
										}						
									} else {
										JOptionPane.showMessageDialog(null, "Los datos no cumplen los requisitos(Usuario - Letras y numeros)", "ERROR", JOptionPane.ERROR_MESSAGE);		
									}
								} else {
									JOptionPane.showMessageDialog(null, "Los datos no cumplen los requisitos(Email - Letras y numeros)", "ERROR", JOptionPane.ERROR_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "Los datos no cumplen los requisitos(Apellidos - Solo letras)", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Los datos no cumplen los requisitos(Nombre - Solo letras)", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Los datos no cumplen los requisitos(DNI - 8 digitos sin la letra)", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Los datos no cumplen los requisitos(Edad)", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
				/*
				// Mete el user a la bd 
				if (txtNombre.getText().equals("") || txtEmail.getText().equals("")
						|| txtContrasenya.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Hay que rellenar todos los campos", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
					//metodo BuscarUsuario en la clase BD, hay que modificarla a nuestro Modelo
						int resultado = BD.buscarUsuario(txtNombre.getText(), txtContrasenya.getText());
						if (resultado != 0) {
							JOptionPane.showMessageDialog(null, "Ya hay un usuario con este nombre registrado", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						} else {
						//Metdodo RegistrarUsuario anyade el usuario a la Bd eso si hay qye modificarla a nuestro modelo
						 * 
							BD.registrarUsuario(txtNombre.getText(), txtEmail.getText(), txtContrasenya.getText(),txtDNI.getText(),);
							JOptionPane.showMessageDialog(null, "Registro realizado con Exito", "REGISTRO",
									JOptionPane.INFORMATION_MESSAGE);
							ventana.setVisible(false);
							VentanaLogin vl = new VentanaLogin();
							vl.setVisible(true);
						}
					} catch (NumberFormatException e1) {

					}
				}
				 */
			}
		});
		panelSur.add(btnRegistrarse);
	}

}
