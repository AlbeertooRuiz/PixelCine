package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAdministrador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public VentanaAdministrador() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblTitulo = new JLabel("Ventana Administrador");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 29));
		panel.add(lblTitulo);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JButton btnSalir = new JButton("Salir");
		panel_1.add(btnSalir);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new MigLayout("", "[][][45.00][187.00,center][grow]", "[][][][][][][][][]"));
		
		JButton btnUser = new JButton("Modificar Usuario");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			// Ventana cambiar nombre/contraseña/email del usuario
				
			}
		});
		panel_2.add(btnUser, "cell 3 1");
		
		JButton btnPeliculas = new JButton("Gestion Peliculas");
		panel_2.add(btnPeliculas, "cell 3 3");
		// Ventana añadir/quitar peliculas a la bd

	}

}
