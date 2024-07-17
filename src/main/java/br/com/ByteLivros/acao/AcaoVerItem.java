package br.com.ByteLivros.acao;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ByteLivros.dominio.BandeiraCartao;
import br.com.ByteLivros.dominio.CarrinhoDeCompras;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Item;
import br.com.ByteLivros.fachada.Fachada; 


public class AcaoVerItem implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Item item = new Item(Integer.valueOf(request.getParameter("id")));
		
		
		Fachada fac = new Fachada();
		
		
		BandeiraCartao bnd = new BandeiraCartao();
		
		List<EntidadeDominio> listaClientes = fac.consultar(bnd);
		
		Fachada fac2 = new Fachada();
	
		EntidadeDominio entDom = fac2.consultaUnica(item);
		CarrinhoDeCompras cdc = new CarrinhoDeCompras();
		
		request.setAttribute("Itens", cdc.getItens());
		
		request.setAttribute("EntidadeLista", listaClientes);
		request.setAttribute("EntidadeObjeto", entDom);
		return "forward:paginaStatusProduto.jsp";
	}

}
