package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import domain.Categoria;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class VentanaAnyadirPeliculasAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDuracion;
	private JComboBox<Categoria> comboBoxCategoria;
	private JTextField txtAsientosDisponibles;
	private JTextField txtActorPrincipal;
	private JTextField txtFechaHora;
	private VentanaGestionPeliculasAdmin ventanaGestion;
	private static final Logger logger = Logger.getLogger(VentanaLogin.class.getName());

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAnyadirPeliculasAdmin frame = new VentanaAnyadirPeliculasAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaAnyadirPeliculasAdmin() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnVolver);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(7, 2, 10, 10));

		JLabel lblNombre = new JLabel("Nombre:");
		panel_1.add(lblNombre);

		txtNombre = new JTextField();
		panel_1.add(txtNombre);

		JLabel lblDuracion = new JLabel("Duración:");
		panel_1.add(lblDuracion);

		txtDuracion = new JTextField();
		panel_1.add(txtDuracion);

		JLabel lblCategoria = new JLabel("Categoría:");
		panel_1.add(lblCategoria);

		comboBoxCategoria = new JComboBox<>(Categoria.values());
		panel_1.add(comboBoxCategoria);

		JLabel lblAsientosDisponibles = new JLabel("Asientos Disponibles:");
		panel_1.add(lblAsientosDisponibles);

		txtAsientosDisponibles = new JTextField();
		panel_1.add(txtAsientosDisponibles);

		JLabel lblActorPrincipal = new JLabel("Actor Principal:");
		panel_1.add(lblActorPrincipal);

		txtActorPrincipal = new JTextField();
		panel_1.add(txtActorPrincipal);

		JLabel lblFechaHora = new JLabel("Fecha y Hora:");
		panel_1.add(lblFechaHora);

		txtFechaHora = new JTextField();
		panel_1.add(txtFechaHora);

		JButton btnAnyadir = new JButton("Añadir Película");
		btnAnyadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anadirPelicula();
			}
		});
		panel.add(btnAnyadir);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);

		JLabel lblTitulo = new JLabel("Añadir Películas");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 27));
		panel_2.add(lblTitulo);
		setLocationRelativeTo(null);
	}

	private void anadirPelicula() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("resources/data/Peliculas.csv", true))) {
			String nombre = txtNombre.getText();
			int duracion = Integer.parseInt(txtDuracion.getText());
			Categoria categoria = (Categoria) comboBoxCategoria.getSelectedItem();
			int asientosDisponibles = Integer.parseInt(txtAsientosDisponibles.getText());
			String actorPrincipal = txtActorPrincipal.getText();
			String fechaHora = txtFechaHora.getText();

			String nuevaPelicula = String.format("\n%s,%d,%s,%d,%s,%s", nombre, duracion, categoria,
					asientosDisponibles, actorPrincipal, fechaHora);

			bw.write(nuevaPelicula);

			bw.flush();

			limpiarCampos();
			dispose();

			JOptionPane.showMessageDialog(this, "Película añadida correctamente.", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
			logger.info("Pelicula añadida correctamente");
		} catch (IOException | NumberFormatException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al añadir la película.", "Error", JOptionPane.ERROR_MESSAGE);
			logger.warning("Error al añadir la película.");
		}
	}

	private void limpiarCampos() {
		txtNombre.setText("");
		txtDuracion.setText("");
		comboBoxCategoria.setSelectedIndex(0);
		txtAsientosDisponibles.setText("");
		txtActorPrincipal.setText("");
		txtFechaHora.setText("");
	}
}