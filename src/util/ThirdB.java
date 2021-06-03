package util;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import database.BD;
import database.DAO;
import interfaces.CeapInterface;

public class ThirdB extends CeapInterface {

	public JComboBox nome;
	public JTextField horario;
	public JComboBox equip;
	public JButton adicionar;
	public JButton devolvido;
	public JButton notificar;
	private JPanel whiteLineB;
	private JCheckBox digitaremail;
	private String corpoemail;
	private JTextArea email;
	private JScrollPane emailScroll;

	private DAO dao;

	public ArrayList<String> ids = new ArrayList<String>();
	public ArrayList<String> nomes = new ArrayList<String>();
	public ArrayList<String> emails = new ArrayList<String>();

	public ArrayList<String> itens = new ArrayList<String>();
	public ArrayList<String> predios = new ArrayList<String>();
	
	public ThirdB() {
		PreparedStatement ps;
		ResultSet rs;
		BD bd = new BD();

		if (bd.getConnection()) {

			try {
				dao = new DAO(DAO.COMBOBOX);
				ps = bd.c.prepareStatement("select * from cadastrados");
				rs = ps.executeQuery();

				while (rs.next()) {
					ids.add(rs.getString("id"));
					nomes.add(rs.getString("nome"));
					emails.add(rs.getString("email"));
				}

			} catch (SQLException e) {
				System.out.println("ERROR - thirdB: " + e);
			}
		} else {
			System.out.println("sem conexão com BD");
			System.exit(0);
		}

		try {
			dao = new DAO(DAO.COMBOBOX);
			ps = bd.c.prepareStatement("select * from itens");
			rs = ps.executeQuery();

			while (rs.next()) {
				itens.add(rs.getString("nome"));
				predios.add(rs.getString("predio"));
			}

		} catch (SQLException e) {
			System.out.println("ERROR - thirdB: " + e);
		}

		setPreferredSize(new Dimension((int) (w / 4f), (int) (h * 1.1f)));
		setOpaque(false);

		Color cinzaClaro = new Color(0, 0, 0, 125);

		int GeneralW = (int) (w / 4.5f);
		int GeneralH = (int) (h / 20f);

		int ButtonW = (int) (w / 8f);

		nome = new JComboBox<String>();
		for (int i = 0; i < nomes.size(); i++) {
			nome.addItem(nomes.get(i));
		}
		nome.setPreferredSize(new Dimension(GeneralW, GeneralH));
		nome.setFont(LetterFont);
		nome.setForeground(cinzaClaro);

		horario = new JTextField();
		horario.setPreferredSize(new Dimension(GeneralW, GeneralH));
		horario.setFont(LetterFont);
		horario.setForeground(cinzaClaro);

		equip = new JComboBox<String>();
		for (int i = 0; i < itens.size(); i++) {
			equip.addItem(itens.get(i));
		}
		equip.setPreferredSize(new Dimension(GeneralW, GeneralH));
		equip.setFont(LetterFont);
		equip.setForeground(cinzaClaro);

		adicionar = new JButton("Adicionar");
		adicionar.setPreferredSize(new Dimension(ButtonW, GeneralH));
		adicionar.setFont(LetterFont);
		adicionar.setForeground(cinzaClaro);

		whiteLineB = new JPanel();
		whiteLineB.setPreferredSize(new Dimension(GeneralW, GeneralH / 20));
		whiteLineB.setBackground(Color.white);

		devolvido = new JButton("Devolvido");
		devolvido.setPreferredSize(new Dimension(ButtonW, GeneralH));
		devolvido.setFont(LetterFont);
		devolvido.setForeground(cinzaClaro);

		notificar = new JButton("Notificar");
		notificar.setPreferredSize(new Dimension(ButtonW, GeneralH));
		notificar.setFont(LetterFont);
		notificar.setForeground(cinzaClaro);

		digitaremail = new JCheckBox("Maque para escrever o Email");
		digitaremail.setFont(LetterFont);
		digitaremail.setForeground(Color.white);
		digitaremail.setOpaque(false);

		corpoemail = "Email bla bla";
		email = new JTextArea(corpoemail);
		email.setLineWrap(true);
		// email.setPreferredSize(new Dimension(GeneralW, (int) (h / 4f)));
		email.setFont(LetterFont);
		email.setForeground(cinzaClaro);

		emailScroll = new JScrollPane(email);
		emailScroll.setPreferredSize(new Dimension(GeneralW, (int) (h / 3f)));
		emailScroll.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
		emailScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		add(nome);
		add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		add(horario);
		add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		add(equip);
		add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		add(adicionar);
		add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		add(whiteLineB);
		add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		add(devolvido);
		add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		add(notificar);
		add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		add(digitaremail);
		add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		add(emailScroll);

		

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
