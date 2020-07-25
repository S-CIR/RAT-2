package src.test;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import src.controller.ServletRichiesteSecretary;
import src.model.Request;
import src.model.RequestDAO;
import src.model.Student;
import src.model.UtenteDAO;

public class ServletRichiesteSecretaryTest extends Mockito {
	
	private ServletRichiesteSecretary servlet;
	private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    
    Date start = new Date();
    Date end = new Date();
    public Student st = new Student("Name","Surname",'m',"n.surname3@studenti.unisa.it","pass0000",0);
    public Request re = new Request(22, 60, 6, start, end, 0, 2, 1, st.getEmail(), "0512105422");
	
    @Before
    public void setUp() throws ParseException {
      servlet = new ServletRichiesteSecretary();
      request = new MockHttpServletRequest();
      response = new MockHttpServletResponse();  
      UtenteDAO.doSave(st);
      RequestDAO.doSave(re);
    }
	
	@Test
	public void testViewRequest() throws ServletException, IOException {
		request.addParameter("stateId", "2");
		servlet.doPost(request, response);
	  }
	
	@Test
	public void testdoGet() throws ServletException, IOException {
		request.addParameter("stateId", "10"); //il result set è null in questo caso (si testa il secondo branch dell'if)
		servlet.doGet(request, response);
	  }
	
	@After
	public void deleteSetup() {
		UtenteDAO.doDelete(st);
		RequestDAO.doDelete(re);
	}
	

}
