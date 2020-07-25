package src.systemTesting.form;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import src.controller.Utils;
import src.interfaccia.UserInterface;
import src.model.RequestDAO;
import src.model.Student;
import src.model.UtenteDAO;

public class FormPerformed {
	private WebDriver driver;
	private String s1 = new Utils().generatePwd("Studente1");
	private UserInterface u= new Student("nome","cognome",'M',"n.cognome@studenti.unisa.it",s1,0);
	
	@Before
	public void setUp() throws Exception {
		 System.setProperty("webdriver.gecko.driver",".\\Java Resources\\SeleniumFirefox\\geckodriver.exe");
	    driver = (WebDriver) new FirefoxDriver();
	    UtenteDAO.doSave(u);
	    
	}
	
	@Test
	public void FormPerformedTest() throws InterruptedException {
		driver.get("http://localhost:8080/RAT/pages/index.jsp");
		
		
	    //LOGIN
		
		driver.findElement(By.linkText("Accedi")).click();
		
	    driver.findElement(By.id("user")).click();
	    driver.findElement(By.id("user")).clear();
	    driver.findElement(By.id("user")).sendKeys("n.cognome@studenti.unisa.it");
	    
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("Studente1");
	    
	    driver.findElement(By.id("submit")).click();
	    
	    //INSERIMENTO
	    
	    driver.findElement(By.id("insertReq")).click();
	    
	    //FORM
	    
	    driver.findElement(By.id("year")).click();
	    
	    driver.findElement(By.id("matricola")).click();
	    driver.findElement(By.id("matricola")).clear();
	    driver.findElement(By.id("matricola")).sendKeys("0512105001");
	    
	    driver.findElement(By.id("hours")).click();
	    driver.findElement(By.id("hours")).clear();
	    driver.findElement(By.id("hours")).sendKeys("250");
	    
	    driver.findElement(By.id("azienda")).click();
	    
	    driver.findElement(By.id("startDate")).click();
	   
	    driver.findElement(By.id("endDate")).click();
	    
	    driver.findElement(By.id("cfu")).click();
	    
	    driver.findElement(By.id("submit")).click();
	}
	
	@After
	public  void tearDown () throws InterruptedException {
		Thread.sleep(2000);
		assert(RequestDAO.getCertificateInfo("n.cognome@studenti.unisa.it")!=null);
		driver.quit();
		UtenteDAO.doDelete(u);
	}


}
