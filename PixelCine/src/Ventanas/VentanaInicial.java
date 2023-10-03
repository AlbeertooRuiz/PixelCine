package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class VentanaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JTextField textFieldContrasenia;
	private JFrame ventanaActual;

	
	public VentanaInicial() {
		ventanaActual = this;
		ventanaActual.setSize(400, 400);
		ventanaActual.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblBienvenido = new JLabel("¡Bienvenido a PixelCine!");
		panelNorte.add(lblBienvenido);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panelArriba = new JPanel();
		panelSur.add(panelArriba);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesión");
		panelArriba.add(btnIniciarSesion);
		
		JPanel panelAbajo = new JPanel();
		panelSur.add(panelAbajo);
		
		JLabel lblRegistro = new JLabel("¿No tienes cuenta?");
		panelAbajo.add(lblRegistro);
		
		JButton btnRegistro = new JButton("Registrarme");
		panelAbajo.add(btnRegistro);
		
		JPanel panelOeste = new JPanel();
		contentPane.add(panelOeste, BorderLayout.WEST);
		
		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblUsuario = new JLabel("Usuario:");
		panelCentro.add(lblUsuario, BorderLayout.NORTH);
		
		textFieldUsuario = new JTextField();
		panelCentro.add(textFieldUsuario, BorderLayout.WEST);
		textFieldUsuario.setColumns(10);
		
		JLabel lblContrasenia = new JLabel("Contraseña:");
		panelCentro.add(lblContrasenia, BorderLayout.SOUTH);
		
		textFieldContrasenia = new JTextField();
		panelCentro.add(textFieldContrasenia, BorderLayout.EAST);
		textFieldContrasenia.setColumns(10);
	}

}
