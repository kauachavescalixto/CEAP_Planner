package interfaces;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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

	public String[] listaNomes;
	public String[] listaHorario;
	public String[] listaEquip;
	private JComboBox nome;
	private JComboBox horario;
	private JComboBox equip;
	private JButton adicionar;
	private JPanel whiteLine;
	private JButton devolvido;
	private JButton notificar;
	private JCheckBox digitaremail;
	private String corpoemail;
	private JTextArea email;
	private JScrollPane thirdBScroll;
	private JScrollPane emailScroll;

	private JLabel name;

	public Equip(String nome) {
		this.titulo = nome;
		setBackground(InterfaceManager.orange);
		setLayout(null);

		init();

		secondaryPanel();

		mainPanel();

	}

	public void mainPanel() {
		main = new JPanel();

		main.setSize((int) (w / 1.24f), h);
		main.setLocation(w / 6 + 30, 0);
		main.setBackground(Color.black);

		main.add(name);
		main.add(secondary);

		add(main);
	}

	private void secondaryPanel() {
		secondary = new JPanel();

		secondary.setPreferredSize(new Dimension((int) (w / 1.25f), (int) (h / 1.16f)));
		secondary.setLocation(w / 6 + 30, 0);

		secondary.setBackground(Color.yellow);

		ThirdA();
		ThirdB();

		secondary.add(thirdA);
		secondary.add(thirdBScroll);
	}

	public void ThirdA() {
		thirdA = new JPanel();
		thirdA.setPreferredSize(new Dimension((int) (w / 1.85f), h));
		thirdA.setBackground(Color.blue);

	}

	public void ThirdB() {
		thirdB = new JPanel();
		thirdB.setPreferredSize(new Dimension((int) (w / 4f), h * 2));
		thirdB.setBackground(Color.darkGray);
		
		/* teste */
		listaNomes = new String[] { "Alefgay" };
		listaHorario = new String[] { "14:10" };
		listaEquip = new String[] { "chave da quadra" };

		Color cinzaClaro = new Color(0,0,0,125);
		
		int GeneralW = (int) (w / 4.5f);
		int GeneralH = (int) (h / 20f);

		int ButtonW = (int) (w / 8f);


		
		nome = new JComboBox<String>(listaNomes);
		nome.setPreferredSize(new Dimension(GeneralW, GeneralH));
		nome.setFont(LetterFont);
		nome.setForeground(cinzaClaro);
		
		horario = new JComboBox<String>(listaHorario);
		horario.setPreferredSize(new Dimension(GeneralW, GeneralH));
		horario.setFont(LetterFont);
		horario.setForeground(cinzaClaro);
		
		equip = new JComboBox<String>(listaEquip);
		equip.setPreferredSize(new Dimension(GeneralW, GeneralH));
		equip.setFont(LetterFont);
		equip.setForeground(cinzaClaro);
		
		adicionar = new JButton("Adicionar");
		adicionar.setPreferredSize(new Dimension(ButtonW, GeneralH));
		adicionar.setFont(LetterFont);
		adicionar.setForeground(cinzaClaro);
		
		whiteLine = new JPanel();
		whiteLine.setPreferredSize(new Dimension(GeneralW, GeneralH / 20));
		whiteLine.setBackground(Color.white);

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
		digitaremail.setForeground(cinzaClaro);
		
		corpoemail = "Email bla bla";
		email = new JTextArea(corpoemail);
		email.setPreferredSize(new Dimension(GeneralW, (int) (h / 4f)));
		email.setFont(LetterFont);
		email.setForeground(cinzaClaro);
		
		thirdB.add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		thirdB.add(nome);
		thirdB.add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		thirdB.add(horario);
		thirdB.add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		thirdB.add(equip);
		thirdB.add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		thirdB.add(adicionar);
		thirdB.add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		thirdB.add(whiteLine);
		thirdB.add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		thirdB.add(devolvido);
		thirdB.add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		thirdB.add(notificar);
		thirdB.add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		thirdB.add(digitaremail);
		thirdB.add(Box.createRigidArea(new Dimension(GeneralW, 10)));
		thirdB.add(email);

		thirdBScroll = new JScrollPane(thirdB);
		thirdBScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		thirdBScroll.getVerticalScrollBar().setUnitIncrement(20);
		thirdBScroll.setViewportView(thirdB);
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
		// TODO Auto-generated method stub

	}

}
