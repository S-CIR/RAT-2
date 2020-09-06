package src.systemTesting.login;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import src.interfaccia.UserInterface;
import src.model.Student;
import src.model.UtenteDAO;




public class ErrorFormatPasswordTest {
	 private UserInterface u= new Student("Nome", "Cognome", 'M', "n.cognome@studenti.unisa.it", "password1", 0);
	  private WebDriver driver;

	  /**
	   * Before.
	   */

	  @Before
	  public void setUp() throws Exception {
		u.insert();
	    System.setProperty("webdriver.gecko.driver",".\\Java Resources\\SeleniumFirefox\\geckodriver.exe");
	    driver = new FirefoxDriver();
	  }
	  
	  
	  @Test
	  public void testErrorFormatPassword() throws Exception {
	    driver.get("http://localhost:8080/RAT/pages/index.jsp");
	    driver.findElement(By.linkText("Accedi")).click();
	    driver.findElement(By.id("user")).click();
	    driver.findElement(By.id("user")).clear();
	    driver.findElement(By.id("user")).sendKeys("n.cognome@studenti.unisa.it");
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("password%");
	    driver.findElement(By.id("submit")).click();
	  }
	  
	  
	 @After
	  public void tearDown() throws Exception {
		 Thread.sleep(2000);
		Alert alert= driver.switchTo().alert();
		String message =alert.getText();
		UtenteDAO.doDelete(u);
		 driver.quit();
		if(!message.equals("I dati inseriti non sono corretti, riprova o registrati.")) {
			fail(message);
		}
	  }
}
