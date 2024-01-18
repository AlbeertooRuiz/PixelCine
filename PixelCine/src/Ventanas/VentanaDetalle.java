package Ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Datos.Categoria;
import Datos.Cliente;
import Datos.Pelicula;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.Random;

public class VentanaDetalle extends JFrame {
	private String fecha;
	private static Map<String, List<Pelicula>> peliculasPorFecha = new HashMap<>();
	Cliente cliente;
	private static final Logger logger = Logger.getLogger(VentanaDetalle.class.getName());

	public VentanaDetalle(String fecha, Cliente c) {
		Cliente cliente = c;
		this.fecha = fecha;
		configurarVentana();
		inicializarComponentes(cliente);
	}

	private void configurarVentana() {
		setTitle("Detalles del día");
		setSize(800, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void inicializarComponentes(Cliente c) {
		JLabel labelFecha = new JLabel("Fecha: " + fecha);

		// Datos para la tabla
		String[] columnas = { "Nombre", "Hora Inicio", "Duración", "Categoría", "Asientos Disponibles" };
		DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// Esta generando peliculas aleatorias por fecha

		List<Pelicula> peliculas = new ArrayList<>();

		try {
			peliculasPorFecha = cargarMapa();

			if (peliculasPorFecha.containsKey(fecha)) {
				peliculas = peliculasPorFecha.get(fecha);
			} else {
				peliculas = obtenerOActualizarPeliculas(fecha);
				peliculasPorFecha.put(fecha, peliculas);
				guardarMapa(peliculasPorFecha);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Limitar a 6 películas por día
		int maxPeliculasPorDia = 6;
		int peliculasMostradas = 0;

		Calendar horaInicio = Calendar.getInstance();
		horaInicio.set(Calendar.HOUR_OF_DAY, 12); // Comienza a las 12:00
		horaInicio.set(Calendar.MINUTE, 0);
		horaInicio.set(Calendar.SECOND, 0);

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

		JTable tablaPeliculas = new JTable(modeloTabla);
		tablaPeliculas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // Doble clic para seleccionar la película
					int fila = tablaPeliculas.getSelectedRow();
					String nombrePelicula = modeloTabla.getValueAt(fila, 0).toString();
					String hora = modeloTabla.getValueAt(fila, 1).toString();
					String fechayHora = fecha + " " + hora;
					int duracion = Integer.parseInt(modeloTabla.getValueAt(fila, 2).toString());
					String categoria = modeloTabla.getValueAt(fila, 3).toString();
					int asientosDisponibles = Integer.parseInt(modeloTabla.getValueAt(fila, 4).toString());

					// Crear una instancia de Pelicula y Cliente para pasar a VentanaAsientos
					Pelicula peliculaSeleccionada = new Pelicula(nombrePelicula, duracion, Categoria.valueOf(categoria),
							asientosDisponibles, fechayHora);

					try {
						new VentanaAsientos(VentanaDetalle.this, peliculaSeleccionada, c, fecha).setVisible(true);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					VentanaDetalle.this.dispose();
				}
			}
		});

		for (Pelicula pelicula : peliculas) {
			if (peliculasMostradas >= maxPeliculasPorDia) {
				break; // Detener si se ha alcanzado el máximo de películas para el día
			}

			// Calcular la hora de finalización de la película actual
			Calendar horaFin = (Calendar) horaInicio.clone();
			horaFin.add(Calendar.MINUTE, pelicula.getDuracion());

			// Ajustar la hora de inicio de la próxima película
			ajustarProximaHoraInicio(horaFin);

			if (horaFin.get(Calendar.HOUR_OF_DAY) < 23) { // Asegurarse de que la película comience antes de las 23:00
				Object[] fila = { pelicula.getNombre(), sdf.format(horaInicio.getTime()), // Hora de inicio ajustada
						pelicula.getDuracion(), pelicula.getCategoria(), pelicula.getAsientosDisponibles() };
				modeloTabla.addRow(fila);

				// Establecer la hora de inicio de la siguiente película
				horaInicio.setTimeInMillis(horaFin.getTimeInMillis());

				peliculasMostradas++; // Incrementar el contador de películas mostradas
			}
		}

		JScrollPane scrollPane = new JScrollPane(tablaPeliculas);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(e -> new VentanaCalendario(c).setVisible(true));

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(labelFecha, BorderLayout.NORTH);
		panel.add(scrollPane, BorderLayout.CENTER);
		panel.add(btnVolver, BorderLayout.SOUTH);

		add(panel);
		setVisible(true);
		logger.setLevel(java.util.logging.Level.INFO);
		logger.info("Componentes correctamete inicializados");
	}

	private List<Pelicula> obtenerOActualizarPeliculas(String fecha) {
		// Verificar si ya hay películas asociadas a la fecha
		/*
		 * if (peliculasPorFecha.containsKey(fecha)) { // Si sí, recuperar y devolver
		 * esas películas return peliculasPorFecha.get(fecha);
		 */
		// Si no, obtener nuevas películas aleatorias y asociarlas a la fecha
		List<Pelicula> nuevasPeliculas = obtenerPeliculasAleatoriasDesdeCSV("Peliculas.csv");
		peliculasPorFecha.put(fecha, nuevasPeliculas);
		return nuevasPeliculas;

	}

	private List<Pelicula> obtenerPeliculasAleatoriasDesdeCSV(String rutaArchivo) {
		List<Pelicula> peliculasAleatorias = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
			String linea;
			br.readLine(); // Saltar la primera línea si contiene encabezados

			List<String[]> lineas = new ArrayList<>();
			while ((linea = br.readLine()) != null) {
				String[] datosPelicula = linea.split(",");
				lineas.add(datosPelicula);
			}

			Random random = new Random();
			int numPeliculas = random.nextInt(lineas.size()) + 1; // Obtener entre 1 y el número total de películas

			for (int i = 0; i < numPeliculas; i++) {
				int indicePelicula = random.nextInt(lineas.size());
				String[] datosPelicula = lineas.get(indicePelicula);

				if (datosPelicula.length >= 4) {
					// Crear una película con nombre, duración, categoría y asientos disponibles
					Pelicula nuevaPelicula = new Pelicula(datosPelicula[0].trim(), // nombre
																					// Hora inicio
							Integer.parseInt(datosPelicula[1].trim()), // duración
							Categoria.valueOf(datosPelicula[2].trim()), // categoría (asumiendo que Categoria es un
																		// enum)
							Integer.parseInt(datosPelicula[3].trim()) // asientos disponibles
					);

					peliculasAleatorias.add(nuevaPelicula);
				}
			}

		} catch (IOException | IllegalArgumentException e) {
			e.printStackTrace();
		}

		return peliculasAleatorias;
	}

