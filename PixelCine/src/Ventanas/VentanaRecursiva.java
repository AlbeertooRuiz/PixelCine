package Ventanas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Datos.Categoria;
import Datos.Pelicula;


public class VentanaRecursiva extends JFrame {

	private static final long serialVersionUID = 1L;
		
	private List<Pelicula> Peliculas;
	private int mouseRowActors = -1;
	
	private JTable tablaPeliculas;
	private DefaultTableModel modeloDatosPeliculas;
	private JTable tablaActors;
	private DefaultTableModel modeloDatosActors;
	private JScrollPane scrollPaneActors;
	private JTextField txtFiltro;
	
	public void JFramePrincipal(List<Pelicula> Peliculas) {
		//Asignamos la lista de Peliculas a la varaible local
		this.Peliculas = Peliculas;

		//Se inicializan las tablas y sus modelos de datos
		this.initTables();
		//Se cargan los Peliculas en la tabla de Peliculas
		this.loadPeliculas();
		
		//La tabla de Peliculas se inserta en un panel con scroll
		JScrollPane scrollPanePeliculas = new JScrollPane(this.tablaPeliculas);
		scrollPanePeliculas.setBorder(new TitledBorder("Peliculas"));
		this.tablaPeliculas.setFillsViewportHeight(true);
		
		//La tabla de Actors se inserta en otro panel con scroll
		this.scrollPaneActors = new JScrollPane(this.tablaActors);
		this.scrollPaneActors.setBorder(new TitledBorder("Actors"));		
		this.tablaActors.setFillsViewportHeight(true);
				
		this.txtFiltro = new JTextField(20);
		//Se aÃ±ade un listener para detectar cambios en el campo de texto
		this.txtFiltro.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				filtrarPeliculas();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				filtrarPeliculas();
			}

