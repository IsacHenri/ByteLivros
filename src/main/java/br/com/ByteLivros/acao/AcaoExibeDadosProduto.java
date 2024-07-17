package br.com.ByteLivros.acao;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Livro;
import br.com.ByteLivros.fachada.Fachada;

public class AcaoExibeDadosProduto implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String paramAcao = request.getParameter("acao");
		
		Livro entidadeDominio = new Livro();
		
		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);
		
		System.out.println("Consultando dados do id " + id);
		
		entidadeDominio.setId(id);
		Fachada fac = new Fachada();
	
		EntidadeDominio entDom = fac.consultaUnica(entidadeDominio);

		request.setAttribute("EntidadeLista", entDom);
		request.setAttribute("EntidadeObjeto", entDom);
		
		return "forward:paginaProduto.jsp";
	}

}
