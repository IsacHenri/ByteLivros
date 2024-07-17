package br.com.ByteLivros.acao;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Pedido;
import br.com.ByteLivros.dominio.Usuario;
import br.com.ByteLivros.fachada.Fachada;

public class AcaoConsultarPedido implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Pedido pedido = new Pedido();
		Fachada fac2 = new Fachada();
		
		HttpSession sessao = request.getSession();
		EntidadeDominio usuario = (EntidadeDominio) sessao.getAttribute("usuarioLogado");
		Usuario usu = new Usuario(usuario.getId());
		Fachada fac = new Fachada();
		EntidadeDominio entDom = fac.consultaUnica(usu);
		Usuario usu2 = (Usuario) entDom;
		if(usu2.getIsAdmin()) {
			List<EntidadeDominio> entidades = fac2.consultar(pedido);
			request.setAttribute("EntidadeObjeto", entidades);
			request.setAttribute("EntidadeLista", entidades);
			
			return "forward:paginaPedidoAdm.jsp";
		}else {
			
		pedido.setUsuario(usu2);
		List<EntidadeDominio> entidades = fac2.consultar(pedido);
		request.setAttribute("EntidadeObjeto", entidades);
		request.setAttribute("EntidadeLista", entidades);
		
		return "forward:paginaPedido.jsp";

		}
	}

}
