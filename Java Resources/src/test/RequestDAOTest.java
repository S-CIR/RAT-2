package src.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;


import org.junit.jupiter.api.Test;

import src.interfaccia.UserInterface;
import src.model.Request;
import src.model.RequestDAO;
import src.model.Student;
import src.model.UtenteDAO;

class RequestDAOTest {

	
	Date start = new Date();
    Date end = new Date();
    public Student st = new Student("Name","Surname",'m',"n.surname2@studenti.unisa.it","pass0000",0);
	public Request re = new Request(21, 60, 6, start, end, 0, 1, 1, st.getEmail(), "0512105421");
	

	
	@Test
	public void testGetRequestState() throws ParseException {
		UtenteDAO.doSaveOrUpdate(st);
		RequestDAO.doSaveOrUpdate(re);
		RequestDAO.doSaveOrUpdate(re);
		int state = RequestDAO.getRequestState(21);
		assertNotNull(state);
		UtenteDAO.doDelete(st);
		RequestDAO.doDelete(re);
		
	}
	
	@Test
	public void testGetRequestState2() throws ParseException {
		UtenteDAO.doSaveOrUpdate(st);
		RequestDAO.doSaveOrUpdate(re);
		RequestDAO.doSaveOrUpdate(re);
		int state = RequestDAO.getRequestState(25);
		assertNotNull(state);
		UtenteDAO.doDelete(st);
		RequestDAO.doDelete(re);
		
	}
		
	
	@Test
	public void testGetLastUserRequestPartiallyCompleted() throws ParseException {
		UserInterface u= new Student("Name","Surname",'m',"n.surname2@studenti.unisa.it","pass0000",0);
		UtenteDAO.doSave(u);
		RequestDAO.doSave(re);
		Request rq=new Request();
		rq=RequestDAO.getLastUserRequestPartiallyCompleted(u);
		assertNotNull(rq);
		UtenteDAO.doDelete(u);
		RequestDAO.doDelete(rq);
	}
	@Test
	public void testGetLastUserRequestPartiallyCompleted2() throws ParseException {
		UserInterface u1= new Student("Nome","Surname",'m',"n.surname5@studenti.unisa.it","pass0000",0);
		Request re1 = new Request(22, 60, 6, start, end, 0, 2, 1, "n.surname5@studenti.unisa.it", "0512105421");
		UtenteDAO.doSave(u1);
		RequestDAO.doSave(re1);
		Request rq=new Request();
		rq=RequestDAO.getLastUserRequestPartiallyCompleted(u1);
		assertNull(rq);
		UtenteDAO.doDelete(u1);
		RequestDAO.doDelete(re1);
	}
	
	@Test 
	public void testFindIdByUserAndMiddleState() throws ParseException {
		UtenteDAO.doSave(st);
		RequestDAO.doSave(re);
		int i=RequestDAO.findIdByUserAndMiddleState("n.surname2@studenti.unisa.it");
		assertEquals(21,i);
		UtenteDAO.doDelete(st);
		RequestDAO.doDelete(re);
	}
	
	@Test 
	public void testFindIdByUserAndMiddleState2() throws ParseException {
		Student u1= new Student("Nome","Surname",'m',"n.surname5@studenti.unisa.it","pass0000",0);
		Request re1 = new Request(22, 60, 6, start, end, 0, 2, 1, "n.surname5@studenti.unisa.it", "0512105421");
		UtenteDAO.doSave(u1);
		RequestDAO.doSave(re1);
		int i=RequestDAO.findIdByUserAndMiddleState("n.surname2@studenti.unisa.it");
		assertEquals(-1,i);
		UtenteDAO.doDelete(u1);
		RequestDAO.doDelete(re);
	}
	
	
	@Test
	public void testGetCertificateInfo() throws ParseException {
		UtenteDAO.doSaveOrUpdate(st);
		RequestDAO.doSaveOrUpdate(re);
		ResultSet r = RequestDAO.getCertificateInfo(st.getEmail());
		assertNotNull(r);
		UtenteDAO.doDelete(st);
		RequestDAO.doDelete(re);
		
	}
	
	@Test
	public void testGetCertificateInfo2() throws ParseException {
		ResultSet r = RequestDAO.getCertificateInfo(st.getEmail());
		assertNull(r);
		
	}
	
	@Test
	public void testSetWorkingSecretaryState() throws ParseException {
		UtenteDAO.doSaveOrUpdate(st);
		RequestDAO.doSaveOrUpdate(re);
		assertEquals(true, RequestDAO.setWorkingSecretaryState(21));
		UtenteDAO.doDelete(st);
		RequestDAO.doDelete(re);
	}
	
