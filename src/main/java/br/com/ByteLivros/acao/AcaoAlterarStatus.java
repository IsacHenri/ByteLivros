package br.com.ByteLivros.acao;

import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ByteLivros.dominio.CarrinhoDeCompras;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Item;
import br.com.ByteLivros.dominio.Usuario;
import br.com.ByteLivros.fachada.Fachada;

public class AcaoAlterarStatus implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		Usuario usuario = new Usuario(Integer.valueOf(request.getParameter("idUsuario")));
		
		Item item = new Item(Integer.valueOf(request.getParameter("idItem")), 
				             request.getParameter("statusItem"),
				             new BigDecimal(request.getParameter("precoItem")),
				             usuario);
		EntidadeDominio entDom = item;
		Fachada fac = new Fachada();
		
		Fachada fac2 = new Fachada();
		
		fac.alterar(entDom);
		EntidadeDominio item2 = fac2.consultaUnica(entDom);
		
		CarrinhoDeCompras cdc = new CarrinhoDeCompras();
		
		request.setAttribute("Itens", cdc.getItens());
		
		request.setAttribute("EntidadeLista", item2);
		
		request.setAttribute("EntidadeObjeto", item2);
		
		return "forward:paginaStatusProduto.jsp";
	}

}
