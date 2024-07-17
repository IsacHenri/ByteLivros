package br.com.ByteLivros.acao;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ByteLivros.dominio.BandeiraCartao;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.fachada.Fachada;


public class AcaoAdicionarCartao implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		BandeiraCartao bandeira = new BandeiraCartao();
		Fachada fac = new Fachada();
		List<EntidadeDominio> lista = fac.consultar(bandeira);
		
		request.setAttribute("EntidadeLista", lista);
		
		return "forward:formCadastroCartao.jsp";
	}

}
