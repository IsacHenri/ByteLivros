package br.com.ByteLivros.acao;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Usuario;
import br.com.ByteLivros.fachada.Fachada;

public class AcaoConsultarUsuarios implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Usuario usuario = new Usuario(request.getParameter("paramBuscaCliente"));
		Fachada fac = new Fachada();
		List<EntidadeDominio> users = fac.consultar(usuario);
		System.out.println();
		request.setAttribute("EntidadeDominio", users);
		return "forward:paginaConsultaUsuario.jsp";
	}

}
