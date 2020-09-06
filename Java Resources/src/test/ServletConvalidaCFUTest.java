package src.test;

import static org.junit.jupiter.api.Assertions.*;

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

import src.controller.ServletConvalidaCFU;
import src.model.Request;
import src.model.RequestDAO;
import src.model.Student;
import src.model.UtenteDAO;

public class ServletConvalidaCFUTest extends Mockito {
	
	private ServletConvalidaCFU servlet;
	private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    Date start = new Date();
    Date end = new Date();
    public Student st = new Student("Name","Surname",'m',"n.surname@studenti.unisa.it","pass0000",0);
    public Request re = new Request(20, 60, 6, start, end, 0, 2, 1, st.getEmail(), "0512105420");

    @Before
    public void setUp() throws ParseException {
      servlet = new ServletConvalidaCFU();
      request = new MockHttpServletRequest();
      response = new MockHttpServletResponse();
      UtenteDAO.doSave(st);
      RequestDAO.doSave(re);
    }
    
	@Test
	public void testConvalida() throws ServletException, IOException {
		request.addParameter("req_id", "20");
		servlet.doGet(request, response);
	}
	
	@Test
	public void testDoPost() throws ServletException, IOException {
		request.addParameter("req_id", "20");
		servlet.doPost(request, response);
	}
	
	@After
	public void deleteSetup() {
		UtenteDAO.doDelete(st);
		RequestDAO.doDelete(re);
	}

}
