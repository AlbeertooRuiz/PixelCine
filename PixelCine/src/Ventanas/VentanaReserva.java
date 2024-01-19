package Ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Datos.Asiento;
import Datos.Cliente;
import Datos.Pelicula;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.Buffer;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import BaseDeDatos.BD;

public class VentanaReserva extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame ventanaActual, ventanaAnterior;
	private JFrame ventanaPeliculas;
	String numeroAsientos;
	Connection con;
	private static final Logger logger = Logger.getLogger(VentanaReserva.class.getName());

	public VentanaReserva(JFrame va, Pelicula p, List<Asiento> Asientos, Cliente c, String fecha) {

		logger.setLevel(java.util.logging.Level.INFO);

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

				for (Asiento a : Asientos) {
					int numAsiento = Integer.parseInt(String.valueOf(a.getFila()) + String.valueOf(a.getColumna()));
					Connection con = BD.initBD("pixelcine.db");
					BD.insertarReserva(con, cliente.getUsuario(), pelicula.getNombre(), pelicula.getFechayhora(),
							numAsiento);
					BD.closeBD(con);
				}

				guardarAsientosReservados(Asientos, pelicula, fecha);

				JOptionPane.showMessageDialog(null, "Gracias por su compra!! Esperamos que disfrute!!");
				logger.info("Compra Realizada");
				int resul = JOptionPane.showConfirmDialog(null, "¿Quiere imprimir sus entradas?");
				if (resul == 0) {
					JFileChooser fileChooser = new JFileChooser();

					FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt");
					fileChooser.setFileFilter(filter);

					int result = fileChooser.showSaveDialog(null);

					if (result == JFileChooser.APPROVE_OPTION) {
						String filePath = fileChooser.getSelectedFile().getAbsolutePath();
						if (!filePath.toLowerCase().endsWith(".txt")) {
							filePath += ".txt";
						}

						try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
							writer.println("Pelicula: " + pelicula.getNombre());
							writer.println("Dia y hora: " + pelicula.getFechayhora());
							String as = "";
							for (Asiento a : Asientos) {
								as = as + (String.valueOf(a.getFila()) + String.valueOf(a.getColumna()) + ",");
							}
							as = as.substring(0, as.length() - 1);
							writer.println("Asientos: " + as);
						} catch (Exception ex) {

						}

					} else if (result == JFileChooser.CANCEL_OPTION) {

					}
				} else {

				}
				JOptionPane.showMessageDialog(null, "¡Sus entradas se han guardado correctamente!");
				logger.info("Entradas Guardadas Correctamente");
				int resul2 = JOptionPane.showConfirmDialog(null, "¿Quiere comprar mas entradas?");
				if (resul2 == 0) {
					ventanaActual.dispose();
					ventanaPeliculas = new VentanaPeliculas(cliente, fecha);
					ventanaPeliculas.setVisible(true);
				} else {
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
			as = as + (String.valueOf(a.getFila()) + String.valueOf(a.getColumna()) + ",");
		}
		if (!as.isEmpty()) {
			as = as.substring(0, as.length() - 1);
		}

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

	private void guardarAsientosReservados(List<Asiento> asientos, Pelicula pelicula, String fecha) {

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("asientosReservados.csv", true));

			for (Asiento asiento : asientos) {
				bw.write(pelicula.getFechayhora() + "," + pelicula.getNombre() + "," + Integer.toString(asiento.getFila()) + ","
						+ Integer.toString(asiento.getColumna()));
				bw.newLine();
			}

			bw.close();
		} catch (IOException e1) {
			System.out.println("Falla fichero");
		}

	}
}
