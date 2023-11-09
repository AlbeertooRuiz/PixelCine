package Ventanas;

import javax.swing.*;

import Datos.Coordenadas;
import Datos.Pelicula;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaAsientos extends JFrame {
    private JButton[][] celdas; 
    
	
    public VentanaAsientos(JFrame va, Pelicula p) {  
    	ArrayList <Coordenadas> listaCoordenadas = new ArrayList<>();
    	
    	listaCoordenadas.add(new Coordenadas(3,3));
    	listaCoordenadas.add(new Coordenadas(1,1));
    	listaCoordenadas.add(new Coordenadas(5,5));
    	
  	
        // Configurar la ventana
        setTitle("Tabla de Asientos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 350);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(11, 6)); // 10 filas + 1 fila para números, 5 columnas + 1 columna para números  
        
        
          // Agregar la fila de números de columna (1-5) en la parte superior
        for (int i = 0; i <= 6; i++) {
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
   		 
   	  	}
        
        // Mostrar la ventana
        setVisible(true);
    }
    public static void main(String[] args) {
	    SwingUtilities.invokeLater(() -> new VentanaAsientos(null, null));
	}
}