package Ventanas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class VentanaGestionPeliculasAdmin extends JFrame {
	private static final Logger logger = Logger.getLogger(VentanaDetalle.class.getName());
    private JPanel contentPane;
    private JTable tablaPeliculas;
    private DefaultTableModel modeloTabla;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaGestionPeliculasAdmin frame = new VentanaGestionPeliculasAdmin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VentanaGestionPeliculasAdmin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 691, 404);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        String[] columnas = {"Nombre", "Duración", "Categoría", "Asientos Disponibles"};
        modeloTabla = new DefaultTableModel(columnas, 0);


        tablaPeliculas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaPeliculas);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JButton btnEliminar = new JButton("Eliminar Película(s)");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarPeliculasSeleccionadas();
            }
        });


        JButton btnAnadir = new JButton("Añadir Película");
        btnAnadir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentanaAnadirPelicula();
            }
        });
        
        JButton btnActualizar = new JButton("Actualizar JTable");
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarDatosDesdeCSV("Peliculas.csv");
            }
        });
     // Botón para volver a la ventana del Administrador
        JButton btnVolverAdmin = new JButton("Volver ");
        btnVolverAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volverAVentanaAdmin();
            }
        });

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnEliminar);
        panelBotones.add(btnAnadir);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnVolverAdmin);
        
        contentPane.add(panelBotones, BorderLayout.SOUTH);

        // Cargar datos desde el CSV al iniciar la ventana
        cargarDatosDesdeCSV("Peliculas.csv");
        setLocationRelativeTo(null);
    }

    private void cargarDatosDesdeCSV(String rutaArchivo) {
        // Limpiar el modelo antes de cargar nuevos datos
        modeloTabla.setRowCount(0);

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datosPelicula = linea.split(",");
                if (datosPelicula.length >= 4) {
                    // Agregar fila al modelo de la tabla
                    modeloTabla.addRow(new Object[]{datosPelicula[0], datosPelicula[1], datosPelicula[2], datosPelicula[3]});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void eliminarPeliculasSeleccionadas() {
        int[] filasSeleccionadas = tablaPeliculas.getSelectedRows();
        if (filasSeleccionadas.length > 0) {
            DefaultTableModel model = (DefaultTableModel) tablaPeliculas.getModel();
            String rutaArchivo = "Peliculas.csv";
            List<String> filasAEliminar = new ArrayList<>();

            for (int fila : filasSeleccionadas) {
                String nombrePelicula = (String) model.getValueAt(fila, 0);
                filasAEliminar.add(nombrePelicula);
            }

            eliminarFilasDelCSV(filasAEliminar, rutaArchivo);
            cargarDatosDesdeCSV(rutaArchivo);

            model.fireTableDataChanged();
            tablaPeliculas.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona al menos una película para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarFilasDelCSV(List<String> filasAEliminar, String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
             BufferedWriter fw = new BufferedWriter(new FileWriter("temp.csv"))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                String nombrePelicula = linea.split(",")[0].trim();
                if (!filasAEliminar.contains(nombrePelicula)) {
                    fw.write(linea + System.lineSeparator());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Copiar contenido del archivo temporal al archivo original
        try (BufferedReader br = new BufferedReader(new FileReader("temp.csv"));
             BufferedWriter fw = new BufferedWriter(new FileWriter(rutaArchivo))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                fw.write(linea + System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        File archivoTemporal = new File("temp.csv");
        if (archivoTemporal.delete()) {
            logger.info("Película(s) eliminada(s) correctamente");
        } else {
            logger.warning("Error al eliminar la(s) película(s)");
        }
    }
    private void abrirVentanaAnadirPelicula() {
        VentanaAnyadirPeliculasAdmin ventanaAnadirPelicula = new VentanaAnyadirPeliculasAdmin();
        ventanaAnadirPelicula.setVisible(true);
    }
    private void volverAVentanaAdmin() {
        dispose();  
        VentanaAdministrador ventanaAdmin = new VentanaAdministrador();
        ventanaAdmin.setVisible(true);
    }
}

