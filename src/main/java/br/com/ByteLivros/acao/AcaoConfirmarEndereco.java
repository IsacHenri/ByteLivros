package br.com.ByteLivros.acao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ByteLivros.dominio.CarrinhoDeCompras;
import br.com.ByteLivros.dominio.Endereco;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Pedido;
import br.com.ByteLivros.dominio.Usuario;
import br.com.ByteLivros.fachada.Fachada;

public class AcaoConfirmarEndereco implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Pedido pedido = new Pedido();
		HttpSession sessao = request.getSession();
		EntidadeDominio usuario = (EntidadeDominio) sessao.getAttribute("usuarioLogado");
		Usuario usu = new Usuario(usuario.getId());
		Fachada fac = new Fachada();
		EntidadeDominio entDom = fac.consultaUnica(usu);
		Usuario usu2 = (Usuario) entDom;
		
		if(request.getParameter("idEndereco") != null && !request.getParameter("idEndereco").isEmpty()) {
			Endereco endereco = new Endereco(Integer.valueOf(request.getParameter("idEndereco")));
			pedido.setEndereco(endereco);
		}else {
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		out.print("� preciso selecionar um endere�o de entrega");
		}
		request.setAttribute("EntidadeLista", pedido);
		CarrinhoDeCompras cdc = new CarrinhoDeCompras();
		request.setAttribute("EntidadeObjeto", usu2);
		request.setAttribute("usuarioLogado", usu2);
		request.setAttribute("Itens", cdc.getItens());
		
		request.setAttribute("QtdeItens", cdc.getQtdeItensCarrinho());
		request.setAttribute("pedidoEnderecoId", pedido.getEndereco().getId());
		return "forward:paginaFormaPagamento.jsp";
	}

}
