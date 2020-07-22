package src.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import src.model.Student;
import src.model.UtenteDAO;

class StudentTest {

	@Test
	void testStudentConstructorEmpty() {
		Student student=new Student();
		assertNotNull(student);
		
	}

	@Test
	void testStudentConstuctorFull() {
		Student student=new Student("name","surname",'m',"n.surname@email.com","password",0);
		assertNotNull(student);
	}


	@Test
	void testGetName() {
		Student student=new Student("name","surname",'m',"n.surname@email.com","password",0);
		assertEquals("name",student.getName());
	}

	@Test
	void testSetName() {
		Student student=new Student("name","surname",'m',"n.surname@email.com","password",0);
		student.setName("newName");		
		assertEquals("newName",student.getName());
	}

	@Test
	void testGetSurname() {
		Student student=new Student("name","surname",'m',"n.surname@email.com","password",0);
		assertEquals("surname",student.getSurname());
	}

	@Test
	void testSetSurname() {
		Student student=new Student("name","surname",'m',"n.surname@email.com","password",0);
		student.setSurname("newSurname");		
		assertEquals("newSurname",student.getSurname());
	}

	@Test
	void testGetGender() {
		Student student=new Student("name","surname",'m',"n.surname@email.com","password",0);
		assertEquals('m',student.getGender());
	}

	@Test
	void testSetGender() {
		Student student=new Student("name","surname",'m',"n.surname@email.com","password",0);
		student.setGender('f');		
		assertEquals('f',student.getGender());
	}

	@Test
	void testGetEmail() {
		Student student=new Student("name","surname",'m',"n.surname@email.com","password",0);
		assertEquals("n.surname@email.com",student.getEmail());
	}

	@Test
	void testSetEmail() {
		Student student=new Student("name","surname",'m',"n.surname@email.com","password",0);
		student.setEmail("n.surname1@email.com");		
		assertEquals("n.surname1@email.com",student.getEmail());
	}

	@Test
	void testGetPassword() {
		Student student=new Student("name","surname",'m',"n.surname@email.com","password",0);
		assertEquals("password",student.getPassword());
	}

	@Test
	void testSetPassword() {
		Student student=new Student("name","surname",'m',"n.surname@email.com","password",0);
		student.setPassword("password1");		
		assertEquals("password1",student.getPassword());
	}

	@Test
	void testGetUser_Type() {
		Student student=new Student("name","surname",'m',"n.surname@email.com","password",0);
		assertEquals(0,student.getUser_Type());
	}

	@Test
	void testSetUser_Type() {
		Student student=new Student("name","surname",'m',"n.surname@email.com","password",0);
		student.setUser_Type(1);		
		assertEquals(1,student.getUser_Type());
	}

	@Test 
	void testInsert() {
		Student student=new Student("name","surname",'m',"n.surname@email.com","password",0);
		int res=student.insert();
		assertEquals(0,res);
		UtenteDAO.doDelete(student);
	}
	
	@Test 
	void testToString() {
		Student student=new Student("name","surname",'m',"n.surname@email.com","password",0);
		String result = "Student [name=name, surname=surname, gender=m, email=n.surname@email.com, password=password, user_Type=0]";
		assertEquals(student.toString(),result);
	}

}
