package src.test;

import static org.junit.Assert.*;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import src.controller.CheckSession;
import src.interfaccia.UserInterface;
import src.model.Admin;
import src.model.Secretary;
import src.model.Student;
import src.model.UtenteDAO;


public class CheckSessionTest {
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private   HttpSession session;
	private  CheckSession check;
	private UserInterface u;
	
	@Before
	public void setUp() throws Exception {
		request= new MockHttpServletRequest();
		response= new MockHttpServletResponse();
		session= request.getSession();
	}

	@Test
	public void testCheckSession() {
		check=new CheckSession("aa", "bb", session);
		assertNotNull(check);
	}

	@Test
	public void testGetSession() {
		check=new CheckSession("aa", "bb", session);
		assertEquals(session, check.getSession());
	}

	@Test
	public void testSetSession() {
		check=new CheckSession("aa", "bb", session);
		HttpSession s1=request.getSession();
		check.setSession(s1);
		assertEquals(s1, check.getSession());
	}

	@Test
	public void testGetUrlRedirect() {
		check=new CheckSession("aa", "bb", session);
		assertEquals("/index.jsp", check.getUrlRedirect());
	}

	@Test
	public void testSetUrlRedirect() {
		CheckSession check = new CheckSession("","",session);
	    check.setUrlRedirect("/logout.jsp");
	    assertEquals("/logout.jsp", check.getUrlRedirect());
	}

	@Test
	public void testGetPageName() {
		 CheckSession check = new CheckSession("","page1",session);
		 assertEquals("page1", check.getPageName());
	}

	@Test
	public void testSetPageName() {
		check = new CheckSession("","page1",session);
	    check.setPageName("page2");
	    assertEquals("page2", check.getPageName());
	}

	@Test
	public void testGetPageFolder() {
		check = new CheckSession("folder1","",session);
	    assertEquals("folder1", check.getPageFolder());
	}

	@Test
	public void testSetPageFolder() {
		check = new CheckSession("folder1","",session);
	    check.setPageFolder("folder2");
	    assertEquals("folder2", check.getPageFolder());
	}
	
	@Test
	 public void testFail() {
	    request = new MockHttpServletRequest();
	    u = new Student( "name", "surname", 'M',"stu.session@studenti.unisa.it", "password", 0);
	    request.getSession().setAttribute("user", u);
	    check = new CheckSession("_areaStudent","",session);
	    assertEquals(false, check.isAllowed());
	  }

	@Test
	public void testIsAllowed() {
	    u = new Student( "Maria", "Sierra", 'F',"stu.session@studenti.unisa.it", "password", 0);
	    UtenteDAO.doSave(u);
	    request.getSession().setAttribute("user", u);
	    check = new CheckSession("_areaStudent","",session);
	    assertEquals("_areaStudent",check.getPageFolder());
	    assertEquals(true, check.isAllowed());
	    UtenteDAO.doDelete(u);
	}
	@Test
	public void testIsAllowed1() {
	    u = new Secretary( "Maria", "Sierra", 'F',"segret@unisa.it", "password", 1);
	    UtenteDAO.doSave(u);
	    request.getSession().setAttribute("user", u);
	    check = new CheckSession("_areaSecretary","",session);
	    assertEquals(true, check.isAllowed());
	    UtenteDAO.doDelete(u);
	}
	@Test
	public void testIsAllowed2() {
	    u = new Admin( "Maria", "Sierra", 'F',"segret@unisa.it", "password", 2);
	    UtenteDAO.doSave(u);
	    request.getSession().setAttribute("user", u);
	    check = new CheckSession("_areaAdmin","",session);
	    assertEquals(true, check.isAllowed());
	    UtenteDAO.doDelete(u);
	}
	@Test
	public void testIsAllowed3() {
	    u = new Admin( "Maria", "Sierra", 'F',"segret@unisa.it", "password", 2);
	    UtenteDAO.doSave(u);
	    request.getSession().setAttribute("user", u);
	    check = new CheckSession("_areaSecretary","",session);
	    assertEquals(false, check.isAllowed());
	    UtenteDAO.doDelete(u);
	}
	@Test
	public void testIsAllowed4() {
	    u = new Admin( "Maria", "Sierra", 'F',"segret@unisa.it", "password", 1);
	    UtenteDAO.doSave(u);
	    request.getSession().setAttribute("user", u);
	    check = new CheckSession("_areaAdmin","",session);
	    assertEquals(false, check.isAllowed());
	    UtenteDAO.doDelete(u);
	}
	@Test
	public void testIsAllowed5() {
	    u = new Admin( "Maria", "Sierra", 'F',"segret@studenti.unisa.it", "password", 0);
	    UtenteDAO.doSave(u);
	    request.getSession().setAttribute("user", u);
	    check = new CheckSession("_areaAdmin","",session);
	    assertEquals(false, check.isAllowed());
	    UtenteDAO.doDelete(u);
	}
}
