package interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import database.PreLoadConnect;
import main.CeapWindow;
import main.InterfaceManager;

public class Cadastro_Equip extends CeapInterface {

	private String nome = "Cadastro de item";

	private JLabel lbnome = new JLabel("");
	private JPanel mainPanel;

	private JLabel lbname = new JLabel("Nome:");
	private JLabel tipo = new JLabel("Tipo:");
	private JLabel predio = new JLabel("Prédio:");
	public JTextField tf = new JTextField();
	/*public JCheckBox ck_chave = new JCheckBox("Chave");
	public JCheckBox ck_equip = new JCheckBox("Equipamento");
	private ButtonGroup grp = new ButtonGroup();
	private String[] predios = new String[] {"Predio X", "Predio Y"};
	public JComboBox predio_cb = new JComboBox();*/
	public JButton add = new JButton("Adicionar");

	private JLabel confirmation = new JLabel("");
	
	private PreLoadConnect plc;
	
	public Cadastro_Equip(int w, int h) {
		this.nome = nome;
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

		mainPanel = new JPanel();
		mainPanel.setSize((int) (CeapWindow.width / 1.24f), CeapWindow.height);
		//mainPanel.setBackground(Color.cyan);
		mainPanel.setLocation(CeapWindow.width / 6 + 30, 0);
		mainPanel.setOpaque(false);

		mainPanel.add(lbnome);
		mainPanel.add(Box.createRigidArea(new Dimension(w, h / 10)));

		add(mainPanel);
	}

	@Override
	public void init() {

		mainPanel.add(lbname);
		lbname.setFont(SemiHeaderFont);
		lbname.setForeground(Color.white);
		lbname.setPreferredSize(new Dimension(w / 5, 50));

		mainPanel.add(tf);
		tf.setFont(LetterFont);
		tf.setForeground(cinzaClaro);
		tf.setPreferredSize(new Dimension(w / 2, h / 20));
		mainPanel.add(Box.createRigidArea(new Dimension(w, h / 15)));
		
		/*mainPanel.add(tipo);
		tipo.setFont(SemiHeaderFont);
		tipo.setForeground(Color.white);
		tipo.setPreferredSize(new Dimension(w / 5, 50));

		mainPanel.add(ck_chave);
		ck_chave.setFont(LetterFont);
		ck_chave.setForeground(Color.white);
		ck_chave.setPreferredSize(new Dimension(w / 5, 50));
		ck_chave.setOpaque(false);
		
		mainPanel.add(ck_equip);
		ck_equip.setFont(LetterFont);
		ck_equip.setForeground(Color.white);
		ck_equip.setPreferredSize(new Dimension(w / 5, 50));
		ck_equip.setOpaque(false);
		mainPanel.add(Box.createRigidArea(new Dimension(w, h / 15)));
		grp.add(ck_chave);
		grp.add(ck_equip);

		mainPanel.add(predio);
		predio.setFont(SemiHeaderFont);
		predio.setForeground(Color.white);
		predio.setPreferredSize(new Dimension(w / 5, 50));

		mainPanel.add(predio_cb);
		predio_cb.setFont(LetterFont);
		predio_cb.setForeground(Color.black);
		predio_cb.setPreferredSize(new Dimension(w / 2, h / 20));
		
		for (int i = 0; i < predios.length; i++) {
			predio_cb.addItem(predios[i]);
		}*/
		
		mainPanel.add(Box.createRigidArea(new Dimension(w, h / 12)));
		mainPanel.add(add);
		add.setFont(LetterFont);
		add.setForeground(Color.black);
		add.setPreferredSize(new Dimension(w / 8, 25));
		
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
				plc.itensDAO.itens.setId(0);
				plc.itensDAO.itens.setNome(tf.getText());
				//plc.itensDAO.itens.setPredio(String.valueOf(predio_cb.getSelectedItem()));
				plc.itensDAO.itens.setPredio("null at this point of coding");
				
				plc.itensDAO.atualizar(INCLUSAO);
				
				confirmation.setText("Adicionado com sucesso");
				
			}
		});

	}

}
