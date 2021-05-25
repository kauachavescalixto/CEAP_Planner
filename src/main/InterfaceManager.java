package main;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import interfaces.Cadastro;
import interfaces.Cadastro_Equip;
import interfaces.Cadastro_User;
import interfaces.Equip;
import interfaces.Estoque;
import interfaces.LogoCeap;
import interfaces.Timeline;

public class InterfaceManager extends JPanel {

	String name;
	/* 		*COLORS*
	 * 
	 * ORANGE - 241, 149, 0
	 * GREEN - 126, 185, 40
	 * BLUE - 3, 175, 230
	 * PURPLE - 90, 80, 157
	 * WHITE - 256, 256, 256
	 * BLACK - 0, 0, 0 
	 * 
	 */
		
	public static final Color orange = new Color(241,149,0);
	public static final Color green = new Color(126, 185, 40);
	public static final Color blue = new Color(3, 175, 230);
	public static final Color purple = new Color(90, 80, 157);
	
	private static int width = CeapWindow.width;
	private static int height = CeapWindow.height;
	
	public static final byte CADASTRO = 0;
	public static final byte CADASTRO_USER = 1;
	public static final byte CADASTRO_EQUIP = 2;
	public static final byte EQUIP = 3;
	public static final byte TIMELINE = 4;
	public static final byte ESTOQUE = 5;
	
	
	private Cadastro cadastro = new Cadastro("Cadastro");
	private Cadastro_User cadastro_user = new Cadastro_User("Cadastro de Usuário");
	private Cadastro_Equip cadastro_equip = new Cadastro_Equip("Cadastro de Equipamento");
	private Equip equip = new Equip("Equipamentos em Uso");
	private Timeline timeline = new Timeline("Linha do Tempo");
	private Estoque estoque = new Estoque("Estoque");
	
	private LogoCeap logoCeap;
	
	private JPanel sub = new JPanel(null);
	

	
	private JPanel buttonsPanel;
	private JButton[] sideButtons = new JButton[] {
			new JButton("Cadastro"),
			new JButton("Equip Em Uso"),
			new JButton("Timeline"),
			new JButton("Estoque")
	};
	private JCheckBox[] checkbox = new JCheckBox[4];
	private ButtonGroup checkboxGrp;
	
	private JPanel sideBar;
	
	
	public InterfaceManager() {
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.black);
		setLayout(null);
		setOpaque(false);

		logoCeap = new LogoCeap(width, height);


		sideBar();
		PanelsInstance();
		sideButtons[0].doClick();
		
	}
	
	public void sideBar() {
	
		GridLayout layout = new GridLayout(0,1);
		layout.setVgap(25);
		buttonsPanel = new JPanel(layout);
		
		buttonsPanel.setSize(width / 6, height / 2 - 100);
		buttonsPanel.setLocation(0, logoCeap.getHeight());

		checkboxGrp = new ButtonGroup();
		
		for (int i = 0; i < sideButtons.length; i++) {
			checkbox[i] = new JCheckBox();
			checkboxGrp.add(checkbox[i]);

			//Teste checkboxes
			/*checkbox[i].setSize(50,50);
			checkbox[i].setLocation(0, i*100);
			add(checkbox[i]);*/
			
			
			buttonsPanel.add(sideButtons[i]);
			sideButtons[i].setFont(new Font("Century Gothic",0,26));
			sideButtons[i].setForeground(Color.white);
			sideButtons[i].setBackground(green);
			sideButtons[i].setBorder(null);
		}
		
		defeventos();

		
		buttonsPanel.setBorder(BorderFactory.createDashedBorder(Color.green, 5f, 5f));
		add(buttonsPanel);
		
		/////////////////////////////////////////////////////////////////////////////////////
		
		sideBar = new JPanel(null);

		logoCeap.setBorder(BorderFactory.createDashedBorder(Color.black));
		
		sideBar.setSize(width / 5, height / 2 + 100);
		sideBar.setLocation(15,15);
		
		
		sideBar.add(logoCeap);
		sideBar.add(buttonsPanel);
		
		sideBar.setOpaque(false);
		add(sideBar);
	}
	
	public void init(int screen) {
		sub.removeAll();
		if(screen == CADASTRO) {
			sub.add(cadastro);
		}
		if(screen == CADASTRO_USER) {
			sub.add(cadastro_user);
		}
		if(screen == CADASTRO_EQUIP) {
			sub.add(cadastro_equip);
		}
		if(screen == EQUIP) {
			sub.add(equip);
		}
		if(screen == TIMELINE) {
			sub.add(timeline);
		}
		if(screen == ESTOQUE) {
			sub.add(estoque);
		}
		repaint();
		revalidate();
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void defeventos() {
		
		/////////////////////BUTTONS////////////////////////
		
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
		sideButtons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click(3);
				init(ESTOQUE);
			}
		});

		////////////////////CHECKBOX///////////////////////
		
		checkbox[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkbox[0].isSelected()) {
					sideButtons[0].setBackground(orange);
				}
			}
		});
		checkbox[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkbox[1].isSelected()) {
					sideButtons[1].setBackground(orange);
				}
			}
		});
		checkbox[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkbox[2].isSelected()) {
					sideButtons[2].setBackground(orange);
				}
			}
		});
		checkbox[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkbox[3].isSelected()) {
					sideButtons[3].setBackground(orange);
				}
			}
		});
	}

	public void PanelsInstance() {

		cadastro.setSize(width, height);
		
		cadastro_user.setSize(width, height);
		
		cadastro_equip.setSize(width, height);
		
		equip.setSize(width, height);
		
		timeline.setSize(width, height);
		
		estoque.setSize(width, height);
		
		
		sub.setBounds(0,0,width, height);
		add(sub);
	}
	
	public void click(int index) {
		for (int i = 0; i < checkbox.length; i++) {
			sideButtons[i].setBackground(green);
		}
		checkbox[index].doClick();
		
	}
	

}
