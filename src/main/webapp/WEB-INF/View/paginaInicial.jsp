<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/controller" var="linkController"/>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio</title>
    <link rel="stylesheet" href="<c:url value="/style/styleTemplate.css"/>">
    <link rel="stylesheet" href="<c:url value="/style/styleHome.css"/>">
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
                	<input class="barra_pesquisa" type="hidden" id="prompt" name="prompt" value="testou legal ?">
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
    <main class="home">
        
        <section class="home__produtos__lista">
            <c:forEach items="${EntidadeDominio}" var="produto">
                <div class="home__produtos__produto"> 
                    <a id="selecionarProduto" class="home__produtos__produto__link" href="/ByteLivros/controller?acao=ExibeDadosProduto&id=${produto.id}">
                        <div class="produto__container">
                    		<img class="imagem__produto" src="<c:url value="/assets/${produto.codImagem}.png"/>" alt="Livro"/>
                    		<h3>${produto.titulo}</h3>
                    		<p>R$ ${produto.preco}</p>
                		</div>
                    </a>
                </div>
            </c:forEach>    
        </section>
    </main>
    <footer class="rodape">
        <p>Feito por Isac Henrique</p>
    </footer>  
</body>
</html>
