package br.com.ByteLivros.acao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ByteLivros.dominio.CarrinhoDeCompras;
import br.com.ByteLivros.dominio.Item;
import br.com.ByteLivros.fachada.Fachada;


public class AcaoRemoverDoCarrinho implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Item item = new Item(Integer.valueOf(request.getParameter("id")));
		Fachada fac = new Fachada();
		
		fac.removerItensDoCarrinho(item);
		
		CarrinhoDeCompras cdc = new CarrinhoDeCompras();
		request.setAttribute("EntidadeObjeto", item);
		
		request.setAttribute("Itens", cdc.getItens());
		return "forward:paginaCarrinho.jsp";
	}

}
