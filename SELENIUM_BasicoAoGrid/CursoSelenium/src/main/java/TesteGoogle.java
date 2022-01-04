import org.junit.Assert;
import org.junit.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TesteGoogle {
	
	@Test
	public void test() {
		
		//Abrir Chrome
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();		
		
		//Abrir Firefox
		//System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
		//WebDriver driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		driver.get("http://www.google.com.br");
		Assert.assertEquals("Google", driver.getTitle());
		driver.quit();
		
	} 

}
