package src.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


import src.controller.ServletInoltro;
import src.controller.ServletRichiesteSecretary;
import src.model.Request;
import src.model.RequestDAO;
import src.model.Student;
import src.model.UtenteDAO;

public class ServletInoltroTest extends Mockito {
	
	private ServletInoltro servlet;
	private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    Date start = new Date();
    Date end = new Date();
    public Student st = new Student("Name","Surname",'m',"n.surname1@studenti.unisa.it","pass0000",0);
    public Request re = new Request(30, 60, 6, start, end, 0, 2, 1, st.getEmail(), "0512105429");

    @Before
    public void setUp() throws ParseException {
      servlet = new ServletInoltro();
      request = new MockHttpServletRequest();
      response = new MockHttpServletResponse();
      UtenteDAO.doSave(st);
      RequestDAO.doSave(re);
    }
	@Test
	public void testInoltroSecretary() throws ServletException, IOException {
		request.addParameter("req_id","30");
		request.addParameter("next_state","3");
		request.addParameter("user_id","1");
		servlet.doGet(request,response);
	}
	@Test
	public void testInoltroAdmin() throws ServletException, IOException {
		request.addParameter("req_id","30");
		request.addParameter("next_state","5");
		request.addParameter("user_id","2");
		servlet.doGet(request,response);
	}
	
	@Test (expected=NullPointerException.class)
	public void testInoltroOther() throws ServletException, IOException {
		request.addParameter("req_id","30");
		request.addParameter("next_state","5");
		request.addParameter("user_id","3");
		servlet.doGet(request,response);
	}
	
	@Test
	public void testDoPost() throws ServletException, IOException {
		request.addParameter("req_id","30");
		request.addParameter("next_state","3");
		request.addParameter("user_id","1");
		servlet.doPost(request,response);
	}
	
	@After
	public void deleteSetup() {
		UtenteDAO.doDelete(st);
		RequestDAO.doDelete(re);
	}

}
