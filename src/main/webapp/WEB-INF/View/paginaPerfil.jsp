<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/controller" var="linkController" />

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Perfil</title>
<link rel="stylesheet" href="<c:url value="/style/styleTemplate.css"/>">
<link rel="stylesheet" href="<c:url value="/style/stylePerfil.css"/>">

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
                    
                </button>
            </form>  
        </div>
        
    
    <a class="link__voltar" href="/ByteLivros/controller?acao=PaginaInicial">
        <img class="icone__voltar" src="./assets/botaoVoltar.png">
    </a>

	<main class="perfil">
		<c:if test="${not empty mensagem}">
    		<div>
        		<h2 class="nao__encontrado">${mensagem}</h2>
    		</div>
		</c:if>
		<%
	    	// Remove a mensagem da sessão para que ela não seja exibida novamente na próxima página
	    	session.removeAttribute("mensagem"); 
	    %>
	
		<div class="perfil__linkb">
			<img class="perfil__imagem" src="./assets/perfil.png" alt="">
			<p> ${usuarioLogado.nome}.
			</p>
		</div>
			<a class="perfil__link"
			href="/ByteLivros/controller?acao=ExibeDadosUsuario&id=${usuarioLogado.id}"><img
			class="perfil__imagem" src="./assets/perfil.png" alt="Clientes">
			<p>
				Alterar Dados
			</p></a>
			<a class="perfil__link"
			href="/ByteLivros/controller?acao=ConsultarPedido"><img
			class="perfil__imagem" src="./assets/vendas.png" alt="Vendas">
			<p>
				Consultar Compras
			</p></a> <a class="perfil__link" href="/ByteLivros/controller?acao=LogOut"><img
			class="perfil__imagem" src="./assets/logout.png" alt="LogOut">
			<p>
				Encerrar Sessão
			</p></a>
	</main>
	<footer class="rodape">
        <p>Feito por Isac Henrique</p>
    </footer>
</body>
</html>