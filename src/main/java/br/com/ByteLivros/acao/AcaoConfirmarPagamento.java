package br.com.ByteLivros.acao;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ByteLivros.dominio.CarrinhoDeCompras;
import br.com.ByteLivros.dominio.CartaoDeCredito;
import br.com.ByteLivros.dominio.Endereco;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Pedido;
import br.com.ByteLivros.dominio.Usuario;
import br.com.ByteLivros.fachada.Fachada;



public class AcaoConfirmarPagamento implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		CarrinhoDeCompras cdc = new CarrinhoDeCompras();
		Pedido pedido = new Pedido();
		Usuario usuario = new Usuario(Integer.valueOf(request.getParameter("idUsuario")));
		
		pedido.setUsuario(usuario);
		
		Endereco endereco = new Endereco(Integer.valueOf(request.getParameter("pedidoEnderecoId")), 
				                         new BigDecimal(request.getParameter("valorFrete")));
		
		pedido.setEndereco(endereco);
		
		pedido.setDtCadastro(LocalDate.now());
		
		pedido.setListaItens(cdc.getItens());
		
		pedido.setValorProdutos(new BigDecimal(request.getParameter("valorProdutos")));
		
		if (request.getParameter("idCartao") != null && !request.getParameter("idCartao").isEmpty()) {
			CartaoDeCredito cartao = new CartaoDeCredito(Integer.valueOf(request.getParameter("idCartao")));
			pedido.setCartao(cartao);
			
			Fachada fac = new Fachada();
			Fachada fac2 = new Fachada();
			
			fac.salvar(pedido);
			
			List<EntidadeDominio> entidades = fac2.consultar(pedido);
			
			request.setAttribute("EntidadeObjeto", entidades);
			request.setAttribute("Itens", cdc.getItens());
			request.setAttribute("QtdeItens", cdc.getQtdeItensCarrinho());
			request.setAttribute("EntidadeLista", entidades);
			
			return "forward:paginaPedido.jsp";
		} else {
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			out.print("� preciso selecionar um m�todo de pagamento");
		}
		return null;
	}

}
