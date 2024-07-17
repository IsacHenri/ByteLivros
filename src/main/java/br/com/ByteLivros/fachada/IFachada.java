package br.com.ByteLivros.fachada;

import java.util.List;

import br.com.ByteLivros.dominio.EntidadeDominio;

public interface IFachada {
	public String salvar(EntidadeDominio entidade);
	public String alterar(EntidadeDominio entidade);
	public String excluir(EntidadeDominio entidade);
	public List<EntidadeDominio> consultar(EntidadeDominio entidade);
	public EntidadeDominio consultaUnica(EntidadeDominio entidade);
}
