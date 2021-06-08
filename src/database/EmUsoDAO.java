package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmUsoDAO {

	public EmUso emUso;
	public BD bd;
	private PreparedStatement st;
	private ResultSet rs;
	private String men, sql;
	public static final byte INCLUSAO = 1;
	public static final byte ALTERACAO = 2;
	public static final byte EXCLUSAO = 3;

	public ArrayList<Integer> ids = new ArrayList<>();
	public ArrayList<String> nomes = new ArrayList<>();
	public ArrayList<String> horarios = new ArrayList<>();
	public ArrayList<String> equipamentos = new ArrayList<>();
	public ArrayList<String> emails = new ArrayList<>();
	
	public EmUsoDAO() {
		bd = new BD();
		emUso = new EmUso();
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
	public boolean localizar(String nome) {
		emUso.setNome(nome);
		sql = "select * from tb_uso where nome=?;";
		try {
			if (bd.getConnection()) {
				st = bd.c.prepareStatement(sql);
				st.setString(1, emUso.getNome());
				st.executeQuery();
				rs = st.executeQuery();
				rs.next();

				emUso.setId(rs.getInt("id"));
				emUso.setNome(rs.getString("nome"));
				emUso.setHora(rs.getString("hora"));
				emUso.setEquip(rs.getString("equip"));
				emUso.setEmail(rs.getString("email"));

				return true;
			}
			return false;
		} catch (SQLException erro) {
			return false;
		}
	}
	public void fillArrays() {
		sql = "select * from tb_uso";
		try {
			if(bd.getConnection()) {
				st = bd.c.prepareStatement(sql);
				rs = st.executeQuery();
				while (rs.next()) {
					ids.add(rs.getInt("id"));
					nomes.add(rs.getString("nome"));
					horarios.add(rs.getString("hora"));
					equipamentos.add(rs.getString("equip"));
					emails.add(rs.getString("email"));
				}
				
			}

		} catch (SQLException e) {
			System.out.println("ERROR - fillArrays: "+e);
		}
		
	}
	
	
	public void cleanArrays() {
		ids.clear();
		nomes.clear();
		horarios.clear();
		equipamentos.clear();
		emails.clear();
	}
	
	public String atualizar(int operacao) {
		men = "Operação realizada com sucesso ";
		try {
			if (bd.getConnection()) {
				if (operacao == INCLUSAO) {
					sql = "insert into tb_uso values (?,?,?,?,?)";
					st = bd.c.prepareStatement(sql);

					st.setInt(1, emUso.getId());
					st.setString(2, emUso.getNome());
					st.setString(3, emUso.getHora());
					st.setString(4, emUso.getEquip());
					st.setString(5, emUso.getEmail());

				} else if (operacao == ALTERACAO) {
					sql = "update tb_uso set nome = ?, hora = ?, equip = ?, email = ? where id =?";
					st = bd.c.prepareStatement(sql);

					st.setString(1, emUso.getNome());
					st.setString(2, emUso.getHora());
					st.setString(3, emUso.getEquip());
					st.setString(4, emUso.getEmail());
					st.setInt(1, emUso.getId());

				} else if (operacao == EXCLUSAO) {
					sql = "delete from tb_uso where nome =?";
					st = bd.c.prepareStatement(sql);
					st.setString(1, emUso.getNome());
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
