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
    <title>Gráfico</title>
    <link rel="stylesheet" href="<c:url value="/style/styleTemplate.css"/>">
    <link rel="stylesheet" href="<c:url value="/style/stylePaginaGrafico.css"/>">
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

     <a class="link__voltar" href="/ByteLivros/controller?acao=PaginaPerfil">   
        <img class="icone__voltar" src="./assets/botaoVoltar.png"> Voltar 
    </a>
       
     <main class="grafico">
    <div class="pagina__grafico__formulario">
        <div class="pagina__grafico__titulos">
            <h2>Gráfico de Vendas</h2>
            <hr>
        </div>
        <form action="${linkController}">
            <div>
                <label for="dtInicio" class="calendario_label">Data Inicio</label>
                <div>
                    <input type="date" class="pagina__grafico__inputs" id="dtInicio" name="dtInicio" value="${dtInicioPesquisada}">
                </div>
            </div>
            <div>
                <label for="dtFim" class="calendario_label">Data Fim</label>
                <div>
                    <input type="date" class="pagina__grafico__inputs" id="dtFim" name="dtFim" value="${dtFimPesquisada}">
                </div>
            </div>
            <div>
                <button type="submit" class="gerar__grafico" id="acao" name="acao" value="GerarGrafico">Gerar Gráfico</button>
            </div>
        </form>
    </div>
    <div class="grafico__grafico">
        <div>
            <canvas id="myChart"></canvas>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
        const ctx = document.getElementById('myChart').getContext('2d');
        new Chart(ctx, {
            type: 'line',
            data: {
                labels: ${txtLabels},
                datasets: ${dataSet}
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    </script>
</main>
<footer class="rodape">
    <p>Feito por Isac Henrique</p>
</footer>