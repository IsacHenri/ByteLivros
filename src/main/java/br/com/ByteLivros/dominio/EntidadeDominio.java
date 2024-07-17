package br.com.ByteLivros.dominio;

import java.time.LocalDate;

public class EntidadeDominio {
	
	private Integer id;

	private LocalDate dtCadastro;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(LocalDate dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	
	
}
