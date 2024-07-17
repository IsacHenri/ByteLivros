package br.com.ByteLivros.dominio;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Frete {

	private static Endereco endereco;
	
	private static ArrayList<Endereco> listadeEnderecos = new ArrayList<>();
	
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		Frete.endereco = endereco;
	}
	public ArrayList<Endereco> getListadeEnderecos() {
		return listadeEnderecos;
	}
	public void setListadeEnderecos(Endereco endereco) {
		Frete.listadeEnderecos.add(endereco);
	}
}
