package br.com.ByteLivros.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ByteLivros.dominio.BandeiraCartao;
import br.com.ByteLivros.dominio.Cidade;
import br.com.ByteLivros.dominio.Endereco;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Estado;
import br.com.ByteLivros.dominio.Login;
import br.com.ByteLivros.dominio.Pais;
import br.com.ByteLivros.dominio.Telefone;

public class BandeiraCartaoDAO extends AbstractDAO implements IDAO {

	public BandeiraCartaoDAO(Connection con) {
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
		
		BandeiraCartao bandeira = (BandeiraCartao) entidade;
		
		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList<EntidadeDominio> listaDeBandeiras = new ArrayList<>();

		StringBuilder sql = new StringBuilder();

		sql.append("Select * from bandeiras_cartao");
		
		openConnection();

		try{
			
			st = con.prepareStatement(sql.toString());
			
			st.executeQuery();
			rs = st.getResultSet(); 
			
			while (rs.next()) {
					
				
				bandeira = new BandeiraCartao(rs.getString("bnd_nome"));
				bandeira.setId(rs.getInt("bnd_id"));
				
				
				listaDeBandeiras.add(bandeira);
				
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
		return listaDeBandeiras;
		
	}

	@Override
	public EntidadeDominio consultarPorId(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
