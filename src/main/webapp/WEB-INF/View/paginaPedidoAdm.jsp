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
		
            	<a class="link__voltar" href="/ByteLivros/controller?acao=PaginaPerfil">
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
                    <th class="pedidos__lista__header">Pedidos</th>
                </tr>

				<c:set var="contadorElementosLista" value="0" scope="page" />
				
				<c:forEach items="${EntidadeLista}" var="pedido">
                <tr class="pedidos__lista__informacoes">
                	<td><strong>Número</strong>
                		<p class="pedidos__lista__informacoes__dados__numeropedido"> ${pedido.id}</p>
                	</td>
        			
        			<td><strong>Data</strong>
        				<p class="pedidos__lista__informacoes__dados__numeropedido">${pedido.dataPedido}</p>
        			</td>
        			
        			<td><strong>Valor</strong>
        				<p class="pedidos__lista__informacoes__dados__numeropedido">R$ ${pedido.valorTotal + pedido.endereco.valorFrete}</p>
        			</td>
					
					<td><strong>Cliente</strong>
        				<p class="pedidos__lista__informacoes__dados__numeropedido">${pedido.usuario.nome}</p>
        			</td>
                    
                    <td class="pedidos__lista__informacoes__acoes__admin">
                        <a class="botao__pesquisar_produto" href="/ByteLivros/controller?acao=VerItensPedido&id=${pedido.id}">
							<img class="botao__pesquisar__lupa_produto" src="./assets/icone_menu.png"
							alt="Pesquisar">
                        </a>
                      </td>
                      <td>  
                        <div class="pedidos__lista__informacoes__acoes__admin">
                        <form action="${linkController}">
								<select class="status__select" name="statusPedido" id="statusPedido"
									required>
									<option value="" disabled selected>Selecione o status</option>
									<option value="EM PROCESSAMENTO">EM PROCESSAMENTO</option>
									<option value="PEDIDO APROVADO">PEDIDO APROVADO</option>
									<option value="PAGAMENTO RECUSADO">PAGAMENTO RECUSADO</option>
									<option value="PEDIDO CANCELADO">PEDIDO CANCELADO</option>
									<option value="EM TRÂNSITO">EM TRÂNSITO</option>
									<option value="ENTREGUE">ENTREGUE</option>
									<option value="TROCA APROVADA">TROCA APROVADA</option>
									<option value="TROCA REALIZADA">TROCA REALIZADA</option>
									<option value="TROCA RECUSADA">TROCA RECUSADA</option>
								</select> <input type="hidden" name="idPedido" id="idPedido"
									value="${pedido.id}">
									
                        		<input type="hidden" name="idCliente" id="idCliente" value="${pedido.usuario.id}">
                        		<input type="hidden" name="valorPedido" id="valorPedido" value="${pedido.valorTotal}">

								<button class="status__alterar" type="submit" name="acao"
								id="acao" value="AlterarStatusPedido"><img class="botao__pesquisar__lupa" src="./assets/icone_atualizar.png"
								alt="Alterar"></button>
							</form> 
						</div>
                    </td>
                    </tr>
                    
                    <c:set var="contadorElementosLista"
						value="${contadorElementosLista + 1}" scope="page" />
						
                </c:forEach>

            </table>
 
        </section>
        <c:if test="${contadorElementosLista == 0}">
                    <div>
                    	<h2 class="nao__encontrado">Ninguem ainda fez compras :(</h2>
                   	</div>
                    </c:if>

    </main>
    <footer class="rodape">
        <p>Feito por Isac Henrique</p>
    </footer>
</body>

</html>