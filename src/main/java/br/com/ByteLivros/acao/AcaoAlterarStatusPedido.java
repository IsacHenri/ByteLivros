package br.com.ByteLivros.acao;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ByteLivros.dominio.CarrinhoDeCompras;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Item;
import br.com.ByteLivros.dominio.Pedido;
import br.com.ByteLivros.dominio.Usuario;
import br.com.ByteLivros.fachada.Fachada;


public class AcaoAlterarStatusPedido implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Usuario usuario = new Usuario(Integer.valueOf(request.getParameter("idCliente")));
		Pedido pedido = new Pedido();
		Item item = new Item(request.getParameter("statusPedido"));

		pedido = new Pedido(Integer.valueOf(request.getParameter("idPedido")),
							new BigDecimal(request.getParameter("valorPedido")),
							item,
							usuario);
		
		Fachada fac = new Fachada();
		
		Fachada fac2 = new Fachada();
		
		if(pedido.getItem().getId()!= null) {
			
			fac.alterar(pedido.getItem());
		
			EntidadeDominio entDom = fac2.consultaUnica(pedido);
			request.setAttribute("EntidadeObjeto", entDom);
			
			CarrinhoDeCompras cdc = new CarrinhoDeCompras();
			
			request.setAttribute("Itens", cdc.getItens());
			
			request.setAttribute("QtdeItens", cdc.getQtdeItensCarrinho());
			return "redirect:controller?acao=ConsultarPedido";
		
		}else {

			fac.alterar(pedido);
			
			Pedido pedido2 = new Pedido();
			
			List<EntidadeDominio> entDom = fac2.consultar(pedido2);
			request.setAttribute("EntidadeLista", entDom);
			
			return "redirect:controller?acao=ConsultarPedido";
		}
	}

}
