package br.com.ByteLivros.dominio;

import java.math.BigDecimal;

public class GrupoDePrecificacao extends EntidadeDominio{
	
	private String descricao;
	private BigDecimal margemLucro;
	
	public GrupoDePrecificacao(String descricao, BigDecimal margemLucro) {
		this.descricao = descricao;
		this.margemLucro = margemLucro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getMargemLucro() {
		return margemLucro;
	}

	public void setMargemLucro(BigDecimal margemLucro) {
		this.margemLucro = margemLucro;
	}
		
	

}
