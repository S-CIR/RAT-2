package src.test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


import src.controller.ServletRegistrazioneStudente;
import src.interfaccia.UserInterface;
import src.model.UtenteDAO;

public class ServletRegistrazioneStudenteTest extends Mockito {
	private ServletRegistrazioneStudente servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	

	@Before
	public void setUp() throws Exception {
		servlet=new ServletRegistrazioneStudente();
		request=new MockHttpServletRequest();
		response=new MockHttpServletResponse();
		 
	}


	@Test
	public void testNameFail1() throws ServletException, IOException {
		
		String nome="";
		String cognome="Sarbato";
		char sex='F';
		String email="l.sabato@studenti.unisa.it";
		String password="Password1";
		
		request.setParameter("nome", nome);
		request.setParameter("cognome", cognome);
		request.setParameter("sex", ""+sex);
		request.setParameter("email", email);
		request.setParameter("password",password);
		servlet.doPost(request,response);
		assert(!UtenteDAO.ifExist(email));
	
	}

	@Test
	public void testNameFail2() throws ServletException, IOException {
		
		String nome="Nome1";
		String cognome="Cognome";
		char sex='F';
		String email="n.cognome@studenti.unisa.it";
		String password="Password1";
		
		request.setParameter("nome", nome);
		request.setParameter("cognome", cognome);
		request.setParameter("sex", ""+sex);
		request.setParameter("email", email);
		request.setParameter("password",password);
		servlet.doPost(request,response);
		assert(!UtenteDAO.ifExist(email));
	
	}
	@Test
	public void testNameFail() throws ServletException, IOException {
		
		String nome="Nomeeeeeeeeeeeeeeeeee";
		String cognome="Cognome";
		char sex='F';
		String email="n.cognome@studenti.unisa.it";
		String password="Password1";
		
		request.setParameter("nome", nome);
		request.setParameter("cognome", cognome);
		request.setParameter("sex", ""+sex);
		request.setParameter("email", email);
		request.setParameter("password",password);
	    servlet.doPost(request,response);
	    assert(!UtenteDAO.ifExist(email));
		
	}
	@Test
	public void testSurnameFail() throws ServletException, IOException {
		
		String nome="Nome";
		String cognome="Cognomeeeeeeeeeeeeeee";
		char sex='F';
		String email="n.cognome@studenti.unisa.it";
		String password="Password1";
		
		request.setParameter("nome", nome);
		request.setParameter("cognome", cognome);
		request.setParameter("sex", ""+sex);
		request.setParameter("email", email);
		request.setParameter("password",password);
		servlet.doPost(request,response);
		assert(!UtenteDAO.ifExist(email));
		
	}
	@Test
	public void testSurnameFail1() throws ServletException, IOException {
		
		String nome="Nome";
		String cognome="";
		char sex='F';
		String email="n.cognome@studenti.unisa.it";
		String password="Password1";
		
		request.setParameter("nome", nome);
		request.setParameter("cognome", cognome);
		request.setParameter("sex", ""+sex);
		request.setParameter("email", email);
		request.setParameter("password",password);
	    servlet.doPost(request,response);
	    assert(!UtenteDAO.ifExist(email));
		
	}
	@Test
	public void testSurnameFail2() throws ServletException, IOException {
		
		String nome="Nome";
		String cognome="Cognome1";
		char sex='F';
		String email="n.cognome@studenti.unisa.it";
		String password="Password1";
		
		request.setParameter("nome", nome);
		request.setParameter("cognome", cognome);
		request.setParameter("sex", ""+sex);
		request.setParameter("email", email);
		request.setParameter("password",password);
		servlet.doPost(request,response);
		assert(!UtenteDAO.ifExist(email));
	
	}
	@Test
	public void testSexFail() throws ServletException, IOException {
		
		String nome="Nome";
		String cognome="Cognome";
		char sex='X';
		String email="n.cognome@studenti.unisa.it";
		String password="Password1";
		
		request.setParameter("nome", nome);
		request.setParameter("cognome", cognome);
		request.setParameter("sex", ""+sex);
		request.setParameter("email", email);
		request.setParameter("password",password);
		servlet.doPost(request,response);
		assert(!UtenteDAO.ifExist(email));
		
	}
	@Test
	public void testEmailFail() throws ServletException, IOException {
		
		String nome="Nome";
		String cognome="Cognome";
		char sex='F';
		String email="n.cognome@studenti.it";
		String password="Password1";
		
		request.setParameter("nome", nome);
		request.setParameter("cognome", cognome);
		request.setParameter("sex", ""+sex);
		request.setParameter("email", email);
		request.setParameter("password",password);
		servlet.doPost(request,response);
		assert(!UtenteDAO.ifExist(email));

	}
	@Test
	public void testEmailFail2() throws ServletException, IOException {
	
		String nome="Nome";
		String cognome="Cognome";
		char sex='F';
		String email="";
		String password="Password1";
		
		request.setParameter("nome", nome);
		request.setParameter("cognome", cognome);
		request.setParameter("sex", ""+sex);
		request.setParameter("email", email);
		request.setParameter("password",password);
		servlet.doPost(request,response);
		assert(!UtenteDAO.ifExist(email));
		
	}

	
	@Test
	public void testEmailFail3() throws ServletException, IOException {
	
		String nome="Nome";
		String cognome="Cognome";
		char sex='F';
		String email="a@studenti.unisa.it";
		String password="Password1";
		
		request.setParameter("nome", nome);
		request.setParameter("cognome", cognome);
		request.setParameter("sex", ""+sex);
		request.setParameter("email", email);
		request.setParameter("password",password);
	    servlet.doPost(request,response);
	    assert(!UtenteDAO.ifExist(email));
	
	}
	@Test
	public void testEmailFail4() throws ServletException, IOException {
		
		String nome="Nome";
		String cognome="Cognome";
		char sex='F';
		String email="aaa@studenti.unisa.it";
		String password="Password1";
		
		request.setParameter("nome", nome);
		request.setParameter("cognome", cognome);
		request.setParameter("sex", ""+sex);
		request.setParameter("email", email);
		request.setParameter("password",password);
		servlet.doPost(request,response);
		
		assert(!UtenteDAO.ifExist(email));

	}
	
