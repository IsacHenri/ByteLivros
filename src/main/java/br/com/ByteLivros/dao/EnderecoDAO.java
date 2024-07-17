package br.com.ByteLivros.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ByteLivros.dominio.Cidade;
import br.com.ByteLivros.dominio.Endereco;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Estado;
import br.com.ByteLivros.dominio.Pais;


public class EnderecoDAO extends AbstractDAO implements IDAO {

	public EnderecoDAO(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		Endereco endereco = (Endereco) entidade;
		
		StringBuilder sql = new StringBuilder();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		openConnection();

		sql.append("INSERT INTO enderecos(end_tipo_residencia, end_tipo_logradouro,");
		sql.append("end_logradouro, end_numero, end_bairro, end_cep, end_cidade, end_estado,");
		sql.append(" end_pais, end_usu_id, end_esta_ativo, end_tipo_endereco)");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		try {
			
			st = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				st.setString(1, endereco.getTipoResidencia());
				st.setString(2, endereco.getTipoLogradouro());
				st.setString(3, endereco.getLogradouro());
				st.setString(4, endereco.getNumero());
				st.setString(5, endereco.getBairro());
				st.setString(6, endereco.getCep());
				st.setString(7, endereco.getCidade().getDescricao());
				st.setString(8, endereco.getCidade().getEstado().getDescricao());
				st.setString(9, endereco.getCidade().getEstado().getPais().getDescricao());
				st.setInt(10, endereco.getEnderecoUsuarioId());
				st.setBoolean(11, true);
				st.setString(12, endereco.getTipoEndereco());
				st.execute();
			
			
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
		Endereco endereco = (Endereco) entidade;
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE enderecos SET end_esta_ativo = false WHERE end_id = ?");
		PreparedStatement st = null;
		
		try {
			con.setAutoCommit(false);
			st = con.prepareStatement(sql.toString());
			st.setInt(1, endereco.getId());
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
		Endereco endereco = (Endereco) entidade;
		
		StringBuilder sql = new StringBuilder();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		openConnection();
		
		con.setAutoCommit(false);

		sql.append("UPDATE enderecos SET end_tipo_residencia=?, end_tipo_logradouro=?,");
		sql.append("end_logradouro=?, end_numero=?, end_bairro=?, end_cep=?, ");
		sql.append("end_cidade=?, end_estado=?, end_pais=?, end_tipo_endereco=? ");
		sql.append("WHERE end_id=?");
		
		try {
			
			st = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
				st.setString(1, endereco.getTipoResidencia());
				st.setString(2, endereco.getTipoLogradouro());
				st.setString(3, endereco.getLogradouro());
				st.setString(4, endereco.getNumero());
				st.setString(5, endereco.getBairro());
				st.setString(6, endereco.getCep());
				st.setString(7, endereco.getCidade().getDescricao());
				st.setString(8, endereco.getCidade().getEstado().getDescricao());
				st.setString(9, endereco.getCidade().getEstado().getPais().getDescricao());
				st.setString(10, endereco.getTipoEndereco());
				st.setInt(11, endereco.getId());
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
		Pais pais = null;
		Estado estado = null;
		Cidade cidade = null;
		Endereco endereco = null;
		
		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList<EntidadeDominio> listaDeEnderecos = new ArrayList<>();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT end_id, end_tipo_residencia, end_tipo_logradouro, ");
		sql.append("end_logradouro, end_numero, end_bairro, end_cep, end_cidade, ");
		sql.append("end_estado, end_pais, end_observacao, end_tipo_endereco FROM enderecos ");
		sql.append("WHERE end_id = ? AND end_esta_ativo = true");
	
		try{
			
			st = con.prepareStatement(sql.toString());
			
			st.setInt(1, entidade.getId());
			
			st.executeQuery();
			rs = st.getResultSet(); 
			
			while (rs.next()) {
				
				pais = new Pais(rs.getString("end_pais"));
				
				estado = new Estado(rs.getString("end_estado"), pais);
				
				cidade = new Cidade(estado, rs.getString("end_cidade"));
				
				endereco= new Endereco(rs.getString("end_tipo_residencia"),
									   rs.getString("end_logradouro"),
									   rs.getString("end_numero"),
									   rs.getString("end_cep"),
									   rs.getString("end_bairro"),
									   rs.getString("end_tipo_logradouro"),
									   rs.getString("end_tipo_endereco"),
									   rs.getString("end_observacao"),
									   cidade);
				endereco.setId(rs.getInt("end_id"));
				
				listaDeEnderecos.add(endereco);
				
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
		return listaDeEnderecos;
	}

	@Override
	public EntidadeDominio consultarPorId(EntidadeDominio entidade) throws SQLException {
		
		for(EntidadeDominio entDom : consultar(entidade)) {
			
			return entDom;	
			
		}
		return null;
	}
	
}
		
