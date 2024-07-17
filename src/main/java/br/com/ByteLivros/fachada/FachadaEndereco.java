package br.com.ByteLivros.fachada;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ByteLivros.dao.EnderecoDAO;
import br.com.ByteLivros.dominio.Endereco;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Usuario;
import br.com.ByteLivros.strategy.CalcularFrete;
import br.com.ByteLivros.strategy.IStrategy;
import br.com.ByteLivros.strategy.ValidarCpf;

public class FachadaEndereco implements IFachada {

	private EnderecoDAO dao;
    private List<IStrategy> estrategias;

    public FachadaEndereco(EnderecoDAO dao) {
        this.dao = dao;
        this.estrategias = new ArrayList<>();
        this.estrategias.add(new ValidarCpf());
        // Adicione as estratégias específicas para a entidade Endereco
        // this.estrategias.add(new SuaEstrategia()); // Estratégia personalizada, se necessário
    }

    public String salvar(EntidadeDominio entidade) {
    	Endereco endereco = (Endereco) entidade;
        StringBuilder msgs = new StringBuilder();

        // Executa as estratégias antes de salvar
        for (IStrategy estrategia : estrategias) {
            String msg = estrategia.processar(endereco);
            if (msg != null) {
                msgs.append(msg);
            }
        }

        if (msgs.length() == 0) {
            try {
                dao.salvar(endereco);
                return null; // Indica que a operação foi bem-sucedida
            } catch (SQLException e) {
                e.printStackTrace();
                return "Erro ao salvar endereço: " + e.getMessage();
            }
        } else {
            return msgs.toString(); // Retorna mensagens de validação se houver
        }
    }

    public String alterar(EntidadeDominio entidade) {
    	try {
			dao.alterar(entidade);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
    	return null;
    }

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

