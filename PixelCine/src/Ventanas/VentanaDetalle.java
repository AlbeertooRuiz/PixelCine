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
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class VentanaDetalle extends JFrame {
    private String fecha;
    private static Map<String, List<Pelicula>> peliculasPorFecha = new HashMap<>();
    Cliente cliente;

    public VentanaDetalle(String fecha) {
        this.fecha = fecha;
        configurarVentana();
        inicializarComponentes();
    }
    
    private void configurarVentana() {
        setTitle("Detalles del día");
        setSize(800, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void inicializarComponentes() {
        JLabel labelFecha = new JLabel("Fecha: " + fecha);

        // Datos para la tabla
        String[] columnas = {"Nombre", "Hora Inicio", "Duración", "Categoría", "Asientos Disponibles"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        List<Pelicula> peliculas = obtenerOActualizarPeliculas(fecha);

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
                    int duracion = Integer.parseInt(modeloTabla.getValueAt(fila, 2).toString());
                    String categoria = modeloTabla.getValueAt(fila, 3).toString();
                    int asientosDisponibles = Integer.parseInt(modeloTabla.getValueAt(fila, 4).toString());

                    // Crear una instancia de Pelicula y Cliente para pasar a VentanaAsientos
                    Pelicula peliculaSeleccionada = new Pelicula(nombrePelicula, duracion, Categoria.valueOf(categoria), asientosDisponibles);
                    Cliente cliente = new Cliente(); // Asumiendo que tienes una forma de obtener o crear un Cliente

                    new VentanaAsientos(VentanaDetalle.this, peliculaSeleccionada, cliente).setVisible(true);
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
                Object[] fila = {
                        pelicula.getNombre(),
                        sdf.format(horaInicio.getTime()), // Hora de inicio ajustada
                        pelicula.getDuracion(),
                        pelicula.getCategoria(),
                        pelicula.getAsientosDisponibles()
                };
                modeloTabla.addRow(fila);

                // Establecer la hora de inicio de la siguiente película
                horaInicio.setTimeInMillis(horaFin.getTimeInMillis());

                peliculasMostradas++; // Incrementar el contador de películas mostradas
            }
        }
        
        

        
        JScrollPane scrollPane = new JScrollPane(tablaPeliculas);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> volverAVentanaCalendario());

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(labelFecha, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnVolver, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private void volverAVentanaCalendario() {
        dispose();
    }

    private List<Pelicula> obtenerOActualizarPeliculas(String fecha) {
        // Verificar si ya hay películas asociadas a la fecha
        if (peliculasPorFecha.containsKey(fecha)) {
            // Si sí, recuperar y devolver esas películas
            return peliculasPorFecha.get(fecha);
        } else {
            // Si no, obtener nuevas películas aleatorias y asociarlas a la fecha
            List<Pelicula> nuevasPeliculas = obtenerPeliculasAleatoriasDesdeCSV("Peliculas.csv");
            peliculasPorFecha.put(fecha, nuevasPeliculas);
            return nuevasPeliculas;
        }
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
                    Pelicula nuevaPelicula = new Pelicula(
                            datosPelicula[0].trim(), // nombre
                                                                        // Hora inicio
                            Integer.parseInt(datosPelicula[1].trim()), // duración
                            Categoria.valueOf(datosPelicula[2].trim()), // categoría (asumiendo que Categoria es un enum)
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
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaDetalle("2023-01-01"));
    }
}
