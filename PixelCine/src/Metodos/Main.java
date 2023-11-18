package Metodos;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Ventanas.VentanaLogin;
import Ventanas.VentanaReserva;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(() -> {
		    JFrame ventanaReserva = new VentanaReserva();
		    ventanaReserva.setVisible(true);
		VentanaLogin vi=new VentanaLogin();
		vi.setVisible(true);
		
		 });
	
	}
}	