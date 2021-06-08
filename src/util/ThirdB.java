package util;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import database.BD;
import database.PreLoadConnect;
import interfaces.CeapInterface;
import main.InterfaceManager;

public class ThirdB extends CeapInterface {

	private JLabel Nome = new JLabel("Nome:");
	private JLabel Hora = new JLabel("Hora:");
	private JLabel Item = new JLabel("Item:");
	
	public JComboBox nome;
	public JTextField horario;
	public JComboBox equip;
	public JButton adicionar;
	public JButton devolvido;
	public JButton notificar;
	private JPanel whiteLineB;
	
	/*private JCheckBox digitaremail;
	private String corpoemail;
	private JTextArea email;
	private JScrollPane emailScroll;*/

	private PreLoadConnect plc = InterfaceManager.plc;
	
	
	public ThirdB() {
		

		setPreferredSize(new Dimension((int) (w / 4f), (int) (h * 1.1f)));
		setOpaque(false);

		

		int GeneralW = (int) (w / 4.5f);
		int GeneralH = (int) (h / 20f);

		int ButtonW = (int) (w / 8f);

		Nome.setFont(LetterFont);
		Nome.setForeground(Color.white);
		
		nome = new JComboBox<String>();
		nome.setPreferredSize(new Dimension(GeneralW, GeneralH));
		nome.setFont(LetterFont);
		nome.setForeground(cinzaClaro);

		Hora.setFont(LetterFont);
		Hora.setForeground(Color.white);
		
		horario = new JTextField();
		horario.setPreferredSize(new Dimension(GeneralW, GeneralH));
		horario.setFont(LetterFont);
		horario.setForeground(cinzaClaro);

		Item.setFont(LetterFont);
		Item.setForeground(Color.white);
		
		equip = new JComboBox<String>();
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

		/*digitaremail = new JCheckBox("Maque para escrever o Email");
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
		emailScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);*/
		
		add(Nome);
		add(nome);
		add(Box.createRigidArea(new Dimension(GeneralW, 5)));
		add(Hora);
		add(horario);
		add(Box.createRigidArea(new Dimension(GeneralW, 5)));
		add(Item);
		add(equip);
		add(Box.createRigidArea(new Dimension(GeneralW, 5)));
		add(adicionar);
		add(Box.createRigidArea(new Dimension(GeneralW, 5)));
		add(whiteLineB);
		add(Box.createRigidArea(new Dimension(GeneralW, 5)));
		add(devolvido);
		add(Box.createRigidArea(new Dimension(GeneralW, 5)));
		add(notificar);
		add(Box.createRigidArea(new Dimension(GeneralW, 5)));
		/*add(digitaremail);
		add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		add(emailScroll);*/

		fill();
	}
	
	
	public void fill() {
	
		for (int i = 0; i < plc.cadDAO.nomes.size(); i++) {
			nome.addItem(plc.cadDAO.nomes.get(i));
		}
		
		for (int i = 0; i < plc.itensDAO.ids.size(); i++) {
			equip.addItem(plc.itensDAO.nomes.get(i));
			
		}
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
