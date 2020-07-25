package src.test;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import src.controller.CheckSession;
import src.controller.ServletLogin;
import src.controller.Utils;
import src.interfaccia.UserInterface;
import src.model.Admin;
import src.model.Secretary;
import src.model.Student;
import src.model.UtenteDAO;

public class ServletLoginTest  extends Mockito{
	private ServletLogin servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private UserInterface s;
	private Utils u;
	private   HttpSession session;
	private  CheckSession check;

	

	@Before
	public void setUp() {
		servlet=new ServletLogin();
		request=new MockHttpServletRequest();
		response=new MockHttpServletResponse();
		 session = request.getSession();
		 u=new Utils();
		 check = new CheckSession("","",session);
	}
	
	
	@Test
	public void test() throws ServletException, IOException {
		String s1= u.generatePwd("password1");
		 s=new Student("Nome","Cognome",'M',"n.cognome@studenti.unisa.it",s1,3);
		 UtenteDAO.doSave(s);
		String email="n.cognome@studenti.unisa.it";
		String password ="password1";
		request.setParameter("email", email);
		request.setParameter("password", password);
		 servlet.doGet(request, response);
		 UtenteDAO.doDelete(s);
	}

	@Test
	public void testAdmin() throws ServletException, IOException {
		 String s1= u.generatePwd("password1");
		 s=new Admin("Nome","Cognome",'M',"n.cognome@unisa.it", s1, 2);
		 UtenteDAO.doSave(s);
	     String email1="n.cognome@unisa.it";
		 String	password1="password1";
		 request.setParameter("email", email1);
		 request.setParameter("password", password1);
	     assertEquals("/index.jsp", check.getUrlRedirect());
	     servlet.doGet(request, response);
	     UtenteDAO.doDelete(s);
	}
	
	@Test
	public void testStudente() throws ServletException, IOException {
		String s1= u.generatePwd("password1");
		 s=new Student("Nome","Cognome",'M',"n.cognome@studenti.unisa.it",s1,0);
		 UtenteDAO.doSave(s);
		String email="n.cognome@studenti.unisa.it";
		String password ="password1";
		request.setParameter("email", email);
		request.setParameter("password", password);
		 assertEquals("/index.jsp", check.getUrlRedirect());
	     servlet.doGet(request, response);
	     UtenteDAO.doDelete(s);
	}
	
	@Test
	public void testSegreteria() throws ServletException, IOException {
		 String s1= u.generatePwd("password1");
		 s=new Secretary("Nome","Cognome",'M',"n.cognome@unisa.it",s1,1);
		 UtenteDAO.doSave(s);
	     String email1="n.cognome@unisa.it";
		 String	password1="password1";
		 request.setParameter("email", email1);
		 request.setParameter("password", password1);
	     assertEquals("/index.jsp", check.getUrlRedirect());
	     servlet.doPost(request, response);
	     UtenteDAO.doDelete(s);
	}
	
	@Test
	public void testFail() throws ServletException, Exception {
		String email="n.cinnamo@studenti.unisa.it";
		String password ="password1";
		request.setParameter("email", email);
		request.setParameter("password", password);
		 assertEquals("/index.jsp", check.getUrlRedirect());
		 servlet.doGet(request, response);
		
	}

}