			@Override
			public void changedUpdate(DocumentEvent e) { }		
		
		});		
		
		JPanel panelFiltro = new JPanel();
		panelFiltro.add(new JLabel("Filtro por tÃ­tulo: "));
		panelFiltro.add(txtFiltro);
		
		JPanel panelPeliculas = new JPanel();
		panelPeliculas.setLayout(new BorderLayout());
		panelPeliculas.add(BorderLayout.CENTER, scrollPanePeliculas);
		panelPeliculas.add(BorderLayout.NORTH, panelFiltro);
		
		//Listener para los eventos de teclado
		
		
		//Se define el listener para detectar cuando el ratÃ³n sale de la tabla de Actors

		
		//Se define el listener para controlar el movimiento del ratÃ³n sobre la tabla de Actors
		this.tablaActors.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				//Se actualiza la fila/columna sobre la que estÃ¡ el ratÃ³n
				mouseRowActors = tablaActors.rowAtPoint(e.getPoint());
				
				//Se provoca el redibujado de la tabla
				tablaActors.repaint();
			}			
		});
				
		this.tablaActors.addKeyListener(myKeyListener);
		this.tablaPeliculas.addKeyListener(myKeyListener);
		this.txtFiltro.addKeyListener(myKeyListener);
		
		this.tablaActors.addKeyListener(myKeyListener);
		this.tablaPeliculas.addKeyListener(myKeyListener);
		
		//El Layout del panel principal es un matriz con 2 filas y 1 columna
		this.getContentPane().setLayout(new GridLayout(2, 1));
		this.getContentPane().add(panelPeliculas);
		this.getContentPane().add(this.scrollPaneActors);
		
		this.setTitle("Ventana principal de Peliculas");		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);		
	}
	
	private void initTables() {
		//Cabecera del modelo de datos
		Vector<String> cabeceraPeliculas = new Vector<String>(Arrays.asList( "Nombre", "Duracion", "Categoria", "Actor Principal"));
		//Se crea el modelo de datos para la tabla de Peliculas sÃ³lo con la cabecera
		this.modeloDatosPeliculas = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraPeliculas);
		//Se crea la tabla de Peliculas con el modelo de datos
		this.tablaPeliculas = new JTable(this.modeloDatosPeliculas) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int col) {
				
					return false;
	         	
	         }
		};
		
		//Se define un CellRenderer para las celdas de las dos tabla usando una expresiÃ³n lambda
		TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel(value.toString());
			
			//Se definen para el label los colores de texto y fondo por defecto de la tabla
			result.setBackground(table.getBackground());
			result.setForeground(table.getForeground());
						
			//Si el valor es de tipo Editorial: se renderiza con la imagen centrada
			if (value instanceof Categoria) {
				Categoria e = (Categoria) value;
				
				result.setText("");		
				result.setToolTipText(e.toString());
				result.setHorizontalAlignment(JLabel.CENTER);
				
				switch (e) { 
					case Romance:
						result.setIcon(new ImageIcon("Imagenes/romance.png"));
						break;
					case Drama:
						result.setIcon(new ImageIcon("Imagenes/drama.png"));
						break;
					case Terror:
						result.setIcon(new ImageIcon("Imagenes/terror.png"));
						break;
					case Comedia:
						result.setIcon(new ImageIcon("Imagenes/comedia.png"));
						break;
					case Accion:
						result.setIcon(new ImageIcon("Imagenes/accion.png"));
						break;
					default:
				}
			//Si el valor es numÃ©rico se renderiza centrado
			} else if (value instanceof Number) {
				result.setHorizontalAlignment(JLabel.CENTER);
			} else {
				//Si el valor es texto pero representa un nÃºmero se renderiza centrado
				try {
					Integer.parseInt(value.toString());
					result.setHorizontalAlignment(JLabel.CENTER);				
				} catch(Exception ex) {
					result.setText(value.toString());
					
					if (table.equals(tablaPeliculas)) {
						String filter = txtFiltro.getText();
						String txtValue = value.toString();
						StringBuffer txtHtml = new StringBuffer();						
						String txtResaltado;
						
						if (isSelected) {
							txtResaltado = "<strong style='background-color:white; color: red;'>" + filter + "</strong>";
						} else {
							txtResaltado = "<strong style='background-color:yellow; color: blue;'>" + filter + "</strong>";
						}
												
						txtHtml.append("<html>");
						txtHtml.append(txtValue.substring(0, txtValue.indexOf(filter)));
						txtHtml.append(txtResaltado);
						txtHtml.append(txtValue.substring(txtValue.indexOf(filter) + filter.length(), txtValue.length()));
						txtHtml.append("</html");
						
						result.setText(txtHtml.toString());
					}
				}		
			}
			
			//La filas pares e impares se renderizan de colores diferentes de la tabla de Peliculas			
			if (table.equals(tablaPeliculas)) {
				if (row % 2 == 0) {
					result.setBackground(new Color(250, 249, 249));
				} else {
					result.setBackground(new Color(190, 227, 219));
				}
			}			
			
			//Si la celda estÃ¡ seleccionada se renderiza con el color de selecciÃ³n por defecto
			if (isSelected || (table.equals(tablaActors) && row == mouseRowActors)) {
				result.setBackground(table.getSelectionBackground());
				result.setForeground(table.getSelectionForeground());			
			}			
			
			result.setOpaque(true);
			
			return result;
		};
		
		//Se define un CellRenderer para las cabeceras de las dos tabla usando una expresiÃ³n lambda
		TableCellRenderer headerRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel(value.toString());			
			result.setHorizontalAlignment(JLabel.CENTER);
			
			switch (value.toString()) {
				case "TÃ�TULO":
				case "NOMBRE":
				case "EMAIL":
					result.setHorizontalAlignment(JLabel.LEFT);
			}
			
			result.setBackground(table.getBackground());
			result.setForeground(table.getForeground());
			
			result.setOpaque(true);
			
			return result;
		};
		
		//Se crea un CellEditor a partir de un JComboBox()
		JComboBox<Categoria> jComboCategoria = new JComboBox<>(Categoria.values());		
		DefaultCellEditor CategoriaEditor = new DefaultCellEditor(jComboCategoria);
		
		//Se define la altura de las filas de la tabla de Peliculas
		this.tablaPeliculas.setRowHeight(26);
		
		//Se deshabilita la reordenaciÃ³n de columnas
		this.tablaPeliculas.getTableHeader().setReorderingAllowed(false);
		//Se deshabilita el redimensionado de las columna
		this.tablaPeliculas.getTableHeader().setResizingAllowed(false);		

		//Se definen criterios de ordenaciÃ³n por defecto para cada columna
		this.tablaPeliculas.setAutoCreateRowSorter(true);
		
		//Se establecen los renderers al la cabecera y el contenido
		this.tablaPeliculas.getTableHeader().setDefaultRenderer(headerRenderer);		
		this.tablaPeliculas.setDefaultRenderer(Object.class, cellRenderer);
		
		//Se establece el editor especÃ­fico para la Editorial		
		this.tablaPeliculas.getColumnModel().getColumn(1).setCellEditor(CategoriaEditor);
		
		//Se define la anchura de la columna TÃ­tulo
		this.tablaPeliculas.getColumnModel().getColumn(2).setPreferredWidth(400);
		
		//Se modifica el modelo de selecciÃ³n de la tabla para que se pueda selecciona Ãºnicamente una fila
		this.tablaPeliculas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//Se define el comportamiento el evento de selecciÃ³n de una fila de la tabla
		this.tablaPeliculas.getSelectionModel().addListSelectionListener(e -> {
			//Se obtiene el ID del Pelicula de la fila seleccionada si es distinta de -1
			if (tablaPeliculas.getSelectedRow() != -1) {
				this.loadActors(this.Peliculas.get((int) tablaPeliculas.getValueAt(tablaPeliculas.getSelectedRow(), 0) - 1));
			}
		});
		
		//Cabecera del modelo de datos
		Vector<String> cabeceraActors = new Vector<String>(Arrays.asList( "ID", "EDITORIAL", "NOMBRE", "EMAIL"));
		//Se crea el modelo de datos para la tabla de Peliculas sÃ³lo con la cabecera
		this.modeloDatosActors = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraActors);
		//Se crea la tabla de Actors con el modelo de datos
		this.tablaActors = new JTable(this.modeloDatosActors) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
				
		this.tablaActors.getTableHeader().setDefaultRenderer(headerRenderer);		
		this.tablaActors.setDefaultRenderer(Object.class, cellRenderer);
		this.tablaActors.setRowHeight(26);
		this.tablaActors.getColumnModel().getColumn(2).setPreferredWidth(200);
		this.tablaActors.getColumnModel().getColumn(3).setPreferredWidth(200);
	}
	
	private void filtrarPeliculas() {
	    // Se vacían las dos tablas
	    this.modeloDatosPeliculas.setRowCount(0);
	    this.modeloDatosActors.setRowCount(0);

	    // Se ordenan los cómics alfabéticamente
	    List<Pelicula> PeliculasOrdenados = ordenadoRecursivo(this.Peliculas);

	    // Se añaden a la tabla solo los cómics que contengan el texto del filtro
	    PeliculasOrdenados.forEach(c -> {
	        if (c.getNombre().contains(this.txtFiltro.getText())) {
	            this.modeloDatosPeliculas.addRow(
	                    new Object[]{c.getNombre(), c.getFechayhora(), c.getDuracion(), c.getCategoria(), c.getAsientosDisponibles(), c.getActorPrincipal()});
	        }
	    });
	}

	private List<Pelicula> ordenadoRecursivo(List<Pelicula> lista) {
	    if (lista.size() <= 1) {
	        return new ArrayList<>(lista);
	    }

	    int medio = lista.size() / 2;
	    List<Pelicula> izquierda = new ArrayList<>(lista.subList(0, medio));
	    List<Pelicula> derecha = new ArrayList<>(lista.subList(medio, lista.size()));

	    return ordenadoRecursivo(ordenadoRecursivo(izquierda), ordenadoRecursivo(derecha));
	}

	private List<Pelicula> ordenadoRecursivo(List<Pelicula> izquierda, List<Pelicula> derecha) {
	    List<Pelicula> resultado = new ArrayList<>();
	    int i = 0, j = 0;

	    while (i < izquierda.size() && j < derecha.size()) {
	        if (izquierda.get(i).getNombre().compareTo(derecha.get(j).getNombre()) <= 0) {
	            resultado.add(izquierda.get(i++));
	        } else {
	            resultado.add(derecha.get(j++));
	        }
	    }

	    resultado.addAll(izquierda.subList(i, izquierda.size()));
	    resultado.addAll(derecha.subList(j, derecha.size()));

	    return resultado;
	}

	private void loadPeliculas() {
	    // Se borran los datos del modelo de datos
	    this.modeloDatosPeliculas.setRowCount(0);

	    // Se ordenan los cómics alfabéticamente
	    List<Pelicula> PeliculasOrdenados = ordenadoRecursivo(this.Peliculas);

	    // Se añaden los cómics ordenados uno a uno al modelo de datos
	    PeliculasOrdenados.forEach(c -> this.modeloDatosPeliculas.addRow(
                new Object[]{c.getNombre(), c.getFechayhora(), c.getDuracion(), c.getCategoria(), c.getAsientosDisponibles(), c.getActorPrincipal()})
	    );
	}

	
	private void loadActors(Pelicula Pelicula) {
		//Se borran los datos del modelo de datos
		this.modeloDatosActors.setRowCount(0);

		//Se aÃ±aden los Actors uno a uno al modelo de datos
	/*	Pelicula.getActorPrincipal().forEach(p -> this.modeloDatosActors.addRow(
                new Object[]{c.getNombre(), c.getFechayhora(), c.getDuracion(), c.getCategoria(), c.getAsientosDisponibles()})
		);
	*/	
		//Se modifica el texto del bode de la lista de Actors 
		this.scrollPaneActors.setBorder(new TitledBorder(String.format("Actors del Pelicula '%s' [%d]",
				 Pelicula.getActorPrincipal())));
	}
}