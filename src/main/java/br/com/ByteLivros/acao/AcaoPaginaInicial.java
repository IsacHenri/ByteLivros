package br.com.ByteLivros.acao;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Livro;
import br.com.ByteLivros.fachada.Fachada;

public class AcaoPaginaInicial implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Fachada fac = new Fachada();
		Livro livro = new Livro();
		List<EntidadeDominio> entDom = fac.consultar(livro);
		request.setAttribute("EntidadeDominio", entDom);
		return "forward:paginaInicial.jsp";
	}

}
