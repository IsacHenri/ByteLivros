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
    <title>Alterar quantidade</title>
    <link rel="stylesheet" href="<c:url value="/style/styleTemplate.css"/>">
    <link rel="stylesheet" href="<c:url value="/style/stylePaginaItemCarrinho.css"/>">
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
    
    <a class="link__voltar" href="/ByteLivros/controller?acao=Carrinho">
        <img class="icone__voltar" src="./assets/botaoVoltar.png">
    </a>
    <form action="${linkController}"> 
    <main class="carrinho__item">

            <section class="carrinho__conteudo__item">
                    <div  class="carrinho__produtos__tabela">
                        <h2 class="carrinho__produtos__header">Item</h2>

                    <div class="carrinho__produtos__produto">
                        <img class="imagem__produto" src="./assets/${EntidadeObjeto.livro.codImagem}.png" alt="imagem do produto">
                        <div class="carrinho__produtos__informacoes">
                            <p class="carrinho__produtos__informacoes__nome">${EntidadeObjeto.livro.titulo}</p>
                            
                            <p class="carrinho__produtos__valor">R$ ${EntidadeObjeto.precoItem}</p>

							<input type="hidden" id="id" name="id" value="${EntidadeObjeto.id}">
                             <input type="hidden" id="precoItem" name="precoItem" value="${EntidadeObjeto.livro.preco}">
                             
                             <select class="carrinho__produtos__selecao__unidade" id="qtdeItens" name="qtdeItens">
								<option value="${EntidadeObjeto.qtdeProdutos}" >${EntidadeObjeto.qtdeProdutos}</option>
								<c:forEach var="qtdeProdutos" begin="1" end="${EntidadeObjeto.livro.qtde}" step="1">
				         		<option value="${qtdeProdutos}" >${qtdeProdutos}</option>
				      			</c:forEach>
                            </select> 
                            
							<div class="carrinho__produtos__acoes">
                   
                           	<button class="carrinho__produtos__alterar__submit" type="submit" name="acao" id="acao" value="AlterarCarrinho">Alterar</button>
                     	
                        	</div>
                        </div>                        
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