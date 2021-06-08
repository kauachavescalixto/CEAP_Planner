package interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.CeapWindow;
import main.InterfaceManager;

public class Cadastro extends CeapInterface {

	private String nome = "Cadastro";
	private JLabel lbnome;

	private JPanel mainPanel;

	public JCheckBox cadastroUser;
	public JCheckBox cadastroItem;

	public JButton enter = new JButton("Confirma");

	private ButtonGroup grp;

	public Cadastro(int w, int h) {
		setLayout(null);
		setBackground(orange);
		setSize(w,h);
		mainPanel = new JPanel();

		MainPanel();
		init();

		definir_eventos();
	}

	public void MainPanel() {
		lbnome = new JLabel(nome, SwingConstants.CENTER);
		lbnome.setFont(HeaderFont);
		lbnome.setForeground(Color.white);

		mainPanel.setSize((int) (CeapWindow.width / 1.24f), CeapWindow.height);
		// mainPanel.setBackground(Color.cyan);
		mainPanel.setLocation(CeapWindow.width / 6 + 30, 0);
		mainPanel.setOpaque(false);
		//mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		

		mainPanel.add(lbnome);

		add(mainPanel);
	}

	@Override
	public void init() {
		mainPanel.add(Box.createRigidArea(new Dimension(0, h / 5)));

		cadastroUser = new JCheckBox("Cadastro de Usuário");
		cadastroUser.setFont(SemiHeaderFont);
		cadastroUser.setForeground(Color.white);
		cadastroUser.setOpaque(false);
		cadastroUser.setPreferredSize(new Dimension(mainPanel.getWidth(), 50));
		// cadastroUser.setLocation();
		mainPanel.add(cadastroUser);

		cadastroItem = new JCheckBox("Cadastro de Item");
		cadastroItem.setFont(SemiHeaderFont);
		cadastroItem.setForeground(Color.white);
		cadastroItem.setOpaque(false);
		cadastroItem.setPreferredSize(new Dimension(mainPanel.getWidth(), 50));
		mainPanel.add(cadastroItem);

		grp = new ButtonGroup();
		grp.add(cadastroItem);
		grp.add(cadastroUser);

		mainPanel.add(Box.createRigidArea(new Dimension(0, h / 12)));

		enter.setSize(100, 50);
		enter.setFont(LetterFont);
		mainPanel.add(enter);

		mainPanel.add(Box.createRigidArea(new Dimension(0, h / 3)));


	}

	@Override
	public void definir_eventos() {
		// TODO Auto-generated method stub

	}

}
