package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import domain.Pelicula;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Logger;



import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class VentanaAdministrador extends JFrame {

    private JPanel contentPane;
    private JFrame VentanaLogin;
    private JFrame ventanaActual;
    VentanaGestionPeliculasAdmin vp = new VentanaGestionPeliculasAdmin();
    VentanaModificarUsuariosAdmin vm = new VentanaModificarUsuariosAdmin();
    VentanaEstadistica ve = new VentanaEstadistica(); // Asegúrate de pasar la lista real de combinaciones

    private static final Logger logger = Logger.getLogger(VentanaDetalle.class.getName());

    public VentanaAdministrador() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);

        JLabel lblTitulo = new JLabel("Ventana Administrador");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 29));
        panel.add(lblTitulo);

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.SOUTH);

        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volverLogin();
                dispose();
                logger.info("Cerrando sesión como Admin");
            }
        });
        panel_1.add(btnSalir);

        JPanel panel_2 = new JPanel();
        contentPane.add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(
                new MigLayout("", "[112.00,left][187.00,center][grow]", "[grow,top][][37.00,center][][grow,bottom]"));

        JButton btnUser = new JButton("Modificar Usuario");
        btnUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vm.setVisible(true);
                dispose();
            }
        });
        panel_2.add(btnUser, "cell 1 1");

        JButton btnPeliculas = new JButton("Gestión Películas");
        btnPeliculas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vp.setVisible(true);
                dispose();
            }
        });
        panel_2.add(btnPeliculas, "cell 1 2");

        JButton btnEstadisticas = new JButton("Estadísticas");
        btnEstadisticas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				ve.setVisible(true);
            }
        });
        panel_2.add(btnEstadisticas, "cell 1 3");

        setLocationRelativeTo(null);
    }

    private void volverLogin() {
        dispose();
        VentanaLogin vl = new VentanaLogin();
        vl.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaAdministrador frame = new VentanaAdministrador();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
