package gui;

import javax.swing.*;
import java.util.logging.Logger;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import domain.Asiento;
import domain.Cliente;
import domain.Coordenadas;
import domain.Pelicula;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

public class VentanaAsientos extends JFrame implements Serializable {
	private Set<Point> celdasMarcadas = new HashSet<>();

	private static final String S_KEY = "serializable";
	private static final String CSV_KEY = "csv";
	private static final String PROPERTIES_FILE = "resources/data/config.properties";
	
	private static JTable tablaAsientos;
	private DefaultTableModel modeloDatosAsientos;
	private JScrollPane scrollPaneAsientos;
	private JFrame ventanaActual, ventanaAnterior;
	private List<Asiento> asientosOcupados;
	private static final Logger logger = Logger.getLogger(VentanaAsientos.class.getName());
	private static final ImageIcon TICK_ICON = new ImageIcon("Imagenes/Tick.png");

	public VentanaAsientos(JFrame va, Pelicula p, Cliente c, String fecha) throws ClassNotFoundException {
		ventanaActual = this;
		ventanaAnterior = va;
		Cliente cliente = c;
		Pelicula pelicula = p;
		this.initTable(p, fecha);
		this.loadAsientos();



		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(tablaAsientos), BorderLayout.CENTER);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				List<Asiento> asientos = confirmarAsientos();

				JOptionPane.showMessageDialog(VentanaAsientos.this, "Asientos confirmados.");

