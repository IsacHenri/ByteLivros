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
    <title>Endereço</title>
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
	
	<h2 class="entrega__endereco__titulo">Seleção de Endereço</h2>
	
	<br>
	
        <form class="entrega__informacoes__endereco">
            
            <input type="hidden" name="idUsuario" id="idUsuario" value="${EntidadeObjeto.id}">

			<c:forEach items="${usuarioLogado.listadeEnderecos}" var="endereco">
			<c:if test="${endereco.tipoEndereco == 'ENTREGA'}">
            <div class="entrega__informacoes__endereco__opcao">
                <label class="entrega__selecao__opcao__seletor">
                <input type="checkbox" id="idEndereco" name="idEndereco" value="${endereco.id}">
                <span class="checkmark"></span> 
                ${endereco.logradouro}, ${endereco.numero} - ${endereco.cidade.descricao}, ${endereco.cidade.estado.descricao} - CEP ${endereco.cep}
                <br>
                <br>
                	Valor do frete: R$ ${endereco.valorFrete}
            	</label>
            	<img class="imagem__localizacao" src="./assets/localizacao.png" alt="localizacao">
        </div>
        </c:if>
            </c:forEach>
                
            <div class="entrega__acoes">
                <a class="entrega__adiciona__endereco" href="/ByteLivros/controller?acao=AdicionarEndereco">Adicionar endereco</a>
            <input class="entrega__frete__confirmar" type="submit" value="Confirmar Endereco">
            <input type="hidden" id="acao" name="acao" value="ConfirmarEndereco">
            </div>
        </form>

    </section>
    </main>
	<footer class="rodape">
        <p>Feito por Isac Henrique</p>
    </footer>
</body>
</html>