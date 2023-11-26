package Ventanas;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Datos.Cliente;
import Datos.Coordenadas;
import Datos.Pelicula;
import Datos.Asiento;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class VentanaAsientos extends JFrame {
    private List<Asiento> asientos;
    private Set<Point> celdasMarcadas = new HashSet<>();
    
	private JTable tablaAsientos;
	private DefaultTableModel modeloDatosAsientos;
	private JScrollPane scrollPaneAsientos;
	
    public VentanaAsientos(/*JFrame va, Pelicula p, Cliente c*/) {
    	
    	this.initTable();
		//Se cargan los comics en la tabla de comics
		this.loadAsientos();
		
		
    	/*ArrayList <Coordenadas> listaCoordenadas = new ArrayList<>();
    	
    	listaCoordenadas.add(new Coordenadas(3,3));
    	listaCoordenadas.add(new Coordenadas(1,1));
    	listaCoordenadas.add(new Coordenadas(5,5));*/
    	
		JScrollPane scrollPaneAsientos = new JScrollPane(this.tablaAsientos);
		scrollPaneAsientos.setBorder(new TitledBorder("Asientos"));
		this.tablaAsientos.setFillsViewportHeight(true);
		
		setLayout(new BorderLayout());
        add(scrollPaneAsientos, BorderLayout.CENTER);
		
        // Configurar la ventana
        setTitle("Tabla de Asientos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 350);
        setLocationRelativeTo(null);
        setVisible(true);
        // 10 filas + 1 fila para números, 5 columnas + 1 columna para números  
        
        
          // Agregar la fila de números de columna (1-5) en la parte superior
       /* for (int i = 0; i <= 6; i++) {
        	if(i == 0) {
        		JButton botonvac = new JButton();
        		botonvac.setBackground(Color.LIGHT_GRAY);
                botonvac.setEnabled(false);
                add(botonvac);
        	}else {
            JButton colLabel = new JButton("Columna " + (i));
            colLabel.setBackground(Color.LIGHT_GRAY);
            colLabel.setEnabled(false);
            add(colLabel);
        	}
        }

        // Inicializar el arreglo de botones (celdas)
        celdas = new JButton[11][6];

        // Crear y configurar las celdas
        for (int i= 1; i <11; i++) {
            // Agregar la etiqueta de fila (1-10) en la parte izquierda
            JButton rowLabel = new JButton("Fila " + (i));
            rowLabel.setBackground(Color.LIGHT_GRAY);
            rowLabel.setEnabled(false);
            add(rowLabel);

            for (int j = 0; j <6; j++) {
                celdas[i][j] = new JButton("a");
                celdas[i][j].setBackground(Color.WHITE);
                celdas[i][j].setOpaque(true);
                celdas[i][j].setBorderPainted(false);
                final int row = i;
                final int col = j;

                // Agregar un ActionListener para cambiar el color al hacer clic
                celdas[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {                      
                        if (celdas[row][col].getBackground() == Color.BLUE) {               	
                        	celdas[row][col].setBackground(Color.WHITE);
                        }else if(celdas[row][col].getBackground() == Color.WHITE){
                  
                        	celdas[row][col].setBackground(Color.BLUE);
                        }
                    }
                });

                // Agregar la celda a la ventana
                add(celdas[i][j]);
            }
            
        }
    	for (int i = 0; i < listaCoordenadas.size(); i++) {   		
    		Coordenadas a  = listaCoordenadas.get(i);
    		celdas[a.getX()][a.getY()].setBackground(Color.RED);
   		 
   	  	}*/
        
        // Mostrar la ventana
        setVisible(true);
    }
    
    
    
    

    private void initTable() {
    	Vector<String> cabeceraAsientos = new Vector<String>(Arrays.asList(  "Filas", "c1", "c2", "c3", "c4", "c5", "       ", "c6", "c7", "c8", "c9", "c10"));
		
		this.modeloDatosAsientos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraAsientos);
		
		this.tablaAsientos = new JTable(this.modeloDatosAsientos) {
			public boolean isCellEditable(int row, int column) {
				
					return false;
				
				
			}
		};
		
		
		
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

            result.setOpaque(true);
            return result;
        });
		
        tablaAsientos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowC = tablaAsientos.rowAtPoint(e.getPoint());
                int columnC = tablaAsientos.columnAtPoint(e.getPoint());

                if (columnC != 0 && columnC != 6) {
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
		//this.tablaAsientos.setDefaultRenderer(Object.class, cellRenderer);


	    
		
		
		
    }
    
    private ImageIcon getImageIcon() {
        // Reemplaza la URL con la ubicación de tu imagen
        
    	String imagen = "src/Imagenes/Tick.png";
    	
        return new ImageIcon(imagen);
    }

 

    
    private void loadAsientos() {
    	
    	modeloDatosAsientos.setRowCount(0);
    	
    	for (int i = 1; i <=25; i++) {
			
    		modeloDatosAsientos.addRow(
    				new Object[] {"Fila" + Integer.toString(i),
    							  "Asiento" + Integer.toString(i) + "1",
    							  "Asiento" + Integer.toString(i) + "2",
    							  "Asiento" + Integer.toString(i) + "3",
    							  "Asiento" + Integer.toString(i) + "4",
    							  "Asiento" + Integer.toString(i) + "5",
    							  "       "  ,
    							  "Asiento" + Integer.toString(i) + "6",
    							  "Asiento" + Integer.toString(i) + "7",
    							  "Asiento" + Integer.toString(i) + "8",
    							  "Asiento" + Integer.toString(i) + "9",
    							  "Asiento" + Integer.toString(i) + "X",});
			
		}
    	
    }
    public static void main(String[] args) {
	    SwingUtilities.invokeLater(() -> new VentanaAsientos());
	}
}