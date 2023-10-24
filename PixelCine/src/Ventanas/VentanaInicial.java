package Ventanas;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Datos.BD;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font; 

public class VentanaInicial extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldUsuario;
    private JTextField textFieldContrasenia;
    private JFrame ventanaActual;
    Connection con;

    public VentanaInicial() {
        ventanaActual = this;
        ventanaActual.setSize(400, 400);
        ventanaActual.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10)); 

        con = BD.initBD("pixelcine.db");
        BD.crearTablaCliente(con);
        
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panelNorte = new JPanel();
        contentPane.add(panelNorte, BorderLayout.NORTH);

        JLabel lblBienvenido = new JLabel("¡Bienvenido a PixelCine!");
        lblBienvenido.setFont(new Font("Arial", Font.BOLD, 20)); 
        panelNorte.add(lblBienvenido);

        JPanel panelSur = new JPanel();
        contentPane.add(panelSur, BorderLayout.SOUTH);
        panelSur.setLayout(new GridLayout(2, 1, 0, 10)); 

        JPanel panelArriba = new JPanel();
        panelSur.add(panelArriba);

        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        panelArriba.add(btnIniciarSesion);

        JPanel panelAbajo = new JPanel();
        panelSur.add(panelAbajo);

        JLabel lblRegistro = new JLabel("¿No tienes cuenta?");
        panelAbajo.add(lblRegistro);

        JButton btnRegistro = new JButton("Registrarme");
        panelAbajo.add(btnRegistro);

        JPanel panelOeste = new JPanel();
        contentPane.add(panelOeste, BorderLayout.WEST);

        JPanel panelEste = new JPanel();
        contentPane.add(panelEste, BorderLayout.EAST);

        JPanel panelCentro = new JPanel();
        contentPane.add(panelCentro, BorderLayout.CENTER);
        panelCentro.setLayout(new GridLayout(2, 2, 10, 10));

        JLabel lblUsuario = new JLabel("Usuario:");
        panelCentro.add(lblUsuario);
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 16));

        textFieldUsuario = new JTextField();
        panelCentro.add(textFieldUsuario);
        textFieldUsuario.setColumns(10);

        JLabel lblContrasenia = new JLabel("Contraseña:");
        panelCentro.add(lblContrasenia);
        lblContrasenia.setFont(new Font("Arial", Font.PLAIN, 16)); 

        textFieldContrasenia = new JTextField();
        panelCentro.add(textFieldContrasenia);
        textFieldContrasenia.setColumns(10);
    }
}