				VentanaReserva vr = new VentanaReserva(ventanaActual, p, asientos, cliente, fecha);
				vr.setVisible(true);
				ventanaActual.dispose();
			}
		});

		panel.add(btnConfirmar, BorderLayout.SOUTH);

		add(panel);

		JScrollPane scrollPaneAsientos = new JScrollPane(this.tablaAsientos);
		scrollPaneAsientos.setBorder(new TitledBorder("Asientos"));
		this.tablaAsientos.setFillsViewportHeight(true);

		setLayout(new BorderLayout());
		add(scrollPaneAsientos, BorderLayout.CENTER);

		add(btnConfirmar, BorderLayout.SOUTH);

	
		setTitle("Tabla de Asientos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setVisible(true);

	
		setVisible(true);
	}

	private void initTable(Pelicula p, String f) throws ClassNotFoundException {

		asientosOcupados = cargarAsientosReservados(p);

		Vector<String> cabeceraAsientos = new Vector<String>(
				Arrays.asList("Filas", "c1", "c2", "c3", "c4", "c5", "       ", "c6", "c7", "c8", "c9", "c10"));

		this.modeloDatosAsientos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraAsientos);

		this.tablaAsientos = new JTable(this.modeloDatosAsientos) {

			public boolean isCellEditable(int row, int column) {

				return false;

			}
		};

		System.out.println(asientosOcupados);

		tablaAsientos.setDefaultRenderer(Object.class, (table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel((value != null) ? value.toString() : "");

			if (column == 0 || column == 6) {
				result.setBackground(new Color(250, 249, 249));
			} else {
				result.setBackground(new Color(190, 227, 219));
			}

			if (value instanceof ImageIcon) {
				result.setIcon((ImageIcon) value);
				result.setText("");
			}



			for (Asiento a : asientosOcupados) {
                if (row + 1 == a.getFila()) {
                    if (column < 6) {
                        if (column == a.getColumna()) {

                            //result.setIcon(new ImageIcon("src/Imagenes/silla(1).png"));
                            result.setBackground(Color.RED);
                            
                            result.setText("");


                        }
                    } else if (column == 6) {
                        result.setBackground(new Color(250, 249, 249));
                    }

                    else {

                        if (column - 1 == a.getColumna()) {

                            //result.setIcon(new ImageIcon("src/Imagenes/silla(1).png"));
                            result.setBackground(Color.RED);
                            result.setText("");


                        }
                    }
                }
            }
			result.setOpaque(true);
			return result;

			
		});

		tablaAsientos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowC = tablaAsientos.rowAtPoint(e.getPoint());
				int columnC = tablaAsientos.columnAtPoint(e.getPoint());

				List<Point> celdasNoClicables = new ArrayList<>();

				for (Asiento s : asientosOcupados) {
					if (s.getColumna() < 6) {
						celdasNoClicables.add(new Point(s.getFila() - 1, s.getColumna()));
					} else {
						celdasNoClicables.add(new Point(s.getFila() - 1, s.getColumna() + 1));
					}
				}

				Point puntoActual = new Point(rowC, columnC);

				if (columnC != 0 && columnC != 6 && !celdasNoClicables.contains(puntoActual)) {
					DefaultTableModel model = (DefaultTableModel) tablaAsientos.getModel();
					Object currentValue = model.getValueAt(rowC, columnC);

					if (currentValue == null || !(currentValue instanceof ImageIcon)) {
						model.setValueAt(getImageIcon(), rowC, columnC);
					} else {
						String asiento = "Asiento " + (rowC + 1) + columnC;
						model.setValueAt(asiento, rowC, columnC);
					}
				}
				repaint();

			}

		});

		this.tablaAsientos.setRowHeight(26);


	}

	private ImageIcon getImageIcon() {
		
		
		String imagen = "resources/images/Tick.png";

		return new ImageIcon(imagen);
	}

	private void loadAsientos() {

		modeloDatosAsientos.setRowCount(0);

		for (int i = 1; i <= 25; i++) {

			modeloDatosAsientos.addRow(new Object[] { "Fila" + Integer.toString(i),
					"Asiento" + Integer.toString(i) + "1", "Asiento" + Integer.toString(i) + "2",
					"Asiento" + Integer.toString(i) + "3", "Asiento" + Integer.toString(i) + "4",
					"Asiento" + Integer.toString(i) + "5", "       ", "Asiento" + Integer.toString(i) + "6",
					"Asiento" + Integer.toString(i) + "7", "Asiento" + Integer.toString(i) + "8",
					"Asiento" + Integer.toString(i) + "9", "Asiento" + Integer.toString(i) + "X", });
		}
		logger.info("Asientos Cargados");

	}

	public static List<Asiento> confirmarAsientos() {
		List<Asiento> asientos = new ArrayList<Asiento>();

		DefaultTableModel model = (DefaultTableModel) tablaAsientos.getModel();
		int rowCount = model.getRowCount();
		int colCount = model.getColumnCount();

		for (int row = 0; row < rowCount; row++) {
			for (int col = 0; col < colCount; col++) {
				Object value = model.getValueAt(row, col);

				if (value instanceof ImageIcon) {
					if (col < 6) {
						Asiento asiento = new Asiento(row + 1, col, true);
						asientos.add(asiento);
					} else {
						Asiento asiento = new Asiento(row + 1, col - 1, true);
						asientos.add(asiento);
					}
				}
			}
		}

		return asientos;
	}

	private List<Asiento> cargarAsientosReservados(Pelicula pelicula) throws ClassNotFoundException {

		Properties properties = loadProperties();
		
		List<Asiento> as = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(properties.getProperty(CSV_KEY)));
			String linea = br.readLine();
			while (linea != null) {
				String[] token = linea.split(",");
				if (token[0].equals(pelicula.getFechayhora())) {
					if (pelicula.getNombre().equals(token[1])) {
						as.add(new Asiento(Integer.parseInt(token[2]), Integer.parseInt(token[3]), true));
					}
				}

				linea = br.readLine();
			}
		} catch (IOException e1) {
			System.out.println("Falla fichero");

		}

		return as;

	}
	
	private static Properties loadProperties() {
		Properties properties = new Properties();

		try {
			properties.load(new FileReader(PROPERTIES_FILE));
		} catch (Exception ex) {
			System.err.println(String.format("Error leyendo propiedades: %s", ex.getMessage()));
			ex.printStackTrace();
		}

		return properties;
	}
	
}