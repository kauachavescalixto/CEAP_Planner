package main;

import java.awt.Toolkit;

import javax.swing.JFrame;

import interfaces.InterfaceManager;

public class CeapWindow extends JFrame{

	public CeapWindow() {
		
		setTitle("CEAP Planer Alpha 0.1");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(""));
		setContentPane(new InterfaceManager(1152,648));
		pack();
		setLocationRelativeTo(null);
		
	
	}
	
}
