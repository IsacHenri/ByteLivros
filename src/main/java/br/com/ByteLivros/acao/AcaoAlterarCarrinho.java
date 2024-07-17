package br.com.ByteLivros.acao;

import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ByteLivros.dominio.CarrinhoDeCompras;
import br.com.ByteLivros.dominio.Item;
import br.com.ByteLivros.dominio.Livro;
import br.com.ByteLivros.fachada.Fachada;


public class AcaoAlterarCarrinho implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Livro livro = new Livro(new BigDecimal(request.getParameter("precoItem")));
		Item item = new Item(Integer.valueOf(request.getParameter("id")), 
					Integer.valueOf(request.getParameter("qtdeItens")), 
					livro);
		Fachada fac = new Fachada();
		
		fac.alterarItemDoCarrinho(item);
		
		CarrinhoDeCompras cdc = new CarrinhoDeCompras();
		
		request.setAttribute("Itens", cdc.getItens());
		
		request.setAttribute("EntidadeObjeto", item);
		return "forward:paginaCarrinho.jsp";
	}

}
