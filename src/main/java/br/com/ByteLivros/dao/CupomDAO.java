package br.com.ByteLivros.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ByteLivros.dominio.Cupom;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Item;
import br.com.ByteLivros.dominio.Pedido;


public class CupomDAO extends AbstractDAO implements IDAO {

	public CupomDAO(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		PreparedStatement st = null;
		
		con.setAutoCommit(false);
		
		sql.append("INSERT INTO cupons_desconto(cpd_codigo, cpd_desconto, cpd_esta_ativo, cpd_utilizado, cpd_usu_id, cpd_tipo)");
		sql.append("VALUES (?, ?, ?, ?, ?, ?);");
		
		try {
		
		if(entidade.getClass().getName().equals(Pedido.class.getName())) {
			Pedido pedido = (Pedido) entidade;
			
				st = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				st.setString(1,  pedido.getValorTotal().longValue() + "CupomByte");
				st.setBigDecimal(2, pedido.getValorTotal());
				st.setBoolean(3, true);
				st.setBoolean(4, false);
				st.setInt(5, pedido.getUsuario().getId());
				st.setString(6, "TROCA");
				st.execute();

		}else if(entidade.getClass().getName().equals(Item.class.getName())) {
			Item item = (Item) entidade;
			
			st = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			st.setString(1,  item.getPrecoItem().longValue() + "CupomByte");
			st.setBigDecimal(2, item.getPrecoItem());
			st.setBoolean(3, true);
			st.setBoolean(4, false);
			st.setInt(5, item.getUsuario().getId());
			st.setString(6, "TROCA");
			st.execute();
		}
		
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
	public void excluir(EntidadeDominio entidade) throws SQLException {
		Cupom cupom = (Cupom) entidade;
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE cupons_desconto SET cpd_esta_ativo = false, cpd_utilizado = true WHERE cpd_id = ?");
		PreparedStatement st = null;
		openConnection();
		con.setAutoCommit(false);
		
		try {
			
			st = con.prepareStatement(sql.toString());
			st.setInt(1, cupom.getId());
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
		// TODO Auto-generated method stub

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		Cupom cupom = (Cupom) entidade;

		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList<EntidadeDominio> listaDeCupons = new ArrayList<>();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT cpd_id, cpd_codigo, cpd_desconto, cpd_tipo from cupons_desconto ");

		if(cupom.getUsuario()!= null) {
			sql.append("WHERE (cpd_usu_id = ? OR cpd_id IS NULL) AND cpd_esta_ativo = true AND cpd_utilizado = false;");
		}else {
			sql.append("WHERE cpd_usu_id IS NULL AND cpd_esta_ativo = true AND cpd_utilizado = false;");
		}
			
		try{
			
			st = con.prepareStatement(sql.toString());
			
			
			if(cupom.getUsuario()!= null) {
				st.setInt(1, cupom.getUsuario().getId());
			}
			
			st.executeQuery();
			rs = st.getResultSet(); 
			
			while (rs.next()) {
				
				cupom = new Cupom(rs.getInt("cpd_id"),
								  rs.getString("cpd_codigo"), 
								  rs.getBigDecimal("cpd_desconto"), 
								  rs.getString("cpd_tipo"));
				
				
				listaDeCupons.add(cupom);

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
		return listaDeCupons;
	}

	@Override
	public EntidadeDominio consultarPorId(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
