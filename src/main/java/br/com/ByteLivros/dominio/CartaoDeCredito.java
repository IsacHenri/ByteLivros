package br.com.ByteLivros.dominio;

import java.util.Objects;

public class CartaoDeCredito extends EntidadeDominio{
	
	private String nrCartao;
	private String nomeCartao;
	private String codSeguranca;
	private Integer cartaoUsuarioId;
	private BandeiraCartao bandeira;
	
	public CartaoDeCredito(String nrCartao, String nomeCartao, String codSeguranca, BandeiraCartao bandeira) {
		this.nrCartao = nrCartao;
		setNomeCartao(nomeCartao);
		this.codSeguranca = codSeguranca;
		this.bandeira = bandeira;
	}
	public CartaoDeCredito() {
		// TODO Auto-generated constructor stub
	}
	public CartaoDeCredito(Integer id) {
		setId(id);
	}
	public String getNrCartao() {
		return nrCartao;
	}
	public void setNrCartao(String nrCartao) {
		this.nrCartao = nrCartao;
	}
	public String getNomeCartao() {
		return nomeCartao;
	}
	public void setNomeCartao(String nomeCartao) {
		Objects.requireNonNull(nomeCartao);
		nomeCartao = nomeCartao.trim().toUpperCase();
		this.nomeCartao = nomeCartao;
	}
	public String getCodSeguranca() {
		return codSeguranca;
	}
	public void setCodSeguranca(String codSeguranca) {
		this.codSeguranca = codSeguranca;
	}
	public Integer getCartaoUsuarioId() {
		return cartaoUsuarioId;
	}
	public void setCartaoUsuarioId(Integer cartaoUsuarioId) {
		this.cartaoUsuarioId = cartaoUsuarioId;
	}
	public BandeiraCartao getBandeira() {
		return bandeira;
	}
	public void setBandeira(BandeiraCartao bandeira) {
		this.bandeira = bandeira;
	}
	
	

}
