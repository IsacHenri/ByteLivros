package br.com.ByteLivros.fachada;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ByteLivros.dao.PedidoDAO;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Usuario;
import br.com.ByteLivros.strategy.CalcularFrete;

public class FachadaPedido implements IFachada {
	private PedidoDAO dao;

    public FachadaPedido(PedidoDAO dao) {
        this.dao = dao;
    }

    @Override
    public String salvar(EntidadeDominio entidade) {
    	try {
    		dao.salvar(entidade);
    		return null; // Indica que a operação foi bem-sucedida
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return "Erro ao salvar pedido: " + e.getMessage();
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
