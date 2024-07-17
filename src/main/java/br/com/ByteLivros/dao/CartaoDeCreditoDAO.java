package br.com.ByteLivros.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import br.com.ByteLivros.dominio.BandeiraCartao;
import br.com.ByteLivros.dominio.CartaoDeCredito;
import br.com.ByteLivros.dominio.EntidadeDominio;


public class CartaoDeCreditoDAO extends AbstractDAO implements IDAO {

	public CartaoDeCreditoDAO(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		CartaoDeCredito cartao = (CartaoDeCredito) entidade;
		
		StringBuilder sql = new StringBuilder();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		openConnection();
		con.setAutoCommit(false);

		sql.append("INSERT INTO cartoes_de_credito(crt_numero, crt_nome_impresso, ");
		sql.append("crt_bnd_id, crt_codigo_seguranca, crt_usu_id, crt_esta_ativo)");
		sql.append("VALUES (?, ?, ?, ?, ?, ?)");
		
		try {
			st = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			st.setString(1, cartao.getNrCartao());
			st.setString(2, cartao.getNomeCartao());
			st.setInt(3, cartao.getBandeira().getId());
			st.setString(4, cartao.getCodSeguranca());
			st.setInt(5, cartao.getCartaoUsuarioId());
			st.setBoolean(6, true);
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
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		CartaoDeCredito cartao = (CartaoDeCredito) entidade;
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE cartoes_de_credito SET crt_esta_ativo = false WHERE crt_id = ?");
		PreparedStatement st = null;
		
		try {
			con.setAutoCommit(false);
			st = con.prepareStatement(sql.toString());
			st.setInt(1, cartao.getId());
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
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		
			CartaoDeCredito cartao = (CartaoDeCredito) entidade;
		
			StringBuilder sql = new StringBuilder();
			PreparedStatement st = null;
			ResultSet rs = null;
			
			openConnection();
			
			con.setAutoCommit(false);

			sql.append("UPDATE cartoes_de_credito SET crt_numero=?, crt_nome_impresso=?, ");
			sql.append("crt_codigo_seguranca=?, crt_bnd_id=? ");
			sql.append("WHERE crt_id=?");
			
			try {
				
				st = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				
				st.setString(1, cartao.getNrCartao());
				st.setString(2, cartao.getNomeCartao());
				st.setString(3, cartao.getCodSeguranca());
				st.setInt(4, cartao.getBandeira().getId());
				st.setInt(5, cartao.getId());
				st.executeUpdate();
				
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
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		CartaoDeCredito cartao = null;
		BandeiraCartao bandeira = null;
		
		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList<EntidadeDominio> listaDeCartoes = new ArrayList<>();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT crt_id, crt_numero, crt_nome_impresso, crt_codigo_seguranca, bnd_id, bnd_nome ");
		sql.append("FROM cartoes_de_credito inner join bandeiras_cartao on bnd_id = crt_bnd_id ");
		sql.append("WHERE crt_id = ? AND crt_esta_ativo = true order by crt_numero");
		
		try{
			
			st = con.prepareStatement(sql.toString());
			
			st.setInt(1, entidade.getId());
			
			st.executeQuery();
			rs = st.getResultSet(); 
			
			while (rs.next()) {
				
				bandeira = new BandeiraCartao(rs.getString("bnd_nome"));
				bandeira.setId(rs.getInt("bnd_id"));
				
				cartao = new CartaoDeCredito(rs.getString("crt_numero"),
						                     rs.getString("crt_nome_impresso"),
						                     rs.getString("crt_codigo_seguranca"),
						                     bandeira);
				cartao.setId(rs.getInt("crt_id"));
				
				listaDeCartoes.add(cartao);
				
				}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		return listaDeCartoes;
		
	}

	@Override
	public EntidadeDominio consultarPorId(EntidadeDominio entidade) throws SQLException {
		
		for(EntidadeDominio entDom : consultar(entidade)) {
			
			return entDom;	
			
		}
		
		return null;
	}

}
