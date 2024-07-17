package br.com.ByteLivros.dominio;

import java.util.Objects;

public class Cidade extends EntidadeDominio{
	
	private Estado estado;
    private String descricao;
    
    
	public Cidade(Estado estado, String descricao) {
		this.estado = estado;
		setDescricao(descricao);
	}
	

	public Cidade() {
		super();
	}


	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
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
