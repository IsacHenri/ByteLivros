package br.com.ByteLivros.acao;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ByteLivros.dominio.CarrinhoDeCompras;
import br.com.ByteLivros.dominio.Cupom;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Usuario;
import br.com.ByteLivros.fachada.Fachada;

public class AcaoConsultarCupons implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Cupom cupom = new Cupom();
		
		Usuario usuario = new Usuario(Integer.valueOf(request.getParameter("id")));
		
		cupom.setUsuario(usuario);
		Fachada fac = new Fachada();
		
		
		List<EntidadeDominio> listaClientes = fac.consultar(cupom);
		request.setAttribute("EntidadeLista", listaClientes);
		
		
		request.setAttribute("EntidadeObjeto", cupom);
		
		CarrinhoDeCompras cdc = new CarrinhoDeCompras();
		
		request.setAttribute("Itens", cdc.getItens());
		
		request.setAttribute("QtdeItens", cdc.getQtdeItensCarrinho());
		request.setAttribute("CuponsSelecionados", listaClientes);
		return "forward:paginaCupons.jsp";
	}

}
