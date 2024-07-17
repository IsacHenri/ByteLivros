package br.com.ByteLivros.fachada;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ByteLivros.dao.ItemDAO;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Item;
import br.com.ByteLivros.dominio.Usuario;
import br.com.ByteLivros.strategy.CalcularFrete;
import br.com.ByteLivros.strategy.IStrategy;

public class FachadaItem implements IFachada {
	private ItemDAO dao;
    private List<IStrategy> estrategias;

    public FachadaItem(ItemDAO dao) {
        this.dao = dao;
        this.estrategias = new ArrayList<>();
        // Adicionando as estratégias específicas para a entidade Item
        this.estrategias.add(new CalcularFrete());
        // Adicione outras estratégias, se necessário
    }

    @Override
    public String salvar(EntidadeDominio entidade) {
        Item item = (Item) entidade;
        StringBuilder msgs = new StringBuilder();

        // Executa as estratégias antes de salvar
        for (IStrategy estrategia : estrategias) {
            String msg = estrategia.processar(item);
            if (msg != null) {
                msgs.append(msg);
            }
        }

        if (msgs.length() == 0) {
            try {
                dao.salvar(item);
                return null; // Indica que a operação foi bem-sucedida
            } catch (SQLException e) {
                e.printStackTrace();
                return "Erro ao salvar item: " + e.getMessage();
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
