package Ventanas;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.toedter.calendar.JCalendar;

public class VentanaCalendario extends JFrame {
    public VentanaCalendario() {
        setTitle("Ventana con JCalendar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Crear un JCalendar
        JCalendar jCalendar = new JCalendar();
        jCalendar.setTodayButtonVisible(true);

        // Agregar el JCalendar a la ventana
        add(jCalendar);

        setVisible(true);
        
        setResizable(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaCalendario());
    }
}