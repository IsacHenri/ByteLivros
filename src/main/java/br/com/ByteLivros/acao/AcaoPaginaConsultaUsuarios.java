package br.com.ByteLivros.acao;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Login;
import br.com.ByteLivros.dominio.Usuario;
import br.com.ByteLivros.fachada.Fachada;

public class AcaoPaginaConsultaUsuarios implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		HttpSession sessao = request.getSession();
		EntidadeDominio usuario = (EntidadeDominio) sessao.getAttribute("usuarioLogado");
		Usuario usu = new Usuario(usuario.getId());
		Fachada fac2 = new Fachada();
		EntidadeDominio entDom = fac2.consultaUnica(usu);
		Usuario usu2 = (Usuario) entDom;
		if(usu2.getIsAdmin()) {
			Fachada fac = new Fachada();
			List<EntidadeDominio> users = fac.consultarTudo();
			System.out.println();
			request.setAttribute("EntidadeDominio", users);
			return "forward:paginaConsultaUsuario.jsp";
		}
		return "redirect:controller?acao=PaginaPerfil";
	}

}
