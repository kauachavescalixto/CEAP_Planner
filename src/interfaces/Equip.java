package interfaces;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import database.BD;
import database.DAO;
import main.CeapWindow;
import main.InterfaceManager;
import util.ThirdA;
import util.ThirdB;

public class Equip extends CeapInterface {

	private String titulo;

	GridLayout layout = new GridLayout(0, 1);

	private JPanel main;
	private JPanel secondary;

	private ThirdA thirdA;
	private JScrollPane thirdBScroll;
	private ThirdB thirdB;

	private JLabel name;

	public Equip(String nome) {
		this.titulo = nome;
		setBackground(orange);
		setLayout(null);

		init();

		secondaryPanel();

		mainPanel();

		definir_eventos();

	}

	public void mainPanel() {
		main = new JPanel();

		main.setSize((int) (w / 1.24f), h);
		main.setLocation(w / 6 + 30, 0);
		main.setOpaque(false);
		// main.setBackground(Color.black);

		main.add(name);
		main.add(secondary);

		add(main);
	}

	private void secondaryPanel() {
		secondary = new JPanel();
		secondary.setPreferredSize(new Dimension((int) (w / 1.25f), (int) (h / 1.16f)));
		secondary.setLocation(w / 6 + 30, 0);
		secondary.setOpaque(false);

		thirdA = new ThirdA();
		ThirdBScrollMethod();

		secondary.add(thirdA);
		secondary.add(thirdBScroll);
	}

	public void ThirdBScrollMethod() {
		thirdB = new ThirdB();
		thirdBScroll = new JScrollPane(thirdB);
		thirdBScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		thirdBScroll.getVerticalScrollBar().setUnitIncrement(20);
		thirdBScroll.setViewportView(thirdB);
		thirdBScroll.setBorder(null);
		thirdBScroll.setOpaque(false);
		thirdBScroll.getViewport().setOpaque(false);
		thirdBScroll.setPreferredSize(new Dimension(new Dimension((int) (w / 4f), h)));
	}

	@Override
	public void init() {
		name = new JLabel(titulo, SwingConstants.CENTER);
		name.setFont(HeaderFont);
		name.setForeground(Color.white);

	}

	@Override
	public void definir_eventos() {

		thirdB.adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				thirdA.dtm_emUso.addRow(new String[] {
						
						(String) thirdB.nome.getSelectedItem(),
						thirdB.horario.getText(),
						(String) thirdB.equip.getSelectedItem(),
						thirdB.emails.get(thirdB.nome.getSelectedIndex())
						
				});
				
			}
		});
		
	}

}
