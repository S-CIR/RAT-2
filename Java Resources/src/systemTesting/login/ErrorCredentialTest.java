package src.systemTesting.login;

import static org.junit.Assert.fail;

import org.junit.*;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ErrorCredentialTest {
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver","C:\\Users\\cirie\\Eclipse-Workspace-RAT2.0\\SeleniumFirefox\\geckodriver.exe");
	    driver = (WebDriver) new FirefoxDriver();
	}

	@Test
	  public void testErrorCredential() throws Exception {
	    driver.get("http://localhost:8080/RAT/pages/index.jsp");
	    driver.findElement(By.linkText("Accedi")).click();
	    
	    driver.findElement(By.id("user")).click();
	    driver.findElement(By.id("user")).clear();
	    driver.findElement(By.id("user")).sendKeys("n.cognome@studenti.unisa.it");
	    
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("studente11");
	    
	    driver.findElement(By.id("submit")).click();
	    
	  }
	
	@After
	  public void tearDown() throws Exception {
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		String messageAlert = alert.getText();
		System.out.println(messageAlert);
		
		if(!messageAlert.equals("I dati inseriti non sono corretti, riprova o registrati.")) {
			fail();
		}
	    driver.quit();
	  }

}
