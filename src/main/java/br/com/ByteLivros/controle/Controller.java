package br.com.ByteLivros.controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ByteLivros.acao.IAcao;


/**
 * Servlet implementation class Controller
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String paramAcao = request.getParameter("acao");
		System.out.println("Cheguei aqui viuuu");
		HttpSession sessao = request.getSession();
		boolean usuarioNaoEstaLogado = (sessao.getAttribute("usuarioLogado") == null);
		boolean ehUmaAcaoProtegida = !(paramAcao.equals("Login") || paramAcao.equals("LoginForm") || paramAcao.equals("PaginaInicial") || paramAcao.equals("FormCadastroUsuario") || paramAcao.equals("CadastrarUsuario") || paramAcao.equals("ExibeDadosProduto"));
		
		if(ehUmaAcaoProtegida && usuarioNaoEstaLogado) {
			String msg = "É necessario estar logado!";
			sessao.setAttribute("mensagem", msg);
			
			response.sendRedirect("controller?acao=LoginForm");
			
			return;
		}
		
		if(paramAcao.equals("LoginForm") && !usuarioNaoEstaLogado){
			response.sendRedirect("controller?acao=PaginaPerfil");
			return;
		}
		
		String nomeDaClasse = "br.com.ByteLivros.acao.Acao" + paramAcao;
		
		String nome;
		try {
			Class classe = Class.forName(nomeDaClasse);//carrega a classe com o nome 
			IAcao acao = (IAcao) classe.newInstance();
			nome = acao.executa(request,response);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new ServletException(e);
		}
		System.out.println(nome);
		String[] tipoEEndereco = nome.split(":");
		if(tipoEEndereco[0].equals("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/View/" + tipoEEndereco[1]);
			rd.forward(request, response);
		} else {
			response.sendRedirect(tipoEEndereco[1]);
		}
		
		
	}

}