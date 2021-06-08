package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class cadastradosDAO {

	public Cadastrados cadastrados;
	public BD bd;
	public PreparedStatement st;
	public ResultSet rs;
	private String men, sql;
	public static final byte INCLUSAO = 1;
	public static final byte ALTERACAO = 2;
	public static final byte EXCLUSAO = 3;

	public ArrayList<Integer> ids = new ArrayList<>();
	public ArrayList<String> nomes = new ArrayList<>();
	public ArrayList<String> emails = new ArrayList<>();

	public cadastradosDAO() {
		bd = new BD();
		cadastrados = new Cadastrados();
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
		sql = "select * from cadastrados";
		try {
			if (bd.getConnection()) {
				st = bd.c.prepareStatement(sql);
				rs = st.executeQuery();
				while (rs.next()) {
					ids.add(rs.getInt("id"));
					nomes.add(rs.getString("nome"));
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
		emails.clear();
	}

	public boolean localizar(String nome) {
		cadastrados.setNome(nome);
		sql = "select * from cadastrados where nome=?;";
		try {
			if (bd.getConnection()) {
				st = bd.c.prepareStatement(sql);
				st.setString(1, cadastrados.getNome());
				st.executeQuery();
				rs = st.executeQuery();
				rs.next();

				cadastrados.setId(rs.getInt("id"));
				cadastrados.setNome(rs.getString("nome"));
				cadastrados.setEmail(rs.getString("email"));

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
					sql = "insert into cadastrados values (?,?,?)";
					st = bd.c.prepareStatement(sql);
					st.setInt(1, cadastrados.getId());
					st.setString(2, cadastrados.getNome());
					st.setString(3, cadastrados.getEmail());

				} else if (operacao == ALTERACAO) {
					sql = "update cadastrados set nome = ?, email = ? where id =?";
					st = bd.c.prepareStatement(sql);

					st.setString(1, cadastrados.getNome());
					st.setString(2, cadastrados.getEmail());
					st.setInt(3, cadastrados.getId());
				} else if (operacao == EXCLUSAO) {
					sql = "delete from cadastrados where nome =?";
					st = bd.c.prepareStatement(sql);
					st.setString(1, cadastrados.getNome());
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
