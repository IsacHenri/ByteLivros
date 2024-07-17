package br.com.ByteLivros.dominio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoDeCompras extends EntidadeDominio{

	private static List<Item> listaItens = new ArrayList<>();
	private BigDecimal valorCarrinho;
	private static Integer qtdeItensCarrinho;
	private static Integer chaveSequencial = 1;
	
	
	
	public CarrinhoDeCompras() {
		// TODO Auto-generated constructor stub
	}

	public void adiciona(Item item) {
		item.setId(CarrinhoDeCompras.chaveSequencial++);
		listaItens.add(item);
		
	}
	
	public List<Item> getItens(){
		return CarrinhoDeCompras.listaItens;
	}

	public BigDecimal getValorCarrinho() {
		return valorCarrinho;
	}

	public void setValorCarrinho(BigDecimal valorCarrinho) {
		this.valorCarrinho = valorCarrinho;
	}

	public Integer getQtdeItensCarrinho() {
		return qtdeItensCarrinho;
	}

	public void setQtdeItensCarrinho(Integer qtdeItensCarrinho) {
		CarrinhoDeCompras.qtdeItensCarrinho = qtdeItensCarrinho;
	}
	public static void setListaItens(List<Item> listaItens) {
		CarrinhoDeCompras.listaItens = listaItens;
	}

	public static Integer getChaveSequencial() {
		return chaveSequencial;
	}

	public static void setChaveSequencial(Integer chaveSequencial) {
		CarrinhoDeCompras.chaveSequencial = chaveSequencial;
	}
}
