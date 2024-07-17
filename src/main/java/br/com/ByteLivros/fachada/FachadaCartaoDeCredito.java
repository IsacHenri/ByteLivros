package br.com.ByteLivros.fachada;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ByteLivros.dao.CartaoDeCreditoDAO;
import br.com.ByteLivros.dominio.CartaoDeCredito;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Usuario;
import br.com.ByteLivros.strategy.CalcularFrete;
import br.com.ByteLivros.strategy.IStrategy;
import br.com.ByteLivros.strategy.ValidarCpf;

public class FachadaCartaoDeCredito implements IFachada {
	private CartaoDeCreditoDAO dao;
    private List<IStrategy> estrategias;

    public FachadaCartaoDeCredito(CartaoDeCreditoDAO dao) {
        this.dao = dao;
        this.estrategias = new ArrayList<>();
        // Adicione as estratégias específicas para a entidade Usuario
        this.estrategias.add(new ValidarCpf()); // Estratégia para validar o CPF do usuário
        // Adicione outras estratégias, se necessário
    }
	@Override
	public String salvar(EntidadeDominio entidade) {
		CartaoDeCredito cartao = (CartaoDeCredito) entidade;
	    StringBuilder msgs = new StringBuilder();

	    // Executa as estratégias antes de salvar
	    for (IStrategy estrategia : estrategias) {
	        String msg = estrategia.processar(cartao);
	        if (msg != null) {
	            msgs.append(msg);
	        }
	    }

	    if (msgs.length() == 0) {
	        try {
	            dao.salvar(cartao);
	            return null; // Indica que a operação foi bem-sucedida
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return "Erro ao salvar cartão de crédito: " + e.getMessage();
	        }
	    } else {
	        return msgs.toString(); // Retorna mensagens de validação se houver
	    }
	}

	@Override
	public String alterar(EntidadeDominio entidade) {
		try {
			dao.alterar(entidade);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String excluir(EntidadeDominio entidade) {
    	try {
			dao.excluir(entidade);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return null;
    }

    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
    	List<EntidadeDominio> lista = new ArrayList<>();
		
		try {
			lista = dao.consultar(entidade);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
    }
	@Override
	public EntidadeDominio consultaUnica(EntidadeDominio entidade) {
		List<EntidadeDominio> lista = new ArrayList<>();
	    try {
	        lista = dao.consultar(entidade);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    for (EntidadeDominio entDom : lista) {
	    	if(entDom.getClass().getName().equals(Usuario.class.getName())) {
				CalcularFrete cf = new CalcularFrete();
				cf.processar(entDom);
			}
	            return entDom;
	        }
	    return null;
	}

}
