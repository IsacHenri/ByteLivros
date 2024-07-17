package br.com.ByteLivros.acao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Item;
import br.com.ByteLivros.fachada.Fachada;


public class AcaoVisualizarItemDoCarrinho implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Item item = new Item(Integer.valueOf(request.getParameter("id")));
		Fachada fac = new Fachada();
		EntidadeDominio entDom = (EntidadeDominio) fac.consultarItemDoCarrinho(item);
		request.setAttribute("EntidadeObjeto", entDom);
		return "forward:paginaItemCarrinho.jsp";
	}
	
}
