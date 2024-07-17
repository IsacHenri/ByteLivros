<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html; charset=UTF-8" %>


<c:url value="/controller" var="linkController"/>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Venda</title>
    <link rel="stylesheet" href="<c:url value="/style/styleTemplate.css"/>">
    <link rel="stylesheet" href="<c:url value="/style/stylePaginaProduto.css"/>">
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
	<section class="produto__vitrine">
	<div>
        <img class="produto__imagem" src="./assets/${EntidadeObjeto.codImagem}.png" alt="Imagem do produto">
		<input type="hidden" name="codImage" id="codImage" value="${EntidadeObjeto.codImagem}">
	</div>
        <div class="produto__venda">
        	<input type="hidden" name="id" value="${EntidadeObjeto.id}">

            <h1 class="produto__nome"><strong class="titulo__destaque">${EntidadeObjeto.titulo}</strong></h1>
            
            <input type="hidden" name="tituloItem" id="tituloItem" value="${EntidadeObjeto.titulo}">

            <p class="produto__preco"><strong class="produto__preco__destaque">R$ ${EntidadeObjeto.preco}</strong><br></p>
            
            <input type="hidden" name="precoItem" id="precoItem" value="${EntidadeObjeto.preco}">

            <div class="produto__quantidade">
            
                <select class="produto__selecao__unidade" id="qtdeItens" name="qtdeItens"  required>	
				<c:forEach var="qtdeProdutos" begin="1" end="${EntidadeObjeto.qtde}" step="1">
         			<option value="${qtdeProdutos}" >${qtdeProdutos}</option>
      			</c:forEach>
                </select>  
                
                <input type="hidden" name="qtdeEmEstoque" id="qtdeEmEstoque" value="${EntidadeObjeto.qtde}">
                
                <p class="produto__quantidade__unidades">Unidade</p>
            </div>

            <div class="produto__links">
                
                <input class="produto__links__compra" type="submit" name="acao" id="acao" value="Comprar">
                            				
                <input type="hidden" name="acao" id="acao" value="AdicionarNoCarrinho">
                <input class="produto__links__adiciona__carrinho" type="submit" value="Adicionar ao carrinho">
            </div>
         
        </div>
        </section>

         <section class="produto__tabela__informacoes">
    <div class="info-item">
        
        <div class="info-label"><h2>Informações do produto</h2></div>
        <div class="info-item">
            <div class="info-label">Autor:</div>
            <div class="info-value">${EntidadeObjeto.autor}</div>
        </div>
        <div class="info-item">
            <div class="info-label">Categoria:</div>
            <div class="info-value">${EntidadeObjeto.categoria}</div>
        </div>
        <div class="info-item">
            <div class="info-label">Ano:</div>
            <div class="info-value">${EntidadeObjeto.ano}</div>
        </div>
        <div class="info-item">
            <div class="info-label">Editora:</div>
            <div class="info-value">${EntidadeObjeto.editora}</div>
        </div>
        <div class="info-item">
            <div class="info-label">Edição:</div>
            <div class="info-value">${EntidadeObjeto.edicao}</div>
        </div>
        <div class="info-item">
            <div class="info-label">Número de páginas:</div>
            <div class="info-value">${EntidadeObjeto.numeroPaginas}</div>
        </div>
        <div class="info-item">
            <div class="info-label">Sinopse:</div>
            <div class="info-value">${EntidadeObjeto.sinopse}</div>
        </div>
        <div class="info-item">
            <div class="info-label">Altura:</div>
            <div class="info-value">${EntidadeObjeto.altura} cm</div>
            <input type="hidden" name="alturaProduto" id="alturaProduto" value="${EntidadeObjeto.altura}">
        </div>
        <div class="info-item">
            <div class="info-label">Largura:</div>
            <div class="info-value">${EntidadeObjeto.largura} cm</div>
            <input type="hidden" name="larguraProduto" id="larguraProduto" value="${EntidadeObjeto.largura}">
        </div>
        <div class="info-item">
            <div class="info-label">Profundidade:</div>
            <div class="info-value">${EntidadeObjeto.profundidade} cm</div>
            <input type="hidden" name="profundidadeProduto" id="profundidadeProduto" value="${EntidadeObjeto.profundidade}">
        </div>
        <div class="info-item">
            <div class="info-label">Código de barras:</div>
            <div class="info-value">${EntidadeObjeto.codBarras}</div>
        </div>
    </div>
</section>
    </form>
    <footer class="rodape">
        <p>Feito por Isac Henrique</p>
    </footer>
</body>
</html>