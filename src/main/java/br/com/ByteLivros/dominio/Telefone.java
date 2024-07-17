package br.com.ByteLivros.dominio;

public class Telefone extends EntidadeDominio{
	
	private String numero;
	private String tipo;
	
	public Telefone(String numero, String tipo) {
		this.numero = numero;
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
}
