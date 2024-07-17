<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:url value="/controller" var="linkController"/>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastrar Cartao</title>
    <link rel="stylesheet" href="<c:url value="/style/styleTemplate.css"/>">
    <link rel="stylesheet" href="<c:url value="/style/styleCadastroClientes.css"/>">
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
    
    <main class="cliente">
        
     <form class="cliente__cadastro" action="${linkController}" method="post">
        
       <div class="cliente__cadastro__dados">
		
		
                <div class="cliente__cadastro__informacoes__cartao">

					<h2 class="cliente__cadastro__titulo">CARTAO DE CRÉDITO</h2>
					
					<input type="hidden" name="id" value="${EntidadeObjeto.id}" />
					
					<input class="cliente__cadastro__campos" type="text" name="nrCartao" id="nrCartao" value="${EntidadeObjeto.nrCartao}" placeholder="Numero do cartão" required>
					
					<select class="cliente__cadastro__select" id="bandeiraCartao" name="bandeiraCartao"  required>
						<option value="" disabled selected>Bandeira </option>
						<c:forEach items="${EntidadeLista}" var="bandeiras">
						<option value="${bandeiras.id}">${bandeiras.bandeira}</option>
						</c:forEach>
					</select> 
					
					<input class="cliente__cadastro__campos" type="text"
						name="nomeCartao" id="nomeCartao" value="${EntidadeObjeto.nomeCartao}" 
						placeholder="Nome do portador" required> 
						
						<input class="cliente__cadastro__campos" type="text" name="codigoSegCartao" id="codigoSegCartao" value="${EntidadeObjeto.codSeguranca}" 
						placeholder="Codigo de segurança" required> 
						
			</div>
                </div>
			
                <div>
                	<input type="hidden" name="usu_id" value="${usuarioLogado.id}">
                	<input type="hidden" name="acao" value="CadastrarCartao">
                    <input class="cliente__cadastro__cadastrar" type="submit" value="Cadastrar">
                </div>
            </form>

           
    </main>
    <footer class="rodape">
        <p>Desenvolvido por Vittor Onishi</p>
    </footer>
</body>

</html>