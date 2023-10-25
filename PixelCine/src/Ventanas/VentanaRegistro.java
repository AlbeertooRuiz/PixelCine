package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
	private JTextField textEmail;
	private JTextField textDNI;
	private JPasswordField passwordField;
	private JFrame ventanaAnterior, ventanaActual;
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
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		panelCentro.setLayout(new MigLayout("", "[][][204.00][67.00,left][80.00]", "[][][][][][][][][]"));
		
		JLabel lblNombre = new JLabel("Nombre: ");
		panelCentro.add(lblNombre, "cell 1 1,alignx trailing");
		
		textNombre = new JTextField();
		panelCentro.add(textNombre, "cell 2 1,growx");
		textNombre.setColumns(10);
		
		JLabel lblEdad = new JLabel("Edad :");
		panelCentro.add(lblEdad, "cell 3 1,alignx trailing");
		
		JLabel lblMail = new JLabel("Email : ");
		panelCentro.add(lblMail, "cell 1 3,alignx trailing");
		
		textEmail = new JTextField();
		panelCentro.add(textEmail, "cell 2 3,growx");
		textEmail.setColumns(10);
		
		lblDNI = new JLabel("DNI : ");
		panelCentro.add(lblDNI, "flowy,cell 1 5,alignx trailing");
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 130, 1));
		panelCentro.add(spinner, "cell 4 1,alignx center");
		
		textDNI = new JTextField();
		panelCentro.add(textDNI, "cell 2 5,growx");
		textDNI.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Contrase\u00F1a :");
		panelCentro.add(lblNewLabel, "cell 1 7,alignx trailing");
		
		passwordField = new JPasswordField();
		panelCentro.add(passwordField, "cell 2 7,growx");
	}

}
