package src.test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import src.controller.ServletRichiesteStudente;
import src.interfaccia.UserInterface;
import src.model.Request;
import src.model.RequestDAO;
import src.model.Student;
import src.model.UtenteDAO;

public class ServletRichiesteStudenteTest extends Mockito {
<<<<<<< HEAD
	private ServletRichiesteStudente servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private UserInterface u;
	private static SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
=======
private ServletRichiesteStudente servlet;
private MockHttpServletRequest request;
private MockHttpServletResponse response;
private UserInterface u;
private static SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
>>>>>>> master

	@Before
	public void setUp() throws Exception {
		request= new MockHttpServletRequest();
		response= new MockHttpServletResponse();
		servlet= new ServletRichiesteStudente();
	}


	
	@Test
	public void doGet() throws ServletException, IOException, ParseException {
		u=new Student("Mario","Roncato",'M',"m.roncato@studenti.unisa.it","password1",0);
		UtenteDAO.doSave(u);
		request.getSession().setAttribute("user", u);
		Date start= formatter.parse("2020-01-02");
		Date end= formatter.parse("2020-02-01");
		Request r= new Request(0, 150, 6, start, end, 6, 2, 3, "m.roncato@studenti.unisa.it", "0123456789");
		RequestDAO.doSave(r);
		servlet.doGet(request, response);
		UtenteDAO.doDelete(u);
		RequestDAO.doDelete(r);
	}
	@Test
	public void doGet2() throws ServletException, IOException, ParseException {
		u=new Student("Mario","Roncato",'M',"m.roncato@studenti.unisa.it","password1",0);
		UtenteDAO.doSave(u);
		request.getSession().setAttribute("user", u);
		Date start= formatter.parse("2020-01-02");
		Date end= formatter.parse("2020-02-01");
		Request r= new Request(0, 150, 6, start, end, 6, 1, 3, "m.roncato@studenti.unisa.it", "0123456789");
		RequestDAO.doSave(r);
		servlet.doGet(request, response);
		UtenteDAO.doDelete(u);
		RequestDAO.doDelete(r);
	}
	@Test
	public void doGet3() throws ServletException, IOException, ParseException {
		u=new Student("Mario","Roncato",'M',"m.roncato@studenti.unisa.it","password1",0);
		UtenteDAO.doSave(u);
		request.getSession().setAttribute("user", u);
		servlet.doGet(request, response);
		UtenteDAO.doDelete(u);
	}
	@Test
	public void doGetFail() throws ServletException, IOException, ParseException {
		servlet.doGet(request, response);
	}
	
	@Test
	public void doPost() throws ServletException, IOException, ParseException {
		u=new Student("Mario","Roncato",'M',"m.roncato@studenti.unisa.it","password1",0);
		UtenteDAO.doSave(u);
		request.getSession().setAttribute("user", u);
		Date start= formatter.parse("2020-01-02");
		Date end= formatter.parse("2020-02-01");
		Request r= new Request(0, 150, 6, start, end, 6, 2, 3, "m.roncato@studenti.unisa.it", "0123456789");
		RequestDAO.doSave(r);
		servlet.doPost(request, response);
		UtenteDAO.doDelete(u);
		RequestDAO.doDelete(r);
	}
}
