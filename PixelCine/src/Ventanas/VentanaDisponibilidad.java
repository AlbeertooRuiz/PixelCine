package Ventanas;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaDisponibilidad {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaDisponibilidad window = new VentanaDisponibilidad();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaDisponibilidad() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table = new JTable();
		frame.getContentPane().add(table, BorderLayout.CENTER);

		String[] columnNames = { "Horas", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);

		// Agregar horas al modelo
		for (int hora = 12; hora < 22; hora += 2) {
			model.addRow(new Object[] { String.format("%02d:00 - %02d:00", hora, hora + 2) });
		}

		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane);

		frame.setVisible(true);
	}

}
