package br.com.ByteLivros.acao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ByteLivros.dominio.BandeiraCartao;
import br.com.ByteLivros.dominio.CartaoDeCredito;
import br.com.ByteLivros.dominio.Cidade;
import br.com.ByteLivros.dominio.Endereco;
import br.com.ByteLivros.dominio.Estado;
import br.com.ByteLivros.dominio.Login;
import br.com.ByteLivros.dominio.Pais;
import br.com.ByteLivros.dominio.Telefone;
import br.com.ByteLivros.dominio.Usuario;
import br.com.ByteLivros.fachada.Fachada;

public class AcaoCadastrarUsuario implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		if(request.getParameter("senha").equals(request.getParameter("senhaConfirmacao"))) {
			Login login = new Login(request.getParameter("e-mail"), request.getParameter("senha"));
			
			   String paramData = request.getParameter("dataNasc"); // Substitua isso pela sua string de data

			   Date dataNasc = null;
			   
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        try {
		            dataNasc = dateFormat.parse(paramData);
		        } catch (ParseException e) {
		            e.printStackTrace();
		        }
		
			Pais paisResidencial = new Pais(request.getParameter("paisResidencial"));
	
			Estado estadoResidencial = new Estado(request.getParameter("estadoResidencial"), paisResidencial);
			
			Cidade cidadeResidencial = new Cidade(estadoResidencial, request.getParameter("cidadeResidencial"));
			
			Endereco enderecoResidencial = new Endereco(request.getParameter("tipoResidenciaResidencial"), 
					request.getParameter("logradouroResidencial"), 
					request.getParameter("numeroResidencial"), 
					request.getParameter("cepResidencial"), 
					request.getParameter("bairroResidencial"),
					request.getParameter("tipoLogradouroResidencial"),
					"RESIDENCIAL",
					request.getParameter("observacaoResidencial"),
					cidadeResidencial);
			
			Pais paisEntrega = new Pais(request.getParameter("paisEntrega"));
			
			Estado estadoEntrega = new Estado(request.getParameter("estadoEntrega"), paisEntrega);
			
			Cidade cidadeEntrega = new Cidade(estadoEntrega, request.getParameter("cidadeEntrega"));
			
			Endereco enderecoEntrega = new Endereco(request.getParameter("tipoResidenciaEntrega"), 
					request.getParameter("logradouroEntrega"), 
					request.getParameter("numeroEntrega"), 
					request.getParameter("cepEntrega"), 
					request.getParameter("bairroEntrega"),
					request.getParameter("tipoLogradouroEntrega"),
					"ENTREGA",
					request.getParameter("observacaoEntrega"),
					cidadeEntrega);
			
			Telefone telefone = new Telefone(request.getParameter("telefone"), request.getParameter("tipoTelefone"));
			
			Usuario usuario = new Usuario(request.getParameter("nomeCliente")
					, request.getParameter("genero")
					, request.getParameter("cpf")
					, dataNasc
					, login);
			
			usuario.setListadeEnderecos(enderecoResidencial);
			usuario.setListadeEnderecos(enderecoEntrega);
			
			usuario.setListadeTelefones(telefone);
			
			if(request.getParameter("bandeiraCartao") != null && !request.getParameter("bandeiraCartao").isEmpty()) {
				
				BandeiraCartao bandeiraCartao = new BandeiraCartao();
				
				bandeiraCartao.setId(Integer.valueOf(request.getParameter("bandeiraCartao")));
				
				CartaoDeCredito cartao = new CartaoDeCredito(request.getParameter("nrCartao"),
						request.getParameter("nomeCartao"),
						request.getParameter("codigoSegCartao"),
						bandeiraCartao);
				
				usuario.setListadeCartoes(cartao);
				}
			Fachada fac = new Fachada();
			fac.salvar(usuario);
			System.out.println("to salvando ????");
			return "redirect:controller?acao=PaginaInicial";
		}
		return null;
	}

}
