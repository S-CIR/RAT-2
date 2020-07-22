package src.systemTesting.registrazione;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import src.interfaccia.UserInterface;
import src.model.UtenteDAO;

public class RegistrationPerformedTest {

	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Before
	public void setUp() throws Exception {
		 System.setProperty("webdriver.gecko.driver","C:\\Users\\Carmine\\Documents\\RAT-Richiesta Attestazione Tirocinio\\geckodriver.exe");
		 driver = new FirefoxDriver();
	}

	@Test
	public void testPerformed() throws Exception{
		driver.get("http://localhost:8080/RAT/pages/index.jsp");
		driver.findElement(By.linkText("Registrati")).click();
		
		driver.findElement(By.id("nome")).click();
	    driver.findElement(By.id("nome")).clear();
	    driver.findElement(By.id("nome")).sendKeys("Nome");
	    
	    driver.findElement(By.id("cognome")).click();
	    driver.findElement(By.id("cognome")).clear();
	    driver.findElement(By.id("cognome")).sendKeys("Cognome");
	    
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
		String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
		driver.quit();
	    UserInterface u=UtenteDAO.retrieveByEmail("n.cognome@studenti.unisa.it");
	    UtenteDAO.doDelete(u);
	}
}
