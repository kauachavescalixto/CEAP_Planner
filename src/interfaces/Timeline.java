package interfaces;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import database.PreLoadConnect;
import main.InterfaceManager;

public class Timeline extends CeapInterface {

	private String nome = "Linha do tempo";

	private JLabel name;
	private JPanel main;

	private JPanel secondary = new JPanel();

	private DefaultTableModel dtm = new DefaultTableModel(new String[] { "Nome", "Hora", "Equipamento", "Email" }, 0);
	private JTable tb = new JTable(dtm);
	private JScrollPane scroll = new JScrollPane(tb);
	private JPanel pnTb = new JPanel();

	//private JLabel filtrarpor = new JLabel("Filtrar por:");
	//private JPanel pnFiltro = new JPanel();

	private PreLoadConnect plc = InterfaceManager.plc;

	public Timeline(int w, int h) {
		setBackground(orange);
		setLayout(null);
		setSize(w,h);
		MainPanel();

		init();
	}

	public void MainPanel() {
		name = new JLabel(nome, SwingConstants.CENTER);
		name.setFont(HeaderFont);
		name.setForeground(Color.white);

		main = new JPanel();

		main.setSize((int) (w / 1.24f), h);
		main.setLocation(w / 6 + 30, 0);
		main.setOpaque(false);
		main.setBackground(Color.black);

		main.add(name);

		add(main);
	}

	@Override
	public void init() {
		main.add(secondary);

		secondary.setPreferredSize(new Dimension((int) (w / 1.25f), (int) (h / 1.16f)));
		secondary.setLocation(w / 6 + 30, 0);
		secondary.setOpaque(false);

		/*econdary.add(pnFiltro);
		pnFiltro.setOpaque(false);*/
		secondary.add(pnTb);
		// pnTb.setOpaque(false);

		pnTb.setPreferredSize(new Dimension((int) (w / 1.9f), h));
		/*pnFiltro.setPreferredSize(new Dimension(new Dimension((int) (w / 4f), h)));

		pnFiltro.add(filtrarpor);
		filtrarpor.setFont(SemiHeaderFont);
		filtrarpor.setForeground(Color.white);*/

		tb.setOpaque(false);
		pnTb.add(scroll);
		scroll.setViewportView(tb);
		scroll.setPreferredSize(new Dimension((int) (w / 1.9f), h - 100));
		scroll.setOpaque(false);

		fill();
	}

	public void fill() {
		for (int i = 0; i < plc.timelineDAO.ids.size(); i++) {
			dtm.addRow(new String[] { plc.timelineDAO.nomes.get(i), plc.timelineDAO.datahora.get(i),
					plc.timelineDAO.equip.get(i), plc.timelineDAO.emails.get(i) });
		}

	}

	@Override
	public void definir_eventos() {
		// TODO Auto-generated method stub

	}

}