	@Test
	public void testEmailFail5() throws ServletException, IOException {
		
		String nome="Nome";
		String cognome="Cognome";
		char sex='F';
		String email="a.moro@studenti.unisa.it";
		String password="Password1";
		
		request.setParameter("nome", nome);
		request.setParameter("cognome", cognome);
		request.setParameter("sex", ""+sex);
		request.setParameter("email", email);
		request.setParameter("password",password);
		servlet.doPost(request,response);
		assert(UtenteDAO.ifExist(email));

	}

	@Test
	public void testPassFail() throws ServletException, IOException{
		
		String nome="Nome";
		String cognome="Cognome";
		char sex='F';
		String email="n.cognome@studenti.unisa.it";
		String password="pass";
		
		request.setParameter("nome", nome);
		request.setParameter("cognome", cognome);
		request.setParameter("sex", ""+sex);
		request.setParameter("email", email);
		request.setParameter("password",password);
		servlet.doPost(request,response);
		assert(!UtenteDAO.ifExist(email));
		
	}

	@Test
	public void testPassFail2() throws ServletException, IOException{
		
		String nome="Nome";
		String cognome="Cognome";
		char sex='F';
		String email="n.cognome@studenti.unisa.it";
		String password="password";
		
		request.setParameter("nome", nome);
		request.setParameter("cognome", cognome);
		request.setParameter("sex", ""+sex);
		request.setParameter("email", email);
		request.setParameter("password",password);
		
			servlet.doPost(request,response);
		
	}
	
	@Test
	public void testPassFail3() throws ServletException, IOException{
		String nome="Nome";
		String cognome="Cognome";
		char sex='F';
		String email="n.cognome@studenti.unisa.it";
		String password="Pass1";
		
		request.setParameter("nome", nome);
		request.setParameter("cognome", cognome);
		request.setParameter("sex", ""+sex);
		request.setParameter("email", email);
		request.setParameter("password",password);
		
			servlet.doPost(request,response);
		
	}
	@Test
	public void testAllCorrect() throws ServletException, IOException {
		String nome="Nome";
		String cognome="Cognome";
		char sex='F';
		String email="n.cognome@studenti.unisa.it";
		String password="Password1";
		
		request.setParameter("nome", nome);
		request.setParameter("cognome", cognome);
		request.setParameter("sex", ""+sex);
		request.setParameter("email", email);
		request.setParameter("password",password);
		servlet.doGet(request, response);
		UserInterface u= UtenteDAO.retrieveByEmail("n.cognome@studenti.unisa.it");
		UtenteDAO.doDelete(u);
	}
	@Test
	public void testAllCorrect2() throws ServletException, IOException {
		String nome="Nome";
		String cognome="Cognome";
		char sex='M';
		String email="n.cognome@studenti.unisa.it";
		String password="Password1";
		
		request.setParameter("nome", nome);
		request.setParameter("cognome", cognome);
		request.setParameter("sex", ""+sex);
		request.setParameter("email", email);
		request.setParameter("password",password);
		servlet.doPost(request, response);
		UserInterface u= UtenteDAO.retrieveByEmail("n.cognome@studenti.unisa.it");
		UtenteDAO.doDelete(u);
	}

	
	
}
