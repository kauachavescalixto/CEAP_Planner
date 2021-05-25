package interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.CeapWindow;
import main.InterfaceManager;

public class Cadastro extends CeapInterface{

	private String nome;
	private JLabel lbnome;

	private JPanel mainPanel;
	
	
	public Cadastro(String nome) {
		this.nome = nome;
		setBackground(Color.green);
		setLayout(null);
		
		mainPanel = new JPanel();
		
		init();
		MainPanel();
		
		definir_eventos();
	}

	public void MainPanel() {
		GridLayout grid1 = new GridLayout(0,1);
		mainPanel.setSize((int)(CeapWindow.width/1.24f), CeapWindow.height);
		mainPanel.setBackground(Color.cyan);
		mainPanel.setLocation(CeapWindow.width/6 + 30, 0);
		
		mainPanel.add(lbnome);
		
		add(mainPanel);
	}
	
	@Override
	public void init() {
		lbnome = new JLabel(nome, SwingConstants.CENTER);
		lbnome.setFont(HeaderFont);
		lbnome.setForeground(Color.white);
		
	}

	@Override
	public void definir_eventos() {
		// TODO Auto-generated method stub
		
	}

	

}
