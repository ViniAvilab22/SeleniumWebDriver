import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

	public class FramesEPopUp {	
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
	
	//Testes
	@Test
	public void InteragirFrame() {
	dsl.entrarFrame("frame1");
	dsl.clicarBotao("frameButton");
	String mensagem = dsl.alertaObterTextoEAceitar();
	Assert.assertEquals("Frame OK!", mensagem);
	
	dsl.sairframe();
	dsl.escreve("elementosForm:nome", mensagem);
	}
	

	//Este teste só deu certo quando executado no Chrome.
	@Test
	public void InteragirPopup() {
	System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	//driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	driver.get("file:///C:/Users/vinic/eclipse-workspace/SELENIUM_BasicoAoGrid/CursoSelenium/src/main/resources/componentes.html");
	
	
	dsl.clicarBotao("buttonPopUpEasy");
	driver.switchTo().window("Popup");
	dsl.escreve("textarea", "Deu certo!");
	driver.close();
	driver.switchTo().window("");
	dsl.escreve("textarea", "Deu certo de novo!");	
	}
	
	@Test
	public void InteragirPopUpSemTitulo() {
	dsl.clicarBotao("buttonPopUpHard");
	System.out.println("ID da janela principal" + driver.getWindowHandle());
	System.out.println("ID de todas janelas abertas" + driver.getWindowHandles());

	dsl.trocarJanela((String) driver.getWindowHandles().toArray()[1]);
	dsl.escreve(By.tagName("textarea"), "Deu certo?");
	dsl.trocarJanela((String) driver.getWindowHandles().toArray()[0]);
	
	dsl.escreve(By.tagName("textarea"), "Eu consegui de novo!");
	}

}
