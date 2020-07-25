package src.systemTesting.login;

import static org.junit.Assert.*;

import org.junit.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import src.controller.Utils;
import src.interfaccia.UserInterface;
import src.model.Student;
import src.model.UtenteDAO;

public class LoginUserError {
	private  String s1= new Utils().generatePwd("password1");
	private UserInterface u= new Student("nome", "cognome", 'M', "n.cognome@studenti.unisa.it", s1, 0);
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();


	  @Before
	  public void setUp() throws Exception { 
		UtenteDAO.doSave(u);
	    System.setProperty("webdriver.gecko.driver",".\\Java Resources\\SeleniumFirefox\\geckodriver.exe");
	    driver = new FirefoxDriver();
	    Thread.sleep(2000);
	  }

	  @Test
	  public void LoginUserErrorTest() throws Exception {
	    driver.get("http://localhost:8080/RAT/pages/index.jsp");
	    
	    driver.findElement(By.linkText("Accedi")).click();
	    
	    driver.findElement(By.id("user")).click();
	    driver.findElement(By.id("user")).clear();
	    driver.findElement(By.id("user")).sendKeys("n.cognome@studenti.it");
	    
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("password1");
	    
	    driver.findElement(By.id("submit")).click();
	  }

	  
	  @After
	  public void tearDown() throws Exception {
		 Thread.sleep(2000);
		  String verificationErrorString = verificationErrors.toString();
		    if (!"".equals(verificationErrorString)) {
		      fail(verificationErrorString);
		    }
	   driver.quit();
	   UtenteDAO.doDelete(u);
	   
	  }
}

