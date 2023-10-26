package Ventanas;


	
import java.awt.*;
import javax.swing.*;

public class VentanaReserva extends JFrame {
	
    private JButton[][] asientosIzquierda;
    private JButton[][] asientosCentro;
    private JButton[][] asientosDerecha;
    private JFrame ventanaActual, ventanaAnterior;

    public VentanaReserva(JFrame va) {
    	ventanaActual = this;
    	ventanaAnterior = va;
        setTitle("Reserva de Asientos de Cine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelIzquierda = new JPanel(new GridLayout(10, 2));
        JPanel panelCentro = new JPanel(new GridLayout(20, 2));
        JPanel panelDerecha = new JPanel(new GridLayout(10, 2));

        asientosIzquierda = new JButton[10][2];
        asientosCentro = new JButton[20][2];
        asientosDerecha = new JButton[10][2];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 2; j++) {
                JButton asiento = new JButton("Asiento " + (i * 2 + j + 1));
                asientosIzquierda[i][j] = asiento;
                panelIzquierda.add(asiento);
            }
        }

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 2; j++) {
                JButton asiento = new JButton("Asiento " + (i * 2 + j + 1));
                asientosCentro[i][j] = asiento;
                panelCentro.add(asiento);
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 2; j++) {
                JButton asiento = new JButton("Asiento " + (i * 2 + j + 1));
                asientosDerecha[i][j] = asiento;
                panelDerecha.add(asiento);
            }
        }
        getContentPane().setLayout(new BorderLayout(0, 0));

        getContentPane().add(panelIzquierda);
        getContentPane().add(panelCentro);
        getContentPane().add(panelDerecha);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    
}

