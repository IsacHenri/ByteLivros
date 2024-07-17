package br.com.ByteLivros.fachada;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.ByteLivros.controle.Conexao;
import br.com.ByteLivros.dao.BandeiraCartaoDAO;
import br.com.ByteLivros.dao.CartaoDeCreditoDAO;
import br.com.ByteLivros.dao.CupomDAO;
import br.com.ByteLivros.dao.EnderecoDAO;
import br.com.ByteLivros.dao.GraficoDAO;
import br.com.ByteLivros.dao.ItemDAO;
import br.com.ByteLivros.dao.LivroDAO;
import br.com.ByteLivros.dao.PedidoDAO;
import br.com.ByteLivros.dao.UsuarioDAO;
import br.com.ByteLivros.dominio.BandeiraCartao;
import br.com.ByteLivros.dominio.CarrinhoDeCompras;
import br.com.ByteLivros.dominio.CartaoDeCredito;
import br.com.ByteLivros.dominio.Cupom;
import br.com.ByteLivros.dominio.Endereco;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Item;
import br.com.ByteLivros.dominio.Livro;
import br.com.ByteLivros.dominio.Pedido;
import br.com.ByteLivros.dominio.Usuario;
import br.com.ByteLivros.strategy.AdicionarAoCarrinho;
import br.com.ByteLivros.strategy.AlterarItensDoCarrinho;
import br.com.ByteLivros.strategy.CalcularPrecoItens;
import br.com.ByteLivros.strategy.CalcularPrecoTotalDoCarrinho;
import br.com.ByteLivros.strategy.IStrategy;
import br.com.ByteLivros.strategy.RemoveProdutosDoCarrinho;

public class Fachada implements IFachada {
	private Map<Class<? extends EntidadeDominio>, IFachada> operacoes;

    public Fachada() {
        this.operacoes = new HashMap<>();
        inicializarOperacoes();
    }
    
	private void inicializarOperacoes() {
		Conexao conn = new Conexao();
		Connection connection = conn.recuperarConexao();
	    // Inicializa as fachadas para cada entidade e seus respectivos DAOs
	    operacoes.put(Usuario.class, new FachadaUsuario(new UsuarioDAO(connection)));
	    operacoes.put(Endereco.class, new FachadaEndereco(new EnderecoDAO(connection)));
	    operacoes.put(CartaoDeCredito.class, new FachadaCartaoDeCredito(new CartaoDeCreditoDAO(connection)));
	    operacoes.put(Item.class, new FachadaItem(new ItemDAO(connection)));
	    operacoes.put(Pedido.class, new FachadaPedido(new PedidoDAO(connection)));
	    operacoes.put(Livro.class, new FachadaLivro(new LivroDAO(connection)));
	    operacoes.put(BandeiraCartao.class, new FachadaBandeiraCartao(new BandeiraCartaoDAO(connection)));
	    operacoes.put(Cupom.class, new FachadaCupom(new CupomDAO(connection)));
	    // Adicione mais fachadas conforme necessário para outras entidades
	}
	
	@Override
	public String salvar(EntidadeDominio entidade) {
        IFachada fachada = operacoes.get(entidade.getClass());
        if (fachada != null) {
            return fachada.salvar(entidade);
        } else {
            return "Operação não suportada para a entidade: " + entidade.getClass().getSimpleName();
        }
    }
	
	@Override
	public String alterar(EntidadeDominio entidade) {
		IFachada fachada = operacoes.get(entidade.getClass());
        if (fachada != null) {
            return fachada.alterar(entidade);
        } else {
            return null;
        }
	}
	@Override
	public String excluir(EntidadeDominio entidade) {
		IFachada fachada = operacoes.get(entidade.getClass());
        if (fachada != null) {
            return fachada.excluir(entidade);
        } else {
            return null;
        }
	}
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		IFachada fachada = operacoes.get(entidade.getClass());
        if (fachada != null) {
            return fachada.consultar(entidade);
        } else {
            return null;
        }
	}

	@Override
	public EntidadeDominio consultaUnica(EntidadeDominio entidade) {
		IFachada fachada = operacoes.get(entidade.getClass());
        if (fachada != null) {
            return fachada.consultaUnica(entidade);
        } else {
            return null;
        }
	}
	
	public String adicionarItemAoCarrinho(EntidadeDominio entidade) {
		List<IStrategy> rns = new ArrayList<>();
		rns.add(new CalcularPrecoItens());
		rns.add(new CalcularPrecoTotalDoCarrinho());
		rns.add(new AdicionarAoCarrinho());
		
		StringBuilder msgs = new StringBuilder();
		
		for(IStrategy s:rns) {
			String msg = s.processar(entidade);
			if(msg != null) {
				msgs.append(msg);
			}			
		}
		if(msgs.length()==0) {
			return null;
		}else {
			return msgs.toString();
		}
	}
	
	public String alterarItemDoCarrinho(EntidadeDominio entidade) {
		List<IStrategy> rns = new ArrayList<>();
		rns.add(new AlterarItensDoCarrinho());
		
		StringBuilder msgs = new StringBuilder();
		
		for(IStrategy s:rns) {
			String msg = s.processar(entidade);
			if(msg != null) {
				msgs.append(msg);
			}			
		}
		if(msgs.length()==0) {
			return null;
		}else {
			return msgs.toString();
		}
	}
	
	
	public Item consultarItemDoCarrinho(EntidadeDominio entidade) {
		
		Item item = (Item)entidade;
		
		CarrinhoDeCompras cdc = new CarrinhoDeCompras();
		
		for(Item itm : cdc.getItens()) {
			if(itm.getId() == item.getId()) {
				return itm;
			}
		}
		return null;
	}
	
	public String removerItensDoCarrinho(EntidadeDominio entidade) {
		List<IStrategy> rns = new ArrayList<>();
		rns.add(new RemoveProdutosDoCarrinho());
		
		StringBuilder msgs = new StringBuilder();
		
		for(IStrategy s:rns) {
			String msg = s.processar(entidade);
			if(msg != null) {
				msgs.append(msg);
			}			
		}
		if(msgs.length()==0) {
			return null;
		}else {
			return msgs.toString();
		}
	}
	public List<EntidadeDominio> consultarTudo() {
		// TODO Auto-generated method stub
		Conexao conn = new Conexao();
		Connection connection = conn.recuperarConexao();
		List<EntidadeDominio> lista = new ArrayList<>();
		
			UsuarioDAO usu =  new UsuarioDAO(connection);
			try {
				lista = usu.consultarTudo();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		return lista;
	}	
	
	public List<EntidadeDominio> consultarLivros(EntidadeDominio entidade) {
		List<EntidadeDominio> lista = new ArrayList<>();
		Conexao conn = new Conexao();
		Connection connection = conn.recuperarConexao();
		LivroDAO dao = new LivroDAO(connection);
		try {
			lista = dao.livrosComprados(entidade);
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return lista;
	}
	
	public EntidadeDominio consultaGrafico(EntidadeDominio entidade) {

		Conexao conn = new Conexao();
		Connection connection = conn.recuperarConexao();
		GraficoDAO gDao = new GraficoDAO(connection);
		EntidadeDominio grafico = new EntidadeDominio();
		try {
			grafico = gDao.consultaGrafico(entidade);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return grafico;

	}
	
		
}
