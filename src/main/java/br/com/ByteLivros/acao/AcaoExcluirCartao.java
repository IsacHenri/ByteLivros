package br.com.ByteLivros.acao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ByteLivros.dominio.CartaoDeCredito;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.fachada.Fachada;

public class AcaoExcluirCartao implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		EntidadeDominio entidadeDominio = new CartaoDeCredito();
		
		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);
		
		System.out.println("Excluindo " + entidadeDominio.getClass().getName() +" "+ id);
		
		entidadeDominio.setId(id);
		Fachada fac = new Fachada();
		fac.excluir(entidadeDominio);
		
		
		return "redirect:controller?acao=PaginaConsultaUsuarios";
	}

}
