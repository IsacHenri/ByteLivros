package br.com.ByteLivros.dominio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Item extends EntidadeDominio{
	
	private Integer qtdeProdutos;
	private Livro livro;
	private BigDecimal precoItem;
	private String statusPedido;
	private List<Item> listaDeItens = new ArrayList<>();
	private Usuario usuario;
	
	public Item(Integer qtdeProdutos, Livro livro, BigDecimal precoCompra) {
		this.qtdeProdutos = qtdeProdutos;
		this.livro = livro;
		this.precoItem = precoCompra;
	}
	public Item(Integer id, Integer qtdeProdutos, BigDecimal precoCompra, String statusPedido, Livro livro) {
		setId(id);
		this.qtdeProdutos = qtdeProdutos;
		this.precoItem = precoCompra;
		this.statusPedido = statusPedido;
		this.livro = livro;
	}
	public Item(Integer id, Integer qtdeProdutos, BigDecimal precoCompra, String statusPedido, Livro livro, Usuario usuario) {
		setId(id);
		this.qtdeProdutos = qtdeProdutos;
		this.precoItem = precoCompra;
		this.statusPedido = statusPedido;
		this.livro = livro;
		this.usuario = usuario;
	}
	public Item(Integer qtdeProdutos, BigDecimal precoCompra, String statusPedido, Livro livro) {
		this.qtdeProdutos = qtdeProdutos;
		this.precoItem = precoCompra;
		this.statusPedido = statusPedido;
		this.livro = livro;
	}
	public Item(Integer id) {
		setId(id); 	
	}
	public Item(Integer id, String statusPedido) {
		setId(id); 	
		this.statusPedido = statusPedido;
	}
	public Item(Integer id, String statusPedido, BigDecimal preco, Usuario usuario) {
		setId(id); 	
		this.statusPedido = statusPedido;
		this.precoItem = preco;
		this.usuario = usuario;
	}
	public Item(Integer id, Integer qtdeProdutos, Livro livro) {
		setId(id); 	
		this.qtdeProdutos = qtdeProdutos;
		this.livro = livro;
	}
	public Item(String status) {
		this.statusPedido = status;
	}
	public Item() {
	}
	public Item(String status, BigDecimal preco) {
		this.statusPedido = status;
		this.precoItem = preco;
	}
	public Integer getQtdeProdutos() {
		return qtdeProdutos;
	}

	public void setQtdeProdutos(Integer qtdeProdutos) {
		this.qtdeProdutos = qtdeProdutos;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public BigDecimal getPrecoItem() {
		return precoItem;
	}

	public void setPrecoItem(BigDecimal precoCompra) {
		this.precoItem = precoCompra;
	}
	public String getStatusPedido() {
		return statusPedido;
	}
	public void setStatusPedido(String statusPedido) {
		this.statusPedido = statusPedido;
	}
	public List<Item> getListaDeItens() {
		return listaDeItens;
	}
	public void setListaDeItens(Item item) {
		this.listaDeItens.add(item);
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
