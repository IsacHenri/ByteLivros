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

public class AcaoAlterarUsuario implements IAcao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String paramDataNascimento = request.getParameter("dataNasc");
		Date dataNasc = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dataNasc = sdf.parse(paramDataNascimento);
		} catch (ParseException e) {
			throw new ServletException(e);
		}
		
		if(request.getParameter("senha").equals(request.getParameter("senhaConfirmacao"))) {
			Login login = new Login(request.getParameter("e-mail"), request.getParameter("senha"));
			
			
		
			Usuario usuario = new Usuario(request.getParameter("nomeCliente")
				, request.getParameter("genero")
				, request.getParameter("cpf")
				, dataNasc
				, login);
			
			if(request.getParameter("id") != null 
			&& !request.getParameter("id").equals("")) {
							
						usuario.setId(Integer.valueOf(request.getParameter("id")));
						
					}
			
			String[] numeros = request.getParameterValues("telefones[]");
			String[] tipos = request.getParameterValues("tiposTelefones[]");
			String[] ids = request.getParameterValues("tel_ids[]");


			if (numeros != null && tipos != null) {
			    for (int i = 0; i < numeros.length; i++) {
			        Telefone telefone1 = new Telefone(numeros[i], tipos[i]);
			        if (ids != null && ids.length > i && ids[i] != null && !ids[i].isEmpty()) {
			            telefone1.setId(Integer.parseInt(ids[i]));
			        }
			        usuario.setListadeTelefones(telefone1);
			    }
			}
			
			String[] ids1 = request.getParameterValues("endereco_id[]");
			String[] tiposEndereco = request.getParameterValues("tipoEndereco[]");
			String[] tiposResidencia = request.getParameterValues("tipoResidencia[]");
			String[] logradouros = request.getParameterValues("logradouro[]");
			String[] tiposLogradouro = request.getParameterValues("tipoLogradouro[]");
			String[] numeros1 = request.getParameterValues("numero[]");
			String[] observacoes = request.getParameterValues("observacao[]");
			String[] bairros = request.getParameterValues("bairro[]");
			String[] ceps = request.getParameterValues("cep[]");
			String[] cidades = request.getParameterValues("cidade[]");
			String[] estados = request.getParameterValues("estado[]");
			String[] paises = request.getParameterValues("pais[]");

			for(String obv : observacoes) {
				System.out.println("------------------------------------");
				System.out.println(obv);
			}

			if (ids1 != null && tiposEndereco != null && tiposResidencia != null && logradouros != null &&
			    tiposLogradouro != null && numeros1 != null && observacoes != null && bairros != null &&
			    ceps != null && cidades != null && estados != null && paises != null) {

			    for (int i = 0; i < logradouros.length; i++) {
			        Endereco endereco = new Endereco();
			        endereco.setId(Integer.parseInt(ids1[i]));
			        endereco.setTipoEndereco(tiposEndereco[i]);
			        endereco.setTipoResidencia(tiposResidencia[i]);
			        endereco.setLogradouro(logradouros[i]);
			        endereco.setTipoLogradouro(tiposLogradouro[i]);
			        endereco.setNumero(numeros1[i]);
			        endereco.setObservacao(observacoes[i]);
			        endereco.setBairro(bairros[i]);
			        endereco.setCep(ceps[i]);
			        // Você precisa definir a cidade e o estado aqui
			        Cidade cidade = new Cidade();
			        cidade.setDescricao(cidades[i]);
			        Estado estado = new Estado();
			        estado.setDescricao(estados[i]);
			        estado.setPais(new Pais(paises[i])); // Supondo que você tenha um construtor que aceita o nome do país
			        cidade.setEstado(estado);
			        endereco.setCidade(cidade);
			        usuario.setListadeEnderecos(endereco);
			        
			        System.out.println(endereco.getObservacao());
			    }
			}
			System.out.println("-----------------------------------------------------------------------------------");
			for(Endereco end : usuario.getListadeEnderecos()) {
				System.out.println(end.getObservacao());
			}
			System.out.println("-----------------------------------------------------------------------------------");
			String[] ids2 = request.getParameterValues("cartao_id[]");
			String[] numeros2 = request.getParameterValues("nrCartao[]");
			String[] bandeirasSelecionadas = request.getParameterValues("bandeiraCartao[]");
			String[] nomes = request.getParameterValues("nomeCartao[]");
			String[] codigosSeguranca = request.getParameterValues("codigoSegCartao[]");



			if (ids2 != null && numeros2 != null && bandeirasSelecionadas != null && nomes != null && codigosSeguranca != null) {
			    for (int i = 0; i < numeros2.length; i++) {
			        String[] partes = bandeirasSelecionadas[i].split(":");
			        if (partes.length == 2) {
			            String idBandeira = partes[0];
			            String nomeBandeira = partes[1];
			            BandeiraCartao bandeira = new BandeiraCartao();
			            bandeira.setBandeira(nomeBandeira);
			            bandeira.setId(Integer.parseInt(idBandeira));
			            CartaoDeCredito cartao = new CartaoDeCredito();
			            cartao.setId(Integer.parseInt(ids2[i]));
			            cartao.setNrCartao(numeros2[i]);
			            cartao.setBandeira(bandeira);
			            cartao.setNomeCartao(nomes[i]);
			            cartao.setCodSeguranca(codigosSeguranca[i]);
			            usuario.setListadeCartoes(cartao);
			        }
			    }
			}

	
		Fachada fac = new Fachada();
		fac.alterar(usuario);
		return "redirect:controller?acao=PaginaConsultaUsuarios";
		}
		
			
		return null;
	}

}
