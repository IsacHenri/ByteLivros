<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:url value="/controller" var="linkController"/>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Informações do usuario</title>
    <link rel="stylesheet" href="<c:url value="/style/styleTemplate.css"/>">
    <link rel="stylesheet" href="<c:url value="/style/styleConsultaClientes.css"/>">
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

    <div class="busca__clientes">
    <form class="buscar__cliente" action="${linkController}" method="get">
    
        
    <div class="pesquisa-wrapper">
    	<p>Nome</p>
    	<div class="pesquisa-input">
	        <input class="barra__pesquisap" type="text" id="paramBuscaCliente" name="paramBuscaCliente" placeholder="Buscar cliente">
	        <input type="hidden" name="acao" value="ConsultarUsuarios">
	        
	        <button class="botao__pesquisarP" type="submit">
					<img class="botao__pesquisar__lupaP" src="./assets/pesquisar.png" alt="Pesquisar">
				</button>
	    </div>
	</div>
        
    </form>
    </div>

    <main class="perfil">
           
    <h2 class="perfil__titulo">Informações do usuário</h2>
    
    <c:set var="contadorElementosLista" value="0" scope="page" />

	<c:forEach items="${EntidadeDominio}" var="cliente">
    <section class="perfil__informacoes">


        <div class="perfil__informacoes__container">

            <div class="perfil__informacoes__container__cliente">
                <p class="perfil__informacoes__container__cliente__informacao"><strong>Nome: </strong>${cliente.nome}</p>
                <p class="perfil__informacoes__container__cliente__informacao"><strong>E-mail:</strong> ${cliente.login.email}</p>
                
                <c:forEach items="${cliente.listadeTelefones}" var="telefone">
                		<p class="perfil__informacoes__container__cliente__informacao"><strong>Telefone: </strong>${telefone.numero}</p>
                </c:forEach>
                
                <p class="perfil__informacoes__container__cliente__informacao"><strong>Data de nascimento: </strong><fmt:formatDate value="${cliente.dataNasc}" pattern="dd/MM/yyyy"/></p>
            </div>
            

        </div>

        <div class="perfil__informacoes__container__acoes">
            <a id="excluirCliente" class="perfil__acoes__excluir" href="/ByteLivros/controller?acao=ExcluirUsuario&id=${cliente.id}">   
                Excluir conta
            </a>
            <a id="visualizarDadosCliente" class="perfil__acoes__alterar" href="/ByteLivros/controller?acao=ExibeDadosUsuario&id=${cliente.id}">   
                Ver dados
            </a>
        </div>

    </section>
    
    <c:set var="contadorElementosLista"
						value="${contadorElementosLista + 1}" scope="page" />
						
    </c:forEach>
    
    <c:if test="${contadorElementosLista == 0}">
                    <div>
                    	<h2 class="nao__encontrado">Não foi possivel encontrar usuario :(</h2>
                   	</div>
                    </c:if>
    
    </main>
    <footer class="rodape">
        <p>Feito por Isac Henrique</p>
    </footer>
</body>
</html>