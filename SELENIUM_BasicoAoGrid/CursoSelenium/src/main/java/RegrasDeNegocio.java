import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class RegrasDeNegocio {
	private DSL dsl;
	
	//variaveis
	private WebDriver driver;
	
	//Metodos
	@Before
	public void abrirNavegador() {
		System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void fecharNavegador() {
		driver.quit();
	}	
	
	//Teste Regressivo
	@Test
	public void ValidarNomeObrigatorio() {
	dsl.clicarBotao("elementosForm:cadastrar");
	Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceitar());
	}
	
	@Test
	public void ValidarSobrenomeObrigatorio() {
		//Preencher Nome
		dsl.escreve("elementosForm:nome", "Vinicius");
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceitar());
	}
	
	
	@Test
	public void ValidarSexoObrigatorio() {
		//Preencher Nome
		dsl.escreve("elementosForm:nome", "Vinicius");
		//Preencher sobrenome
		dsl.escreve("elementosForm:sobrenome", "Sobrenome qualquer");
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceitar());
	}
	
	
	@Test
	public void ValidarVegetariano() {
		//Preencher Nome
		dsl.escreve("elementosForm:nome", "Vinicius");
		
		//Preencher sobrenome
		dsl.escreve("elementosForm:sobrenome", "Qualquer nome");
		
		//Selecionar sexo
		dsl.clicarBotao("elementosForm:sexo:0");
		
		//Selecionando comida
		dsl.clicarBotao("elementosForm:comidaFavorita:0");
		dsl.clicarBotao("elementosForm:comidaFavorita:3");
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceitar());
		
	}

	
	@Test
	public void ValidarEsportistaIndeciso() {
		dsl.escreve("elementosForm:nome", "Vinicius");
		dsl.escreve("elementosForm:sobrenome", "Avila Macedo");
		dsl.clicarBotao("elementosForm:sexo:0");
		dsl.clicarBotao("elementosForm:comidaFavorita:1");
		dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
		dsl.selecionarCombo("elementosForm:esportes","Corrida");
		dsl.selecionarCombo("elementosForm:esportes","O que eh esporte?");
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceitar());
	}

}
