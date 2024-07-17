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
    <title>Carrinho</title>
    <link rel="stylesheet" href="<c:url value="/style/styleTemplate.css"/>">
    <link rel="stylesheet" href="<c:url value="/style/stylePaginaCarrinho.css"/>">
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
    <form action="${linkController}"> 
    <main class="carrinho">

        <section class="carrinho__produtos">

            <section class="carrinho__conteudo">
                    <div  class="carrinho__produtos__tabela">
                        <h2 class="carrinho__produtos__header">Itens</h2>
                    
                    <c:set var="contadorElementosLista" value="0" scope="page" />
                     <c:set var="valorTotal" value="0" scope="page" />
                   
                    <c:forEach items="${Itens}" var="item">
                    <div class="carrinho__produtos__produto">
                        <img class="imagem__produto" src="./assets/${item.livro.codImagem}.png" alt="imagem do produto">
                        <div class="carrinho__produtos__informacoes">
                            <p class="carrinho__produtos__informacoes__nome">${item.livro.titulo}</p>
                            
                            <p class="carrinho__produtos__valor">R$ ${item.precoItem}</p>
                             
                             <p class="carrinho__produtos__valor">${item.qtdeProdutos} X</p>
                             
                             <input type="hidden" id="id" name="id" value="${item.id}">
                            
							<div class="carrinho__produtos__acoes">
                            
                            <a id="excluirDoCarrinho" class="carrinho__produtos__excluir" href="/ByteLivros/controller?acao=RemoverDoCarrinho&id=${item.id}">Excluir</a>
                            
                            <a id="alterarDoCarrinho" class="carrinho__produtos__alterar" href="/ByteLivros/controller?acao=VisualizarItemDoCarrinho&id=${item.id}">Alterar</a>
                           
                            
                        	</div>
                        </div>                        
                    </div>
                    	
					<c:set var="valorTotal"
						value="${valorTotal + item.precoItem}" scope="page" />
						
					<c:set var="contadorElementosLista"
						value="${contadorElementosLista + 1}" scope="page" />
                    
                    </c:forEach>
                   
                   <c:if test="${contadorElementosLista == 0}">
                    <div>
                    	<h2 class="carrinho__produtos__produto">Seu carrinho está vazio :(</h2>
                   	</div>
                    </c:if>
                   
            </div>
            
              


        </section> 
       
        <div class="carrinho__resumo__compra">
            <div class="carrinho__resumo__compra__informacoes">
            <h2 class="carrinho__resumo__compra__titulo">Resumo</h2>
            
            <p class="carrinho__resumo__compra__dados">Quantidade de itens:
            ${contadorElementosLista}</p>
            
            <p class="carrinho__resumo__compra__dados">Total do pedido: R$ ${valorTotal}</p>
            </div>
            <div class="carrinho__resumo__compra__acao">
            
            <c:if test="${contadorElementosLista != 0}">
            <input class="carrinho__produtos__comprar" type="submit" name="acao" id="acao" value="Continuar">
           	</c:if>
           	
           	<a class="carrinho__produtos__voltar" href="/ByteLivros/controller?acao=PaginaInicial">Voltar as compras</a>
            </div>
            </div>     
    </section>

        
    </main>
     </form>
    <footer class="rodape">
        <p>Feito por Isac Henrique</p>
    </footer>
</body>

</html>