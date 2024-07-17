package br.com.ByteLivros.acao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ByteLivros.dominio.Usuario;
import br.com.ByteLivros.fachada.Fachada;

public class AcaoExcluirUsuario implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);
		
		System.out.println("Excluindo " +" "+ id);
		
		Usuario usu = new Usuario(id);
		Fachada fac = new Fachada();
		fac.excluir(usu);
		return "redirect:controller?acao=PaginaConsultaUsuarios";
	}

}
