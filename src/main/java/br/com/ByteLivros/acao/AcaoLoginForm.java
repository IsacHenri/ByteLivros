package br.com.ByteLivros.acao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AcaoLoginForm implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		HttpSession sessao = request.getSession();
		String msg = (String) sessao.getAttribute("mensagem");
		System.out.println(msg);
		request.setAttribute("mensagem", msg);
		return "forward:paginaLogin.jsp";
	}

}
