package br.com.ByteLivros.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Grafico;

public class GraficoDAO extends AbstractDAO implements IDAO {

	public GraficoDAO(Connection con) {
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
		return null;
	}

	@Override
	public EntidadeDominio consultarPorId(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public EntidadeDominio consultaGrafico(EntidadeDominio entidade) throws SQLException {
			
		 	Grafico graficoMontagem = (Grafico) entidade;
		 	
		 	Grafico graficoConsultado = new Grafico();
	
			PreparedStatement st = null;
			ResultSet rs = null;

			StringBuilder sql = new StringBuilder();

			Map<String, Map<String, BigDecimal>> dados = new TreeMap<>();
			
			sql.append("select ped_data, lvr_categoria, sum(idp_valor) as valor_produtos "); 
			sql.append("from itens_do_pedido ");
			sql.append("inner join livros on idp_lvr_id = lvr_id ");
			sql.append("inner join pedidos on ped_id = idp_ped_id ");
			if (graficoMontagem != null) {
				sql.append("where ped_data between ");
				sql.append("? ");
				sql.append("AND ");
				sql.append("? ");
			}
			sql.append("group by lvr_categoria, ped_data; ");
			
							
			try{
				
				st = con.prepareStatement(sql.toString());
				
				if (graficoMontagem != null) {
					st.setDate(1, Date.valueOf(graficoMontagem.getDataInicio()));
					st.setDate(2, Date.valueOf(graficoMontagem.getDataFim()));
					System.out.println(Date.valueOf(graficoMontagem.getDataFim()));
				}
				
				
				st.executeQuery();
				rs = st.getResultSet(); 
				
				while (rs.next()) {
					
					 	String data = rs.getString("ped_data");
		                
		                String categoria = rs.getString("lvr_categoria");
		                
		                BigDecimal quantidade = null;
		                
						if (rs.getBigDecimal("valor_produtos") != new BigDecimal(0.00)
								&& rs.getBigDecimal("valor_produtos") != null) {
							quantidade = rs.getBigDecimal("valor_produtos");
						} else {
							quantidade = new BigDecimal(0.00);
						}
		                
		                if(dados.keySet().contains(data)) {
		                    Map<String, BigDecimal> categoriaQuantidade = dados.get(data);
		                    
		                    categoriaQuantidade.put(categoria,quantidade);
		                    
		                    dados.put(data, categoriaQuantidade);
		                    
		                    graficoConsultado.setDados(dados);
		                
		                }else {
		                    Map<String, BigDecimal> categoriaQuantidade = new TreeMap<>();
		                    categoriaQuantidade.put(categoria,quantidade);
		                    
		                    dados.put(data, categoriaQuantidade);
		                    
		                    graficoConsultado.setDados(dados);
		                }
					
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
		
		return graficoConsultado;
	}
	
}
