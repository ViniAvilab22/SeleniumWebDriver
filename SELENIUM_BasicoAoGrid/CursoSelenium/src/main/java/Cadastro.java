import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Cadastro {
	
	//Variaveis
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
	
	private static final String List = null;
	private static final String AllSelectedOptions = null;

	//Testes
	@Test
	public void PreenchimentoCadastro() {
		
		//Preencher Nome
		dsl.escreve("elementosForm:nome", "Vinicius");
		
		//Preencher sobrenome
		dsl.escreve("elementosForm:sobrenome", "Avila Macedo");
		
		//Selecionar sexo
		dsl.clicarRadio("elementosForm:sexo:0");
		
		//Selecionando comida
		dsl.clicarRadio("elementosForm:comidaFavorita:1");
		dsl.selecionarCombo("elementosForm:escolaridade","Superior");

		
		//new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Corrida");
		
		//Selecionar esportes
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "Karate");
		//List<WebElement> allSelectedOptions = comboEsportes.getAllSelectedOptions();
		//Assert.assertEquals(3, allSelectedOptions.size());
		
		//Preencher caixa de sugestoes
		dsl.escreve("elementosForm:sugestoes", "Este é meu texto mais legal!");
		
		//Clicar em cadastro
		dsl.clicarBotao("elementosForm:cadastrar");
		

		Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Vinicius"));
		Assert.assertEquals("Sobrenome: Avila Macedo", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Frango", dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: superior", dsl.obterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: Corrida Karate",dsl.obterTexto("descEsportes"));
		Assert.assertTrue(dsl.obterTexto("descSugestoes").endsWith("Este é meu texto mais legal!"));
		
	}
	
}
