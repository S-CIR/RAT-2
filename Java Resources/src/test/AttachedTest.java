package src.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import src.model.Attached;

class AttachedTest {

	@Test
	void testAttachedConstructor() {
		Attached att = new Attached(0,"",0,"");
		assertNotNull(att);
	}

	@Test
	void testAttachedConstructor1() {
		Attached att = new Attached(0,"","");
		assertNotNull(att);
	}

	@Test
	void testAttachedConstructorEmpty() {
		Attached att = new Attached();
		assertNotNull(att);
	}

	@Test
	void testGetIdAttached() {
		Attached att = new Attached(0,"filename",0,"user");
		assertEquals(0,att.getIdAttached());
	}

	@Test
	void testSetIdAttached() {
		Attached att = new Attached(0,"filename",0,"user");
		att.setIdAttached(1);
		assertEquals(1,att.getIdAttached());
	}

	@Test
	void testGetFilename() {
		Attached att = new Attached(0,"filename",0,"user");
		assertEquals("filename",att.getFilename());
	}

	@Test
	void testSetFilename() {
		Attached att = new Attached(0,"filename",0,"user");
		att.setFilename("New filename");
		assertEquals("New filename",att.getFilename());
	}

	@Test
	void testGetFk_request() {
		Attached att = new Attached(0,"filename",0,"user");
		assertEquals(0,att.getFk_request());
	}

	@Test
	void testSetFk_request() {
		Attached att = new Attached(0,"filename",0,"user");
		att.setFk_request(1);
		assertEquals(1,att.getFk_request());
	}

	@Test
	void testGetFk_user() {
		Attached att = new Attached(0,"filename",0,"user");
		assertEquals("user",att.getFk_user());
	}

	@Test
	void testSetFk_user() {
		Attached att = new Attached(0,"filename",0,"user");
		att.setFk_user("New user");
		assertEquals("New user",att.getFk_user());
	}

}
