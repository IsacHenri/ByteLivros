package br.com.ByteLivros.acao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ByteLivros.dominio.BandeiraCartao;
import br.com.ByteLivros.dominio.CarrinhoDeCompras;
import br.com.ByteLivros.dominio.CartaoDeCredito;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Pedido;
import br.com.ByteLivros.dominio.Usuario;
import br.com.ByteLivros.fachada.Fachada;


public class AcaoCadastrarCartao implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		if(request.getParameter("bandeiraCartao") != null && 
				  !request.getParameter("bandeiraCartao").isEmpty()){
					
					BandeiraCartao bandeiraCartao = new BandeiraCartao();
					
					bandeiraCartao.setId(Integer.valueOf(request.getParameter("bandeiraCartao")));
					
					CartaoDeCredito cartao = new CartaoDeCredito(request.getParameter("nrCartao"),
							request.getParameter("nomeCartao"),
							request.getParameter("codigoSegCartao"),
							bandeiraCartao);
					
					if(request.getParameter("id")!= null &&
					  !request.getParameter("id").isEmpty()) {
						
						cartao.setId(Integer.valueOf(request.getParameter("id")));
					}
					
					if(request.getParameter("usu_id")!= null &&
							  !request.getParameter("usu_id").isEmpty()) {
								
								cartao.setCartaoUsuarioId(Integer.valueOf(request.getParameter("usu_id")));
							}
					
					
					Fachada fac = new Fachada();
					
					Fachada fac2 = new Fachada();
					
					fac.salvar(cartao);
					
					Usuario usuario = new Usuario();
					
					usuario.setId(cartao.getCartaoUsuarioId());
					
					EntidadeDominio entDom = fac2.consultaUnica(usuario);
					CarrinhoDeCompras cdc = new CarrinhoDeCompras();
					Usuario usuario2 = (Usuario)entDom;
					HttpSession sessao = request.getSession();
					sessao.setAttribute("usuarioLogado", usuario2);
					request.setAttribute("Itens", cdc.getItens());
					request.setAttribute("QtdeItens", cdc.getQtdeItensCarrinho());
					request.setAttribute("EntidadeObjeto", entDom);
					request.setAttribute("EntidadeLista", entDom);	
					Pedido pedido = new Pedido();
					request.setAttribute("pedidoEnderecoId", pedido.getEndereco().getId());
					return "forward:paginaFormaPagamento.jsp";
					}
		return null;
	}

}
