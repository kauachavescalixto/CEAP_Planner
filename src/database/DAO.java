package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
	
	private String sql;
	
	public static String tb = "tb_uso";
	
	public GetSet gs;
	public BD bd;
	
	public ResultSet rs;
	public PreparedStatement ps;
	
	public static final byte INSERCAO = 0;
	public static final byte ALTERACAO = 1;
	public static final byte EXCLUSAO = 2;
	
	public DAO() {
		
		gs = new GetSet();
		bd = new BD();
	}
	
	public boolean localizar() {
		
		if(bd.getConnection()) {
			try {
				sql = "select * from "+tb+" where nome = ?";
				ps = bd.c.prepareStatement(sql);
				ps.setString(1, gs.getNome());
				rs = ps.executeQuery();
				rs.next();
				gs.setS
				
			} catch (SQLException e) {
				System.out.println("ERROR - Localizar: "+e);
			}
			
			
		}
		
		return false;
	}
	
}
