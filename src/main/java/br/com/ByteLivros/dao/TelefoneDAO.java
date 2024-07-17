package br.com.ByteLivros.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Telefone;
import br.com.ByteLivros.dominio.Usuario;


public class TelefoneDAO extends AbstractDAO implements IDAO {

	public TelefoneDAO(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		
		Usuario usuario = (Usuario) entidade;
		
		StringBuilder sql = new StringBuilder();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		openConnection();

		sql.append("INSERT INTO telefones(tel_numero, tel_tipo, tel_cli_id, tel_esta_ativo)");
		sql.append("VALUES (?, ?, ?, ?)");
		
		try {
			
			st = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			for(Telefone tel : usuario.getListadeTelefones()) {
			st.setString(1, tel.getNumero());
			st.setString(2, tel.getTipo());
			st.setInt(3, usuario.getId());
			st.setBoolean(4, true);
			st.execute();
			}
			
			
		}catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


	}

	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		
		Usuario usuario = (Usuario) entidade;
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE telefones SET tel_esta_ativo = false WHERE tel_usu_id = ?");
		PreparedStatement st = null;
		
		try {
			con.setAutoCommit(false);
			st = con.prepareStatement(sql.toString());
			st.setInt(1, usuario.getId());
			st.execute();
					
			con.commit();
			
		}catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		Usuario usuario = (Usuario) entidade;
		
		StringBuilder sql = new StringBuilder();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		openConnection();

		sql.append("UPDATE telefones SET tel_numero=?, tel_tipo=? ");
		sql.append("WHERE tel_usu_id=?");
		
		try {
			
			st = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			for(Telefone tel : usuario.getListadeTelefones()) {
			st.setString(1, tel.getNumero());
			st.setString(2, tel.getTipo());
			st.setInt(3, usuario.getId());
			st.executeUpdate();
			
			}
			
			
		}catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntidadeDominio consultarPorId(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
