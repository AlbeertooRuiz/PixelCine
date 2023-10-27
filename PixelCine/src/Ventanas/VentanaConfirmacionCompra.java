package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Datos.Asiento;
import Datos.Pelicula;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VentanaConfirmacionCompra extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame ventanaActual, ventanaAnterior;
	private JFrame ventanaPeliculas;
	
	/**
	 * Create the frame.
	 */
	public VentanaConfirmacionCompra(JFrame va, Pelicula p, ArrayList<Asiento> Asientos) {
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
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblInfo = new JLabel("Confirmación de su compra");
		panelNorte.add(lblInfo);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Gracias por su compra!! Esperamos que disfrute!!");
				int resul = JOptionPane.showConfirmDialog(null, "¿Quiere comprar mas entradas?"); 
				if(resul == 0) {
					ventanaActual.dispose();
					ventanaPeliculas = new VentanaPeliculas();
					ventanaPeliculas.setVisible(true);
				}else {
					ventanaActual.dispose();
					VentanaLogin vl = new VentanaLogin();
					vl.setVisible(true);
				}
			}
		});
		panelSur.add(btnComprar);
		
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
		panelCentro.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("New label");
		panelCentro.add(lblNewLabel);
	}

}
