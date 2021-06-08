package interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import database.PreLoadConnect;
import main.CeapWindow;
import main.InterfaceManager;

public class Cadastro_User extends CeapInterface {
	
	
	private String nome = "Cadastro de Usuário";
	
	private JLabel lbnome;
	private JPanel mainPanel;
	
	public JTextField tfNome;
	public JTextField tfEmail;
	public JButton add;
	private JLabel name = new JLabel("Nome:");
	private JLabel email = new JLabel("Email:");
	private JLabel confirmation = new JLabel("");
	
	private PreLoadConnect plc;
	
	public Cadastro_User(int w, int h) {
		this.plc = InterfaceManager.plc;
		setLayout(null);
		setBackground(orange);
		setSize(w,h);
		MainPanel();
		init();
		definir_eventos();
	}

	public void MainPanel() {
		lbnome = new JLabel(nome, SwingConstants.CENTER);
		lbnome.setFont(HeaderFont);
		lbnome.setForeground(Color.white);
		lbnome.setPreferredSize(new Dimension((int) (CeapWindow.width / 1.24f), 50));
		
		mainPanel = new JPanel();
		mainPanel.setSize((int) (CeapWindow.width / 1.24f), (int)(CeapWindow.height / 1.2f));
		//mainPanel.setBackground(Color.cyan);
		mainPanel.setLocation(CeapWindow.width / 6 + 30, 0);
		mainPanel.setOpaque(false);
		;

		mainPanel.add(lbnome);
		mainPanel.add(Box.createRigidArea(new Dimension(w, h / 10)));
		
		add(mainPanel);
	}
	
	
	@Override
	public void init() {
		tfNome = new JTextField();
		tfNome.setPreferredSize(new Dimension(w / 2, h/20));
		tfNome.setFont(LetterFont);
		tfNome.setForeground(cinzaClaro);
		
		tfEmail = new JTextField();
		tfEmail.setPreferredSize(new Dimension(w / 2, h/20));
		tfEmail.setFont(LetterFont);
		tfEmail.setForeground(cinzaClaro);
		
		
		add = new JButton("Adicionar");
		
		
		mainPanel.add(name);
		name.setPreferredSize(new Dimension(w / 5, 50));
		name.setFont(SemiHeaderFont);
		name.setForeground(Color.white);
		
		mainPanel.add(tfNome);
		mainPanel.add(Box.createRigidArea(new Dimension(w, h / 15)));
		mainPanel.add(email);
		email.setPreferredSize(new Dimension(w / 5, 50));
		email.setFont(SemiHeaderFont);
		email.setForeground(Color.white);
		
		mainPanel.add(tfEmail);
		mainPanel.add(Box.createRigidArea(new Dimension(w, h / 15)));

		mainPanel.add(add);
		add.setPreferredSize(new Dimension(w / 8, 25));
		add.setFont(LetterFont);
		add.setForeground(Color.black);
		
		confirmation = new JLabel(nome, SwingConstants.CENTER);
		confirmation.setFont(LetterFont);
		confirmation.setForeground(Color.white);
		confirmation.setPreferredSize(new Dimension((int) (CeapWindow.width / 1.24f), 50));
		mainPanel.add(confirmation);
	}

	@Override
	public void definir_eventos() {
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				plc.cadDAO.cadastrados.setId(0);
				plc.cadDAO.cadastrados.setNome(tfNome.getText());
				plc.cadDAO.cadastrados.setEmail(tfEmail.getText());
				
				plc.cadDAO.atualizar(INCLUSAO);
				confirmation.setText("Adicionado com sucesso!");
				
			}
		});
		
	}

}
