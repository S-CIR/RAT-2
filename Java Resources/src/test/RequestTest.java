package src.test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import src.model.Attached;
import src.model.Request;

class RequestTest {
	
	@Test
	  void testRequestCostructorEmpty() {
	    Request re = new Request();
	    assertNotNull(re);
	  }
	
	//TEST GETTERS AND SETTERS
	
	//ID REQUEST
	@Test
	void testGetIdRequest() {
		Date st = new Date();
		Date end = new Date();
		Request re = new Request(01, 30, 6, st, end, 0, 1, 1, "prova@studenti.unisa.it", "0512105000");
		assertEquals(01, re.getIdRequest());
	}
	
	@Test
	void testSetIdRequest() {
		Date st = new Date();
		Date end = new Date();
		Request re = new Request(01, 30, 6, st, end, 0, 1, 1, "prova@studenti.unisa.it", "0512105000");
		re.setIdRequest(02);
		assertEquals(02, re.getIdRequest());
	}
	
	//HOURS
	
	@Test
	void testGetHours() {
		Request re = new Request(60, 6, new Date(), new Date(), 0, 1, 1, "prova@studenti.unisa.it", "0512105000");
		assertEquals(60, re.getHours());
	}
	
	@Test
	void testSetHours() {
		Request re = new Request(60, 6, new Date(), new Date(), 0, 1, 1, "prova@studenti.unisa.it", "0512105000");
		re.setHours(30);
		assertEquals(30, re.getHours());
	}
	
	//REQUESTED CFU	
	
	@Test
	void testGetRequestCfu() {
		Request re = new Request(60, 6, new Date(), new Date(), 0, 1, 1, "prova@studenti.unisa.it", "0512105000");
		assertEquals(6, re.getRequestCfu());
	}
	
	@Test
	void testSetRequestCfu() {
		Request re = new Request(60, 6, new Date(), new Date(), 0, 1, 1, "prova@studenti.unisa.it", "0512105000");
		re.setRequestCfu(12);
		assertEquals(12, re.getRequestCfu());
	}
	
	//VALIDATED CFU
	
	@Test
	void testGetValidatedCfu() {
		Request re = new Request(60, 6, new Date(), new Date(), 0, 1, 1, "prova@studenti.unisa.it", "0512105000");
		assertEquals(0, re.getValidatedCfu());
	}
	
	@Test
	void testSetValidatedCfu() {
		Request re = new Request(60, 6, new Date(), new Date(), 0, 1, 1, "prova@studenti.unisa.it", "0512105000");
		re.setValidatedCfu(6);
		assertEquals(6, re.getValidatedCfu());
	}
	
	//START DATE
	
	@Test
	void testGetStartDate() {
		Date st = new Date();
		Request re = new Request(60, 6, st, new Date(), 0, 1, 1, "prova@studenti.unisa.it");
		assertEquals(st, re.getStartDate());
	}
	
	@Test
	void testSetStartDate() {
		Date st = new Date();
		Date st2 = new Date();
		Request re = new Request(60, 6, st, new Date(), 0, 1, 1, "prova@studenti.unisa.it");
		re.setStartDate(st2);
		assertEquals(st2, re.getStartDate());
	}
	
	//END DATE
	
		@Test
		void testGetEndDate() {
			Date end = new Date();
			Request re = new Request(60, 6, new Date(), end, 0, 1, 1, "prova@studenti.unisa.it");
			assertEquals(end, re.getEndDate());
		}
		
		@Test
		void testSetEndDate() {
			Date end = new Date();
			Date end2 = new Date();
			Request re = new Request(60, 6, new Date(), end, 0, 1, 1, "prova@studenti.unisa.it");
			re.setEndDate(end2);
			assertEquals(end2, re.getEndDate());
		}
	
	//ATTACHED
	
	@Test
	void testGetAttached() {
		List<Attached> lst = new ArrayList<>();
		Request re = new Request (01, 60, 6, new Date(), new Date(), 0, lst, 1, 1, "prova@studenti.unisa.it");
		assertEquals(lst, re.getAttached());
	}
	
	@Test
	void testSetAttached() {
		List<Attached> lst = new ArrayList<>();
		List<Attached> lst2 = new ArrayList<>();
		Request re = new Request(01, 60, 6, new Date(), new Date(), 0, lst, 1, 1, "prova@studenti.unisa.it", "0512105000");
		re.setAttached(lst2);
		assertEquals(lst2, re.getAttached());
	}
	
	//USER
	
	@Test
	void testGetUser() {
		Request re = new Request(60, 6, new Date(), new Date(), 0, 1, 1, "prova@studenti.unisa.it", "0512105000");
		assertEquals("prova@studenti.unisa.it", re.getUser());
	}
	
	@Test
	void testSetUser() {
		Request re = new Request(60, 6, new Date(), new Date(), 0, 1, 1, "prova@studenti.unisa.it", "0512105000");
		re.setUser("abcd@studenti.unisa.it");
		assertEquals("abcd@studenti.unisa.it", re.getUser());
	}
	
	//MATRICOLA
	
	@Test
	void testGetMatricola() {
		Request re = new Request(60, 6, new Date(), new Date(), 0, 1, 1, "prova@studenti.unisa.it", "0512105000");
		assertEquals("0512105000", re.getMatricola());
	}
	
	@Test
	void testSetMatricola() {
		Request re = new Request(60, 6, new Date(), new Date(), 0, 1, 1, "prova@studenti.unisa.it", "0512105000");
		re.setMatricola("0512105111");
		assertEquals("0512105111", re.getMatricola());
	}
	
	//STATE
	
	@Test
	void testGetState() {
		Request re = new Request(60, 6, new Date(), new Date(), 0, 1, 1, "prova@studenti.unisa.it", "0512105000");
		assertEquals(1, re.getState());
	}
	
	@Test
	void testSetState() {
		Request re = new Request(60, 6, new Date(), new Date(), 0, 1, 1, "prova@studenti.unisa.it", "0512105000");
		re.setState(2);
		assertEquals(2, re.getState());
	}
	
	//ID AZIENDA
	
	@Test
	void testGetAzienda() {
		Request re = new Request(60, 6, new Date(), new Date(), 0, 1, 1, "prova@studenti.unisa.it", "0512105000");
		assertEquals(1, re.getAzienda());
	}
	
	@Test
	void testSetAzienda() {
		Request re = new Request(60, 6, new Date(), new Date(), 0, 1, 1, "prova@studenti.unisa.it", "0512105000");
		re.setAzienda(2);
		assertEquals(2, re.getAzienda());
	}
	
	
	//TOSTRING
	
	@Test
	void testToString() {
		List<Attached> lst = new ArrayList<>();
		Date st = new Date();
		Date end = new Date();
		Request re = new Request (01, 60, 6, st, end, 0, lst, 1, 1, "prova@studenti.unisa.it");
		assertEquals("Request [idRequest=1, hours=60, requestCfu=6, startDate="+ st + ", endDate="+ end +", validatedCfu=0, attached=[], fk_state=1, fk_azienda=1, fk_user=prova@studenti.unisa.it]", re.toString());
	}
	
	//SDF
	
	@Test
	void testSdf() {
		Request re = new Request(60, 6, new Date(), new Date(), 0, 1, 1, "prova@studenti.unisa.it", "0512105000");
		SimpleDateFormat sdf = new SimpleDateFormat("dd - mm - yyyy");
		re.setSdf(sdf);
		assertEquals(sdf, re.getSdf());
	}
	
}
