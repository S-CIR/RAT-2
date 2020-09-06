package src.systemTesting.registrazione;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ErrorFormatSurnameTest {
	private WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		 System.setProperty("webdriver.gecko.driver",".\\Java Resources\\SeleniumFirefox\\geckodriver.exe");
		 driver = new FirefoxDriver();
	}

	@Test
	public void testErrorSurname() throws Exception{
		driver.get("http://localhost:8080/RAT/pages/index.jsp");
		driver.findElement(By.linkText("Registrati")).click();
		
		driver.findElement(By.id("nome")).click();
	    driver.findElement(By.id("nome")).clear();
	    driver.findElement(By.id("nome")).sendKeys("Nome");
	    
	    driver.findElement(By.id("cognome")).click();
	    driver.findElement(By.id("cognome")).clear();
	    driver.findElement(By.id("cognome")).sendKeys("Cognome1");
	    
	    driver.findElement(By.id("email")).click();
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("n.cognome@studenti.unisa.it");
	    
	    driver.findElement(By.id("sex")).click();
	    
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("Password1");
	    
	    driver.findElement(By.id("verifica")).click();
	    driver.findElement(By.id("verifica")).clear();
	    driver.findElement(By.id("verifica")).sendKeys("Password1");
	   
	    driver.findElement(By.id("submit")).click();
		
	}
	
	@After
	public void tearDown () {
		Alert alert = driver.switchTo().alert();
		String messageAlert = alert.getText();
		assert(messageAlert.equals("Formato non corretto: cognome "));
		driver.quit();
	}

}
