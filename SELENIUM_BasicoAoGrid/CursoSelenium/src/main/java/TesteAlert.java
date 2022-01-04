import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAlert {
	
	//variaveis
	private WebDriver driver;
	private DSL dsl;
	
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
	
	//Testes
	@Test
	public void DeveInteragirComAlertSimples() {
		dsl.clicarBotao("alert");
		String texto = dsl.alertaObterTextoEAceitar();
		Assert.assertEquals("Alert Simples", texto);
		dsl.escreve("elementosForm:nome", texto);
	}
	
	@Test
	public void DeveClicarEmConfirmEConfirmar() {
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoEAceitar());
		Assert.assertEquals("Confirmado", dsl.alertaObterTextoEAceitar());
		
		//negativa
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoENega());
		Assert.assertEquals("Negado", dsl.alertaObterTextoENega());
	}
		
	
	@Test
	public void DeveInteragirComPromptCompilado() {
		dsl.clicarBotao("prompt");
		Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
		dsl.alertaEscrever("25");
		
		Assert.assertEquals("Era 25?", dsl.alertaObterTextoEAceitar());
		Assert.assertEquals(":D", dsl.alertaObterTextoEAceitar());
	}
	
}
