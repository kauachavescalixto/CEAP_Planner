package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {

	private String sql;

	public GetSet gs;
	public BD bd;

	public ResultSet rs;
	public PreparedStatement ps;

	public static final byte INSERCAO = 0;
	public static final byte ALTERACAO = 1;
	public static final byte EXCLUSAO = 2;

	public static final byte COMBOBOX = 10;
	public static final byte EMUSO = 11;
	public static final byte DEVENDO = 12;

	public DAO(int QUALTABLE) {

		gs = new GetSet();
		bd = new BD();

		if (QUALTABLE == COMBOBOX) {

			
		} else if (QUALTABLE == EMUSO) {

			
		} else if (QUALTABLE == DEVENDO) {

			
		}

	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean Combo_Localizar() {
		if (bd.getConnection()) {
			try {
				sql = "select * from cadastrados where id = ?";
				ps = bd.c.prepareStatement(sql);
				ps.setString(1, gs.getCombo_id());
				rs = ps.executeQuery();
				rs.next();
				
				gs.setCombo_nome(rs.getString("nome"));
				gs.setCombo_email(rs.getString("email"));
				
			} catch (SQLException e) {
				System.out.println("ERROR - Combo.Localizar: " + e);
			}

		}

		return false;
	
	
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean Uso_Localizar() {
		if (bd.getConnection()) {
			try {
				sql = "select * from tb_uso where id = ?";
				ps = bd.c.prepareStatement(sql);
				ps.setString(1, gs.getNome());
				rs = ps.executeQuery();
				rs.next();

			} catch (SQLException e) {
				System.out.println("ERROR - Localizar: " + e);
			}

		}

		return false;
	
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean Devendo_Localizar() {
		
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