	private void ajustarProximaHoraInicio(Calendar horaFin) {
		int mins = horaFin.get(Calendar.MINUTE);
		if (mins < 15) {
			horaFin.set(Calendar.MINUTE, 15);
		} else if (mins < 30) {
			horaFin.set(Calendar.MINUTE, 30);
		} else if (mins < 45) {
			horaFin.set(Calendar.MINUTE, 45);
		} else {
			horaFin.add(Calendar.HOUR_OF_DAY, 1);
			horaFin.set(Calendar.MINUTE, 0);
		}
		horaFin.set(Calendar.SECOND, 0);
	}
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new VentanaDetalle("2023-01-01"));
//    }

	private Map<String, List<Pelicula>> cargarMapa() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("peliculas.dat"))) {
			return (Map<String, List<Pelicula>>) ois.readObject();
		} catch (EOFException e) {
			System.err.println("Error: Fin de archivo inesperado");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.err.println("Error: Archivo no encontrado");
			e.printStackTrace();
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error durante la lectura del archivo");
			e.printStackTrace();
		}
		return new HashMap<>(); // Retorna un mapa vacío en caso de error
	}

	private void guardarMapa(Map<String, List<Pelicula>> peliculasPorFecha) throws IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("peliculas.dat"))) {
			oos.writeObject(peliculasPorFecha);
		}
	}

}
