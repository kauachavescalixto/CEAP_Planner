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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import database.PreLoadConnect;
import main.CeapWindow;
import main.InterfaceManager;
import util.ThirdA;
import util.ThirdB;

public class Equip extends CeapInterface {

	private String titulo = "Equipamentos em uso";

	GridLayout layout = new GridLayout(0, 1);

	private JPanel main;
	private JPanel secondary;

	private ThirdA thirdA;
	private JScrollPane thirdBScroll;
	private ThirdB thirdB;

	private JLabel name;

	private PreLoadConnect plc;

	public Equip(int w, int h) {
		this.plc = InterfaceManager.plc;
		setLayout(null);
		setBackground(orange);

		setSize(w, h);

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

						(String) thirdB.nome.getSelectedItem(), thirdB.horario.getText(),
						(String) thirdB.equip.getSelectedItem(), plc.cadDAO.emails.get(thirdB.nome.getSelectedIndex())

				});

				plc.usoDAO.emUso.setId(0);
				plc.usoDAO.emUso.setNome((String) thirdB.nome.getSelectedItem());
				plc.usoDAO.emUso.setHora(thirdB.horario.getText());
				plc.usoDAO.emUso.setEquip((String) thirdB.equip.getSelectedItem());
				plc.usoDAO.emUso.setEmail(plc.cadDAO.emails.get(thirdB.nome.getSelectedIndex()));

				plc.usoDAO.atualizar(INCLUSAO);

			}
		});

		thirdB.devolvido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				DateTimeFormatter dtfHora = DateTimeFormatter.ofPattern("HH:mm");

				plc.timelineDAO.timeline.setId(0);

				plc.timelineDAO.timeline
						.setNome(String.valueOf(thirdA.tb_emUso.getValueAt(thirdA.tb_emUso.getSelectedRow(), 0)));

				plc.timelineDAO.timeline.setDatahora(dtf.format(LocalDateTime.now()) + " "
						+ String.valueOf(thirdA.tb_emUso.getValueAt(thirdA.tb_emUso.getSelectedRow(), 1) + " - "
								+ dtfHora.format(LocalDateTime.now())));

				plc.timelineDAO.timeline
						.setEquip(String.valueOf(thirdA.tb_emUso.getValueAt(thirdA.tb_emUso.getSelectedRow(), 2)));

				plc.timelineDAO.timeline
						.setEmail(String.valueOf(thirdA.tb_emUso.getValueAt(thirdA.tb_emUso.getSelectedRow(), 3)));

				plc.timelineDAO.atualizar(INCLUSAO);

				plc.usoDAO.emUso
						.setNome(String.valueOf(thirdA.tb_emUso.getValueAt(thirdA.tb_emUso.getSelectedRow(), 0)));
				plc.usoDAO.atualizar(EXCLUSAO);

				thirdA.dtm_emUso.removeRow(thirdA.tb_emUso.getSelectedRow());

			}
		});

		thirdB.notificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

				plc.devDAO.devendo.setId(0);

				plc.devDAO.devendo
						.setNome(String.valueOf(thirdA.tb_emUso.getValueAt(thirdA.tb_emUso.getSelectedRow(), 0)));

				plc.devDAO.devendo
						.setHora(String.valueOf(thirdA.tb_emUso.getValueAt(thirdA.tb_emUso.getSelectedRow(), 1)));

				plc.devDAO.devendo
						.setEquip(String.valueOf(thirdA.tb_emUso.getValueAt(thirdA.tb_emUso.getSelectedRow(), 2)));

				plc.devDAO.devendo
						.setEmail(String.valueOf(thirdA.tb_emUso.getValueAt(thirdA.tb_emUso.getSelectedRow(), 3)));

				plc.devDAO.devendo.setNotificado(dtf.format(LocalDateTime.now()));

				
				plc.devDAO.atualizar(INCLUSAO);

				
				
				plc.usoDAO.emUso
						.setNome(String.valueOf(thirdA.tb_emUso.getValueAt(thirdA.tb_emUso.getSelectedRow(), 0)));
				
				plc.usoDAO.atualizar(EXCLUSAO);

				thirdA.dtm_emUso.removeRow(thirdA.tb_emUso.getSelectedRow());
				thirdA.dtm_devendo.addRow(new String[] {
						plc.devDAO.devendo.getNome(),
						plc.devDAO.devendo.getHora(),
						plc.devDAO.devendo.getNotificado(),
						plc.devDAO.devendo.getEquip(),
						plc.devDAO.devendo.getEmail()
						
				});
			}
		});

		thirdA.devolvido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				DateTimeFormatter dtfHora = DateTimeFormatter.ofPattern("HH:mm");

				plc.timelineDAO.timeline.setId(0);

				plc.timelineDAO.timeline
						.setNome(String.valueOf(thirdA.tb_devendo.getValueAt(thirdA.tb_devendo.getSelectedRow(), 0)));

				plc.timelineDAO.timeline.setDatahora(dtf.format(LocalDateTime.now()) + " "
						+ String.valueOf(thirdA.tb_devendo.getValueAt(thirdA.tb_devendo.getSelectedRow(), 1) + " - "
								+ dtfHora.format(LocalDateTime.now()) + " (com atraso)"));

				plc.timelineDAO.timeline
						.setEquip(String.valueOf(thirdA.tb_devendo.getValueAt(thirdA.tb_devendo.getSelectedRow(), 2)));

				plc.timelineDAO.timeline
						.setEmail(String.valueOf(thirdA.tb_devendo.getValueAt(thirdA.tb_devendo.getSelectedRow(), 3)));

				plc.timelineDAO.atualizar(INCLUSAO);

				plc.devDAO.devendo
						.setNome(String.valueOf(thirdA.tb_devendo.getValueAt(thirdA.tb_devendo.getSelectedRow(), 0)));
				plc.devDAO.atualizar(EXCLUSAO);

				thirdA.dtm_devendo.removeRow(thirdA.tb_devendo.getSelectedRow());

			}
		});

	}

}
