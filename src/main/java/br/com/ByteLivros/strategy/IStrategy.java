package br.com.ByteLivros.strategy;

import br.com.ByteLivros.dominio.EntidadeDominio;

public interface IStrategy {
	public String processar(EntidadeDominio entidade);
}
