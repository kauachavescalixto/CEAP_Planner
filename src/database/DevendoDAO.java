package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DevendoDAO {

	public Devendo devendo;
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
	public ArrayList<String> notificado = new ArrayList<>();
	public ArrayList<String> equipamentos = new ArrayList<>();
	public ArrayList<String> emails = new ArrayList<>();
	
	public DevendoDAO() {
		bd = new BD();
		devendo = new Devendo();
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
		sql = "select * from tb_devendo";
		try {
			if(bd.getConnection()) {
				st = bd.c.prepareStatement(sql);
				rs = st.executeQuery();
				while (rs.next()) {
					ids.add(rs.getInt("id"));
					nomes.add(rs.getString("nome"));
					horarios.add(rs.getString("hora"));
					notificado.add(rs.getString("notificado"));
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
		notificado.clear();
		equipamentos.clear();
		emails.clear();
	}
	
	public boolean localizar(String nome) {
		devendo.setNome(nome);
		sql = "select * from tb_devendo where nome=?;";
		try {
			if (bd.getConnection()) {
				st = bd.c.prepareStatement(sql);
				st.setString(1, devendo.getNome());
				st.executeQuery();
				rs = st.executeQuery();
				rs.next();

				devendo.setId(rs.getInt("id"));
				devendo.setNome(rs.getString("nome"));
				devendo.setHora(rs.getString("nome"));
				devendo.setNotificado(rs.getString("nome"));
				devendo.setEquip(rs.getString("nome"));
				devendo.setEmail(rs.getString("nome"));

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
					sql = "insert into tb_devendo values (?,?,?,?,?,?)";
					st = bd.c.prepareStatement(sql);

					st.setInt(1, devendo.getId());
					st.setString(2, devendo.getNome());
					st.setString(3, devendo.getHora());
					st.setString(4, devendo.getNotificado());
					st.setString(5, devendo.getEquip());
					st.setString(6, devendo.getEmail());
					

				} else if (operacao == ALTERACAO) {
					sql = "update tb_devendo set nome = ?, hora = ?, notificado = ?, equip = ?, email = ? where id =?";
					st = bd.c.prepareStatement(sql);

					st.setString(1, devendo.getNome());
					st.setString(2, devendo.getHora());
					st.setString(3, devendo.getNotificado());
					st.setString(4, devendo.getEquip());
					st.setString(5, devendo.getEmail());
					st.setInt(6, devendo.getId());

				} else if (operacao == EXCLUSAO) {
					sql = "delete from tb_devendo where nome =?";
					st = bd.c.prepareStatement(sql);
					st.setString(1, devendo.getNome());
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
