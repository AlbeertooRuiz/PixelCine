package Ventanas;

import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Datos.Categoria;
import Datos.Cliente;
import Datos.Pelicula;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VentanaPeliculas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel modeloTablaPeliculas;
	private JTable tablaPeliculas;
	private JScrollPane scrollTabla;
	private JFrame ventanaActual;
	private ArrayList<Pelicula> peliculas;

	/**
	 * Create the frame.
	 */
	public VentanaPeliculas(Cliente c, String fecha) {
		Cliente cliente = c;
		ventanaActual = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanaActual.setSize(650, 277);
        ventanaActual.setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				VentanaLogin vl = new VentanaLogin();
				vl.setVisible(true);
			}
		});
		panelSur.add(btnCerrarSesion);
		
		JPanel panelOeste = new JPanel();
		contentPane.add(panelOeste, BorderLayout.WEST);
		
		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		
		modeloTablaPeliculas = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tablaPeliculas = new JTable(modeloTablaPeliculas);
		
		String [] titulos = {"Nombre", "Duración", "Categoría", "Asientos Disponibles", "Actor principal"};
		modeloTablaPeliculas.setColumnIdentifiers(titulos);
		
		tablaPeliculas.getTableHeader().setReorderingAllowed(false);
		
		peliculas = cargarPeliculasTablaCsv();
		for(Pelicula p: peliculas) {
			Object [] datos = {p.getNombre(), p.getDuracion(), p.getCategoria(),p.getAsientosDisponibles(), p.getActorPrincipal()};
			modeloTablaPeliculas.addRow(datos);
		}
		
		scrollTabla = new JScrollPane(tablaPeliculas);
		
		scrollTabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		contentPane.add(scrollTabla, BorderLayout.CENTER);
		
		tablaPeliculas.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount() == 2) {
					int fila = tablaPeliculas.rowAtPoint(e.getPoint());
					String nombre = (String) modeloTablaPeliculas.getValueAt(fila, 0);
					int duracion = (int) modeloTablaPeliculas.getValueAt(fila, 1);;
					Categoria categoria = (Categoria) modeloTablaPeliculas.getValueAt(fila, 2);
					int asientosDisponibles = (int) modeloTablaPeliculas.getValueAt(fila, 3);;
					String actorPrincipal = (String) modeloTablaPeliculas.getValueAt(fila, 4);;
					Pelicula pelicula = new Pelicula(nombre, duracion, categoria, asientosDisponibles, actorPrincipal, actorPrincipal);
					VentanaAsientos va;
					try {
						va = new VentanaAsientos(ventanaActual, pelicula, cliente, fecha);
						va.setVisible(true);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					ventanaActual.dispose();
				}
			}
		});
		
	}
	
	public static ArrayList<Pelicula> cargarPeliculasTablaCsv() {
		ArrayList<Pelicula> peliculas = new ArrayList<>();
		String input = "Peliculas.csv";
		try (BufferedReader br = new BufferedReader(new FileReader(input))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] datos = line.split(",");
				String nombre = datos[0];
				int duracion = Integer.parseInt(datos[1]);
				Categoria categoria = Categoria.valueOf(datos[2]);
				int asientosDisponibles = Integer.parseInt(datos[3]);
				String actorPrincipal = datos[4];
				
				Pelicula p = new Pelicula(nombre, duracion, categoria, asientosDisponibles, actorPrincipal, actorPrincipal);
				peliculas.add(p);
			}
			br.close();
			return peliculas;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}

	}

}
