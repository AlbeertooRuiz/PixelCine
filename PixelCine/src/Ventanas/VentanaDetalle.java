package Ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class VentanaDetalle extends JFrame {
    private String fecha;
    private static Map<String, List<Pelicula>> peliculasPorFecha = new HashMap<>();

    public VentanaDetalle(String fecha) {
        this.fecha = fecha;
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Detalles del día");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void inicializarComponentes() {
        JLabel labelFecha = new JLabel("Fecha: " + fecha);

        // Lista de películas asociadas a la fecha actual
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        List<Pelicula> peliculas = obtenerOActualizarPeliculas(fecha);
        for (Pelicula pelicula : peliculas) {
            modeloLista.addElement(pelicula.toString());
        }

        JList<String> listaPeliculas = new JList<>(modeloLista);

        JScrollPane scrollPane = new JScrollPane(listaPeliculas);
        scrollPane.setPreferredSize(new Dimension(250, 100));

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
            List<Pelicula> nuevasPeliculas = obtenerPeliculasAleatorias();
            peliculasPorFecha.put(fecha, nuevasPeliculas);
            return nuevasPeliculas;
        }
    }

    private List<Pelicula> obtenerPeliculasAleatorias() {
        // Simula obtener películas aleatorias
        String[] nombresPeliculas = {"Película 1", "Película 2", "Película 3"};
        String[] horarios = {"15:00", "18:30", "21:00"};

        Random random = new Random();
        int numPeliculas = random.nextInt(3) + 1; // Obtener entre 1 y 3 películas aleatorias
        List<Pelicula> peliculasAleatorias = new ArrayList<>();

        for (int i = 0; i < numPeliculas; i++) {
            int indicePelicula = random.nextInt(nombresPeliculas.length);
            int indiceHorario = random.nextInt(horarios.length);
            Pelicula nuevaPelicula = new Pelicula(nombresPeliculas[indicePelicula], horarios[indiceHorario]);
            peliculasAleatorias.add(nuevaPelicula);
        }

        return peliculasAleatorias;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaDetalle("2023-01-01"));
    }
}

class Pelicula {
    private String nombre;
    private String horario;

    public Pelicula(String nombre, String horario) {
        this.nombre = nombre;
        this.horario = horario;
    }

    @Override
    public String toString() {
        return nombre + " - Hora: " + horario + " - Edad: PG-13"; // Puedes ajustar según tus necesidades
    }
}