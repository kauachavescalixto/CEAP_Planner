package interfaces;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Estoque extends CeapInterface {

	private String nome;
	
	private JLabel name = new JLabel("");
	private JPanel main;
	
	
	
	public Estoque(String nome) {
		this.nome = nome;
		setBackground(orange);
		
		MainPanel();
		
		init();		
	}
	

	public void MainPanel() {
		name = new JLabel(nome, SwingConstants.CENTER);
		name.setFont(HeaderFont);
		name.setForeground(Color.white);
		
		main = new JPanel();

		main.setSize((int) (w / 1.24f), h);
		main.setLocation(w / 6 + 30, 0);
		main.setOpaque(false);
		//main.setBackground(Color.black);

		main.add(name);

		add(main);
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void definir_eventos() {
		// TODO Auto-generated method stub

	}

}
