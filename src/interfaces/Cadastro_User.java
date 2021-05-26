package interfaces;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import main.CeapWindow;

public class Cadastro_User extends CeapInterface {
	
	
	private String nomeKKK;
	
	public Cadastro_User(String nome) {
		this.nomeKKK = nome;
		
		setSize(CeapWindow.width, CeapWindow.height);
		
	}

	@Override
	public void init() {
		
		System.out.println("print de teste");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void definir_eventos() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("");
		frame.getContentPane().add(new Cadastro_User("Cadastro De Usuário"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(CeapWindow.width, CeapWindow.height);
		Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((sd.width - frame.getWidth()) / 2, (sd.height - frame.getHeight()) / 2);
		frame.setVisible(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(""));
	}

}
