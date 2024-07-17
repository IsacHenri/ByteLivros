<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:url value="/controller" var="linkController"/>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alterar Dados Cliente</title>
    <link rel="stylesheet" href="<c:url value="/style/styleTemplate.css"/>">
	<link rel="stylesheet" href="<c:url value="/style/styleCadastroClientes.css"/>">
</head>

<body>
    <header class="cabecalho">
        <div class="mHeader">
            <div class="mTitulo">
                <a href="/ByteLivros/controller?acao=PaginaInicial" class="botao-link"><p class="titulo__site">ByteLivros</p></a>
            </div>
        
            <div class="mNav">
                <nav class="cabecalho__menu">
                	<a class="cabecalho__menu__link" href="/ByteLivros/controller?acao=Recomendacoes">
                         <img class="cabecalho__menu__icones" src="./assets/recomendacao.png" alt="Recomendacoes">Recomendações</a>
                    <a class="cabecalho__menu__link" href="/ByteLivros/controller?acao=Carrinho">
                       <img class="cabecalho__menu__icones" src="./assets/carrinho.png" alt="Carrinho">Carrinho</a>
                    <a class="cabecalho__menu__link" href="/ByteLivros/controller?acao=ConsultarPedido">
                        <img class="cabecalho__menu__icones" src="./assets/sacola.png" alt="Pedidos">Pedidos</a>
                    <a class="cabecalho__menu__link" href="/ByteLivros/controller?acao=LoginForm">
                        <img class="cabecalho__menu__icones" src="./assets/perfil.png" alt="Login">Login</a>
                </nav>
            </div>
        </div>
        
    </header>
    <div class="mBuscar">
            <form class="buscar__produto">
                <input class="barra_pesquisa" type="text" id="numero" name="numero" placeholder="Pesquisar">
                <button class="botao__pesquisar" type="submit">
                    <img class="botao__pesquisar__lupa" src="./assets/pesquisar.png" alt="Pesquisar">
                </button>
            </form>  
    </div>
    
    <a class="link__voltar" href="/ByteLivros/controller?acao=PaginaInicial">
        <img class="icone__voltar" src="./assets/botaoVoltar.png">
    </a>
    
    <main class="cliente">
    
            <form class="cliente__cadastro" action="${linkController}" method="post">
                <div class="cliente__cadastro__dados">
                <div class="cliente__cadastro__informacoes__pessoais">
                <h2 class="cliente__cadastro__titulo">Dados do Usuario</h2>
                
                <input type="hidden" name="id" value="${EntidadeObjeto.id}" />
                
                
                
                <select class="cliente__cadastro__select" id="genero" name="genero">
                    <option value="${EntidadeObjeto.genero}">${EntidadeObjeto.genero}</option>
                    <option value="M">Masculino</option>
                    <option value="F">Feminino</option>
    				<option value="Outro">Outro</option>
                </select>
                
                <input class="cliente__cadastro__campos" type="text" name="nomeCliente" id="nomeCliente" value="${EntidadeObjeto.nome}" placeholder="Nome" required>
                
                <input class="cliente__cadastro__campos" type="date" name="dataNasc" id="dataNasc" value="${EntidadeObjeto.dataNasc}" placeholder="Data de nascimento" required>
                
                <input class="cliente__cadastro__campos" type="text" name="cpf" id="cpf" value="${EntidadeObjeto.cpf}" placeholder="CPF" required>
                
                <c:forEach items="${EntidadeObjeto.listadeTelefones}" var="telefone" >
    				<input class="cliente__cadastro__campos" type="text" name="telefones[]" value="${telefone.numero}" pattern="\d{2} \d{4,5}-\d{4}" placeholder="Telefone" required>
    				<input type="hidden" name="tel_ids[]" value="${telefone.id}" />
				    <select class="cliente__cadastro__select" name="tiposTelefones[]" required>
				        <option value="${telefone.tipo}">${telefone.tipo}</option>
				        <option value="Residencial">Residencial</option>
				        <option value="Celular">Celular</option>
				        <option value="Contato">Contato</option>
				    </select>
				</c:forEach>
                
                <input class="cliente__cadastro__campos" type="text" name="e-mail" id="e-mail" value="${EntidadeObjeto.login.email}"  pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}" placeholder="E-mail" required>
                
                <input class="cliente__cadastro__campos" type="password" name="senha" id="senha" value="${EntidadeObjeto.login.senha}" placeholder="Senha" required>
                
                <input class="cliente__cadastro__campos" type="password" name="senhaConfirmacao" id="senhaConfirmacao" value="${EntidadeObjeto.login.senha}" placeholder="Confirmar senha" required>
                
                </div>
			
                </div>
                <c:forEach items="${EntidadeObjeto.listadeEnderecos}" var="endereco">
                <div class="cliente__cadastro__dados">
                	
                	<div class="cliente__cadastro__informacoes__endereco"> 
                	<h2 class="cliente__cadastro__titulo">Dados do Endereco</h2>
    				<input type="hidden" name="endereco_id[]" value="${endereco.id}" />
    				<input type="hidden" name="tipoEndereco[]" value="${endereco.tipoEndereco}" />
				    <select class="cliente__cadastro__select" name="tipoResidencia[]" required>
				        <option value="${endereco.tipoResidencia}">${endereco.tipoResidencia}</option>
				        <option value="casa">Casa</option>
				        <option value="apartamento">Apartamento</option>
				    </select>
				    <input class="cliente__cadastro__campos" type="text" name="logradouro[]" value="${endereco.logradouro}" placeholder="Logradouro" required>
				    <select class="cliente__cadastro__select" name="tipoLogradouro[]">
				        <option value="${endereco.tipoLogradouro}">${endereco.tipoLogradouro}</option>
				        <option value="estrada">Estrada</option>
				        <option value="rua">Rua</option>
				    </select>
				    <input class="cliente__cadastro__campos" type="text" name="numero[]" value="${endereco.numero}" placeholder="Número" required>
				    <input class="cliente__cadastro__campos" type="text" name="observacao[]" value="${endereco.observacao}" placeholder="Observação" required>
				    <input class="cliente__cadastro__campos" type="text" name="bairro[]" value="${endereco.bairro}" placeholder="Bairro" required>
				    <input class="cliente__cadastro__campos" type="text" name="cep[]" value="${endereco.cep}" pattern="\d{5}-\d{3}" placeholder="CEP" required>
				    <input class="cliente__cadastro__campos" type="text" name="cidade[]" value="${endereco.cidade.descricao}" placeholder="Cidade" required>
				    <select class="cliente__cadastro__select" name="estado[]" required>
				        <option value="${endereco.cidade.estado.descricao}">${endereco.cidade.estado.descricao}</option>
				        <!-- Opções de estado -->
				    </select>
				    <input class="cliente__cadastro__campos" type="text" name="pais[]" value="${endereco.cidade.estado.pais.descricao}" placeholder="País" required>
				    
				    <c:if test="${endereco.tipoEndereco eq 'ENTREGA'}">
			            <a class="perfil__acoes__excluir" href="/ByteLivros/controller?acao=ExcluirEndereco&id=${endereco.id}">   
			                EXCLUIR ENDEREÇO DE ENTREGA 
			            </a>
					</c:if>
				    </div>
				    
				    
				
				</div>
				</c:forEach>
				
				<c:forEach items="${EntidadeObjeto.listadeCartoes}" var="cartao">
				<div class="cliente__cadastro__dados">
                
                	
                	<div class="cliente__cadastro__informacoes__cartao"> 
                	<h2 class="cliente__cadastro__titulo">Dados do Cartao</h2>
				    <input type="hidden" name="cartao_id[]" value="${cartao.id}" />
				    <input class="cliente__cadastro__campos" type="text" name="nrCartao[]" value="${cartao.nrCartao}" placeholder="Numero do cartão" required>
				    <select class="cliente__cadastro__select" name="bandeiraCartao[]" required>
				        <option value="${cartao.bandeira.bandeira }" >${cartao.bandeira.bandeira }</option>
				        <c:forEach items="${EntidadeLista}" var="bandeira">
				            <option value="${bandeira.id}:${bandeira.bandeira}">${bandeira.bandeira}</option>
				        </c:forEach>
				    </select> 
				    <input class="cliente__cadastro__campos" type="text" name="nomeCartao[]" value="${cartao.nomeCartao}" placeholder="Nome do portador" required> 
				    <input class="cliente__cadastro__campos" type="text" name="codigoSegCartao[]" value="${cartao.codSeguranca}" placeholder="Codigo de segurança" required> 
				    
				    
			        <a class="perfil__acoes__excluir" href="/ByteLivros/controller?acao=ExcluirCartao&id=${cartao.id}">   
			             EXCLUIR CARTÃO
			        </a>
				    </div>
				    
				
				</div>
				</c:forEach>
				
                <div>
                	<input type="hidden" name="acao" value="AlterarUsuario">
                    <input id="atualizarDadosCliente" class="cliente__cadastro__cadastrar" type="submit" value="Atualizar">
                </div>
                
            </form>

           
    </main>
    <footer class="rodape">
        <p>Feito por Isac Henrique</p>
    </footer>
</body>

</html>