package br.com.ByteLivros.fachada;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ByteLivros.dao.BandeiraCartaoDAO;
import br.com.ByteLivros.dominio.EntidadeDominio;

public class FachadaBandeiraCartao implements IFachada {
	private BandeiraCartaoDAO dao;

    public FachadaBandeiraCartao(BandeiraCartaoDAO dao) {
        this.dao = dao;
    }
	@Override
	public String salvar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excluir(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        List<EntidadeDominio> lista = new ArrayList<>();
        try {
            lista = dao.consultar(entidade);
        } catch (SQLException e) {
            e.printStackTrace();
            // Aqui você pode tratar o erro de alguma forma apropriada para o seu sistema
        }
        return lista;
    }
	@Override
	public EntidadeDominio consultaUnica(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
