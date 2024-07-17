package br.com.ByteLivros.dominio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cupom extends EntidadeDominio{
	
	private String codigo;
	private BigDecimal desconto;
	private Boolean estaAtivo;
	private Boolean isUtilizado;
	private String tipo;
	private Pedido pedido;
	private Usuario usuario;
	
	private static List<Cupom> listaCupons = new ArrayList<>();
	public void adiciona(Cupom cupom) {
		listaCupons.add(cupom);
		
	}
	
	public Cupom(Integer id, BigDecimal desconto) {
		setId(id);
		this.desconto = desconto;
	}
	
	public Cupom(Integer id, String codigo, BigDecimal desconto, String tipo) {
		setId(id);
		this.codigo = codigo;
		this.desconto = desconto;
		this.tipo = tipo;
	}

	public Cupom() {
	}


	public List<Cupom> getCupons(){
		return Cupom.listaCupons;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public BigDecimal getDesconto() {
		return desconto;
	}
	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}
	public Boolean getEstaAtivo() {
		return estaAtivo;
	}
	public void setEstaAtivo(Boolean estaAtivo) {
		this.estaAtivo = estaAtivo;
	}
	public Boolean getIsUtilizado() {
		return isUtilizado;
	}
	public void setIsUtilizado(Boolean isUtilizado) {
		this.isUtilizado = isUtilizado;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
