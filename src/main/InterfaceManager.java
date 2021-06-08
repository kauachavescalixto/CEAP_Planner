package main;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.BD;
import database.PreLoadConnect;
import interfaces.Cadastro;
import interfaces.Cadastro_Equip;
import interfaces.Cadastro_User;
import interfaces.CeapInterface;
import interfaces.Equip;
import interfaces.Estoque;
import interfaces.LogoCeap;
import interfaces.Timeline;

public class InterfaceManager extends CeapInterface {

	String name;
	/*
	 * *COLORS*
	 * 
	 * ORANGE - 241, 149, 0 GREEN - 126, 185, 40 BLUE - 3, 175, 230 PURPLE - 90, 80,
	 * 157 WHITE - 256, 256, 256 BLACK - 0, 0, 0
	 * 
	 */

	private static int width = CeapWindow.width;
	private static int height = CeapWindow.height;

	public static final byte CADASTRO = 0;
	public static final byte CADASTRO_USER = 1;
	public static final byte CADASTRO_EQUIP = 2;
	public static final byte EQUIP = 3;
	public static final byte TIMELINE = 4;
	public static final byte ESTOQUE = 5;

	private Cadastro cadastro;
	private Cadastro_User cadastro_user;
	private Cadastro_Equip cadastro_equip;
	/*
	 * private Equip equip = new Equip("Equipamentos em Uso",0,0); private Timeline
	 * timeline = new Timeline("Linha do Tempo"); private Estoque estoque = new
	 * Estoque("Estoque");
	 */

	private LogoCeap logoCeap;

	private JPanel sub = new JPanel(null);

	private JPanel buttonsPanel;
	private JButton[] sideButtons = new JButton[] { new JButton("Cadastro"), new JButton("Equip Em Uso"),
			new JButton("Timeline") };
	private JCheckBox[] checkbox = new JCheckBox[sideButtons.length];
	private ButtonGroup checkboxGrp;

	private JPanel sideBar;

	public static PreLoadConnect plc = new PreLoadConnect();

	public InterfaceManager() {
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.orange);
		setLayout(null);

		logoCeap = new LogoCeap(width, height);

		cadastro = new Cadastro(width, height);
		cadastro_user = new Cadastro_User(width, height);
		cadastro_equip = new Cadastro_Equip(width, height);

		sideBar();

		sub.setBounds(0, 0, width, height);
		add(sub);

		sideButtons[0].doClick();
	}

	public void sideBar() {

		GridLayout layout = new GridLayout(0, 1);
		layout.setVgap(25);
		buttonsPanel = new JPanel(layout);

		buttonsPanel.setSize(width / 6, height / 3);
		buttonsPanel.setLocation(0, logoCeap.getHeight());
		buttonsPanel.setBackground(Color.white);
		checkboxGrp = new ButtonGroup();

		for (int i = 0; i < sideButtons.length; i++) {
			checkbox[i] = new JCheckBox();
			checkboxGrp.add(checkbox[i]);

			// Teste checkboxes
			/*
			 * checkbox[i].setSize(50,50); checkbox[i].setLocation(0, i*100);
			 * add(checkbox[i]);
			 */

			buttonsPanel.add(sideButtons[i]);
			sideButtons[i].setFont(new Font("Century Gothic", 0, 26));
			sideButtons[i].setForeground(Color.white);
			sideButtons[i].setBackground(green);
			sideButtons[i].setBorder(BorderFactory.createLineBorder(Color.white, 6));
		}

		definir_eventos();

		add(buttonsPanel);

		/////////////////////////////////////////////////////////////////////////////////////

		sideBar = new JPanel(null);

		// logoCeap.setBorder(BorderFactory.createDashedBorder(Color.black));

		sideBar.setSize(width / 5, height / 2 + 100);
		sideBar.setLocation(15, 15);

		sideBar.add(logoCeap);
		sideBar.add(buttonsPanel);

		sideBar.setOpaque(false);
		add(sideBar);
	}

	public void init(int screen) {
		if(plc.cadDAO.bd.c == null) {
			
		}else {
		//// Closing connections with database
			plc.cadDAO.closeConnections();
			plc.itensDAO.closeConnections();
			plc.devDAO.closeConnections();
			plc.usoDAO.closeConnections();
			plc.timelineDAO.closeConnections();
		}
		
		//// Cleaning arrays;
		plc.cadDAO.cleanArrays();
		plc.itensDAO.cleanArrays();
		plc.devDAO.cleanArrays();
		plc.usoDAO.cleanArrays();
		plc.timelineDAO.cleanArrays();

		//// Filling arrays;
		plc.cadDAO.fillArrays();
		plc.itensDAO.fillArrays();
		plc.devDAO.fillArrays();
		plc.usoDAO.fillArrays();
		plc.timelineDAO.fillArrays();


		sub.removeAll();
		if (screen == CADASTRO) {
			sub.add(cadastro);
		}
		if (screen == CADASTRO_USER) {
			sub.add(cadastro_user);
		}
		if (screen == CADASTRO_EQUIP) {
			sub.add(cadastro_equip);
		}
		if (screen == EQUIP) {
			sub.add(new Equip(width, height));
		}
		if (screen == TIMELINE) {
			sub.add(new Timeline(width, height));
		}
		/*
		 * if (screen == ESTOQUE) { sub.add(estoque); }
		 */
		repaint();
		revalidate();
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void click(int index) {
		for (int i = 0; i < checkbox.length; i++) {
			sideButtons[i].setBackground(green);
		}
		checkbox[index].doClick();

	}

	public float sideBarGetWidth() {
		return buttonsPanel.getWidth();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void definir_eventos() {

		///////////////////// BUTTONS////////////////////////

		sideButtons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click(0);
				init(CADASTRO);
			}
		});
		sideButtons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click(1);
				init(EQUIP);
			}
		});
		sideButtons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click(2);
				init(TIMELINE);
			}
		});

		//////////////////// CHECKBOX///////////////////////

		checkbox[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkbox[0].isSelected()) {
					sideButtons[0].setBackground(orange);
				}
			}
		});
		checkbox[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkbox[1].isSelected()) {
					sideButtons[1].setBackground(orange);
				}
			}
		});
		checkbox[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkbox[2].isSelected()) {
					sideButtons[2].setBackground(orange);
				}
			}
		});

		cadastro.enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cadastro.cadastroItem.isSelected()) {
					init(CADASTRO_EQUIP);
					repaint();

				} else if (cadastro.cadastroUser.isSelected()) {
					init(CADASTRO_USER);
					repaint();
				}
			}
		});

	}
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.white);
		g.fillRect(15, 350, 192, 270);
	}
	
}
