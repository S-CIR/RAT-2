package src.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import src.model.Admin;
import src.model.UtenteDAO;

class AdminTest {
	
	@Test
	void testAdminEmptyConstructor() {
		Admin ad=new Admin();
		assertNotNull(ad);
	}
	
	//get mail
	@Test
	void testGetEmail() {
		Admin ad=new Admin("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",2) ;
		assertEquals("m.rossi12@unisa.it",ad.getEmail());
	}
	
	//set mail
	@Test
	void testSetEmail() {
		Admin ad=new Admin("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",2) ;
		ad.setEmail("m.rossi55@unisa.it");
		assertEquals("m.rossi55@unisa.it",ad.getEmail());
		
	}
		
	//get name
	@Test
	void testGetName() {
		Admin ad=new Admin("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",2) ;
		assertEquals("Mario",ad.getName());
	}
		
	//set name
	@Test
	void testSetName() {
		Admin ad=new Admin("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",2) ;
		ad.setName("Gianni");
		assertEquals("Gianni",ad.getName());
		
	}

	//get surname
	@Test
	void testGetSurname()
	{
		Admin ad=new Admin("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",2) ;
		assertEquals("Rossi",ad.getSurname());
	}


	//set surname
	@Test
	void testSetSurname()
	{	
		Admin ad=new Admin("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",2) ;
		ad.setSurname("Cerchia");
		assertEquals("Cerchia",ad.getSurname());
	}
	
	//get gender
	@Test
	void testGetGender()
	{
		Admin ad=new Admin("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",2) ;
		assertEquals('M',ad.getGender());
	}
	
	//set gender
	@Test
	void testSetGender()
	{			
		Admin ad=new Admin("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",2);
		ad.setGender('F');
		assertEquals('F',ad.getGender());			
	}
				
	//get password		
	@Test
	void testGetPassword()
	{			
		Admin ad=new Admin("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",2);
		assertEquals("pass1",ad.getPassword());		
	}
	
	//set password
	@Test
	void testSetPassword()
	{				
		Admin ad=new Admin("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",2);
		ad.setPassword("rdr2");
		assertEquals("rdr2",ad.getPassword());		
	}
				
	//get usertype
	@Test
	void testGetUsertype()
	{			
		Admin ad=new Admin("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",2);
		assertEquals(2,ad.getUser_Type());		
	}
	
	//set usertype
	@Test
	void testSetUsertype()
	{		
		Admin ad=new Admin("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",2);
		ad.setUser_Type(0);
		assertEquals(0,ad.getUser_Type());		
	}	
	
	//insert
	@Test 
	void testInsert() {
		Admin ad=new Admin("Mario","Rossi",'M',"m.rossi12@unisa.it","pass1",2);
		int res=ad.insert();
		assertEquals(0,res);
		UtenteDAO.doDelete(ad);
	}
}
