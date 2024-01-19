package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import domain.Pelicula;
import domain.Recursividad;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

    public class VentanaEstadistica extends JFrame {

        private JPanel contentPane;
        private List<Pelicula> peliculas;

        /**
         * Create the frame.
         */
        public VentanaEstadistica() {
            this.peliculas = cargarPeliculasDesdeArchivo(); 

            setResizable(false);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setBounds(100, 100, 600, 400);

            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(new BorderLayout(0, 0));

            JPanel panel = new JPanel();
            contentPane.add(panel, BorderLayout.NORTH);

            JLabel lblTitulo = new JLabel("Ventana de Estadísticas");
            lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
            panel.add(lblTitulo);

            JPanel panel_1 = new JPanel();
            contentPane.add(panel_1, BorderLayout.SOUTH);

            JButton btnCerrar = new JButton("Cerrar");
            btnCerrar.addActionListener(e -> dispose());
            panel_1.add(btnCerrar);

            JPanel panel_2 = new JPanel();
            contentPane.add(panel_2, BorderLayout.CENTER);
            panel_2.setLayout(new BorderLayout(0, 0));

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            panel_2.add(scrollPane, BorderLayout.CENTER);

        
            List<Pelicula> peliculas = Recursividad.cargarPeliculasDesdeArchivo();
            List<List<Pelicula>> combinaciones = new ArrayList<>();
            Recursividad.combinacionesPeliculas(combinaciones, peliculas, 510, new ArrayList<>(), 0);

            mostrarCombinacionesEnTextArea(textArea, combinaciones);
            setLocationRelativeTo(null);
        }

        private List<Pelicula> cargarPeliculasDesdeArchivo() {

            return new ArrayList<>(); 
        }

        private List<List<Pelicula>> obtenerCombinaciones() {
            List<List<Pelicula>> resultado = new ArrayList<>();
            int duracionTotalMaxima = 510;
            Recursividad.combinacionesPeliculas(resultado, peliculas, duracionTotalMaxima, new ArrayList<>(), 0);
            return resultado;
        }

        private void mostrarCombinacionesEnTextArea(JTextArea textArea, List<List<Pelicula>> combinaciones) {
            textArea.setText("Combinaciones de películas que se pueden dar en un dia:\n\n");
            textArea.setText("Numero de combinaciones de películas que se pueden dar en un dia: " + combinaciones.size());
            
            for (List<Pelicula> combinacion : combinaciones) {
                for (Pelicula pelicula : combinacion) {
                    textArea.append("- " + pelicula.getNombre() + "\n");
                }
                textArea.append("\n");
            }
        }
    }

    /**
     * Create the frame.
     */
    