package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Datos.Asiento;
import Datos.BD;
import Datos.Cliente;
import Datos.Pelicula;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class VentanaReserva extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame ventanaActual, ventanaAnterior;
	private JFrame ventanaPeliculas;
	String numeroAsientos;
	Connection con;
	
	/**
	 * Create the frame.
	 */
	public VentanaReserva(JFrame va, Pelicula p, List<Asiento> Asientos, Cliente c) {
		Pelicula pelicula = p;
		Cliente cliente = c;
		ventanaActual = this;
		ventanaAnterior = va;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		con = BD.initBD("pixelcine.db");
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblInfo = new JLabel("Confirmación de su compra");
		panelNorte.add(lblInfo);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnReservar = new JButton("Reservar");
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for(Asiento a: Asientos) {
					int numAsiento = Integer.parseInt(String.valueOf(a.getFila()) + String.valueOf(a.getColumna()));
					Connection con = BD.initBD("pixelcine.db");
					BD.insertarReserva(con, cliente.getUsuario(), pelicula.getNombre(), pelicula.getFechayhora(), numAsiento);
					BD.closeBD(con);
				}
				
				JOptionPane.showMessageDialog(null, "Gracias por su compra!! Esperamos que disfrute!!");
				int resul = JOptionPane.showConfirmDialog(null, "¿Quiere comprar mas entradas?"); 
				if(resul == 0) {
					ventanaActual.dispose();
					ventanaPeliculas = new VentanaPeliculas(cliente);
					ventanaPeliculas.setVisible(true);
				}else {
					ventanaActual.dispose();
					VentanaLogin vl = new VentanaLogin();
					vl.setVisible(true);
				}
			}
		});
		panelSur.add(btnReservar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				ventanaAnterior.setVisible(true);
			}
		});
		panelSur.add(btnVolver);
		
		JPanel panelOeste = new JPanel();
		contentPane.add(panelOeste, BorderLayout.WEST);
		
		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel Pelicula = new JLabel("Pelicula :");
		panelCentro.add(Pelicula);
		
		JLabel lblPelicula = new JLabel("");
		panelCentro.add(lblPelicula);
		lblPelicula.setText(p.getNombre());
		
		JLabel Asiento = new JLabel("Asiento(s) :");
		panelCentro.add(Asiento);
		
		JLabel lblAsientos = new JLabel("");
		panelCentro.add(lblAsientos);
		String as = "";
		for (Asiento a : Asientos) {
		    
		    as = as +( String.valueOf(a.getFila()) + String.valueOf(a.getColumna()) + ",");
		   
		}
		as = as.substring(0, as.length()-1);
		lblAsientos.setText(as);
		
		JLabel DiaHora = new JLabel("Dia y hora :");
		panelCentro.add(DiaHora);
		
		JLabel lblDiaHora = new JLabel("");
		panelCentro.add(lblDiaHora);
		lblDiaHora.setText(p.getFechayhora());
	}
	
//	public static void main(String[] args) {
//	    SwingUtilities.invokeLater(() -> new VentanaReserva(cliente));
//	}

}
