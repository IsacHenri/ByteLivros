package br.com.ByteLivros.fachada;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ByteLivros.dao.CupomDAO;
import br.com.ByteLivros.dominio.Cupom;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Usuario;
import br.com.ByteLivros.strategy.CalcularFrete;
import br.com.ByteLivros.strategy.IStrategy;

public class FachadaCupom implements IFachada {
	private CupomDAO dao;
    private List<IStrategy> estrategias;

    public FachadaCupom(CupomDAO dao) {
        this.dao = dao;
    }

	@Override
	public String salvar(EntidadeDominio entidade) {
		Cupom cupom = (Cupom) entidade;
		try {
            dao.salvar(cupom);
            return null; // Indica que a operação foi bem-sucedida
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao salvar item: " + e.getMessage();
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
