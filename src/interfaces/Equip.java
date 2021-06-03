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

public class Equip extends CeapInterface {

	private static final int w = CeapWindow.width;
	private static final int h = CeapWindow.height;

	private String titulo;

	GridLayout layout = new GridLayout(0, 1);

	private JPanel main;
	private JPanel secondary;
	private JPanel thirdA;
	private JPanel thirdB;

	private JTable tb_emUso;
	private JPanel whiteLineA;
	private JTable tb_devendo;
	private DefaultTableModel dtm_emUso;
	private DefaultTableModel dtm_devendo;
	private JScrollPane scroll_emUso;
	private JScrollPane scroll_devedo;

	public ArrayList<String> ids;
	public ArrayList<String> nomes;
	public ArrayList<String> horarios;
	public ArrayList<String> equipamentos;
	public ArrayList<String> emails;

	private JComboBox nome;
	private JComboBox horario;
	private JComboBox equip;
	private JButton adicionar;
	private JPanel whiteLineB;
	private JButton devolvido;
	private JButton notificar;
	private JCheckBox digitaremail;
	private String corpoemail;
	private JTextArea email;
	private JScrollPane thirdBScroll;
	private JScrollPane emailScroll;

	private JLabel name;

	private DAO dao;
	private BD bd;
	private ResultSet rs;
	private PreparedStatement ps;

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

	private void databank() {
		if (dao.bd.getConnection()) {
			try {
				/*String sql = "select * from " + dao.tb;
				dao.ps = dao.bd.c.prepareStatement(sql);
				dao.rs = dao.ps.executeQuery();*/

				while (rs.next()) {
					try {
						ids.add(rs.getString("id"));
						nomes.add(rs.getString("nome"));
						horarios.add(rs.getString("hora"));
						equipamentos.add(rs.getString("equip"));
						emails.add(rs.getString("email"));

					} catch (SQLException e) {
						System.out.println("ERROR - databank.rs(): " + e);
					}

				}

			} catch (SQLException e) {
				System.out.println("ERROR - databank: " + e);
			}

		}
	}

	private void secondaryPanel() {
		secondary = new JPanel();

		secondary.setPreferredSize(new Dimension((int) (w / 1.25f), (int) (h / 1.16f)));
		secondary.setLocation(w / 6 + 30, 0);
		secondary.setOpaque(false);

		// secondary.setBackground(Color.yellow);
		// thirdA.setBackground(Color.blue);
		// thirdB.setBackground(Color.darkGray);

		ThirdA();
		ThirdB();

		secondary.add(thirdA);
		secondary.add(thirdBScroll);
	}

	public void ThirdA() {
		thirdA = new JPanel();
		thirdA.setPreferredSize(new Dimension((int) (w / 1.9f), h));
		thirdA.setOpaque(false);

		tb_emUso = new JTable(new DefaultTableModel(new String[] { "Nome", "Horario", "Item", "Email" }, 0));
		dtm_emUso = (DefaultTableModel) tb_emUso.getModel();
		dtm_emUso.addRow(new String[] { "Alef", "14:10", "Tudo", "alef@gmail.com.br.net.html" });

		scroll_emUso = new JScrollPane(tb_emUso);
		scroll_emUso.setPreferredSize(new Dimension((int) (w / 1.9f), (int) (h / 2.3f)));

		whiteLineA = new JPanel();
		whiteLineA.setBackground(Color.white);
		whiteLineA.setPreferredSize(new Dimension((int) (w / 2f), 2));

		tb_devendo = new JTable(
				new DefaultTableModel(new String[] { "Nome", "Horario", "Notificado Há", "Item", "Email" }, 0));
		DefaultTableModel dtm_devendo = (DefaultTableModel) tb_devendo.getModel();
		dtm_devendo.addRow(new String[] { "Alef", "14:10", "10 horas", "Tudo", "alefgay@gmail.com.br.net.html" });

		scroll_devedo = new JScrollPane(tb_devendo);
		scroll_devedo.setPreferredSize(new Dimension((int) (w / 1.9f), (int) (h / 2.8f)));

		thirdA.add(scroll_emUso);
		thirdA.add(Box.createRigidArea(new Dimension((int) (w / 5f), 10)));
		thirdA.add(whiteLineA);
		thirdA.add(Box.createRigidArea(new Dimension((int) (w / 5f), 10)));
		thirdA.add(scroll_devedo);

	}

	public void ThirdB() {
		thirdB = new JPanel();
		thirdB.setPreferredSize(new Dimension((int) (w / 4f), (int) (h * 1.1f)));
		thirdB.setOpaque(false);

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

		horario = new JComboBox<String>();
		for (int i = 0; i < horarios.size(); i++) {
			horario.addItem(horarios);
		}
		horario.setPreferredSize(new Dimension(GeneralW, GeneralH));
		horario.setFont(LetterFont);
		horario.setForeground(cinzaClaro);

		equip = new JComboBox<String>();
		equip.addItem(equipamentos);
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

		thirdB.add(nome);
		thirdB.add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		thirdB.add(horario);
		thirdB.add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		thirdB.add(equip);
		thirdB.add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		thirdB.add(adicionar);
		thirdB.add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		thirdB.add(whiteLineB);
		thirdB.add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		thirdB.add(devolvido);
		thirdB.add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		thirdB.add(notificar);
		thirdB.add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		thirdB.add(digitaremail);
		thirdB.add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		thirdB.add(emailScroll);

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

		try {
			bd = new BD();
			if(bd.getConnection()) {
				ps = bd.c.prepareStatement("select * from " + dao.tb);
				rs = ps.executeQuery();
				dao = new DAO();
				databank();
			}else {
				System.out.println("Connection not acquired");
			}
		} catch (SQLException e) {
			System.out.println("ERROR - init: " + e);
		}

	}

	@Override
	public void definir_eventos() {

		adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dtm_emUso.addRow(new String[] { (String) nome.getSelectedItem(), (String) horario.getSelectedItem(),
						(String) equip.getSelectedItem(), listaEmails[nome.getSelectedIndex()] });
			}
		});

	}

}
