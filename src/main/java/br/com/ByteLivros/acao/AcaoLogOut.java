package br.com.ByteLivros.acao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AcaoLogOut implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		HttpSession sessao = request.getSession();
		
//		sessao.removeAttribute("usuarioLogado");
		sessao.invalidate();
		return "redirect:controller?acao=PaginaInicial";
	}

}
