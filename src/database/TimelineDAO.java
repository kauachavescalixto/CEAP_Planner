package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TimelineDAO {
	public Timeline timeline;
	public BD bd;
	private PreparedStatement st;
	private ResultSet rs;
	private String men, sql;
	public static final byte INCLUSAO = 1;
	public static final byte ALTERACAO = 2;
	public static final byte EXCLUSAO = 3;

	public ArrayList<Integer> ids = new ArrayList<>();
	public ArrayList<String> nomes = new ArrayList<>();
	public ArrayList<String> datahora = new ArrayList<>();
	public ArrayList<String> equip = new ArrayList<>();
	public ArrayList<String> emails = new ArrayList<>();

	public TimelineDAO() {
		bd = new BD();
		timeline = new Timeline();
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
		sql = "select * from timeline";
		try {
			if (bd.getConnection()) {
				st = bd.c.prepareStatement(sql);
				rs = st.executeQuery();
				while (rs.next()) {
					ids.add(rs.getInt("id"));
					nomes.add(rs.getString("nome"));
					datahora.add(rs.getString("datahora"));
					equip.add(rs.getString("equip"));
					emails.add(rs.getString("email"));
				}

			}

		} catch (SQLException e) {
			System.out.println("ERROR - fillArrays: " + e);
		}

	}
	
	public void cleanArrays() {
		ids.clear();
		nomes.clear();
		datahora.clear();
		equip.clear();
		emails.clear();
	}
	
	public boolean localizar(String nome) {
		timeline.setNome(nome);
		sql = "select * from timeline where nome=?;";
		try {
			if (bd.getConnection()) {
				st = bd.c.prepareStatement(sql);
				st.setString(1, timeline.getNome());
				st.executeQuery();
				rs = st.executeQuery();
				rs.next();

				timeline.setId(rs.getInt("id"));
				timeline.setNome(rs.getString("nome"));
				timeline.setDatahora(rs.getString("datahora"));
				timeline.setEquip(rs.getString("equip"));
				timeline.setEmail(rs.getString("email"));

				return true;
			}
			return false;
		} catch (SQLException erro) {
			return false;
		}
	}

	public String atualizar(int operacao) {
		men = "Operação realizada com sucesso ";
		try {
			if (bd.getConnection()) {
				if (operacao == INCLUSAO) {
					sql = "insert into timeline values (?,?,?,?,?)";
					st = bd.c.prepareStatement(sql);
					
					st.setInt(1, timeline.getId());
					System.out.println(timeline.getId());
					st.setString(2, timeline.getNome());
					st.setString(3, timeline.getDatahora());
					st.setString(4, timeline.getEquip());
					st.setString(5, timeline.getEmail());
					
				} else if (operacao == ALTERACAO) {
					sql = "update timeline set nome = ?, datahora = ?, equip = ?, email = ? where id =?";
					st = bd.c.prepareStatement(sql);
					
					st.setString(1, timeline.getNome());
					st.setString(2, timeline.getDatahora());
					st.setString(3, timeline.getEquip());
					st.setString(4, timeline.getEmail());
					st.setInt(5, timeline.getId());
				} else if (operacao == EXCLUSAO) {
					sql = "delete from timeline where nome =?";
					st = bd.c.prepareStatement(sql);
					st.setString(1, timeline.getNome());
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
