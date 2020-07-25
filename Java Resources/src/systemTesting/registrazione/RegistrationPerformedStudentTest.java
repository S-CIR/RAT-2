package src.systemTesting.registrazione;

import org.junit.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import src.interfaccia.UserInterface;
import src.model.Student;
import src.model.UtenteDAO;

public class RegistrationPerformedStudentTest {
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver","C:\\Users\\cirie\\Eclipse-Workspace-RAT2.0\\SeleniumFirefox\\geckodriver.exe");
	    driver = (WebDriver) new FirefoxDriver();
	}

	@Test
	public void test() {
		driver.get("http://localhost:8080/RAT/pages/index.jsp");
	    driver.findElement(By.linkText("Registrati")).click();
	    
	    driver.findElement(By.id("nome")).click();
	    driver.findElement(By.id("nome")).clear();
	    driver.findElement(By.id("nome")).sendKeys("nome");
	    
	    driver.findElement(By.id("cognome")).click();
	    driver.findElement(By.id("cognome")).clear();
	    driver.findElement(By.id("cognome")).sendKeys("cognome");
	    
	    driver.findElement(By.id("email")).click();
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("n.cognome@studenti.unisa.it");
	    
	    driver.findElement(By.id("sex")).click();
	    
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("Studente1");
	    
	    driver.findElement(By.id("verifica")).click();
	    driver.findElement(By.id("verifica")).clear();
	    driver.findElement(By.id("verifica")).sendKeys("Studente1");
	    
	    driver.findElement(By.id("submit")).click();
	}
	
	@After
	public void tearDown() throws InterruptedException {
		UserInterface u = UtenteDAO.retrieveByEmail("n.cognome@studenti.unisa.it");
		assert(u!=null);
		if(u!=null) {
			UtenteDAO.doDelete(new Student("nome","cognome",'M',"n.cognome@studenti.unisa.it","Studente1",0));
		}	
		Thread.sleep(2000);
		driver.quit();
	}

}