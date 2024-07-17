package br.com.ByteLivros.acao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ByteLivros.dominio.Cidade;
import br.com.ByteLivros.dominio.Endereco;
import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Estado;
import br.com.ByteLivros.dominio.Pais;
import br.com.ByteLivros.dominio.Usuario;
import br.com.ByteLivros.fachada.Fachada;


public class AcaoCadastrarEndereco implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Pais pais = new Pais(request.getParameter("pais"));
		
		Estado estado = new Estado(request.getParameter("estado"), pais);
		
		Cidade cidade = new Cidade(estado, request.getParameter("cidade"));
		
		Endereco endereco = new Endereco(
				request.getParameter("tipoResidencia"), 
				request.getParameter("logradouro"), 
				request.getParameter("numero"), 
				request.getParameter("cep"), 
				request.getParameter("bairro"),
				request.getParameter("tipoLogradouro"),
				request.getParameter("tipoEndereco"),
				request.getParameter("observacao"),
				cidade);
		
		if(request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
			
			endereco.setId(Integer.valueOf(request.getParameter("id")));
			
		}
		
		if(request.getParameter("usu_id") != null && !request.getParameter("usu_id").isEmpty()) {
			
			endereco.setEnderecoUsuarioId(Integer.valueOf(request.getParameter("usu_id")));
			Fachada fac = new Fachada();
			fac.salvar(endereco);
			Fachada fac2 = new Fachada();
			Usuario usuario = new Usuario();
			
			usuario.setId(endereco.getEnderecoUsuarioId());
			
			EntidadeDominio usuario2 = (EntidadeDominio) fac2.consultaUnica(usuario);
			HttpSession sessao = request.getSession();
			sessao.setAttribute("usuarioLogado", usuario2);
			return "forward:paginaSelecaoEndereco.jsp";
		}
		return null;
	}

}
