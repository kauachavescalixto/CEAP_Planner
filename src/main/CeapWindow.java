package main;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class CeapWindow extends JFrame{
	
	//1152, 648
	public static final int width = 1152;
	public static final int height = 648;
	
	public CeapWindow(String version) {

		
		setTitle("CEAP Planer "+version);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(""));
		setContentPane(new InterfaceManager());
		pack();
		setLocationRelativeTo(null);
		
	
	}
	
}
