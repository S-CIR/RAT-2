package src.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import src.model.Azienda;

class AziendaTest {
	@Test
	void testEmptyConstructor() {
	Azienda az=new Azienda();
	assertNotNull(az);
	}
	
	//get id
	@Test
	void testGetId() {
		Azienda az=new Azienda(1,"axis@gmail.com","Axis","axis.com") ;
		assertEquals(1,az.getId());
	}
	
	//set id
	@Test
	void testSetId() {
		Azienda az=new Azienda(1,"axis@gmail.com","Axis","axis.com") ;
		az.setId(2);
		assertEquals(2,az.getId());
	}	
	
	//get email
	@Test
	void testGetEmail() {
		Azienda az=new Azienda(1,"axis@gmail.com","Axis","axis.com") ;
		assertEquals("axis@gmail.com",az.getEmail());
	}
	
	//set email
	@Test
	void testSetEmail() {
		Azienda az=new Azienda(1,"axis@gmail.com","Axis","axis.com") ;
		az.setEmail("axis@domain.com");
		assertEquals("axis@domain.com",az.getEmail());
	}
	
	//get name
	@Test
	void testGetName() {
		Azienda az=new Azienda(1,"axis@gmail.com","Axis","axis.com") ;
		assertEquals("Axis",az.getName());
	}
	
	//set name
	@Test
	void testSetName() {
		Azienda az=new Azienda(1,"axis@gmail.com","Axis","axis.com") ;
		az.setName("Axis2");
		assertEquals("Axis2",az.getName());
	}
	
	//get site
	@Test
	void testGetSite() {
		Azienda az=new Azienda(1,"axis@gmail.com","Axis","axis.com") ;
		assertEquals("axis.com",az.getSite());
		}
	
	//set site
	@Test
	void testSetSite() {
		Azienda az=new Azienda(1,"axis@gmail.com","Axis","axis.com") ;
		az.setSite("axis.net");
		assertEquals("axis.net",az.getSite());
		}
	
}
	