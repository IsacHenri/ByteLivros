package br.com.ByteLivros.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import br.com.ByteLivros.dominio.Cidade;
import br.com.ByteLivros.dominio.Endereco;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Estado;
import br.com.ByteLivros.dominio.Item;
import br.com.ByteLivros.dominio.Livro;
import br.com.ByteLivros.dominio.Pedido;
import br.com.ByteLivros.dominio.Usuario;


public class ItemDAO extends AbstractDAO implements IDAO{

	public ItemDAO(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		Item item = (Item) entidade;
		
		StringBuilder sql = new StringBuilder();
		PreparedStatement st = null;
		
		openConnection();
		con.setAutoCommit(false);

		sql.append("UPDATE itens_do_pedido SET idp_status=? WHERE idp_id=?;");
		
		try {
			st = con.prepareStatement(sql.toString());
			st.setString(1, item.getStatusPedido());
			st.setInt(2, item.getId());
			st.executeUpdate();
			
			if(item.getStatusPedido().equals("TROCA REALIZADA")) {
				
				CupomDAO cpnd = new CupomDAO(con);
				
				cpnd.salvar(item);
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
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {

		Item item = (Item) entidade;
		Pedido pedido = null;
		Livro livro = null;
		Endereco endereco = null;
		Cidade cidade = null;
		Estado estado = null;
		Usuario usuario = null;

		
		PreparedStatement st = null;
		ResultSet rs = null;

		List<EntidadeDominio> listaDeItens = new ArrayList<>();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT usu_id, ped_id, ped_data, idp_id, lvr_id, lvr_titulo, lvr_cod_image, idp_qtde_produtos, ");
		sql.append("ped_frete_valor, ped_valor, end_logradouro, end_numero, end_bairro, end_cidade, end_estado, ");
		sql.append("idp_valor, idp_status FROM itens_do_pedido ");
		sql.append("INNER JOIN LIVROS on lvr_id = idp_lvr_id ");
		sql.append("INNER JOIN PEDIDOS on idp_ped_id = ped_id ");
		sql.append("INNER JOIN ENDERECOS on ped_end_id = end_id ");
		sql.append("INNER JOIN USUARIOS on ped_usu_id = usu_id ");
		sql.append("WHERE idp_id = ?");
		
		try{
			
			st = con.prepareStatement(sql.toString());
			
			st.setInt(1, item.getId());
			
			st.executeQuery();
			rs = st.getResultSet(); 
			
			while (rs.next()) {
				
				usuario = new Usuario(rs.getInt("usu_id"));
				
				livro = new Livro(rs.getInt("lvr_id"), 
						          rs.getString("lvr_titulo"), 
						          rs.getString("lvr_cod_image"));
				
				item = new Item(rs.getInt("idp_id"),
								rs.getInt("idp_qtde_produtos"), 
								rs.getBigDecimal("idp_valor"),
								rs.getString("idp_status"),
								livro, 
								usuario);
				
				estado = new Estado(rs.getString("end_estado"));
				
				cidade = new Cidade(estado,
									rs.getString("end_cidade")
									);
				
				endereco = new Endereco(rs.getString("end_logradouro"), 
										rs.getString("end_numero"),
										rs.getString("end_bairro"),
										rs.getBigDecimal("ped_frete_valor"),
										cidade
										);
				
				pedido = new Pedido(rs.getInt("ped_id"), 
									rs.getDate("ped_data").toLocalDate(),
									rs.getBigDecimal("ped_valor"),
									endereco,
									item);
				
						
				listaDeItens.add(pedido);
				
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
		return listaDeItens;
	}

	@Override
	public EntidadeDominio consultarPorId(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		for(EntidadeDominio entDom : consultar(entidade)) {
			
			return entDom;	
			
		}
		return null;
	}

}
