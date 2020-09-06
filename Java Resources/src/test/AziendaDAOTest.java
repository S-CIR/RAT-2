package src.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import src.model.Azienda;
import src.model.AziendaDAO;

class AziendaDAOTest {
	public Azienda a= new Azienda(20,"azienda@gmail.com","Azienda1"," ");
	

	
	@Test
	void testDoSave() {
		AziendaDAO.doSave(a);
		AziendaDAO.ifExist(20);
		AziendaDAO.doDelete(a);
	}

	@Test
	void testDoSaveOrUpdate() {
		Azienda b= new Azienda(25,"azienda1@gmail.com","Azienda2"," ");
		AziendaDAO.doSaveOrUpdate(b);
		AziendaDAO.doDelete(b);
	}
	
	@Test
	void testDoSaveOrUpdate1() {
		AziendaDAO.doSave(a);
		a.setName("Azienda11");
		AziendaDAO.doSaveOrUpdate(a);
		AziendaDAO.doDelete(a);
	}

	@Test
	void testDoDelete() throws InterruptedException {
		Azienda b= new Azienda(25,"azienda1@gmail.com","Azienda2"," ");
		AziendaDAO.doDelete(b);
		Thread.sleep(2000);
		AziendaDAO.doDelete(b);
	}

	@Test
	void testDoRetrieveAll() {
		ArrayList<Azienda> a= new ArrayList<Azienda>();
		a=AziendaDAO.doRetrieveAll();
		assertEquals(a.size(),AziendaDAO.doRetrieveAll().size());
		
	}

	@Test
	void testDoRetrieveById() {
		Azienda b= new Azienda(25,"azienda1@gmail.com","Azienda2"," ");
		AziendaDAO.doSave(b);
		ArrayList<Azienda> a= new ArrayList<Azienda>();
		a=AziendaDAO.doRetrieveById(25);
		for(Azienda w:a) {
			assertEquals(b.getId(),w.getId());
		}
		AziendaDAO.doDelete(b);
	}

	@Test
	void testDoRetrieveById2() {
     
		assertEquals(null,AziendaDAO.doRetrieveById(0));
	}
	
	@Test
	void testDoRetrieveByState() {
     	ArrayList<Azienda> a= new ArrayList<Azienda>();
		a=AziendaDAO.doRetrieveByState(1);
		assertEquals(a.size(),AziendaDAO.doRetrieveByState(1).size());
	}

	@Test
	void testDoRetrieveByState2() {
     
		assertEquals(null,AziendaDAO.doRetrieveByState(0));
	}

	
}
