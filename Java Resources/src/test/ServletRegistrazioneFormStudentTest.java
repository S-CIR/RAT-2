package src.test;

import static org.junit.Assert.*;

import java.io.IOException;
<<<<<<< HEAD
import java.sql.ResultSet;
=======
>>>>>>> master
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;

import org.junit.*;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import src.controller.ServletRegistrazioneFormStudent;
import src.interfaccia.UserInterface;
import src.model.Request;
import src.model.RequestDAO;
import src.model.Student;
import src.model.UtenteDAO;

public class ServletRegistrazioneFormStudentTest extends Mockito{
	
	private ServletRegistrazioneFormStudent servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
<<<<<<< HEAD
	
	
	
=======

>>>>>>> master
	private UserInterface u= new Student("nome","cognome",'M',"n.cognome@studenti.unisa.it","studente1",0);
	private Request r;

	@Before
	public void setUp() throws ParseException {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		servlet = new ServletRegistrazioneFormStudent();
		
		UtenteDAO.doSave(u);
	}
	
	@After
	public void resetDB() {
		UtenteDAO.doDelete(u);
	}
	
	@Test
	public void testDoPost() throws ServletException, IOException, ParseException {
		Date start = formatter.parse("2020-01-01"), end = formatter.parse("2020-06-01");
		r = new Request(100, 250, 6, start, end, 0, 1, 1, "n.cognome@studenti.unisa.it", "0123456789");
		RequestDAO.doSave(r);
		
		request.setParameter("year", "2020");
		request.setParameter("matricola", "0123456789");
		request.setParameter("hours", "250");
		request.setParameter("azienda", "1");
		request.setParameter("endDate", "2020-06-01");
		request.setParameter("startDate", "2020-01-01");
		request.setParameter("cfu", "6");
		
		request.getSession().setAttribute("user", u);
		
		servlet.doPost(request, response);
		
		assertEquals(r.getIdRequest(), RequestDAO.getLastUserRequestPartiallyCompleted(u).getIdRequest());
	}
	
	@Test
	public void testDoGet() throws ServletException, IOException {
		
		request.setParameter("year", "2020");
		request.setParameter("matricola", "0123456789");
		request.setParameter("hours", "250");
		request.setParameter("azienda", "1");
		request.setParameter("endDate", "2020-06-01");
		request.setParameter("startDate", "2020-01-01");
		request.setParameter("cfu", "6");
		
		request.getSession().setAttribute("user", u);
		
		servlet.doGet(request, response);
	}
	
