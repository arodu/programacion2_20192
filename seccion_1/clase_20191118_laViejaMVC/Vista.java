package clase_20191118;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Vista implements ActionListener{
	Controlador controlador;
	
	JFrame ventana;
	JButton celdas[];
	JLabel info;
	FlowLayout statusBar;
	JMenuItem acerca, salir, nuevo;
	
	public Vista(Controlador c) {
		controlador = c;
		celdas = new JButton[9];
		inicio();
	}
	
	public void inicio() {
		ventana = new JFrame();

		ventana.setSize(300, 340);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.setTitle("La Vieja");
		
		ventana.setLayout(new BorderLayout());
		
		// Centro
		JPanel centro = new JPanel(new GridLayout(3,3));
		
		for(int i=0; i<9; i++) {
			celdas[i] = new JButton("");
			celdas[i].addActionListener(this);
			centro.add(celdas[i]);
		}

		ventana.add(centro, BorderLayout.CENTER);
		
		// status
		statusBar = new FlowLayout(FlowLayout.LEFT);
		JPanel abajo = new JPanel(statusBar);
		info = new JLabel("Jugador 1");
		abajo.add(info);
		
		ventana.add(abajo, BorderLayout.SOUTH);
		
		// Menu
		JMenuBar bar = new JMenuBar();
		
		JMenu juego = new JMenu("Juego");
		bar.add(juego);
		
		nuevo = new JMenuItem("Nuevo Juego");
		nuevo.addActionListener(this);
		juego.add(nuevo);
		
		salir = new JMenuItem("Salir");
		salir.addActionListener(this);
		juego.add(salir);
		
		JMenu ayuda = new JMenu("Ayuda");
		bar.add(ayuda);
		
		acerca = new JMenuItem("Acerca de...");
		acerca.addActionListener(this);
		ayuda.add(acerca);
		
		ventana.add(bar, BorderLayout.NORTH);
		
		ventana.setVisible(true);
	}
	
	public void mostrarTurno(int turno) {
		if(turno == 1) {
			statusBar.setAlignment(FlowLayout.LEFT);
		}else {
			statusBar.setAlignment(FlowLayout.RIGHT);
		}
		info.setText("Jugador "+turno);
	}
	
	public void mostrarJugada(int pos, int turno) {
		if(turno == 1) {
			celdas[pos].setText("X");
		}else {
			celdas[pos].setText("O");
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		for(int i=0; i<9; i++) {
			if(arg0.getSource().equals(celdas[i])) {
				controlador.jugada(i);
			}
		}
		
		if(arg0.getSource().equals(acerca)) {
			JOptionPane.showMessageDialog(ventana, "Este juego fue realizado por el profesor Alberto Rodriguez");
		}
		
		if(arg0.getSource().equals(nuevo)) {
			System.out.println("Nuevo juego");
		}
		
		if(arg0.getSource().equals(salir)) {
			if(JOptionPane.showConfirmDialog(ventana, "¿Esta seguro que desea salir?") == 0) {
				System.exit(0);
			}
		}
	}
	
	
}













