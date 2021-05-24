package interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class InterfaceManager extends JPanel {

	private static int width;
	private static int height;
	
	public static final byte CADASTRO = 0;
	public static final byte CADASTRO_USER = 1;
	public static final byte CADASTRO_EQUIP = 2;
	public static final byte EQUIP = 3;
	public static final byte TIMELINE = 4;
	public static final byte ESTOQUE = 5;
	
	
	private JButton[] sideButtons;
	private JPanel buttonsPanel;
	
	
	
	private Cadastro cadastro = new Cadastro();
	private Cadastro_User cadastro_user = new Cadastro_User();
	private Cadastro_Equip cadastro_equip = new Cadastro_Equip();
	private Equip equip = new Equip();
	private Timeline timeline = new Timeline();
	private Estoque estoque = new Estoque();
	
	
	
	public InterfaceManager(int width, int height) {
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.black);
		setLayout(null);
		
		this.width = width;
		this.height = height;
		
		PanelsInstance();
		init(CADASTRO);
		sideBar();
	}
	
	public void sideBar() {
		buttonsPanel = new JPanel(new GridLayout(0,1));
		buttonsPanel.setBackground(Color.blue);
		buttonsPanel.setSize(100,200);
		
		sideButtons = new JButton[6];
		for (int i = 0; i < sideButtons.length; i++) {
			sideButtons[i] = new JButton(""+i);
			
			buttonsPanel.add(sideButtons[i]);
		}
		defeventos();
		
		add(buttonsPanel);
		repaint();
	}
	
	public void defeventos() {
		sideButtons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				init(CADASTRO);
			}
		});
		sideButtons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				init(CADASTRO_USER);
			}
		});
		sideButtons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				init(CADASTRO_EQUIP);
			}
		});
		sideButtons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				init(EQUIP);
			}
		});
		sideButtons[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				init(TIMELINE);
			}
		});
		sideButtons[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				init(ESTOQUE);
			}
		});
		
	}

	public void PanelsInstance() {
		cadastro.setSize(width, height);
		add(cadastro);
		
		cadastro_user.setSize(width, height);
		add(cadastro_user);
		
		cadastro_equip.setSize(width, height);
		add(cadastro_equip);
		
		equip.setSize(width, height);
		add(equip);
		
		timeline.setSize(width, height);
		add(timeline);
		
		estoque.setSize(width, height);
		add(estoque);
	}
	
	public void init(int screen) {
		if(screen == CADASTRO) {
			setVisibleFalse();
			cadastro.setVisible(true);
		}
		if(screen == CADASTRO_USER) {
			setVisibleFalse();
			cadastro_user.setVisible(true);
		}
		if(screen == CADASTRO_EQUIP) {
			setVisibleFalse();
			cadastro_equip.setVisible(true);
		}
		if(screen == EQUIP) {
			setVisibleFalse();
			equip.setVisible(true);
		}
		if(screen == TIMELINE) {
			setVisibleFalse();
			timeline.setVisible(true);
		}
		if(screen == ESTOQUE) {
			setVisibleFalse();
			estoque.setVisible(true);
		}
	}
	
	public void setVisibleFalse() {
		cadastro.setVisible(false);
		cadastro_user.setVisible(false);
		cadastro_equip.setVisible(false);
		equip.setVisible(false);
		timeline.setVisible(false);
		estoque.setVisible(false);
	}
	
	
}
