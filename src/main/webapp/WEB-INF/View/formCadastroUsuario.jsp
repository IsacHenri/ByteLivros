<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/controller" var="linkController" />

<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cadastro</title>
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
		<form class="cliente__cadastro" action="${linkController}"
			method="post">
			<div class="cliente__cadastro__dados">
				<div class="cliente__cadastro__informacoes__pessoais">
					<h2 class="cliente__cadastro__titulo">DADOS PESSOAIS</h2>


					<input class="cliente__cadastro__campos" type="text"
						name="nomeCliente" id="nomeCliente" placeholder="Nome" required>
					<select class="cliente__cadastro__select" id="genero" name="genero"
						required>
						<option disabled selected>Gênero</option>
						<option value="Masculino">Masculino</option>
						<option value="Feminino">Feminino</option>
						<option value="Não Especificado">Não Especificado</option>
					</select> <input class="cliente__cadastro__campos" type="date"
						name="dataNasc" id="dataNasc" pattern="\d{2}/\d{2}/\d{4}"
						placeholder="Data de nascimento" required> <input
						class="cliente__cadastro__campos" type="text" name="cpf" id="cpf"
						placeholder="CPF" required> <input
						class="cliente__cadastro__campos" type="text" name="telefone"
						id="telefone" pattern="\d{2} \d{4,5}-\d{4}" placeholder="Telefone"
						required> <select class="cliente__cadastro__select"
						name="tipoTelefone" id="tipoTelefone" required>
						<option value="" disabled selected>Selecione um tipo</option>
						<option value="Residencial">Residencial</option>
						<option value="Celular">Celular</option>
						<option value="Contato">Contato</option>
					</select> <input class="cliente__cadastro__campos" type="text" name="e-mail"
						id="e-mail" placeholder="E-mail"
						pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}" required>

					<input class="cliente__cadastro__campos" type="password"
						name="senha" id="senha" placeholder="Senha" required> <input
						class="cliente__cadastro__campos" type="password"
						name="senhaConfirmacao" id="senhaConfirmacao"
						placeholder="Confirmar senha" required>

				</div>

				<div class="cliente__cadastro__informacoes__cartao">

					<h2 class="cliente__cadastro__titulo">CARTAO DE CRÉDITO</h2>

					<input class="cliente__cadastro__campos" type="text"
						name="nrCartao" id="nrCartao" placeholder="Numero do cartão" maxlength="12"
						required> <select class="cliente__cadastro__select"
						id="bandeiraCartao" name="bandeiraCartao" required>
						<option value="" disabled selected>Bandeira</option>
						<c:forEach items="${EntidadeLista}" var="bandeiras">
							<option value="${bandeiras.id}">${bandeiras.bandeira}</option>
						</c:forEach>
					</select> <input class="cliente__cadastro__campos" type="text"
						name="nomeCartao" id="nomeCartao" placeholder="Nome do portador"
						required> <input class="cliente__cadastro__campos"
						type="text" name="codigoSegCartao" id="codigoSegCartao"
						placeholder="Codigo de segurança" required>

				</div>

			</div>

			<div class="cliente__cadastro__dados">
				<div class="cliente__cadastro__informacoes__endereco">
					<h2 class="cliente__cadastro__titulo">ENDEREÇO RESIDENCIAL</h2>

					<select class="cliente__cadastro__select"
						name="tipoResidenciaResidencial" id="tipoResidenciaResidencial"
						required>
						<option value="" disabled selected>Tipo de residencia</option>
						<option value="casa">Casa</option>
						<option value="apartamento">Apartamento</option>

					</select> <input class="cliente__cadastro__campos" type="text"
						name="logradouroResidencial" id="logradouroResidencial"
						placeholder="Logradouro" required> <select
						class="cliente__cadastro__select" name="tipoLogradouroResidencial"
						id="tipoLogradouroResidencial" required>
						<option value="" disabled selected>Tipo de logradouro</option>
						<option value="estrada">Estrada</option>
						<option value="rua">Rua</option>
					</select> <input class="cliente__cadastro__campos" type="text"
						name="numeroResidencial" id="numeroResidencial"
						placeholder="Número" required> <input
						class="cliente__cadastro__campos" type="text"
						name="observacaoResidencial" id="observacaoResidencial"
						placeholder="Observação" required> <input
						class="cliente__cadastro__campos" type="text"
						name="bairroResidencial" id="bairroResidencial"
						placeholder="Bairro" required> <input
						class="cliente__cadastro__campos" type="text"
						name="cepResidencial" id="cepResidencial" placeholder="CEP"
						pattern="\d{5}-\d{3}" required> <input
						class="cliente__cadastro__campos" type="text"
						name="cidadeResidencial" id="cidade" placeholder="Cidade" required>

					<select class="cliente__cadastro__select" id="estadoResidencial"
						name="estadoResidencial" required>
						<option value="" disabled selected>Selecione um estado</option>
						<option value="AC">Acre</option>
						<option value="AL">Alagoas</option>
						<option value="AP">Amapá</option>
						<option value="AM">Amazonas</option>
						<option value="BA">Bahia</option>
						<option value="CE">Ceará</option>
						<option value="DF">Distrito Federal</option>
						<option value="ES">Espirito Santo</option>
						<option value="GO">Goiás</option>
						<option value="MA">Maranhão</option>
						<option value="MT">Mato Grosso</option>
						<option value="MS">Mato Grosso do Sul</option>
						<option value="MG">Minas Gerais</option>
						<option value="PA">Pará</option>
						<option value="PB">Paraíba</option>
						<option value="PR">Paraná</option>
						<option value="PE">Pernambuco</option>
						<option value="PI">Piauí</option>
						<option value="RJ">Rio de Janeiro</option>
						<option value="RN">Rio Grande no Norte</option>
						<option value="RS">Rio Grande do Sul</option>
						<option value="RO">Rondônia</option>
						<option value="RR">Roraima</option>
						<option value="SC">Santa Catarina</option>
						<option value="SP">Sâo Paulo</option>
						<option value="SE">Sergipe</option>
						<option value="TO">Tocantins</option>
					</select> <input class="cliente__cadastro__campos" type="text"
						name="paisResidencial" id="paisResidencial" placeholder="País"
						required>

				</div>

				<div class="cliente__cadastro__informacoes__endereco">
					<h2 class="cliente__cadastro__titulo">ENDEREÇO DE ENTREGA</h2>

					<select class="cliente__cadastro__select"
						name="tipoResidenciaEntrega" id="tipoResidenciaEntrega" required>
						<option disabled selected>Tipo de residencia</option>
						<option value="casa">Casa</option>
						<option value="apartamento">Apartamento</option>

					</select> <input class="cliente__cadastro__campos" type="text"
						name="logradouroEntrega" id="logradouroEntrega"
						placeholder="Logradouro" required> <select
						class="cliente__cadastro__select" name="tipoLogradouroEntrega"
						id="tipoLogradouroEntrega" required>
						<option value="">Tipo de logradouro</option>
						<option value="estrada">Estrada</option>
						<option value="rua">Rua</option>
					</select> <input class="cliente__cadastro__campos" type="text"
						name="numeroEntrega" id="numeroEntrega" placeholder="Número"
						required> <input class="cliente__cadastro__campos"
						type="text" name="observacaoEntrega" id="observacaoEntrega"
						placeholder="Observação" required> <input
						class="cliente__cadastro__campos" type="text" name="bairroEntrega"
						id="bairroEntrega" placeholder="Bairro" required> <input
						class="cliente__cadastro__campos" type="text" name="cepEntrega"
						id="cepEntrega" placeholder="CEP" pattern="\d{5}-\d{3}" required>

					<input class="cliente__cadastro__campos" type="text"
						name="cidadeEntrega" id="cidadeEntrega" placeholder="Cidade" required>

					<select class="cliente__cadastro__select" id="estadoEntrega"
						name="estadoEntrega" required>
						<option value="" disabled selected>Selecione um estado</option>
						<option value="AC">Acre</option>
						<option value="AL">Alagoas</option>
						<option value="AP">Amapá</option>
						<option value="AM">Amazonas</option>
						<option value="BA">Bahia</option>
						<option value="CE">Ceará</option>
						<option value="DF">Distrito Federal</option>
						<option value="ES">Espirito Santo</option>
						<option value="GO">Goiás</option>
						<option value="MA">Maranhão</option>
						<option value="MT">Mato Grosso</option>
						<option value="MS">Mato Grosso do Sul</option>
						<option value="MG">Minas Gerais</option>
						<option value="PA">Pará</option>
						<option value="PB">Paraíba</option>
						<option value="PR">Paraná</option>
						<option value="PE">Pernambuco</option>
						<option value="PI">Piauí</option>
						<option value="RJ">Rio de Janeiro</option>
						<option value="RN">Rio Grande no Norte</option>
						<option value="RS">Rio Grande do Sul</option>
						<option value="RO">Rondônia</option>
						<option value="RR">Roraima</option>
						<option value="SC">Santa Catarina</option>
						<option value="SP">São Paulo</option>
						<option value="SE">Sergipe</option>
						<option value="TO">Tocantins</option>
					</select> <input class="cliente__cadastro__campos" type="text"
						name="paisEntrega" id="paisEntrega" placeholder="País" required>

				</div>

			</div>

			<div>
				<input type="hidden" name="acao" value="CadastrarUsuario"> <input
					class="cliente__cadastro__cadastrar" type="submit"
					value="Cadastrar">
			</div>
		</form>


	</main>
	<footer class="rodape">
        <p>Feito por Isac Henrique</p>
    </footer>
</body>

</html>