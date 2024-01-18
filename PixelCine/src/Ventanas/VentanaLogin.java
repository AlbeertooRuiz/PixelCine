package Ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import BaseDeDatos.BD;
import Datos.Cliente;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;

import java.util.logging.Logger;

public class VentanaLogin extends JFrame {

	private static final Logger logger = Logger.getLogger(VentanaLogin.class.getName());
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField textContrasenia;
	private JFrame ventanaActual;
	Connection con;

	public VentanaLogin() {
		setResizable(false);
		ventanaActual = this;
		ventanaActual.setSize(625, 325);
		ventanaActual.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

		con = BD.initBD("pixelcine.db");
		BD.crearTablaCliente(con);
		BD.crearTablaReservas(con);

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);

		JLabel lblBienvenido = new JLabel("¡Bienvenido a PixelCine!");
		lblBienvenido.setFont(new Font("Arial", Font.BOLD, 30));
		panelNorte.add(lblBienvenido);

		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new GridLayout(2, 1, 0, 10));

		JPanel panelArriba = new JPanel();
		panelSur.add(panelArriba);

		JButton btnIniciarSesion = new JButton("Iniciar Sesión");
		panelArriba.add(btnIniciarSesion);
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String u = textUsuario.getText();
				String c = textContrasenia.getText();
				Cliente cliente = BD.obtenerDatosCliente(con, u);
				if (u.equals("admin") && c.equals("pixelcine")) {
					BD.closeBD(con);
					VentanaAdministrador va = new VentanaAdministrador();
					logger.info("Loggin Como admin Completado");
					va.setVisible(true);
					dispose();
				} else if (cliente == null) {
					JOptionPane.showMessageDialog(null, "El nombre de usuario no es correcto");
					logger.warning("Fallo al introducir el Nombre Usuario");
				} else if (!cliente.getContrasenia().equals(c)) {
					JOptionPane.showMessageDialog(null, "La contraseï¿½a no es correcta");
					logger.warning("Fallo al introducir la clave del Usuario");
				} else {
					BD.closeBD(con);
					JOptionPane.showMessageDialog(null, "¡Bienvenido/a!");
					logger.info("Loggin Completado");
					VentanaCalendario cl = new VentanaCalendario(cliente);
					cl.setVisible(true);
					ventanaActual.dispose();
				}
			}
		});

		JPanel panelAbajo = new JPanel();
		panelSur.add(panelAbajo);

		JLabel lblRegistro = new JLabel("¿No tienes cuenta?");
		lblRegistro.setForeground(Color.BLUE);
		panelAbajo.add(lblRegistro);

		JButton btnRegistro = new JButton("Registrarme");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame ventanaRegistro = new VentanaRegistro(ventanaActual);
				ventanaActual.setVisible(false);
				ventanaRegistro.setVisible(true);
			}
		});
		panelAbajo.add(btnRegistro);

		JPanel panelOeste = new JPanel();
		contentPane.add(panelOeste, BorderLayout.WEST);

		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);

		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(
				new MigLayout("", "[169.00px][302.00px,center][55.00,grow]", "[18.00px][15.00px][][][][][][]"));

		JLabel lblUsuario = new JLabel("Usuario:");
		panelCentro.add(lblUsuario, "cell 0 3,alignx trailing,growy");
		lblUsuario.setFont(new Font("Arial", Font.PLAIN, 16));

		textUsuario = new JTextField();
		panelCentro.add(textUsuario, "cell 1 3,grow");
		textUsuario.setColumns(10);

		JLabel lblContrasenia = new JLabel("Contraseña:");
		panelCentro.add(lblContrasenia, "cell 0 5,alignx trailing,growy");
		lblContrasenia.setFont(new Font("Arial", Font.PLAIN, 16));

		textContrasenia = new JPasswordField();
		panelCentro.add(textContrasenia, "cell 1 5,grow");
		textContrasenia.setColumns(10);
	}
}
