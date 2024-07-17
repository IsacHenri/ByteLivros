package br.com.ByteLivros.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.GrupoDePrecificacao;
import br.com.ByteLivros.dominio.Livro;
import br.com.ByteLivros.dominio.Usuario;

public class LivroDAO extends AbstractDAO implements IDAO {

	public LivroDAO(Connection con) {
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

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		Livro livro = (Livro) entidade;
		GrupoDePrecificacao gdp = null;
		
		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList<EntidadeDominio> listaDeLivros = new ArrayList<>();

		StringBuilder sql = new StringBuilder();

		sql.append("Select * from livros inner join grupo_precificacao on lvr_grp_id = grp_id ");
		
		if(livro.getId()!= null) {
			sql.append("where lvr_id = ? and lvr_esta_ativo = true;");
		}

		openConnection();
		
		try{
			
			st = con.prepareStatement(sql.toString());
			
			if(livro.getId()!= null) {
				st.setInt(1, livro.getId());
			}
			
			st.executeQuery();
			rs = st.getResultSet(); 
			
			while (rs.next()) {
				
				gdp = new GrupoDePrecificacao(rs.getString("grp_descricao"), rs.getBigDecimal("grp_margem_lucro"));
				
				BigDecimal margemDeLucro = rs.getBigDecimal("lvr_preco_de_custo")
								   .divide(new BigDecimal(100));
				
				margemDeLucro = margemDeLucro.multiply(gdp.getMargemLucro());
				
				BigDecimal preco = rs.getBigDecimal("lvr_preco_de_custo")
						           .add(margemDeLucro);
				
				livro = new Livro(rs.getString("lvr_autor"), 
								  rs.getString("lvr_categoria"),
								  preco.setScale(2, RoundingMode.HALF_UP),
								  rs.getInt("lvr_ano"),
								  rs.getString("lvr_titulo"),
								  rs.getString("lvr_editora"),
								  rs.getString("lvr_edicao"),
								  rs.getString("lvr_isbn"),
								  rs.getInt("lvr_numero_paginas"),
								  rs.getInt("lvr_qtde"),
								  rs.getString("lvr_sinopse"),
								  rs.getBigDecimal("lvr_altura"),
								  rs.getBigDecimal("lvr_largura"),
								  rs.getBigDecimal("lvr_profundidade"),
								  rs.getString("lvr_cod_barras"),
								  gdp);
				
	
				livro.setCodImagem(rs.getString("lvr_cod_image"));
				
				livro.setId(rs.getInt("lvr_id"));
				
				listaDeLivros.add(livro);
				
		}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
		try {
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		return listaDeLivros;
	}
		
	

	@Override
	public EntidadeDominio consultarPorId(EntidadeDominio entidade) throws SQLException {
		
		return null;
	}
	
	public ArrayList<EntidadeDominio> livrosComprados(EntidadeDominio entidade) throws SQLException {
	    Usuario usu = (Usuario) entidade;
	    GrupoDePrecificacao gdp = null;
	    
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    ArrayList<EntidadeDominio> listaDeLivros = new ArrayList<>();

	    StringBuilder sql = new StringBuilder();

	    // Construindo a consulta SQL para recuperar os livros comprados pelo usuário
	    sql.append("SELECT livro.*, grupo_precificacao.grp_descricao, grupo_precificacao.grp_margem_lucro ");
	    sql.append("FROM itens_do_pedido ");
	    sql.append("JOIN pedidos ON itens_do_pedido.idp_ped_id = pedidos.ped_id ");
	    sql.append("JOIN livros AS livro ON itens_do_pedido.idp_lvr_id = livro.lvr_id ");
	    sql.append("JOIN grupo_precificacao ON livro.lvr_grp_id = grupo_precificacao.grp_id ");
	    sql.append("WHERE pedidos.ped_usu_id = ?");

	    openConnection();
	    
	    try {
	        st = con.prepareStatement(sql.toString());
	        
	        // Definindo o parâmetro para o ID do usuário
	        st.setInt(1, usu.getId());
	        
	        st.executeQuery();
	        rs = st.getResultSet(); 
	        
	        while (rs.next()) {
	            // Lógica para criar objeto GrupoDePrecificacao
	            gdp = new GrupoDePrecificacao(rs.getString("grp_descricao"), rs.getBigDecimal("grp_margem_lucro"));
	            
	            // Lógica para calcular o preço do livro com base na margem de lucro
	            BigDecimal margemDeLucro = rs.getBigDecimal("lvr_preco_de_custo")
	                               .divide(new BigDecimal(100));
	            
	            margemDeLucro = margemDeLucro.multiply(gdp.getMargemLucro());
	            
	            BigDecimal preco = rs.getBigDecimal("lvr_preco_de_custo")
	                       .add(margemDeLucro);
	            
	            // Criando objeto Livro com base nos dados do ResultSet e do objeto GrupoDePrecificacao
	            Livro livro = new Livro(rs.getString("lvr_autor"), 
	                              rs.getString("lvr_categoria"),
	                              preco.setScale(2, RoundingMode.HALF_UP),
	                              rs.getInt("lvr_ano"),
	                              rs.getString("lvr_titulo"),
	                              rs.getString("lvr_editora"),
	                              rs.getString("lvr_edicao"),
	                              rs.getString("lvr_isbn"),
	                              rs.getInt("lvr_numero_paginas"),
	                              rs.getInt("lvr_qtde"),
	                              rs.getString("lvr_sinopse"),
	                              rs.getBigDecimal("lvr_altura"),
	                              rs.getBigDecimal("lvr_largura"),
	                              rs.getBigDecimal("lvr_profundidade"),
	                              rs.getString("lvr_cod_barras"),
	                              gdp);
	            
	            livro.setCodImagem(rs.getString("lvr_cod_image"));
	            
	            livro.setId(rs.getInt("lvr_id"));
	            
	            listaDeLivros.add(livro);
	        }
	    } catch (SQLException e1) {
	        e1.printStackTrace();
	    } finally {
	        try {
	            if (st != null) st.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return listaDeLivros;
	}
}
