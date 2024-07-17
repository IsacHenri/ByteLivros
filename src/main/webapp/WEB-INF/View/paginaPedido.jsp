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
<title>Pedidos</title>
<link rel="stylesheet" href="<c:url value="/style/styleTemplate.css"/>">
<link rel="stylesheet" href="<c:url value="/style/stylePedidos.css"/>">
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
    
	<main class="pedidos">

        <section class="pedidos">
            	<a class="link__voltar" href="/ByteLivros/controller?acao=PaginaInicial">
                	<img class="icone__voltar" src="./assets/botaoVoltar.png">
            	</a>
            
            <form class="buscar__pedido" action="${linkController}" method="get">
				<div class="pesquisa-wrapper">
					<p>Nome</p>
					<div class="pesquisa-input">
						<input class="barra__pesquisap" type="text" id="paramBuscaPedido"
						name="paramBuscaPedido" placeholder="Buscar pedido"> <input
						type="hidden" name="acao" value="ConsultarPedido">

						<button class="botao__pesquisarP" type="submit">
							<img class="botao__pesquisar__lupaP" src="./assets/pesquisar.png" alt="Pesquisar">
						</button>
					</div>
				 </div>
			</form>
    		
    		<table class="pedidos__lista__produtos">
                <tr>
                    <th class="pedidos__lista__header" colspan="3">Pedidos</th>
                </tr>


				<c:set var="contadorElementosLista" value="0" scope="page" />
                
				<c:forEach items="${EntidadeLista}" var="pedido">
                <tr class="pedidos__lista__informacoes">
                    <td class="pedidos__lista__informacoes__dados">

                        <p class="pedidos__lista__informacoes__dados__titulo"><strong class="titulo__destaque">
                                Dados do pedido:
                            </strong></p>

                        <p class="pedidos__lista__informacoes__dados__numeropedido">Número do pedido: ${pedido.id}</p>
                        <p class="pedidos__lista__informacoes__dados__datapedido">Data do pedido: ${pedido.dataPedido}</p>
                        <p class="pedidos__lista__informacoes__dados__preco">Valor: R$ ${pedido.valorTotal + pedido.endereco.valorFrete}</p>
					</td>
                       
                       <td class="pedidos__lista__informacoes__dados">
                        <p class="pedidos__lista__dados__produtos__titulo">
								<strong class="titulo__destaque"> Produtos: </strong>
							</p> 
						
							<c:forEach items="${pedido.listaItens}" var="itemPedido">
								<p class="pedidos__lista__dados__produtos__titulo">${itemPedido.qtdeProdutos}X
									${itemPedido.livro.titulo} 
									
								</p>
								<c:set var="produtoEstaEntregue" value="false" />
								<c:if test="${itemPedido.statusPedido == 'ENTREGUE'}">
									<c:set var="produtoEstaEntregue" value="true" />
								</c:if>
								
							</c:forEach>
					</td>	
					
                    <td class="pedidos__lista__informacoes__acoes">
                        <a class="pedidos__lista__informacoes__acoes__ver__pedido" href="/ByteLivros/controller?acao=VerItensPedido&id=${pedido.id}">
                            Ver pedido
                        </a>
                        <c:if test="${produtoEstaEntregue}">
                        	<form action="${linkController}">
                        		<input type="hidden" name="idPedido" id="idPedido" value="${pedido.id}">
                    			<input type="hidden" name="statusPedido" id="statusPedido" value="TROCA SOLICITADA">
                    			<button class="pedidos__lista__informacoes__acoes__trocar__pedido" type="submit" name="acao" id="acao" value="SolicitarTrocaPedido">   
                        			Trocar pedido
                    			</button>
                    		</form>
	                      
                        </c:if>
                       
                    </td>
                </tr>
                
                <c:set var="contadorElementosLista"
						value="${contadorElementosLista + 1}" scope="page" />
						
                </c:forEach>
                
                

            </table>
        </section>
        <c:if test="${contadorElementosLista == 0}">
                    <div>
                    	<h2 class="nao__encontrado">Você ainda não fez compras :(</h2>
                   	</div>
                    </c:if>

    </main>
    <footer class="rodape">
        <p>Feito por Isac Henrique</p>
    </footer>
</body>

</html>