package br.com.ByteLivros.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ByteLivros.dominio.CarrinhoDeCompras;
import br.com.ByteLivros.dominio.Cidade;
import br.com.ByteLivros.dominio.Cupom;
import br.com.ByteLivros.dominio.Endereco;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Estado;
import br.com.ByteLivros.dominio.Item;
import br.com.ByteLivros.dominio.Livro;
import br.com.ByteLivros.dominio.Login;
import br.com.ByteLivros.dominio.Pedido;
import br.com.ByteLivros.dominio.Usuario;


public class PedidoDAO extends AbstractDAO implements IDAO {

	public PedidoDAO(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		Pedido pedido = (Pedido) entidade;
		
		StringBuilder sql = new StringBuilder();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		openConnection();
		con.setAutoCommit(false);

		sql.append("INSERT INTO pedidos(ped_usu_id, ped_data, ped_end_id, ped_crt_id, ped_frete_valor, ped_valor)");
		sql.append("VALUES (?, ?, ?, ?, ?, ?);");
		
		try {
			st = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, pedido.getUsuario().getId());
			st.setObject(2, pedido.getDtCadastro());
			st.setInt(3, pedido.getEndereco().getId());
			st.setInt(4, pedido.getCartao().getId());
			st.setBigDecimal(5, pedido.getEndereco().getValorFrete());
			st.setBigDecimal(6, pedido.getValorProdutos());
			st.execute();
			
			rs = st.getGeneratedKeys();
			
			
			while (rs.next()) {
				
				Integer idPedido = rs.getInt(1);
				
				pedido.setId(idPedido);
				
				salvarItensDoPedido(pedido);
				
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
	
	public void salvarItensDoPedido(Pedido pedido) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		PreparedStatement st = null;

		con.setAutoCommit(false);

		sql.append("INSERT INTO itens_do_pedido(idp_lvr_id, idp_qtde_produtos, idp_ped_id, idp_valor, idp_status)");
		sql.append("VALUES (?, ?, ?, ?, ?);");
		
		try {
			
			st = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			for(Item item : pedido.getListaItens()) {
				
				st.setInt(1, item.getLivro().getId());
				st.setObject(2, item.getQtdeProdutos());
				st.setInt(3, pedido.getId());
				st.setBigDecimal(4, item.getPrecoItem());
				st.setString(5, "EM PROCESSAMENTO");
				st.execute();

			}
			
			CarrinhoDeCompras cdc = new CarrinhoDeCompras();
			cdc.getItens().clear();
			cdc.setValorCarrinho(new BigDecimal(0));
			
			pedido.setId(null);
			pedido.setEndereco(null);
			pedido.setCartao(null);
			
			Cupom cupom = new Cupom();
			cupom.getCupons().clear();
			
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
		// TODO Auto-generated method stub

	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		Pedido pedido = (Pedido) entidade;
		StringBuilder sql = new StringBuilder();
		PreparedStatement st = null;
		
		openConnection();
		con.setAutoCommit(false);
		
		sql.append("UPDATE itens_do_pedido SET IDP_STATUS = ? WHERE IDP_PED_ID = ? ");
		
		if(pedido.getUsuario() == null) {
        	sql.append("AND IDP_STATUS = 'ENTREGUE'; ");
		}
		
		try {
			
			st = con.prepareStatement(sql.toString());
			
				st.setString(1, pedido.getItem().getStatusPedido());
				st.setInt(2, pedido.getId());
				st.executeUpdate();
				
				if(pedido.getItem().getStatusPedido().equals("TROCA REALIZADA")) {
					
					CupomDAO cpnd = new CupomDAO(con);
					
					cpnd.salvar(pedido);
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
		// TODO Auto-generated method stub
		Pedido pedido = (Pedido) entidade;
		Endereco endereco = null;
		Cidade cidade = null;
		Estado estado = null;
		Usuario usuario = null;
		Login login = null;
		
		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList<EntidadeDominio> listaDePedidos = new ArrayList<>();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ped_id, ped_data, ped_valor, ped_frete_valor, end_logradouro, usu_cpf, ");
		sql.append("usu_nome, usu_id, usu_cpf, usu_email, end_numero, end_bairro, end_cidade, end_estado FROM pedidos ");
		sql.append("INNER JOIN enderecos on ped_end_id = end_id ");
		sql.append("INNER JOIN USUARIOS on usu_id = ped_usu_id ");
		

		if(pedido.getUsuario()!= null) {
			sql.append("WHERE ped_usu_id = ? AND usu_esta_ativo = true order by ped_id DESC");
		}
		
		if(pedido.getId()!= null) {
			sql.append("WHERE ped_id = ? AND usu_esta_ativo = true order by ped_id DESC");
		}
		
		if(pedido.getUsuario()!= null && pedido.getId()!= null) {
			sql.append("WHERE usu_esta_ativo = true order by ped_id DESC");
		}
			
		try{
			
			st = con.prepareStatement(sql.toString());
			
			
			if(pedido.getUsuario()!= null) {
				st.setInt(1, pedido.getUsuario().getId());
			}
			
			if(pedido.getId()!= null) {
				st.setInt(1, pedido.getId());
			}
			
			st.executeQuery();
			rs = st.getResultSet(); 
			
			while (rs.next()) {
				
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
				
				login = new Login(rs.getString("usu_email"));
				
				usuario = new Usuario(rs.getInt("usu_id"), 
									  rs.getString("usu_nome"),
									  rs.getString("usu_cpf"),
									  login);
				
				pedido = new Pedido(rs.getInt("ped_id"), 
									rs.getDate("ped_data").toLocalDate(),
									rs.getBigDecimal("ped_valor"),
									endereco,
									usuario);
																																																																																																																			
				for(Item item : consultarItensDoPedido(pedido)) {
					
					pedido.setListaItens(item);
				
				}

				listaDePedidos.add(pedido);
				
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
		return listaDePedidos;
	}
	
	
	public List<Item> consultarItensDoPedido(Pedido pedido) throws SQLException {
		// TODO Auto-generated method stub
		Livro livro = null;
		Item item = null;

		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		ArrayList<Item> listaDeItens = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT idp_id, lvr_id, lvr_titulo, lvr_cod_image, idp_qtde_produtos, ");
		sql.append("idp_valor, idp_status FROM itens_do_pedido ");
		sql.append("INNER JOIN LIVROS on lvr_id = idp_lvr_id ");
		sql.append("WHERE idp_ped_id = ?");
		
		try{
			
			st = con.prepareStatement(sql.toString());
			
			st.setInt(1, pedido.getId());
			
			st.executeQuery();
			rs = st.getResultSet(); 
			
			while (rs.next()) {
				
				livro = new Livro(rs.getInt("lvr_id"), 
						          rs.getString("lvr_titulo"), 
						          rs.getString("lvr_cod_image"));
				
				item = new Item(rs.getInt("idp_id"),
								rs.getInt("idp_qtde_produtos"), 
								rs.getBigDecimal("idp_valor"),
								rs.getString("idp_status"),
								livro);
						
				listaDeItens.add(item);
				
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
