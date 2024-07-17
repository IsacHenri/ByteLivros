package br.com.ByteLivros.acao;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ByteLivros.dominio.CarrinhoDeCompras;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Item;
import br.com.ByteLivros.dominio.Pedido;
import br.com.ByteLivros.fachada.Fachada;

public class AcaoSolicitarTrocaPedido implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Pedido pedido = new Pedido();
		Item item = new Item(request.getParameter("statusPedido"));

		pedido = new Pedido(Integer.valueOf(request.getParameter("idPedido")), item);
		Fachada fac = new Fachada();
		
		Fachada fac2 = new Fachada();
		
		fac.alterar(pedido);
		
		List<EntidadeDominio> entDom = fac2.consultar(pedido);
		request.setAttribute("EntidadeLista", entDom);
		request.setAttribute("EntidadeObjeto", entDom);
		
		CarrinhoDeCompras cdc = new CarrinhoDeCompras();
		
		request.setAttribute("Itens", cdc.getItens());
		
		request.setAttribute("QtdeItens", cdc.getQtdeItensCarrinho());
		
		return "redirect:controller?acao=ConsultarPedido";
	}

}
	