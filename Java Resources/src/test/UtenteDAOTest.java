package src.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import src.interfaccia.UserInterface;
import src.model.Admin;
import src.model.Secretary;
import src.model.Student;
import src.model.UtenteDAO;

class UtenteDAOTest {
	
	public Student st = new Student("Nome","Cognome",'m',"n.cognome1@studenti.unisa.it","pass0000",0);
	public Secretary se = new Secretary("Nome", "Cognome", 'm', "n.cognomeS@unisa.it","pass0000", 1);
	public Admin ad = new Admin("Nome", "Cognome", 'm', "n.cognomeA@unisa.it","pass0000", 2);

	@Test
	public void testRetrieveAll() {
		UtenteDAO.doSaveOrUpdate(st);
		UtenteDAO.doSaveOrUpdate(st);
		ArrayList<UserInterface> u = UtenteDAO.retrieveAll();
		assertNotNull(u);
		UtenteDAO.doDelete(st);
	}
	
	
	@Test
	public void testRetrieveStudent() {
		UtenteDAO.doSaveOrUpdate(st);
		UserInterface s = UtenteDAO.retrieveByEmail(st.getEmail());
		assertNotNull(s);
		UtenteDAO.doDelete(st);
	}
	
	@Test
	public void testRetrieveSecretary() {
		UtenteDAO.doSaveOrUpdate(se);
		UserInterface s = UtenteDAO.retrieveByEmail(se.getEmail());
		assertNotNull(s);
		UtenteDAO.doDelete(se);
	}
	
	@Test
	public void testRetrieveAdmin() {
		UtenteDAO.doSaveOrUpdate(ad);
		UserInterface s = UtenteDAO.retrieveByEmail(ad.getEmail());
		assertNotNull(s);
		UtenteDAO.doDelete(ad);
	}
	
	@Test
	public void testDoDelete(){
		UserInterface s=new Student("Nome","Cognome",'m',"n.cognome1@studenti.unisa.it","pass0000",0);
		UtenteDAO.doDelete(s);
	}
	
	@Test
	public void testRetrieveByMail() {
		UserInterface u=UtenteDAO.retrieveByEmail("n.cognom@studenti.unisa.it");
		assertNull(u);
	}
	
	@Test
	public void testVerifyLogin() {
		UtenteDAO.doSave(st);
		UserInterface u=UtenteDAO.verifyLogin(st.getEmail(), st.getPassword());
		assertEquals(st.getEmail(),u.getEmail());	
		UtenteDAO.doDelete(st);
		
		UtenteDAO.doSave(se);
		UserInterface u1=UtenteDAO.verifyLogin(se.getEmail(),se.getPassword());
		assertEquals(se.getEmail(),u1.getEmail());	
		UtenteDAO.doDelete(se);
		
		UtenteDAO.doSave(ad);
		UserInterface u2=UtenteDAO.verifyLogin(ad.getEmail(),ad.getPassword());
		assertEquals(ad.getEmail(),u2.getEmail());	
		UtenteDAO.doDelete(ad);
	}
	@Test
	public void testVerifyLogin2() {
		UserInterface u=UtenteDAO.verifyLogin(st.getEmail(), st.getPassword());
		assertNull(u);	
	
	}
}
