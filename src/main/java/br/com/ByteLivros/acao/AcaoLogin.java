package br.com.ByteLivros.acao;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ByteLivros.dominio.EntidadeDominio;
import br.com.ByteLivros.dominio.Login;
import br.com.ByteLivros.dominio.Usuario;
import br.com.ByteLivros.fachada.Fachada;

public class AcaoLogin implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		Login login = new Login(email,senha);
		Usuario usu = new Usuario(login);
		Fachada fac = new Fachada();
		List<EntidadeDominio> lista =  fac.consultar(usu);
		boolean usuarioLogado = false;
		String msg = null;

		for (EntidadeDominio entidade : lista) {
		    Usuario usuarioExistente = (Usuario) entidade;
		    
		    if (usuarioExistente.getLogin().getEmail().equals(login.getEmail()) && 
		        usuarioExistente.getLogin().getSenha().equals(login.getSenha())) {
		        
		        usuarioLogado = true;
		        usu = usuarioExistente;
		        break; // Sai do loop assim que o usuário é encontrado e autenticado
		    }
		}
		if(usuarioLogado == true) {
			System.out.println("Usuario eh admin ? " + usu.getIsAdmin());
			msg = "Login realizado com sucesso!";
			HttpSession sessao = request.getSession();
			sessao.setAttribute("mensagem", msg);
			sessao.setAttribute("usuarioLogado", usu);
			return "redirect:controller?acao=PaginaPerfil";
		} else {
			msg = "Não foi possível realizar o login!";
			HttpSession sessao = request.getSession();
			sessao.setAttribute("mensagem", msg);
			return "redirect:controller?acao=LoginForm";
		}
	}

}
