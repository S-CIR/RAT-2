package src.test;


import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.*;


import src.controller.Utils;
import src.interfaccia.UserInterface;
import src.model.Attached;
import src.model.AttachedDAO;
import src.model.Request;
import src.model.RequestDAO;
import src.model.Student;
import src.model.UtenteDAO;

public class AttachedDAOTest {
	private String pw = new Utils().generatePwd("Studente1");
	private Date start = new Date();
	private Date end = new Date();
	private UserInterface u = new Student("nome","cognome",'m',"n.cognome@studenti.unisa.it","Studente1",0);
	private Request r = new Request(22, 60, 6, start, end, 0, 2, 1, u.getEmail(), "0512105422");
	private Attached a = new Attached(100, "0123456789_2019-01-01_2020-01-01.pdf", 22, "n.cognome@studenti.unisa.it");
	
    @Test
	public void testFindNameByRequestId() throws ParseException {
		UtenteDAO.doSave(u);
		RequestDAO.doSave(r);
		AttachedDAO.doSave(a);
		
		String res = AttachedDAO.findNameByRequestId(22);
		assertEquals("0123456789_2019-01-01_2020-01-01.pdf", res);
		
		ArrayList <Attached> list = new ArrayList<Attached>();
		list = AttachedDAO.doRetrieveAll(a);
		for(Attached i:list) {
			AttachedDAO.doDelete(i);
		}
		RequestDAO.doDelete(r);
		UtenteDAO.doDelete(u);
	}
	
	@Test
	public void testDoSaveOrUpdate() throws ParseException {
		UtenteDAO.doSave(u);
		RequestDAO.doSave(r);
		
		AttachedDAO.doSaveOrUpdate(a);
		
		String res = AttachedDAO.findNameByRequestId(22);
		assertEquals("0123456789_2019-01-01_2020-01-01.pdf", res);
		
		ArrayList <Attached> list = new ArrayList<Attached>();
		list = AttachedDAO.doRetrieveAll(a);
		for(Attached i:list) {
			AttachedDAO.doDelete(i);
		}
		RequestDAO.doDelete(r);
		UtenteDAO.doDelete(u);
	}
	
	@Test
	public void testDoSaveOrUpdate2() throws InterruptedException, ParseException {
		UtenteDAO.doSave(u);
		RequestDAO.doSave(r);
		Attached ab = new Attached(22,"0123456789_2019-01-01_2020-01-01.pdf", "n.cognome@studenti.unisa.it");
		AttachedDAO.doSaveOrUpdate(ab);
		Thread.sleep(2000);
		AttachedDAO.ifExist(100, 22, "n.cognome@studenti.unisa.it");
		
		ArrayList <Attached> list = new ArrayList<Attached>();
		list=AttachedDAO.doRetrieveAll(ab);
		Attached b=new Attached();
		for(Attached a:list) {
			if(a.getFk_user().equals("n.cognome@studenti.unisa.it")) {
				b.setIdAttached(a.getIdAttached());
				b.setFk_request(a.getFk_request());
				b.setFilename(a.getFilename());
				b.setFk_user(a.getFk_user());
			}
		}
		
		
		b.setFilename("newName");
		AttachedDAO.doSaveOrUpdate(b);

	
		ArrayList <Attached> list1 = new ArrayList<Attached>();
		list = AttachedDAO.doRetrieveAll(a);
		for(Attached i:list1) {
			AttachedDAO.doDelete(i);
		}
		RequestDAO.doDelete(r);
		UtenteDAO.doDelete(u);
		AttachedDAO.doDelete(b);
	}
	
	@Test
	public void testDoDelete() throws ParseException {
		UtenteDAO.doSave(u);
		RequestDAO.doSave(r);
		
		AttachedDAO.doDelete(a);
		
		assertEquals(AttachedDAO.ifExist(100, 22, "n.cognome@studenti.unisa.it"),false);
		
		UtenteDAO.doDelete(u);
	}
	
	@Test
	public void testDoDelete2() throws InterruptedException, ParseException {
		UtenteDAO.doSave(u);
		RequestDAO.doSave(r);
		AttachedDAO.doSave(a);
		Thread.sleep(2000);
		
		AttachedDAO.doDelete(a);
		
		assertEquals(AttachedDAO.ifExist(100, 100, "n.cognome@studenti.unisa.it"),false);
		
		ArrayList <Attached> list = new ArrayList<Attached>();
		list = AttachedDAO.doRetrieveAll(a);
		for(Attached i:list) {
			AttachedDAO.doDelete(i);
		}
		RequestDAO.doDelete(r);
		UtenteDAO.doDelete(u);
	}

	@Test
	public void testDoRetrieveAll() {
		ArrayList<Attached> list = new ArrayList<Attached>();
		list= AttachedDAO.doRetrieveAll(a);
		
		assertEquals(null, list);
	}
	
	@Test
	public void testDoRetrieveAll2() throws InterruptedException, ParseException {
		UtenteDAO.doSave(u);
		RequestDAO.doSave(r);
		AttachedDAO.doSave(a);
		Thread.sleep(2000);
		
		ArrayList<Attached> list = new ArrayList<Attached>();
		list= AttachedDAO.doRetrieveAll(a);
		
		assertNotEquals(0, list.size());
		
		for(Attached i:list) {
			AttachedDAO.doDelete(i);
		}
		RequestDAO.doDelete(r);
		UtenteDAO.doDelete(u);
	}

	@Test
	public void testDoRetrieveById() {
		ArrayList<Attached> list = new ArrayList<Attached>();
		list= AttachedDAO.doRetrieveById(a);
		
		assertEquals(null, list);
	}
	
	@Test
	public void testDoRetrieveById2() throws ParseException, InterruptedException {
		UtenteDAO.doSave(u);
		RequestDAO.doSave(r);
		AttachedDAO.doSave(a);
		Thread.sleep(2000);
		
		ArrayList<Attached> list = new ArrayList<Attached>();
		list= AttachedDAO.doRetrieveAll(a);
		a=list.get(0);
		list=AttachedDAO.doRetrieveById(a);
		
		assertNotEquals(0, list.size());
		
		for(Attached i:list) {
			AttachedDAO.doDelete(i);
		}
		RequestDAO.doDelete(r);
		UtenteDAO.doDelete(u);
	}
	
	@Test
	public void testIfExist() throws ParseException, InterruptedException {
		UtenteDAO.doSave(u);
		RequestDAO.doSave(r);
		AttachedDAO.doSave(a);
		Thread.sleep(2000);
		
		ArrayList <Attached> list = new ArrayList<Attached>();
		list = AttachedDAO.doRetrieveAll(a);
		a=list.get(0);
		
		assertEquals(true, AttachedDAO.ifExist(a.getIdAttached(), 22, "n.cognome@studenti.unisa.it"));
		
		for(Attached i:list) {
			AttachedDAO.doDelete(i);
		}
		RequestDAO.doDelete(r);
		UtenteDAO.doDelete(u);
	}
	
}