	@Test
	public void testDoGet1() throws ServletException, IOException, ParseException {
		Date start = formatter.parse("2020-01-01"), end = formatter.parse("2020-06-01");
		r = new Request(100, 250, 6, start, end, 0, 1, 1, "n.cognome@studenti.unisa.it", "0123456789");
		RequestDAO.doSave(r);
		
		request.setParameter("year", "2020");
		request.setParameter("matricola", "0123456789");
		request.setParameter("hours", "250");
		request.setParameter("azienda", "1");
		request.setParameter("endDate", "2020-06-01");
		request.setParameter("startDate", "2020-01-01");
		request.setParameter("cfu", "6");
		
		request.getSession().setAttribute("user", u);
		
		servlet.doGet(request, response);
		
		assertEquals(r.getIdRequest(), RequestDAO.getLastUserRequestPartiallyCompleted(u).getIdRequest());
	}
	
<<<<<<< HEAD
	@Test
	public void testDoGet3() throws ServletException, IOException {
		
		request.setParameter("year", "2020");
		request.setParameter("matricola", "0123456789");
		request.setParameter("hours", "250");
		request.setParameter("azienda", "1");
		request.setParameter("endDate", "2020-06-01");
		request.setParameter("startDate", "2020-01-01");
		request.setParameter("cfu", "12");
		
		request.getSession().setAttribute("user", u);
		
		servlet.doGet(request, response);
	}
	@Test
	public void testAnnoImmatricolazioneErrato() throws ServletException, IOException {
=======
	@Test 
	public void testAnnoImmatricolazioneErrato() throws ServletException, IOException {
		
>>>>>>> master
		request.setParameter("year", "");
		request.setParameter("matricola", "0123456789");
		request.setParameter("hours", "250");
		request.setParameter("azienda", "1");
		request.setParameter("endDate", "2020-06-01");
		request.setParameter("startDate", "2020-01-01");
		request.setParameter("cfu", "6");
<<<<<<< HEAD
		servlet.doGet(request,response);
		ResultSet res=RequestDAO.findByUserMail("n.cognome@studenti.unisa.it");
		assert(res==null);
		
	}
	
	@Test
	public void testMatricolaErrata() throws ServletException, IOException {
		request.setParameter("year", "2020");
		request.setParameter("matricola", "012345678");
=======
		
		servlet.doGet(request,response);
		
		assert(RequestDAO.findByUserMail("n.cognome@studenti.unisa.it")==null);
	}
	
	@Test 
	public void testMatricolaErrata() throws ServletException, IOException {
		
		request.setParameter("year", "2020");
		request.setParameter("matricola", "");
>>>>>>> master
		request.setParameter("hours", "250");
		request.setParameter("azienda", "1");
		request.setParameter("endDate", "2020-06-01");
		request.setParameter("startDate", "2020-01-01");
		request.setParameter("cfu", "6");
<<<<<<< HEAD
		servlet.doGet(request,response);
		ResultSet res=RequestDAO.findByUserMail("n.cognome@studenti.unisa.it");
		assert(res==null);
	}
	
	@Test
	public void testOreErrate() throws ServletException, IOException {
=======
				
		servlet.doGet(request,response);
		
		assert(RequestDAO.findByUserMail("n.cognome@studenti.unisa.it")==null);
	}
	
	@Test 
	public void testOreErrate() throws ServletException, IOException {
		
>>>>>>> master
		request.setParameter("year", "2020");
		request.setParameter("matricola", "0123456789");
		request.setParameter("hours", "50");
		request.setParameter("azienda", "1");
		request.setParameter("endDate", "2020-06-01");
		request.setParameter("startDate", "2020-01-01");
		request.setParameter("cfu", "6");
<<<<<<< HEAD
		servlet.doGet(request,response);
		ResultSet res=RequestDAO.findByUserMail("n.cognome@studenti.unisa.it");
		assert(res==null);
	}
	
	@Test
	public void testAziendaErrata() throws ServletException, IOException {
=======

		servlet.doGet(request,response);

		assert(RequestDAO.findByUserMail("n.cognome@studenti.unisa.it")==null);
	}
	
	@Test 
	public void testAziendaErrata() throws ServletException, IOException  {
		
>>>>>>> master
		request.setParameter("year", "2020");
		request.setParameter("matricola", "0123456789");
		request.setParameter("hours", "250");
		request.setParameter("azienda", "0");
		request.setParameter("endDate", "2020-06-01");
		request.setParameter("startDate", "2020-01-01");
		request.setParameter("cfu", "6");
<<<<<<< HEAD
		servlet.doGet(request,response);
		ResultSet res=RequestDAO.findByUserMail("n.cognome@studenti.unisa.it");
		assert(res==null);
	}
	
	@Test
	public void testDataFineErrata() throws ServletException, IOException {
=======
		
		servlet.doGet(request,response);
		
		assert(RequestDAO.findByUserMail("n.cognome@studenti.unisa.it")==null);
	}
	
	@Test 
	public void testDataFineErrata() throws ServletException, IOException {
		
>>>>>>> master
		request.setParameter("year", "2020");
		request.setParameter("matricola", "0123456789");
		request.setParameter("hours", "250");
		request.setParameter("azienda", "1");
		request.setParameter("endDate", "");
		request.setParameter("startDate", "2020-01-01");
		request.setParameter("cfu", "6");
<<<<<<< HEAD
		servlet.doGet(request,response);
		ResultSet res=RequestDAO.findByUserMail("n.cognome@studenti.unisa.it");
		assert(res==null);
	}
	
	@Test
	public void testDataInizioErrata() throws ServletException, IOException {
=======
		
		servlet.doGet(request,response);
		
		assert(RequestDAO.findByUserMail("n.cognome@studenti.unisa.it")==null);
	}
	
	@Test 
	public void testDataInizioErrata() throws ServletException, IOException {
		
>>>>>>> master
		request.setParameter("year", "2020");
		request.setParameter("matricola", "0123456789");
		request.setParameter("hours", "250");
		request.setParameter("azienda", "1");
		request.setParameter("endDate", "2020-06-01");
		request.setParameter("startDate", "");
		request.setParameter("cfu", "6");
<<<<<<< HEAD
		servlet.doGet(request,response);
		ResultSet res=RequestDAO.findByUserMail("n.cognome@studenti.unisa.it");
		assert(res==null);
	}
	
	@Test
	public void testCFUErrati() throws ServletException, IOException {
=======
		
		servlet.doGet(request,response);
		
		assert(RequestDAO.findByUserMail("n.cognome@studenti.unisa.it")==null);
	}
	
	@Test 
	public void testCFUErrati() throws ServletException, IOException {
		
>>>>>>> master
		request.setParameter("year", "2020");
		request.setParameter("matricola", "0123456789");
		request.setParameter("hours", "250");
		request.setParameter("azienda", "1");
		request.setParameter("endDate", "2020-06-01");
		request.setParameter("startDate", "2020-01-01");
		request.setParameter("cfu", "7");
<<<<<<< HEAD
		servlet.doGet(request,response);
		ResultSet res=RequestDAO.findByUserMail("n.cognome@studenti.unisa.it");
		assert(res==null);
=======
		
		servlet.doGet(request,response);
		
		assert(RequestDAO.findByUserMail("n.cognome@studenti.unisa.it")==null);
>>>>>>> master
	}

}
