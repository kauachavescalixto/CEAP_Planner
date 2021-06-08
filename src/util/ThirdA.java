package util;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.PreLoadConnect;
import interfaces.CeapInterface;
import main.InterfaceManager;

public class ThirdA extends CeapInterface {

	public JTable tb_emUso;
	private JPanel whiteLineA;
	public JTable tb_devendo;
	public DefaultTableModel dtm_emUso;
	public DefaultTableModel dtm_devendo;
	private JScrollPane scroll_emUso;
	private JScrollPane scroll_devedo;
	
	public JButton devolvido = new JButton("Devolvido");
	
	private PreLoadConnect plc = InterfaceManager.plc;
	
	public ThirdA() {
		
		setPreferredSize(new Dimension((int) (w / 1.9f), h));
		setOpaque(false);

		tb_emUso = new JTable(new DefaultTableModel(new String[] { "Nome", "Horario", "Item", "Email" }, 0));
		dtm_emUso = (DefaultTableModel) tb_emUso.getModel();

		tb_emUso.setFont(TableFont);
		tb_emUso.setRowHeight(25);
		
		scroll_emUso = new JScrollPane(tb_emUso);
		scroll_emUso.setPreferredSize(new Dimension((int) (w / 1.9f), (int) (h / 2.3f)));

		
		whiteLineA = new JPanel();
		whiteLineA.setBackground(Color.white);
		whiteLineA.setPreferredSize(new Dimension((int) (w / 2f), 2));

		tb_devendo = new JTable(
				new DefaultTableModel(new String[] { "Nome", "Horario", "Notificado em", "Item", "Email" }, 0));
		dtm_devendo = (DefaultTableModel) tb_devendo.getModel();

		tb_devendo.setFont(TableFont);
		tb_devendo.setRowHeight(25);
		
		scroll_devedo = new JScrollPane(tb_devendo);
		scroll_devedo.setPreferredSize(new Dimension((int) (w / 1.9f), (int) (h / 4f)));

		add(scroll_emUso);
		add(Box.createRigidArea(new Dimension(getWidth(), 25)));
		add(whiteLineA);
		add(Box.createRigidArea(new Dimension(getWidth(), 25)));
		add(scroll_devedo);
		add(Box.createRigidArea(new Dimension(getWidth(), 30)));

		devolvido.setFont(LetterFont);
		devolvido.setForeground(cinzaClaro);
		add(devolvido);
		
		fill();
	}
	
	public void fill() {
		
		
		for (int i = 0; i < plc.devDAO.ids.size(); i++) {
			dtm_devendo.addRow(new String[] {
					plc.devDAO.nomes.get(i),
					plc.devDAO.horarios.get(i),
					plc.devDAO.notificado.get(i),
					plc.devDAO.equipamentos.get(i),
					plc.devDAO.emails.get(i)
			});
		}
	
		for (int i = 0; i < plc.usoDAO.ids.size(); i++) {
			dtm_emUso.addRow(new String[] {
					plc.usoDAO.nomes.get(i),
					plc.usoDAO.horarios.get(i),
					plc.usoDAO.equipamentos.get(i),
					plc.usoDAO.emails.get(i)
			});
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
