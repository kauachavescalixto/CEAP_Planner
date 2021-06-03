package util;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.BD;
import database.DAO;
import interfaces.CeapInterface;

public class ThirdA extends CeapInterface {

	private JTable tb_emUso;
	private JPanel whiteLineA;
	private JTable tb_devendo;
	public DefaultTableModel dtm_emUso;
	public DefaultTableModel dtm_devendo;
	private JScrollPane scroll_emUso;
	private JScrollPane scroll_devedo;

	private DAO dao;
	
	public ArrayList<String> ids = new ArrayList<>();
	public ArrayList<String> nomes = new ArrayList<>();
	public ArrayList<String> horarios = new ArrayList<>();
	public ArrayList<String> equipamentos = new ArrayList<>();
	public ArrayList<String> emails = new ArrayList<>();
	
	
	
	public ThirdA() {
		
		PreparedStatement ps;
		ResultSet rs;
		BD bd = new BD();

		if (bd.getConnection()) {

			try {
				dao = new DAO(DAO.EMUSO);
				ps = bd.c.prepareStatement("select * from tb_uso");
				rs = ps.executeQuery();

				while (rs.next()) {
					ids.add(rs.getString("id"));
					nomes.add(rs.getString("nome"));
					horarios.add(rs.getString("hora"));
					equipamentos.add(rs.getString("equip"));
					emails.add(rs.getString("email"));
					
				}

			} catch (SQLException e) {
				System.out.println("ERROR - thirdB: " + e);
			}
		} else {
			System.out.println("sem conexão com BD");
			System.exit(0);
		}		
		
		
		setPreferredSize(new Dimension((int) (w / 1.9f), h));
		setOpaque(false);

		tb_emUso = new JTable(new DefaultTableModel(new String[] { "Nome", "Horario", "Item", "Email" }, 0));
		dtm_emUso = (DefaultTableModel) tb_emUso.getModel();

		scroll_emUso = new JScrollPane(tb_emUso);
		scroll_emUso.setPreferredSize(new Dimension((int) (w / 1.9f), (int) (h / 2.3f)));

		fillEmUso();
		
		whiteLineA = new JPanel();
		whiteLineA.setBackground(Color.white);
		whiteLineA.setPreferredSize(new Dimension((int) (w / 2f), 2));

		tb_devendo = new JTable(
				new DefaultTableModel(new String[] { "Nome", "Horario", "Notificado Há", "Item", "Email" }, 0));
		DefaultTableModel dtm_devendo = (DefaultTableModel) tb_devendo.getModel();

		scroll_devedo = new JScrollPane(tb_devendo);
		scroll_devedo.setPreferredSize(new Dimension((int) (w / 1.9f), (int) (h / 2.8f)));

		add(scroll_emUso);
		add(Box.createRigidArea(new Dimension((int) (w / 5f), 10)));
		add(whiteLineA);
		add(Box.createRigidArea(new Dimension((int) (w / 5f), 10)));
		add(scroll_devedo);

	}
	
	public void fillEmUso() {
		for (int i = 0; i < ids.size(); i++) {
			//dtm_emUso.addRow(rowData);
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
