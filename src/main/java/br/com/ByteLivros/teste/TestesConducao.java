package br.com.ByteLivros.teste;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


@TestMethodOrder(OrderAnnotation.class)
public class TestesConducao {
	private WebDriver createWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Inicia o navegador em tela cheia
        return new ChromeDriver(options);
    }
	@Test
	@Order(1)
	public void testeCompra() {
		WebDriver browser = createWebDriver();
		browser.navigate().to("http://localhost:8080/ByteLivros/controller?acao=LoginForm");
		
		
		WebElement email = browser.findElement(By.id("email"));
		email.sendKeys("zackin@gmail.com");
		
		WebElement senha = browser.findElement(By.id("senha"));
		senha.sendKeys("senha1234");
		
		WebElement enviar = browser.findElement(By.className("login__cliente__botao"));
		enviar.click();
		
		browser.navigate().to("http://localhost:8080/ByteLivros/controller?acao=PaginaInicial");
		
		browser.navigate().to("http://localhost:8080/ByteLivros/controller?acao=ExibeDadosProduto&id=1");

        WebElement selectElement = browser.findElement(By.id("qtdeItens"));
        
        Select select = new Select(selectElement);
        try {
            Thread.sleep(1000);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        select.selectByVisibleText("4"); 
        
        WebElement adicionarAoCarrinhoButtonCasoEvandro = browser.findElement(By.className("produto__links__adiciona__carrinho"));
        adicionarAoCarrinhoButtonCasoEvandro.click();
        
        browser.navigate().to("http://localhost:8080/ByteLivros/controller?acao=ExibeDadosProduto&id=2");
        
        WebElement selectElement2 = browser.findElement(By.id("qtdeItens"));
        Select select2 = new Select(selectElement2);
        select2.selectByVisibleText("5"); 
        try {
            Thread.sleep(1000);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement adicionarAoCarrinhoButtonIt = browser.findElement(By.className("produto__links__adiciona__carrinho"));
        adicionarAoCarrinhoButtonIt.click();
        
        browser.navigate().to("http://localhost:8080/ByteLivros/controller?acao=ExibeDadosProduto&id=3");
        
        WebElement selectElement3 = browser.findElement(By.id("qtdeItens"));
        Select select3 = new Select(selectElement3);
        select3.selectByVisibleText("1"); 
        
        WebElement adicionarAoCarrinhoButtonHp = browser.findElement(By.className("produto__links__adiciona__carrinho"));
        adicionarAoCarrinhoButtonHp.click();
        
        browser.navigate().to("http://localhost:8080/ByteLivros/controller?acao=Carrinho");
        
        try {
            Thread.sleep(1000);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        WebElement alterarDoCarrinho = browser.findElement(By.id("alterarDoCarrinho"));
        alterarDoCarrinho.click();
        
        WebElement qtdeItens = browser.findElement(By.id("qtdeItens"));
        Select quantidade = new Select(qtdeItens);
        quantidade.selectByValue("2");
        try {
            Thread.sleep(1000);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        WebElement carrinho__produtos__alterar__submit = browser.findElement(By.className("carrinho__produtos__alterar__submit"));
        carrinho__produtos__alterar__submit.click();
        
        
        WebElement continuarCompra = browser.findElement(By.id("acao"));
        continuarCompra.click();
        
        List<WebElement> checkboxEndereco = browser.findElements(By.className("checkmark"));
        checkboxEndereco.get(0).click();
        
        WebElement confirmarLink = browser.findElement(By.className("entrega__frete__confirmar"));
        confirmarLink.click();
        
        List<WebElement> checkboxCartao = browser.findElements(By.className("checkmark"));
        checkboxCartao.get(0).click();
        
        WebElement fecharPedido = browser.findElement(By.id("acao"));
        fecharPedido.click();
        
        try {
            Thread.sleep(1000);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

	    browser.quit();
	}
	
	@Test
	@Order(2)
	public void testeAlterandoStatus() {
		WebDriver browser = createWebDriver();
		browser.navigate().to("http://localhost:8080/ByteLivros/controller?acao=LoginForm");
		
		
		WebElement email = browser.findElement(By.id("email"));
		email.sendKeys("teste@gmail.com");
		
		WebElement senha = browser.findElement(By.id("senha"));
		senha.sendKeys("senha1234");
		
		WebElement enviar = browser.findElement(By.className("login__cliente__botao"));
		enviar.click();
		
		try {
            Thread.sleep(1000);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		browser.navigate().to("http://localhost:8080/ByteLivros/controller?acao=ConsultarPedido");
		// Encontrar todos os elementos da lista
	    List<WebElement> elementosPedido = browser.findElements(By.cssSelector(".pedidos__lista__informacoes"));

	    // Extrair o último elemento da lista
	    WebElement ultimoPedido = elementosPedido.get(elementosPedido.size() - 1);

	    // Agora, você pode manipular o último elemento conforme necessário, por exemplo:
	    WebElement selectStatus = ultimoPedido.findElement(By.id("statusPedido"));
	    Select statusDropdown = new Select(selectStatus);
	    statusDropdown.selectByVisibleText("ENTREGUE");
	    try {
            Thread.sleep(1000);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	    // Enviar o formulário para alterar o status
	    WebElement botaoAlterar = ultimoPedido.findElement(By.id("acao"));
	    botaoAlterar.click();
	    
	    browser.quit();
	}
	
	@Test
	@Order(3)
	public void testeSolicitarTroca() {
		WebDriver browser = createWebDriver();
		browser.navigate().to("http://localhost:8080/ByteLivros/controller?acao=LoginForm");
		
		
		WebElement email = browser.findElement(By.id("email"));
		email.sendKeys("zackin@gmail.com");
		
		WebElement senha = browser.findElement(By.id("senha"));
		senha.sendKeys("senha1234");
		
		WebElement enviar = browser.findElement(By.className("login__cliente__botao"));
		enviar.click();
		
		browser.navigate().to("http://localhost:8080/ByteLivros/controller?acao=ConsultarPedido");
		
		try {
            Thread.sleep(1000);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		
		WebElement trocarPedido = browser.findElement(By.className("pedidos__lista__informacoes__acoes__trocar__pedido"));
		trocarPedido.click();
		
		try {
            Thread.sleep(1000);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		browser.quit();
	}
	
	@Test
	@Order(4)
	public void testeAlterandoStatusTrocaRealizada() {
		WebDriver browser = createWebDriver();
		browser.navigate().to("http://localhost:8080/ByteLivros/controller?acao=LoginForm");
		
		
		WebElement email = browser.findElement(By.id("email"));
		email.sendKeys("teste@gmail.com");
		
		WebElement senha = browser.findElement(By.id("senha"));
		senha.sendKeys("senha1234");
		
		WebElement enviar = browser.findElement(By.className("login__cliente__botao"));
		enviar.click();
		
		try {
            Thread.sleep(1000);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		browser.navigate().to("http://localhost:8080/ByteLivros/controller?acao=ConsultarPedido");
		// Encontrar todos os elementos da lista
	    List<WebElement> elementosPedido = browser.findElements(By.cssSelector(".pedidos__lista__informacoes"));

	    // Extrair o último elemento da lista
	    WebElement ultimoPedido = elementosPedido.get(elementosPedido.size() - 1);
	    
	    // Agora, você pode manipular o último elemento conforme necessário, por exemplo:
	    WebElement selectStatus = ultimoPedido.findElement(By.id("statusPedido"));
	    Select statusDropdown = new Select(selectStatus);
	    statusDropdown.selectByVisibleText("TROCA REALIZADA");
	    try {
            Thread.sleep(2500);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	    // Enviar o formulário para alterar o status
	    WebElement botaoAlterar = ultimoPedido.findElement(By.id("acao"));
	    botaoAlterar.click();
	    
	    browser.quit();
	}
	
	@Test
	@Order(5)
	public void testeCompraComCupom() {
		WebDriver browser = createWebDriver();
		browser.navigate().to("http://localhost:8080/ByteLivros/controller?acao=LoginForm");
		
		
		WebElement email = browser.findElement(By.id("email"));
		email.sendKeys("zackin@gmail.com");
		
		WebElement senha = browser.findElement(By.id("senha"));
		senha.sendKeys("senha1234");
		
		WebElement enviar = browser.findElement(By.className("login__cliente__botao"));
		enviar.click();
		
		browser.navigate().to("http://localhost:8080/ByteLivros/controller?acao=PaginaInicial");
		
		browser.navigate().to("http://localhost:8080/ByteLivros/controller?acao=ExibeDadosProduto&id=1");

        WebElement selectElement = browser.findElement(By.id("qtdeItens"));
        Select select = new Select(selectElement);
        select.selectByVisibleText("2"); 
        try {
            Thread.sleep(1000);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement adicionarAoCarrinhoButtonCasoEvandro = browser.findElement(By.className("produto__links__adiciona__carrinho"));
        adicionarAoCarrinhoButtonCasoEvandro.click();
        
        browser.navigate().to("http://localhost:8080/ByteLivros/controller?acao=ExibeDadosProduto&id=2");
        
        WebElement selectElement2 = browser.findElement(By.id("qtdeItens"));
        Select select2 = new Select(selectElement2);
        select2.selectByVisibleText("1"); 
        try {
            Thread.sleep(1000);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement adicionarAoCarrinhoButtonIt = browser.findElement(By.className("produto__links__adiciona__carrinho"));
        adicionarAoCarrinhoButtonIt.click();
        
        browser.navigate().to("http://localhost:8080/ByteLivros/controller?acao=ExibeDadosProduto&id=3");
        
        WebElement selectElement3 = browser.findElement(By.id("qtdeItens"));
        Select select3 = new Select(selectElement3);
        select3.selectByVisibleText("1"); 
        
        WebElement adicionarAoCarrinhoButtonHp = browser.findElement(By.className("produto__links__adiciona__carrinho"));
        adicionarAoCarrinhoButtonHp.click();
        
        browser.navigate().to("http://localhost:8080/ByteLivros/controller?acao=Carrinho");
        try {
            Thread.sleep(1000);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        WebElement continuarCompra = browser.findElement(By.id("acao"));
        continuarCompra.click();
        
        List<WebElement> checkboxEndereco = browser.findElements(By.className("checkmark"));
        checkboxEndereco.get(0).click();
        
        WebElement confirmarLink = browser.findElement(By.className("entrega__frete__confirmar"));
        confirmarLink.click();
        
        
        WebElement adicionaCupom = browser.findElement(By.className("pagamento__adiciona__cupom"));
        adicionaCupom.click();
        
        try {
            Thread.sleep(2500);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> checkboxCupom = browser.findElements(By.className("checkmark"));
        checkboxCupom.get(0).click();
        
        try {
            Thread.sleep(2500);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        WebElement confirmarCupom = browser.findElement(By.id("acao"));
        confirmarCupom.click();
        
        List<WebElement> checkboxCartao = browser.findElements(By.className("checkmark"));
        checkboxCartao.get(0).click();
        
        WebElement fecharPedido = browser.findElement(By.id("acao"));
        fecharPedido.click();
        
        try {
            Thread.sleep(2500);  // Aguarda 2,5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

	    browser.quit();
	}
}
