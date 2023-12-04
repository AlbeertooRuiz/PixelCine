package Ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

public class VentanaCalendario extends JFrame {
    private JButton[][] botonesDias;
    private JComboBox<String> comboMes;
    private JLabel labelAnio;

    public VentanaCalendario() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Calendario Simple");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        JPanel panelSuperior = new JPanel(new FlowLayout());
        comboMes = new JComboBox<>(new DateFormatSymbols().getMonths());
        
        //Codigo para quitar el Undecimber del .getMonths 
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboMes.getModel();
        model.removeElementAt(model.getSize() - 1);
        
        labelAnio = new JLabel();
        JButton botonActualizar = new JButton("Actualizar");
        botonActualizar.addActionListener(e -> actualizarBotones());

        panelSuperior.add(comboMes);
        panelSuperior.add(labelAnio);
        panelSuperior.add(botonActualizar);

        JPanel panelCentral = new JPanel(new GridLayout(7, 7));
        botonesDias = new JButton[6][7];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                botonesDias[i][j] = new JButton();
                botonesDias[i][j].setEnabled(false);
                panelCentral.add(botonesDias[i][j]);

                final int fila = i;
                final int columna = j;
                botonesDias[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        abrirVentanaDetalle(fila, columna);
                    }
                });
            }
        }

        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);

        setVisible(true);
        setResizable(false);

        actualizarBotones();
    }

    private void abrirVentanaDetalle(int fila, int columna) {
        int diaSeleccionado = Integer.parseInt(botonesDias[fila][columna].getText());
        int mesSeleccionado = comboMes.getSelectedIndex();
        int anioSeleccionado = Calendar.getInstance().get(Calendar.YEAR);

        String fecha = String.format("%02d-%02d-%04d", diaSeleccionado, mesSeleccionado + 1, anioSeleccionado);
        VentanaDetalle ventanaDetalle = new VentanaDetalle(fecha);
    }

    private void actualizarBotones() {
        int mesIndex = comboMes.getSelectedIndex();
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);

        Calendar calendar = Calendar.getInstance();
        calendar.set(anioActual, mesIndex, 1);

        int primerDiaSemana = calendar.get(Calendar.DAY_OF_WEEK);
        int diasEnMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (JButton[] fila : botonesDias) {
            for (JButton boton : fila) {
                boton.setText("");
                boton.setEnabled(false);
            }
        }

        labelAnio.setText(String.valueOf(anioActual));

        int fila = 0;
        int columna = (primerDiaSemana - Calendar.SUNDAY + 7) % 7;

        for (int i = 1; i <= diasEnMes; i++) {
            botonesDias[fila][columna].setText(String.valueOf(i));
            botonesDias[fila][columna].setEnabled(true);

            if (columna == 6) {
                fila++;
                columna = 0;
            } else {
                columna++;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaCalendario());
    }
}

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