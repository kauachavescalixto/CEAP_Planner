package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItensDAO {
	public Itens itens;
	public BD bd;
	private PreparedStatement st;
	private ResultSet rs;
	private String men, sql;
	public static final byte INCLUSAO = 1;
	public static final byte ALTERACAO = 2;
	public static final byte EXCLUSAO = 3;
	
	public ArrayList<Integer> ids = new ArrayList<>();
	public ArrayList<String> nomes = new ArrayList<>();
	public ArrayList<String> predios = new ArrayList<>();

	public ItensDAO() {
		bd = new BD();
		itens = new Itens();
	}
	public void closeConnections() {
		try {
			bd.close();
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	public void fillArrays() {
		sql = "select * from itens";
		try {
			if(bd.getConnection()) {
				st = bd.c.prepareStatement(sql);
				rs = st.executeQuery();
				while (rs.next()) {
					ids.add(rs.getInt("id"));
					nomes.add(rs.getString("nome"));
					predios.add(rs.getString("predio"));
				}
				
			}

		} catch (SQLException e) {
			System.out.println("ERROR - fillArrays: "+e);
		}
		
	}
	
	public void cleanArrays() {
		ids.clear();
		nomes.clear();
		predios.clear();
	}
	
	public boolean localizar(String nome) {
		itens.setNome(nome);
		sql = "select * from itens where nome=?;";
		try {
			if (bd.getConnection()) {
				st = bd.c.prepareStatement(sql);
				st.setString(1, itens.getNome());
				st.executeQuery();
				rs = st.executeQuery();
				rs.next();

				itens.setId(rs.getInt("id"));
				itens.setNome(rs.getString("nome"));
				itens.setPredio(rs.getString("email"));

				return true;
			}
			return false;
		} catch (SQLException erro) {
			return false;
		}
	}

	public String atualizar(int operacao) {
		men = "Operação realizada com sucesso";
		try {
			if (bd.getConnection()) {
				if (operacao == INCLUSAO) {
					sql = "insert into itens values (?,?,?)";
					st = bd.c.prepareStatement(sql);

					st.setInt(1, itens.getId());
					st.setString(2, itens.getNome());
					st.setString(3, itens.getPredio());

				} else if (operacao == ALTERACAO) {
					sql = "update itens set nome = ?, email = ? where id =?";
					st = bd.c.prepareStatement(sql);

					st.setString(1, itens.getNome());
					st.setString(2, itens.getPredio());
					st.setInt(3, itens.getId());
				} else if (operacao == EXCLUSAO) {
					sql = "delete from itens where nome =?";
					st = bd.c.prepareStatement(sql);
					st.setString(1, itens.getNome());
				}
				if (st.executeUpdate() == 0) {
					men = "Falha na operação";
				}
			}
		} catch (SQLException erro) {
			men = "Falha na operação " + erro.toString();
		}
		return men;
	}

}
