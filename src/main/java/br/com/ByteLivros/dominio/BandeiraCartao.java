package br.com.ByteLivros.dominio;

public class BandeiraCartao extends EntidadeDominio{
	
	private String bandeira;

	public BandeiraCartao(String bandeira) {
		this.bandeira = bandeira;
	}

	
	public BandeiraCartao() {
		// TODO Auto-generated constructor stub
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}
	
	

}
