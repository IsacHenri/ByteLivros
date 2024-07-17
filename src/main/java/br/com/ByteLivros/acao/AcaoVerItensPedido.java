package br.com.ByteLivros.acao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Pedido;
import br.com.ByteLivros.dominio.Usuario;
import br.com.ByteLivros.fachada.Fachada;


public class AcaoVerItensPedido implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Pedido pede = new Pedido();
		EntidadeDominio entidade = pede;
		
		
		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);
		
		System.out.println("Consultando dados do id " + id);
		
		entidade.setId(id);
		Fachada fac = new Fachada();
	
		EntidadeDominio entDom = fac.consultaUnica(entidade);
		request.setAttribute("EntidadeObjeto", entDom);
		HttpSession sessao = request.getSession();
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		System.out.println("Enviando para pagina perfil");
		if(usuarioLogado.getIsAdmin() == false) {
			return "forward:paginaItensPedido.jsp";
		}else {
			return "forward:paginaItensPedidoAdm.jsp";
		}
	}

}
