package br.com.ByteLivros.acao;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ByteLivros.dominio.BandeiraCartao;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Usuario;
import br.com.ByteLivros.fachada.Fachada;


public class AcaoExibeDadosUsuario implements IAcao {	
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Fachada fac = new Fachada();
		String idParam = request.getParameter("id");
		System.out.println(request.getParameter("id"));
		Integer ide = Integer.parseInt(idParam);
		List<EntidadeDominio> users = fac.consultarTudo();
		for (EntidadeDominio entidade : users) {
		    if(entidade.getId() == ide) {
		    	System.out.println("reconheceu o id");
		    	request.setAttribute("EntidadeObjeto", entidade);
		    }
		}
		Fachada fac2 = new Fachada();
		BandeiraCartao ban = new BandeiraCartao();
		List<EntidadeDominio> bandeiras = fac2.consultar(ban);
		request.setAttribute("EntidadeLista", bandeiras);
		
		
		return "forward:formAlteraUsuario.jsp";
	}

}
