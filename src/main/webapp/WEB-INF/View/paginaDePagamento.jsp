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
    <title>Pagamento</title>
    <link rel="stylesheet" href="<c:url value="/style/stylePaginaPagamento.css"/>">
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
    
    <a class="link__voltar" href="/ByteLivros/controller?acao=PaginaInicial">
        <img class="icone__voltar" src="./assets/botaoVoltar.png">
    </a>

    <main class="pagamento">

        <section class="pagamento__produto__metodos">

            <form class="pagamento__selecao">

                <div class="pagamento__selecao__opcao">
                    <label class="pagamento__selecao__opcao__seletor">
                        <input type="checkbox" id="caixaSelecao" name="opcao" value="">
                        <span class="checkmark"></span>
                        PIX</label>
                </div>

                <div class="pagamento__selecao__opcao">
                    <label class="pagamento__selecao__opcao__seletor">
                        <input type="checkbox" id="caixaSelecao" name="opcao" value="">
                        <span class="checkmark"></span>
                        Cartão de crédito</label>
                </div>

                <div class="pagamento__selecao__opcao">
                    <label class="pagamento__selecao__opcao__seletor">
                        <input type="checkbox" id="caixaSelecao" name="opcao" value="">
                        <span class="checkmark"></span>
                        Cartão de débito</label>
                </div>

                <div class="pagamento__selecao__opcao">
                    <label class="pagamento__selecao__opcao__seletor">
                        <input type="checkbox" id="caixaSelecao" name="opcao" value="">
                        <span class="checkmark"></span>
                        Boleto </label>
                </div>

                <div class="pagamento__acoes">
                    <a class="pagamento__adiciona__metodo" href="">
                        <img class="imagem__adicionar" src="./assets/adicionar.png" alt="add">    Adicionar método de pagamento
                    </a>
                <a class="pagamento__adiciona__cupom" href="">
                    <img class="imagem__adicionar" src="./assets/adicionar.png" alt="add"> Adicionar cupom de desconto
                    </a>
                    </div>

                <input class="pagamento__selecao__confirmar" type="submit" value="Confirmar">

            </form>

            
        </section>



        <section class="descricao__produto">


            <p class="resumo__compra">
                <strong>
                    Resumo da compra
                </strong>
            </p>

            <img class="pagamento__produto__imagem" src="./assets/trilogia-o-senhor-dos-aneis-novo-01.png"
                alt="Imagem do produto">

            <p class="resumo__compra__produto">
                Kit Luxo Trilogia O Senhor Dos Anéis – Versão Exclusiva
            </p>

            <p class="resumo__compra__preco">
                R$700,00
            </p>

            <p class="resumo__compra__quantidade">
                1 unidade
            </p>

        </section>

    </main>
    <footer class="rodape">
        <p>Feito por Isac Henrique</p>
    </footer>
</body>

</html>