	@Test
	public void testSetWorkingSecretaryState2() throws ParseException {
		assertEquals(false, RequestDAO.setWorkingSecretaryState(21));
	}
	
	@Test
	public void testRetrieveAll() throws ParseException {
		UtenteDAO.doSaveOrUpdate(st);
		RequestDAO.doSaveOrUpdate(re);
		ArrayList<Request> req =RequestDAO.retrieveAll();
		assertNotNull(req);
		UtenteDAO.doDelete(st);
		RequestDAO.doDelete(re);
	}
	
	@Test
	public void testRetrieveById() throws ParseException {
		UtenteDAO.doSaveOrUpdate(st);
		RequestDAO.doSaveOrUpdate(re);
		Request r = RequestDAO.retrieveById(re.getIdRequest());
		assertNotNull(r);
		UtenteDAO.doDelete(st);
		RequestDAO.doDelete(re);
	}
	
	@Test
	public void testRetrieveById2() throws ParseException {
		Request re1 = new Request(22, 60, 6, start, end, 0, 2, 1, "n.surname5@studenti.unisa.it", "0512105421");
		Request r = RequestDAO.retrieveById(re1.getIdRequest());
		assertNull(r);
	}
	
	@Test
	void testExistPendent() throws ParseException {
		UtenteDAO.doSaveOrUpdate(st);
		RequestDAO.doSaveOrUpdate(re);
		Request r = RequestDAO.existPendent(st.getEmail(), 1);
		assertNotNull(r);
		UtenteDAO.doDelete(st);
		RequestDAO.doDelete(re);
	}
	
	@Test
	void testExistPendent2() throws ParseException {;
		Request r = RequestDAO.existPendent(st.getEmail(), 1);
		assertNull(r);
	}
	
	@Test
	public void testRetrieveByDateAndUser() throws ParseException {
		UtenteDAO.doSaveOrUpdate(st);
		RequestDAO.doSaveOrUpdate(re);
		Request r = RequestDAO.retrieveByDateAndUser(re);
		assertNotNull(r);
		UtenteDAO.doDelete(st);
		RequestDAO.doDelete(re);
	}
	
	@Test
	public void testRetrieveByDateAndUser2() throws ParseException {
		Request r = RequestDAO.retrieveByDateAndUser(re);
		assertNull(r);
	}
	
	@Test
	public void testDoDelete() throws ParseException {
		UtenteDAO.doSaveOrUpdate(st);
		RequestDAO.doSaveOrUpdate(re);
		RequestDAO.doDelete(re);
		UtenteDAO.doDelete(st);
	}
	
	@Test
	public void testInoltra() throws ParseException {
		UtenteDAO.doSaveOrUpdate(st);
		RequestDAO.doSaveOrUpdate(re);
		RequestDAO.inoltra(21, 2);
		RequestDAO.doDelete(re);
		UtenteDAO.doDelete(st);
		
	}
	
	@Test
	public void testAddCFU() throws ParseException {
		UtenteDAO.doSaveOrUpdate(st);
		RequestDAO.doSaveOrUpdate(re);
		RequestDAO.addCFU(21);
		RequestDAO.doDelete(re);
		UtenteDAO.doDelete(st);
	}
	
	@Test
	public void testPrelevaCFU(){
		int r = RequestDAO.prelevaCFU(21);
		assertEquals(0, r);
	}
	
	@Test
	public void testFindByState()throws ParseException {
		UtenteDAO.doSaveOrUpdate(st);
		RequestDAO.doSaveOrUpdate(re);
		ResultSet r= RequestDAO.findByState(1);
		assertNotNull(r);
		RequestDAO.doDelete(re);
		UtenteDAO.doDelete(st);
	}
	
	@Test
	public void testFindByState2() {
		ResultSet r= RequestDAO.findByState(1);
		assertNull(r);
	}
	
	@Test
	public void testFindByUserMail() throws ParseException {
		UtenteDAO.doSaveOrUpdate(st);
		RequestDAO.doSaveOrUpdate(re);
		ResultSet r= RequestDAO.findByUserMail("n.surname2@studenti.unisa.it");
		assertNotNull(r);
		RequestDAO.doDelete(re);
		UtenteDAO.doDelete(st);
		
	}
	@Test
	public void testFindByUserMail2(){
		ResultSet r= RequestDAO.findByUserMail("n.surname2@studenti.unisa.it");
		assertNull(r);
		
	}
	
}
