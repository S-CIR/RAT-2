package src.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import src.model.Secretary;
import src.model.UtenteDAO;

class SecretaryTest {
	
	@Test
	void testSecretaryEmptyConstructor() {
		Secretary se=new Secretary();
		assertNotNull(se);
	}
	//get mail
	@Test
	void testGetEmail() {
		Secretary se=new Secretary("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",1) ;
		assertEquals("m.rossi12@unisa.it",se.getEmail());
	}
	
	//set mail
	@Test
	void testSetEmail() {
		Secretary se=new Secretary("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",1) ;
		se.setEmail("m.rossi55@unisa.it");
		assertEquals("m.rossi55@unisa.it",se.getEmail());
	}
		
		//get name
		@Test
		void testGetName() {
		Secretary se=new Secretary("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",1) ;
		assertEquals("Mario",se.getName());
	}
		
	//set name
	@Test
	void testSetName() {
		Secretary se=new Secretary("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",1) ;
		se.setName("Gianni");
		assertEquals("Gianni",se.getName());
	}

		
	//get surname
	@Test
	void testGetSurname()
	{	
		Secretary se=new Secretary("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",1) ;
		assertEquals("Rossi",se.getSurname());	
	}


	//set surname
	@Test
	void testSetSurname()
	{	
		Secretary se=new Secretary("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",1) ;
		se.setSurname("Cerchia");
		assertEquals("Cerchia",se.getSurname());
	}
	
	
	//get gender
	@Test
	void testGetGender()
	{
		Secretary se=new Secretary("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",1) ;
		assertEquals('M',se.getGender());	
	}
	
	//set gender
	@Test
	void testSetGender()
	{		
		Secretary se=new Secretary("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",1);
		se.setGender('F');
		assertEquals('F',se.getGender());			
	}
				
	//get password		
	@Test
	void testGetPassword()
	{		
		Secretary se=new Secretary("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",1);
		assertEquals("pass1",se.getPassword());			
	}
		
	//set password
	@Test
	void testSetPassword()
	{		
		Secretary se=new Secretary("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",1);
		se.setPassword("rdr2");
		assertEquals("rdr2",se.getPassword());			
	}
				
	//get usertype
	@Test
	void testGetUsertype()
	{		
		Secretary se=new Secretary("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",1);
		assertEquals(1,se.getUser_Type());			
	}
	
	//set usertype
	@Test
	void testSetUsertype()
	{			
		Secretary se=new Secretary("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",1);
		se.setUser_Type(0);
		assertEquals(0,se.getUser_Type());			
	}
	
	//insert
	@Test 
	void testInsert() {
		Secretary se=new Secretary("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",1);
		int res=se.insert();
		assertEquals(0,res);
		UtenteDAO.doDelete(se);		
	}
}
