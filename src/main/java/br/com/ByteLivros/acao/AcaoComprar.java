package br.com.ByteLivros.acao;

import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Item;
import br.com.ByteLivros.dominio.Livro;
import br.com.ByteLivros.fachada.Fachada;
import br.com.ByteLivros.dominio.CarrinhoDeCompras;

public class AcaoComprar implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		if(
				   request.getParameter("id") != null &&
				   request.getParameter("qtdeItens") != null &&
				   !request.getParameter("id").isEmpty() &&
				   !request.getParameter("qtdeItens").isEmpty()
				   ) {
					
						Livro livro = new Livro(Integer.valueOf(request.getParameter("id")),
												request.getParameter("tituloItem"),
												new BigDecimal(request.getParameter("precoItem")),
												Integer.valueOf(request.getParameter("qtdeEmEstoque")),
												request.getParameter("codImage"),
												new BigDecimal(request.getParameter("alturaProduto")),
												new BigDecimal(request.getParameter("larguraProduto")),
												new BigDecimal(request.getParameter("profundidadeProduto")));
						
						Item item = new Item(Integer.valueOf(request.getParameter("qtdeItens")), livro, new BigDecimal(request.getParameter("precoItem")));
						
						Fachada fac = new Fachada();
						
						Fachada fac2 = new Fachada();
						
						fac.adicionarItemAoCarrinho(item);
						EntidadeDominio entidade = fac2.consultaUnica(item.getLivro());
						request.setAttribute("EntidadeObjeto", entidade);
						
						
						CarrinhoDeCompras cdc = new CarrinhoDeCompras();
						
						request.setAttribute("Itens", cdc.getItens());
						
						request.setAttribute("EntidadeLista", entidade);
						return "forward:paginaCarrinho.jsp";
					}
		return null;
	}

}
