package br.com.ByteLivros.acao;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ByteLivros.dominio.CarrinhoDeCompras;
import br.com.ByteLivros.dominio.Cupom;
import br.com.ByteLivros.dominio.Endereco;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Pedido;
import br.com.ByteLivros.dominio.Usuario;
import br.com.ByteLivros.fachada.Fachada;

public class AcaoAdicionarCupom implements IAcao{

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		Cupom cupom = new Cupom();
		
		int contador = Integer.valueOf(request.getParameter("qtdeLista"));
		
		for(int i = 1; i <= contador; i++) {
			
			if(request.getParameter("idCupom" + i) != null &&
			   request.getParameter("desconto" + i) != null) {
				
				cupom = new Cupom(Integer.valueOf(request.getParameter("idCupom" + i)),
								  new BigDecimal(request.getParameter("desconto" + i)));
				
				Cupom cpm = new Cupom();
				
				cpm.adiciona(cupom);
				Fachada fac = new Fachada();
				fac.excluir(cupom);
				
				request.setAttribute("EntidadeLista", cupom);
				request.setAttribute("EntidadeObjeto", cupom);
				Pedido pedido = new Pedido();
				HttpSession sessao = request.getSession();
				EntidadeDominio usuario = (EntidadeDominio) sessao.getAttribute("usuarioLogado");
				Usuario usu = new Usuario(usuario.getId());
				Fachada fac2 = new Fachada();
				EntidadeDominio entDom = fac2.consultaUnica(usu);
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
				CarrinhoDeCompras cdc = new CarrinhoDeCompras();
				request.setAttribute("usuarioLogado", usu2);
				request.setAttribute("Itens", cdc.getItens());
				request.setAttribute("pedidoEnderecoId", pedido.getEndereco().getId());
				request.setAttribute("QtdeItens", cdc.getQtdeItensCarrinho());
				request.setAttribute("CuponsSelecionados", cpm.getCupons());
				
				
				
				return "forward:paginaFormaPagamento.jsp";
			}
		}
		
		return null;
	}

}
