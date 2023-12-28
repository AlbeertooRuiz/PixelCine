package Ventanas;

import javax.swing.*;

import Datos.Cliente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VentanaCalendario extends JFrame {
	
    private JButton[][] botonesDias;
    private JComboBox<String> comboMes;
    private JLabel labelAnio;
    private String fechaHoy;

    public VentanaCalendario(Cliente c) {
    	Cliente cliente = c;
        configurarVentana();
        inicializarComponentes(cliente);
    }

    private void configurarVentana() {
        setTitle("Elige el dia que quieres venir a PixelCine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes(Cliente c) {
        JPanel panelSuperior = new JPanel(new FlowLayout());
        comboMes = new JComboBox<>(new DateFormatSymbols().getMonths());
        comboMes.addActionListener(e -> actualizarBotones());
        
        //Codigo para quitar el Undecimber del .getMonths 
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboMes.getModel();
        model.removeElementAt(model.getSize() - 1);
        
        labelAnio = new JLabel();

        panelSuperior.add(comboMes);
        panelSuperior.add(labelAnio);


        JPanel panelCentral = new JPanel(new GridLayout(6, 7));
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
                        abrirVentanaDetalle(fila, columna, c);
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

    private void abrirVentanaDetalle(int fila, int columna, Cliente cliente) {
        int diaSeleccionado = Integer.parseInt(botonesDias[fila][columna].getText());
        int mesSeleccionado = comboMes.getSelectedIndex();
        int anioSeleccionado = Calendar.getInstance().get(Calendar.YEAR);

        String fecha = String.format("%02d-%02d-%04d", diaSeleccionado, mesSeleccionado + 1, anioSeleccionado);
        LocalDate fechaHoy = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaComparar = LocalDate.parse(fecha, formatter);
        if (fechaComparar.isBefore(fechaHoy)) {
        	JOptionPane.showMessageDialog(null, "El dia seleccionado es anterior a hoy", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
        	VentanaDetalle ventanaDetalle = new VentanaDetalle(fecha, cliente);
        }
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

            // Si es el último día antes de un salto de fila, poner en rojo
            if (columna == 0 && i != 1) {
                botonesDias[fila - 1][6].setForeground(Color.RED);
            }

            columna++;

            // Si la columna alcanza 7, avanza a la siguiente fila
            if (columna == 7) {
                columna = 0;
                fila++;
            }
        }

    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new VentanaCalendario());
//    }
}
