package br.com.ByteLivros.acao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ByteLivros.dominio.CarrinhoDeCompras;
import br.com.ByteLivros.dominio.Pedido;


public class AcaoCarrinho implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		CarrinhoDeCompras cdc = new CarrinhoDeCompras();
		
		request.setAttribute("Itens", cdc.getItens());
		
		
		Pedido pedido = new Pedido();
		pedido.setId(null);
		pedido.setEndereco(null);
		pedido.setCartao(null);
		
		return "forward:paginaCarrinho.jsp";
	}

}
