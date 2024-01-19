package domain;

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

import domain.Pelicula;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Recursividad {

    public static void main(String[] args) {
        List<Pelicula> peliculas = cargarPeliculasDesdeArchivo();

        if (peliculas != null) {
            List<List<Pelicula>> resultado = new ArrayList<>();
            int duracionTotalMaxima = 630; // Duración máxima en minutos para un día (10 horas y 30 minutos)

            combinacionesPeliculas(resultado, peliculas, duracionTotalMaxima, new ArrayList<>(), 0);

            // Imprimir resultado
            System.out.println("Número de combinaciones posibles: " + resultado.size());
            for (List<Pelicula> combinacion : resultado) {
                System.out.println("Peliculas para un día:");
                System.out.println("Número de combinaciones posibles: " + resultado.size());
                for (Pelicula pelicula : combinacion) {
                    System.out.println("- " + pelicula.getNombre());
                }
                System.out.println();
            }
        } else {
            System.out.println("Error al cargar las películas desde el archivo.");
        }
    }

    public static List<Pelicula> cargarPeliculasDesdeArchivo() {
        String nombreArchivo = "peliculas.csv"; // Cambia el nombre de archivo si es necesario

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            List<Pelicula> peliculas = new ArrayList<>();
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(","); // Separar los valores por comas

                if (partes.length == 5) {
                    String nombre = partes[0];
                    int duracion = Integer.parseInt(partes[1]);
                    Categoria categoria = Categoria.valueOf(partes[2]); // Ajusta esto según tu implementación de Categoria
                    int asientosDisponibles = Integer.parseInt(partes[3]);
                    String actorPrincipal = partes[4];

                    Pelicula pelicula = new Pelicula(nombre, duracion, categoria, asientosDisponibles, actorPrincipal);
                    peliculas.add(pelicula);
                } else {
                    System.out.println("Formato incorrecto en la línea del archivo CSV: " + linea);
                }
            }

            return peliculas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	public static void combinacionesPeliculas(List<List<Pelicula>> resultado, List<Pelicula> peliculas,
			int duracionMaxima, List<Pelicula> temp, int duracionAcumulada) {
		if (duracionAcumulada <= duracionMaxima) {
			resultado.add(new ArrayList<>(temp));
		}

		for (Pelicula p : peliculas) {
			if (!temp.contains(p) && duracionAcumulada + p.getDuracion() <= duracionMaxima) {
				temp.add(p);
				combinacionesPeliculas(resultado, peliculas, duracionMaxima, temp, duracionAcumulada + p.getDuracion());
				temp.remove(temp.size() - 1);
			}
		}
}


}
