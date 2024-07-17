<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<c:url value="/controller" var="linkController" />

<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cupons</title>
<link rel="stylesheet" href="<c:url value="/style/stylePaginaSelecaoEndereco.css"/>">
    <link rel="stylesheet" href="<c:url value="/style/styleTemplate.css"/>">
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

    <main class="entrega">
    
    <section class="entrega__informacoes">
	
	<h2 class="entrega__endereco__titulo">Seleção de Cupons</h2>
	
	<br>
		
		<c:set var="contadorElementosLista" value="0" scope="page" />
        <form class="entrega__informacoes__endereco">
			<c:forEach items="${EntidadeLista}" var="cupom">
			
            <div class="entrega__informacoes__endereco__opcao">
                <label class="entrega__selecao__opcao__seletor">
                <c:set var="contadorElementosLista" value="${contadorElementosLista + 1}" scope="page" />
                <input type="checkbox" id="idCupom${contadorElementosLista}" name="idCupom${contadorElementosLista}" value="${cupom.id}">
                <span class="checkmark"></span> 
                <strong>${cupom.codigo}:</strong> Desconto de ${cupom.desconto} - Cupom de ${cupom.tipo}
            	</label>
            	<img class="imagem__localizacao" src="./assets/cupom_desconto.png" alt="cupom">
        	</div>
			<input type="hidden" name="desconto${contadorElementosLista}" id="desconto${contadorElementosLista}" value="${cupom.desconto}">
			
			</c:forEach>
				
			<c:if test="${contadorElementosLista == 0}">
                    <div class="entrega__informacoes__endereco__opcao">
                    	<h2 class="entrega__endereco__titulo">Não há cupons no momento :(</h2>
                   	</div>
          	</c:if>
			
			<input type="hidden" name="qtdeLista" id="qtdeLista" value="${contadorElementosLista}">
            
            <c:if test="${contadorElementosLista != 0}">    
		            <div class="entrega__acoes">
		            <button class="entrega__frete__confirmar" type="submit" id="acao" name="acao" value="AdicionarCupom">
		            Adicionar cupom
		            </button>
		            </div>
            </c:if>
        </form>

    </section>
    </main>
	<footer class="rodape">
        <p>Feito por Isac Henrique</p>
    </footer>
</body>
</html>