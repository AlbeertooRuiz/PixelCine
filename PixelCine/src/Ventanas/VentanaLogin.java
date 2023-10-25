package Ventanas;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Datos.BD;
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

public class VentanaLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textUsuario;
    private JTextField textContrasenia;
    private JFrame ventanaActual;
    Connection con;

    public VentanaLogin() {
        ventanaActual = this;
        ventanaActual.setSize(400, 400);
        ventanaActual.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10)); 

        con = BD.initBD("pixelcine.db");
        BD.crearTablaCliente(con);
        
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panelNorte = new JPanel();
        contentPane.add(panelNorte, BorderLayout.NORTH);

        JLabel lblBienvenido = new JLabel("¡Bienvenido a PixelCine!");
        lblBienvenido.setFont(new Font("Arial", Font.BOLD, 20)); 
        panelNorte.add(lblBienvenido);

        JPanel panelSur = new JPanel();
        contentPane.add(panelSur, BorderLayout.SOUTH);
        panelSur.setLayout(new GridLayout(2, 1, 0, 10)); 

        JPanel panelArriba = new JPanel();
        panelSur.add(panelArriba);

        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String u = textUsuario.getText();
				String c = textContrasenia.getText();
				Cliente cliente = BD.obtenerDatosCliente(con, u);
				if(u.equals("admin") && c.equals("pixelcine")) {
					BD.closeBD(con);
//					VentanaAdministrador va=new VentanaAdministrador() ;
//					va.setVisible(true);
					dispose();
				}else if(cliente == null) {
					JOptionPane.showMessageDialog(null, "El nombre de usuario no es correcto");
				}else if(!cliente.getContrasenia().equals(c)) {
					JOptionPane.showMessageDialog(null, "La contraseï¿½a no es correcta");
				}else {
					BD.closeBD(con);
					JOptionPane.showMessageDialog(null, "Bienvenido/a!!");
//					VentanaUsuario vu= new VentanaUsuario(ventanaActual);
//					vu.setVisible(true);
//					ventanaActual.dispose();
				}
			}
		});
        panelArriba.add(btnIniciarSesion);

        JPanel panelAbajo = new JPanel();
        panelSur.add(panelAbajo);

        JLabel lblRegistro = new JLabel("¿No tienes cuenta?");
        panelAbajo.add(lblRegistro);

        JButton btnRegistro = new JButton("Registrarme");
        btnRegistro.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JFrame ventanaRegistro = new VentanaRegistro(ventanaActual);
        		ventanaActual.setVisible(false);
        		ventanaRegistro.setVisible(true);        	}
        });
        panelAbajo.add(btnRegistro);

        JPanel panelOeste = new JPanel();
        contentPane.add(panelOeste, BorderLayout.WEST);

        JPanel panelEste = new JPanel();
        contentPane.add(panelEste, BorderLayout.EAST);

        JPanel panelCentro = new JPanel();
        contentPane.add(panelCentro, BorderLayout.CENTER);
        panelCentro.setLayout(new GridLayout(2, 2, 10, 10));

        JLabel lblUsuario = new JLabel("Usuario:");
        panelCentro.add(lblUsuario);
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 16));

        textUsuario = new JTextField();
        panelCentro.add(textUsuario);
        textUsuario.setColumns(10);

        JLabel lblContrasenia = new JLabel("Contraseña:");
        panelCentro.add(lblContrasenia);
        lblContrasenia.setFont(new Font("Arial", Font.PLAIN, 16)); 

        textContrasenia = new JTextField();
        panelCentro.add(textContrasenia);
        textContrasenia.setColumns(10);
    }
}
