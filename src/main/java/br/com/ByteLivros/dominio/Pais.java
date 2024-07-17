package br.com.ByteLivros.dominio;

import java.util.Objects;

public class Pais extends EntidadeDominio{
	
	private String descricao;

	public Pais(String descricao) {
		setDescricao(descricao);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		Objects.requireNonNull(descricao);
		descricao = descricao.trim().toUpperCase();
		this.descricao = descricao;
	}
	
	
	
}
