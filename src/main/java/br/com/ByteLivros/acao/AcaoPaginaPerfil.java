package br.com.ByteLivros.acao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ByteLivros.dominio.Usuario;

public class AcaoPaginaPerfil implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		HttpSession sessao = request.getSession();
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		String msg = (String) sessao.getAttribute("mensagem");
		System.out.println("Enviando para pagina perfil");
		System.out.println(msg);
		request.setAttribute("usuarioLogado", usuarioLogado);
		request.setAttribute("mensagem", msg);
		if(usuarioLogado.getIsAdmin() == false) {
			return "forward:paginaPerfil.jsp";
		}else {
			return "forward:paginaPerfilAdm.jsp";
		}
		
	}

}
