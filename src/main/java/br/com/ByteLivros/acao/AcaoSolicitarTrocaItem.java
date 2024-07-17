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

public class AcaoSolicitarTrocaItem implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Item item = new Item(Integer.valueOf(request.getParameter("idItem")), 
   			 request.getParameter("statusItem"));

		Pedido pedido = new Pedido(Integer.valueOf(request.getParameter("idPedido")), item);
		
		Fachada fac = new Fachada();
		
		Fachada fac2 = new Fachada();
		if(pedido.getItem().getId()!= null) {
			
			fac.alterar(pedido.getItem());
		
			EntidadeDominio entDom = fac2.consultaUnica(pedido);
			request.setAttribute("EntidadeObjeto", entDom);
			
			return "forward:paginaItensPedido.jsp";
		
		}else {

			fac.alterar(pedido);
			
			Pedido pedido2 = new Pedido();
			
			List<EntidadeDominio> entDom = fac2.consultar(pedido2);
			CarrinhoDeCompras cdc = new CarrinhoDeCompras();
			
			request.setAttribute("Itens", cdc.getItens());
			
			request.setAttribute("EntidadeLista", entDom);
			return "forward:paginaItensPedido.jsp";
		}
	}

}
