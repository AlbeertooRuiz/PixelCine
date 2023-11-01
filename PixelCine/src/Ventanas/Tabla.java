package Ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tabla extends JFrame {
    private JButton[][] celdas;

    public Tabla() {
        // Configurar la ventana
        setTitle("Tabla de Asientos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 350);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(11, 6)); // 10 filas + 1 fila para números, 5 columnas + 1 columna para números

        // Agregar la fila de números de columna (1-5) en la parte superior
        for (int i = 1; i <= 5; i++) {
            JButton colLabel = new JButton("Columna " + i);
            colLabel.setBackground(Color.LIGHT_GRAY);
            colLabel.setEnabled(false);
            add(colLabel);
        }

        // Inicializar el arreglo de botones (celdas)
        celdas = new JButton[10][5];

        // Crear y configurar las celdas
        for (int i = 0; i < 10; i++) {
            // Agregar la etiqueta de fila (1-10) en la parte izquierda
            JButton rowLabel = new JButton("Fila " + (i + 1));
            rowLabel.setBackground(Color.LIGHT_GRAY);
            rowLabel.setEnabled(false);
            add(rowLabel);

            for (int j = 0; j < 5; j++) {
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
                        celdas[row][col].setBackground(Color.BLUE);
                    }
                });

                // Agregar la celda a la ventana
                add(celdas[i][j]);
            }
        }

        // Mostrar la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Tabla());
    }
}
