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
import br.com.ByteLivros.dominio.Cidade;
import br.com.ByteLivros.dominio.Endereco;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Estado;
import br.com.ByteLivros.dominio.Login;
import br.com.ByteLivros.dominio.Pais;
import br.com.ByteLivros.dominio.Telefone;
import br.com.ByteLivros.dominio.Usuario;

public class UsuarioDAO extends AbstractDAO implements IDAO {

	public UsuarioDAO(Connection con){
		super(con);
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		Usuario usuario = (Usuario) entidade;
	
		StringBuilder sql = new StringBuilder();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		openConnection();
		con.setAutoCommit(false);

		sql.append("INSERT INTO usuarios(usu_nome, usu_genero,");
		sql.append("usu_data_nascimento, usu_cpf, usu_email, usu_senha, usu_esta_ativo)");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?)");
		
		try {
			st = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			st.setString(1, usuario.getNome());
			st.setString(2, usuario.getGenero());
			st.setObject(3, new java.sql.Timestamp(usuario.getDataNasc().getTime()), Types.TIMESTAMP);
			st.setString(4, usuario.getCpf());
			st.setString(5, usuario.getLogin().getEmail());
			st.setString(6, usuario.getLogin().getSenha());
			st.setBoolean(7, true);
			st.execute();
			
			rs = st.getGeneratedKeys();
			
			
			while (rs.next()) {
				
				int idCliente = rs.getInt(1);
				
				
				usuario.setId(idCliente);
				
				salvarTelefone(usuario);
				salvarEndereco(usuario);
				salvarCartaoDeCredito(usuario);
				
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
	
	public void salvarEndereco(Usuario usuario) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		PreparedStatement st = null;

		sql.append("INSERT INTO enderecos(end_tipo_residencia, end_tipo_logradouro,");
		sql.append("end_logradouro, end_numero, end_bairro, end_cep, end_cidade, end_estado,");
		sql.append(" end_pais, end_usu_id, end_esta_ativo, end_tipo_endereco, end_observacao)");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		try {
			
			st = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			for(Endereco end : usuario.getListadeEnderecos()){
				st.setString(1, end.getTipoResidencia());
				st.setString(2, end.getTipoLogradouro());
				st.setString(3, end.getLogradouro());
				st.setString(4, end.getNumero());
				st.setString(5, end.getBairro());
				st.setString(6, end.getCep());
				st.setString(7, end.getCidade().getDescricao());
				st.setString(8, end.getCidade().getEstado().getDescricao());
				st.setString(9, end.getCidade().getEstado().getPais().getDescricao());
				st.setInt(10, usuario.getId());
				st.setBoolean(11, true);
				st.setString(12, end.getTipoEndereco());
				st.setString(13, end.getObservacao());
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
	
	public void salvarTelefone(Usuario usuario) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		PreparedStatement st = null;

		sql.append("INSERT INTO telefones(tel_numero, tel_tipo, tel_usu_id, tel_esta_ativo)");
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
	
	public void salvarCartaoDeCredito(Usuario usuario) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		PreparedStatement st = null;
		

		sql.append("INSERT INTO cartoes_de_credito(crt_numero, crt_nome_impresso, ");
		sql.append("crt_bnd_id, crt_codigo_seguranca, crt_usu_id, crt_esta_ativo)");
		sql.append("VALUES (?, ?, ?, ?, ?, ?)");
		
		try {
			
			st = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			for(CartaoDeCredito cartao : usuario.getListadeCartoes()) {
			
			st.setString(1, cartao.getNrCartao());
			st.setString(2, cartao.getNomeCartao());
			st.setInt(3, cartao.getBandeira().getId());
			st.setString(4, cartao.getCodSeguranca());
			st.setInt(5, usuario.getId());
			st.setBoolean(6, true);
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
		
		if (entidade instanceof Usuario) {
			Usuario cliente = (Usuario) entidade;
			PreparedStatement stEnderecos = null;
			PreparedStatement stCartao = null;
			PreparedStatement stTelefone = null;
			PreparedStatement stCliente = null;

			try {
				openConnection();

				// Inativando endereços associados ao cliente
				String deleteEnderecosSQL = "UPDATE enderecos SET end_esta_ativo = false WHERE end_usu_id = ?";
				stEnderecos = con.prepareStatement(deleteEnderecosSQL);
				stEnderecos.setInt(1, cliente.getId());
				stEnderecos.executeUpdate();

				// Inativando cartão associado ao cliente
				String deleteCartaoSQL = "UPDATE cartoes_de_credito SET crt_esta_ativo = false WHERE crt_usu_id = ?";
				stCartao = con.prepareStatement(deleteCartaoSQL);
				stCartao.setInt(1, cliente.getId());
				stCartao.executeUpdate();

				// Inativando telefone associado ao cliente
				String deleteTelefoneSQL = "UPDATE telefones SET tel_esta_ativo = false WHERE tel_usu_id = ?";
				stTelefone = con.prepareStatement(deleteTelefoneSQL);
				stTelefone.setInt(1, cliente.getId());
				stTelefone.executeUpdate();

				// Inativando o próprio cliente
				String deleteClienteSQL = "UPDATE usuarios SET usu_esta_ativo = false WHERE usu_id = ?";
				stCliente = con.prepareStatement(deleteClienteSQL);
				stCliente.setInt(1, cliente.getId());
				stCliente.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (stEnderecos != null) {
						stEnderecos.close();
					}
					if (stCartao != null) {
						stCartao.close();
					}
					if (stTelefone != null) {
						stTelefone.close();
					}
					if (stCliente != null) {
						stCliente.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			
		}
	}
	
	
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		Usuario usuario = (Usuario) entidade;
		
		StringBuilder sql = new StringBuilder();
		PreparedStatement st = null;

		
		openConnection();
		con.setAutoCommit(false);

		sql.append("UPDATE usuarios SET usu_nome=?, usu_genero=?, ");
		sql.append("usu_data_nascimento=?, usu_cpf=?, usu_email=?, usu_senha=? ");
		sql.append("WHERE usu_id=?");
		
		try {
			st = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			st.setString(1, usuario.getNome());
			st.setString(2, usuario.getGenero());
			st.setObject(3, new java.sql.Timestamp(usuario.getDataNasc().getTime()), Types.TIMESTAMP);
			st.setString(4, usuario.getCpf());
			st.setString(5, usuario.getLogin().getEmail());
			st.setString(6, usuario.getLogin().getSenha());
			st.setInt(7, usuario.getId());
			st.executeUpdate();
			
			alterarTelefone(usuario);
			alterarEndereco(usuario);
			alterarCartao(usuario);
			
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
	
	public void alterarEndereco(Usuario usuario) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		PreparedStatement st = null;


		sql.append("UPDATE enderecos SET end_tipo_residencia=?, end_tipo_logradouro=?,");
		sql.append("end_logradouro=?, end_numero=?, end_bairro=?, end_cep=?, ");
		sql.append("end_cidade=?, end_estado=?, end_pais=?, end_observacao=? ");
		sql.append("WHERE end_id=?");
		
		try {
			
			st = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			for(Endereco end : usuario.getListadeEnderecos()){
				
				st.setString(1, end.getTipoResidencia());
				st.setString(2, end.getTipoLogradouro());
				st.setString(3, end.getLogradouro());
				st.setString(4, end.getNumero());
				st.setString(5, end.getBairro());
				st.setString(6, end.getCep());
				st.setString(7, end.getCidade().getDescricao());
				st.setString(8, end.getCidade().getEstado().getDescricao());
				st.setString(9, end.getCidade().getEstado().getPais().getDescricao());
				st.setString(10, end.getObservacao());
				st.setInt(11, end.getId());
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
	
	public void alterarCartao(Usuario usuario) throws SQLException {
		
	
		StringBuilder sql = new StringBuilder();
		PreparedStatement st = null;
		
		openConnection();
		
		con.setAutoCommit(false);

		sql.append("UPDATE cartoes_de_credito SET crt_numero=?, crt_nome_impresso=?, ");
		sql.append("crt_codigo_seguranca=?, crt_bnd_id=? ");
		sql.append("WHERE crt_id=?");
		
		try {
			
			st = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			for(CartaoDeCredito cartao : usuario.getListadeCartoes()) {
				
				st.setString(1, cartao.getNrCartao());
				st.setString(2, cartao.getNomeCartao());
				st.setString(3, cartao.getCodSeguranca());
				st.setInt(4, cartao.getBandeira().getId());
				st.setInt(5, cartao.getId());
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
	
	public void alterarTelefone(Usuario usuario) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		PreparedStatement st = null;
		sql.append("UPDATE telefones SET tel_numero=?, tel_tipo=? ");
		sql.append("WHERE tel_id=?");
		
		try {
			
			st = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			for(Telefone tel : usuario.getListadeTelefones()) {
			st.setString(1, tel.getNumero());
			st.setString(2, tel.getTipo());
			st.setInt(3, tel.getId());
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
		
		Usuario usuario = (Usuario) entidade;
		Login login = null;
		
		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList<EntidadeDominio> listaDeClientes = new ArrayList<>();

		StringBuilder sql = new StringBuilder();

		sql.append("Select usu_id, usu_nome, usu_email, usu_senha, usu_genero, usu_cpf, usu_data_nascimento, usu_isAdmin FROM usuarios ");

		
		if(usuario.getNome()!= null) {
			sql.append("where usu_nome LIKE ? and usu_esta_ativo = true order by usu_nome");
		}
		if(usuario.getId()!= null) {
			sql.append("where usu_id = ? and usu_esta_ativo = true order by usu_nome");
		}
		if(usuario.getLogin()!= null) {
			sql.append("where usu_email = ? and usu_senha = ? and usu_esta_ativo = true order by usu_nome");
		}
		
		openConnection();

		try{
			
			st = con.prepareStatement(sql.toString());
			
			if(usuario.getNome()!= null) {
				st.setString(1, "%" + usuario.getNome() + "%");
			}
			
			if(usuario.getId()!= null) {
				st.setInt(1, usuario.getId());
			}
			
			if(usuario.getLogin()!= null) {
				st.setString(1, usuario.getLogin().getEmail());
				st.setString(2, usuario.getLogin().getSenha());
			}
			
			st.executeQuery();
			rs = st.getResultSet(); 
			
			while (rs.next()) {
				
				login = new Login(rs.getString("usu_email"), rs.getString("usu_senha"));
				
				usuario = new Usuario(rs.getString("usu_nome"), 
									  rs.getString("usu_genero"), 
									  rs.getString("usu_cpf"), 
									  rs.getDate("usu_data_nascimento"),
									  rs.getBoolean("usu_isAdmin"),
									  login);
				
				usuario.setId(rs.getInt("usu_id"));
				
				for(Telefone telefone : consultarTelefones(usuario)) {
					usuario.setListadeTelefones(telefone);
				}
				
				for(Endereco endereco : consultarEnderecos(usuario)) {
					usuario.setListadeEnderecos(endereco);
				}
				
				for(CartaoDeCredito cartao : consultarCartoes(usuario)) {
					usuario.setListadeCartoes(cartao);
				}
				
				//for(Pedido pedido : consultarPedidos(usuario)) {
					//usuario.setListadePedidos(pedido);
			   //}
				
				listaDeClientes.add(usuario);
				
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
		return listaDeClientes;
		
	}
	
	public List<EntidadeDominio> consultarTudo() throws SQLException {
		
		Usuario usuario = null;
		Login login = null;
		
		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList<EntidadeDominio> listaDeClientes = new ArrayList<>();

		StringBuilder sql = new StringBuilder();

		sql.append("Select * FROM usuarios where usu_esta_ativo = true order by usu_nome");

		
		openConnection();

		try{
			
			st = con.prepareStatement(sql.toString());
			
			st.executeQuery();
			rs = st.getResultSet(); 
			
			while (rs.next()) {
				
				login = new Login(rs.getString("usu_email"), rs.getString("usu_senha"));
				
				usuario = new Usuario(rs.getString("usu_nome"), 
									  rs.getString("usu_genero"), 
									  rs.getString("usu_cpf"), 
									  rs.getDate("usu_data_nascimento"),
									  rs.getBoolean("usu_isAdmin"),
									  login);
				
				usuario.setId(rs.getInt("usu_id"));
				
				for(Telefone telefone : consultarTelefones(usuario)) {
					usuario.setListadeTelefones(telefone);
				}
				
				for(Endereco endereco : consultarEnderecos(usuario)) {
					usuario.setListadeEnderecos(endereco);
				}
				
				for(CartaoDeCredito cartao : consultarCartoes(usuario)) {
					usuario.setListadeCartoes(cartao);
				}
				
				//for(Pedido pedido : consultarPedidos(usuario)) {
					//usuario.setListadePedidos(pedido);
			   //}
				
				listaDeClientes.add(usuario);
				
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
		return listaDeClientes;
		
	}
	
	public List<Telefone> consultarTelefones(Usuario usuario) throws SQLException {
		
		Telefone telefone = null;
		
		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList<Telefone> listaDeTelefones = new ArrayList<>();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT tel_id, tel_numero, tel_tipo FROM telefones ");
		sql.append("WHERE tel_usu_id = ? AND tel_esta_ativo = true order by tel_numero");
	

		try{
			
			st = con.prepareStatement(sql.toString());
			
			st.setInt(1, usuario.getId());
			
			st.executeQuery();
			rs = st.getResultSet(); 
			
			while (rs.next()) {
				
				telefone = new Telefone(rs.getString("tel_numero"), 
										rs.getString("tel_tipo"));
				
				telefone.setId(rs.getInt("tel_id"));
				
				listaDeTelefones.add(telefone);
				
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
		return listaDeTelefones;
		
	}
	
	public List<Endereco> consultarEnderecos(Usuario usuario) throws SQLException {
		
		Pais pais = null;
		Estado estado = null;
		Cidade cidade = null;
		Endereco endereco = null;
		
		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList<Endereco> listaDeEnderecos = new ArrayList<>();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT end_id, end_tipo_residencia, end_tipo_logradouro, ");
		sql.append("end_logradouro, end_numero, end_bairro, end_cep, end_cidade, ");
		sql.append("end_estado, end_pais, end_observacao, end_tipo_endereco FROM enderecos ");
		sql.append("WHERE end_usu_id = ? AND end_esta_ativo = true");
	
		try{
			
			st = con.prepareStatement(sql.toString());
			
			st.setInt(1, usuario.getId());
			
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
	
	public List<CartaoDeCredito> consultarCartoes(Usuario usuario) throws SQLException {
		
		CartaoDeCredito cartao = null;
		BandeiraCartao bandeira = null;
		
		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList<CartaoDeCredito> listaDeCartoes = new ArrayList<>();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT crt_id, crt_numero, crt_nome_impresso, crt_codigo_seguranca, bnd_id, bnd_nome ");
		sql.append("FROM cartoes_de_credito inner join bandeiras_cartao on bnd_id = crt_bnd_id ");
		sql.append("WHERE crt_usu_id = ? AND crt_esta_ativo = true order by crt_numero");
		
		try{
			
			st = con.prepareStatement(sql.toString());
			
			st.setInt(1, usuario.getId());
			
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
	
	public EntidadeDominio consultarLogin(EntidadeDominio entidade) throws SQLException {
	
		for(EntidadeDominio entDom : consultar(entidade)) {
			
			return entDom;	
			
		}
		
		return null;
	}

}
