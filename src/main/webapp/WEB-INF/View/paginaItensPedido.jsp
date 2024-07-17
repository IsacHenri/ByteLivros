<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:url value="/controller" var="linkController"/>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Itens</title>
    <link rel="stylesheet" href="<c:url value="/style/styleTemplate.css"/>">
    <link rel="stylesheet" href="<c:url value="/style/styleItensPedido.css"/>">
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
    
    <main class="compras">
	
    <a class="link__voltar" href="/ByteLivros/controller?acao=ConsultarPedido">
    	<img class="icone__voltar" src="./assets/botaoVoltar.png">
    </a>
	
    <section class="compras__lista">
    		
	<h2 class="compras__lista__produtos__header">Itens</h2>
	
			 
			 <c:set var="contadorElementosLista" value="0" scope="page" />
			 
			 <c:forEach items="${EntidadeObjeto.listaItens}" var="item">
           
           
           <div class="compras__lista__produtos__produto">
           
                <img src="./assets/${item.livro.codImagem}.png" class="imagem__produto" alt="Imagem do produto">

                <div class="compras__lista__produtos__produto__descricao">
                <p class=>
                    <strong class="compras__lista__produtos__produto__titulo__destaque">${item.livro.titulo}</strong>
                </p>
                
                <p>R$ ${item.precoItem}</p>
                
                <p>${item.qtdeProdutos} X itens</p>
                
                
                <p><strong class="compras__lista__produtos__produto__titulo__destaque">
                Endereço de entrega:
                </strong>
                 
                ${EntidadeObjeto.endereco.logradouro}, 
                						${EntidadeObjeto.endereco.bairro},
                						${EntidadeObjeto.endereco.cidade.descricao} - 
                						${EntidadeObjeto.endereco.cidade.estado.descricao}
                						</p>
                						
                 <p><strong class="compras__lista__produtos__produto__titulo__destaque">
                Status: </strong> ${item.statusPedido} </p>						
                
                </div>

                <div class="compras__lista__produtos__produto__acoes">
                    <a class="compras__lista__produtos__produto__comprar__novamente" href="/ByteLivros/controller?acao=ExibeDadosProduto&id=${item.livro.id}">   
                        Comprar novamente
                    </a>
                     
                    <c:if test="${item.statusPedido == 'ENTREGUE'}">
                    <form action="${linkController}">
                    <input type="hidden" name="idPedido" id="idPedido" value="${EntidadeObjeto.id}">
                    <input type="hidden" name="idItem" id="idItem" value="${item.id}">
                    <input type="hidden" name="statusItem" id="statusItem" value="TROCA SOLICITADA">
                    <button class="compras__lista__produtos__produto__trocar__produto" type="submit" name="acao" id="acao" value="SolicitarTrocaItem">   
                        Trocar produto
                    </button>
                    </form>
                    </c:if> 
                </div>
			
			</div>
			
			<c:set var="contadorElementosLista"
						value="${contadorElementosLista + 1}" scope="page" />
			
            </c:forEach>
    </section>
    <c:if test="${contadorElementosLista == 0}">
                    <div>
                    	<h2 class="nao__encontrado">Não possui itens de compra :(</h2>
                   	</div>
                    </c:if>

    </main>
    <footer class="rodape">
        <p>Feito por Isac Henrique</p>
    </footer> 
</body>

</html>