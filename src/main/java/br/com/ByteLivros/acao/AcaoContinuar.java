package br.com.ByteLivros.acao;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.ByteLivros.dominio.BandeiraCartao;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Usuario;
import br.com.ByteLivros.fachada.Fachada;
import br.com.ByteLivros.dominio.CarrinhoDeCompras;
import br.com.ByteLivros.dominio.Endereco;

public class AcaoContinuar implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		HttpSession sessao = request.getSession();
		EntidadeDominio usuario = (EntidadeDominio) sessao.getAttribute("usuarioLogado");
		Usuario usu = new Usuario(usuario.getId());
		Fachada fac = new Fachada();
		
		BandeiraCartao bnd = new BandeiraCartao();
		
		List<EntidadeDominio> listaClientes = fac.consultar(bnd);
		request.setAttribute("EntidadeLista", listaClientes);
		
		Fachada fac2 = new Fachada();
	
		EntidadeDominio entDom = fac2.consultaUnica(usu);
		Usuario usu2 = (Usuario) entDom;
		
		
		request.setAttribute("EntidadeObjeto", usu2);
		request.setAttribute("usuarioLogado", usu2);
		
		
		CarrinhoDeCompras cdc = new CarrinhoDeCompras();
		
		request.setAttribute("Itens", cdc.getItens());
		
		request.setAttribute("QtdeItens", cdc.getQtdeItensCarrinho());
		return "forward:paginaSelecaoEndereco.jsp";
	}

}
