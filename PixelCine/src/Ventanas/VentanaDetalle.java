package Ventanas;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class VentanaDetalle extends JFrame {
    public VentanaDetalle(String fecha) {
        configurarVentana();
        inicializarComponentes(fecha);
    }

    private void configurarVentana() {
        setTitle("Detalles del día");
        setSize(300, 200);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes(String fecha) {
        JLabel labelFecha = new JLabel("Fecha: " + fecha);
        
        // a;adir las peliculas en una Jlits o como veamos
        
        JTextArea textAreaPeliculas = new JTextArea("Películas disponibles:\n- Película 1\n- Película 2");
        textAreaPeliculas.setEditable(false);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(labelFecha, BorderLayout.NORTH);
        panel.add(new JScrollPane(textAreaPeliculas), BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }
}