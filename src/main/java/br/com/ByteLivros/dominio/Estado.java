package br.com.ByteLivros.dominio;

import java.util.Objects;

public class Estado extends EntidadeDominio{

	private String descricao;
	private Pais pais;
	
	
	public Estado(String descricao, Pais pais) {
		setDescricao(descricao);
		this.pais = pais;
	}
	public Estado() {
		super();
	}
	
	public Estado(String descricao) {
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

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	
	
	
}
