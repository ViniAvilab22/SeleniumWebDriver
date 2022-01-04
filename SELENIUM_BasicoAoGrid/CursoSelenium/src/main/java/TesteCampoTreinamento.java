import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {
	
	//Variáveis
	private WebDriver driver;
	private DSL dsl;
	
	//Métodos
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
	public void testeTextField() {
		dsl.escreve("elementosForm:nome", "Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void testeTextFieldDuplo() {
		dsl.escreve("elementosForm:nome", "Vinicius");
		Assert.assertEquals("Vinicius", dsl.obterValorCampo("elementosForm:nome"));
		dsl.escreve("elementosForm:nome", "Avila");
		Assert.assertEquals("Avila", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void deveInteragirComTextArea() {

		dsl.escreve("elementosForm:sugestoes", "Teste no textarea\n\segundalinha\nultima linha");
		Assert.assertEquals("Teste no textarea\n\segundalinha\nultima linha", dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	
	@Test
	public void deveInteragirComRadioButton() {
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
	 }
	
	@Test
	public void deveInteragirComCheckbox() {
		dsl.clicarRadio("elementosForm:comidaFavorita:1");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:1"));
	 }
	
	@Test
	public void deveInterarirComCombo() {
	dsl.selecionarCombo("elementosForm:escolaridade", "1o grau incompleto");
	Assert.assertEquals("1o grau incompleto", dsl.obterValorCampo("elementosForm:escolaridade"));
	
	}
	
	
	@Test
	public void deveVerificarValoresCombo() {
	WebElement elementCombo = driver.findElement(By.id("elementosForm:escolaridade"));
	Select combo = new Select(elementCombo);
	List<WebElement> options = combo.getOptions();
	Assert.assertEquals(8, options.size());
	}
	
	

	@Test
	public void deveVerificarValoresComboMultiplo() {
	dsl.selecionarCombo("elementosForm:esportes", "Natacao");
	dsl.selecionarCombo("elementosForm:esportes", "Corrida");
	dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte");
	WebElement elementCombo = driver.findElement(By.id("elementosForm:esportes"));
	Select combo = new Select(elementCombo);	
	List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
	Assert.assertEquals(3, allSelectedOptions.size());
	}
	
	
	@Test
	public void deveInteragirComBotoes() {
	dsl.clicarBotao("buttonSimple");
	WebElement botao = driver.findElement(By.id("buttonSimple"));
	Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
	}
	
	

	@Test
	public void deveInteragirComLinks() {
	dsl.clicarLink("Voltar");
	Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	
	}
	
	
	@Test
	public void deveBuscarTextoNaPagina() {
	// Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
	// driver.quit();
	Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
	Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	
	}
	
}
