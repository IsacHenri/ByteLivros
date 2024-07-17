package br.com.ByteLivros.teste;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;


@TestMethodOrder(OrderAnnotation.class)
public class TestesCRUDCliente {
	
	private WebDriver createWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Inicia o navegador em tela cheia
        return new ChromeDriver(options);
    }
	
	@Test
	@Order(1)
	public void testeCadastrar() {
		WebDriver browser = createWebDriver();
		browser.navigate().to("http://localhost:8081/ByteLivros/controller?acao=LoginForm");
		try {
            Thread.sleep(2500);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		WebElement criarConta = browser.findElement(By.className("login__cliente__link"));
        criarConta.click();
        try {
            Thread.sleep(2500);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement nomeCliente = browser.findElement(By.id("nomeCliente"));
        nomeCliente.sendKeys("Criado no Teste");
        try {
            Thread.sleep(2500);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement campoGenero = browser.findElement(By.id("genero"));
        Select genero = new Select(campoGenero);
        genero.selectByValue("Masculino");
        try {
            Thread.sleep(2500);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement campoDtNasc = browser.findElement(By.id("dataNasc"));
        campoDtNasc.sendKeys("17122003");
        try {
            Thread.sleep(2500);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement campoCpf = browser.findElement(By.id("cpf"));
        campoCpf.sendKeys("123.456.789-00");
        try {
            Thread.sleep(2500);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement campoTelefone = browser.findElement(By.id("telefone"));
        campoTelefone.sendKeys("12 34567-8900");
       
        WebElement campoTipoTelefone = browser.findElement(By.id("tipoTelefone"));
        Select telefone = new Select(campoTipoTelefone);
        telefone.selectByValue("Residencial");
        
        WebElement campoEmail = browser.findElement(By.id("e-mail"));
        campoEmail.sendKeys("testejunit@fatec.sp.gov.br");
        
        WebElement campoSenha = browser.findElement(By.id("senha"));
        campoSenha.sendKeys("Senha1234");
        
        WebElement campoSenhaConfirm = browser.findElement(By.id("senhaConfirmacao"));
        campoSenhaConfirm.sendKeys("Senha1234");
        
        //Dados cartao
        WebElement nrCartaoInput = browser.findElement(By.id("nrCartao"));
        nrCartaoInput.sendKeys("123456789012");
        
        WebElement bandeiraCartaoDropdown = browser.findElement(By.id("bandeiraCartao"));
        Select selectBandeira = new Select(bandeiraCartaoDropdown);
        selectBandeira.selectByIndex(1); 
        
        WebElement nomeCartaoInput = browser.findElement(By.id("nomeCartao"));
        nomeCartaoInput.sendKeys("Cartaozin");
        
        WebElement codigoSegCartaoInput = browser.findElement(By.id("codigoSegCartao"));
        codigoSegCartaoInput.sendKeys("12");

        //Endereco residencial
        WebElement tipoResidencia = browser.findElement(By.id("tipoResidenciaResidencial"));
        Select selectTipoResidencia = new Select(tipoResidencia);
        selectTipoResidencia.selectByValue("casa");

        WebElement logradouro = browser.findElement(By.id("logradouroResidencial"));
        logradouro.sendKeys("Rua Juraci");

        WebElement tipoLogradouro = browser.findElement(By.id("tipoLogradouroResidencial"));
        Select selectTipoLogradouro = new Select(tipoLogradouro);
        selectTipoLogradouro.selectByValue("rua");

        WebElement numero = browser.findElement(By.id("numeroResidencial"));
        numero.sendKeys("444");

        WebElement observacao = browser.findElement(By.id("observacaoResidencial"));
        observacao.sendKeys("proximo ao restaurante");

        WebElement bairro = browser.findElement(By.id("bairroResidencial"));
        bairro.sendKeys("Vila Cintra");

        WebElement cep = browser.findElement(By.id("cepResidencial"));
        cep.sendKeys("12345-678");

        WebElement cidade = browser.findElement(By.id("cidade"));
        cidade.sendKeys("Mogi das Cruzes");

        WebElement estado = browser.findElement(By.id("estadoResidencial"));
        Select selectEstado = new Select(estado);
        selectEstado.selectByValue("SP");

        WebElement pais = browser.findElement(By.id("paisResidencial"));
        pais.sendKeys("Brasil");
        try {
            Thread.sleep(2500);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Endereco entrega
        WebElement tipoResidenciaEntrega = browser.findElement(By.id("tipoResidenciaEntrega"));
        Select selectTipoResidenciaEntrega = new Select(tipoResidenciaEntrega);
        selectTipoResidenciaEntrega.selectByValue("casa");

        WebElement logradouroEntrega = browser.findElement(By.id("logradouroEntrega"));
        logradouroEntrega.sendKeys("Rua da Silva");

        WebElement tipoLogradouroEntrega = browser.findElement(By.id("tipoLogradouroEntrega"));
        Select selectTipoLogradouroEntrega = new Select(tipoLogradouroEntrega);
        selectTipoLogradouroEntrega.selectByValue("rua");

        WebElement numeroEntrega = browser.findElement(By.id("numeroEntrega"));
        numeroEntrega.sendKeys("321");

        WebElement observacaoEntrega = browser.findElement(By.id("observacaoEntrega"));
        observacaoEntrega.sendKeys("Ao lado da praca");

        WebElement bairroEntrega = browser.findElement(By.id("bairroEntrega"));
        bairroEntrega.sendKeys("Ipiranga");

        WebElement cepEntrega = browser.findElement(By.id("cepEntrega"));
        cepEntrega.sendKeys("12345-678");

        WebElement cidadeEntrega = browser.findElement(By.id("cidadeEntrega"));
        cidadeEntrega.sendKeys("Mogi das Cruzes");

        WebElement estadoEntrega = browser.findElement(By.id("estadoEntrega"));
        Select selectEstadoEntrega = new Select(estadoEntrega);
        selectEstadoEntrega.selectByValue("SP");

        WebElement paisEntrega = browser.findElement(By.id("paisEntrega"));
        paisEntrega.sendKeys("Brasil");
        
        try {
            Thread.sleep(2500);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //enviar os dados
        WebElement botaoEnviar = browser.findElement(By.className("cliente__cadastro__cadastrar"));
        botaoEnviar.click();
		
		browser.quit();
	}
	
	@Test
	@Order(2)
	public void testeConsultarEAlterar() {
		
		 WebDriver browser = createWebDriver();
		browser.navigate().to("http://localhost:8081/ByteLivros/controller?acao=LoginForm");
		
		//Realizando login
		WebElement email = browser.findElement(By.id("email"));
		email.sendKeys("teste@gmail.com");
		
		WebElement senha = browser.findElement(By.id("senha"));
		senha.sendKeys("senha1234");
		
		//Enviando o login
		WebElement enviar = browser.findElement(By.className("login__cliente__botao"));
		enviar.click();
        
		//Acessando a pagina pra consulta
		browser.navigate().to("http://localhost:8081/ByteLivros/controller?acao=PaginaConsultaUsuarios");
        
		try {
            Thread.sleep(2500); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		//Buscando o usuario
		WebElement paramBuscaCliente = browser.findElement(By.id("paramBuscaCliente"));
		paramBuscaCliente.sendKeys("Criado no Teste");
		
		WebElement botao__pesquisar__lupaP = browser.findElement(By.className("botao__pesquisar__lupaP"));
		botao__pesquisar__lupaP.click();
		
		try {
            Thread.sleep(2500); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		//Visualizando o cliente
		WebElement visualizarDados = browser.findElement(By.id("visualizarDadosCliente"));
		visualizarDados.click();
		
		//Alterando os dados do cliente
		WebElement nomeCliente = browser.findElement(By.id("nomeCliente"));
		nomeCliente.clear();
		nomeCliente.sendKeys("Cliente Alterado Teste");

		
		//Atualizando os dados
		WebElement atualizarDadosCliente = browser.findElement(By.id("atualizarDadosCliente"));
		atualizarDadosCliente.click();
		
		try {
            Thread.sleep(2500); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		browser.quit();
	}
	
	@Test
	@Order(3)
	public void testeExcluir() {
		
		 WebDriver browser = createWebDriver();
		browser.navigate().to("http://localhost:8081/ByteLivros/controller?acao=LoginForm");
		
		//Realizando login
		WebElement email = browser.findElement(By.id("email"));
		email.sendKeys("teste@gmail.com");
		
		WebElement senha = browser.findElement(By.id("senha"));
		senha.sendKeys("senha1234");
		
		//Enviando o login
		WebElement enviar = browser.findElement(By.className("login__cliente__botao"));
		enviar.click();
        
		//Acessando a pagina pra consulta
		browser.navigate().to("http://localhost:8081/ByteLivros/controller?acao=PaginaConsultaUsuarios");
        
		try {
            Thread.sleep(2500); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		//Buscando o usuario
		WebElement paramBuscaCliente = browser.findElement(By.id("paramBuscaCliente"));
		paramBuscaCliente.sendKeys("Cliente Alterado Teste");
		
		WebElement botao__pesquisar__lupaP = browser.findElement(By.className("botao__pesquisar__lupaP"));
		botao__pesquisar__lupaP.click();
		
		try {
            Thread.sleep(2500); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		//Excluindo o cliente
		WebElement botaoExcluir = browser.findElement(By.id("excluirCliente"));
		botaoExcluir.click();
		
		try {
            Thread.sleep(2500); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

		//Buscando o usuario
		//Buscando o usuario
		WebElement paramBuscaCliente2 = browser.findElement(By.id("paramBuscaCliente"));
		paramBuscaCliente2.sendKeys("Cliente Alterado Teste");
				
		WebElement botao__pesquisar__lupaP2 = browser.findElement(By.className("botao__pesquisar__lupaP"));
		botao__pesquisar__lupaP2.click();
		try {
            Thread.sleep(2500); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		browser.quit();
	}
}
