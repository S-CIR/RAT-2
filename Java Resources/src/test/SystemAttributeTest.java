package src.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import src.model.SystemAttribute;

class SystemAttributeTest {

	@Test
	void testConstructor() {
		SystemAttribute sa = new SystemAttribute();
		assertNotNull(sa);
	}
	
	@Test
	void testHashMap() {
		SystemAttribute sa = new SystemAttribute();
		sa.getHashMap();
	}
	
	@Test
	void testGetValueByKey() {
		SystemAttribute sa = new SystemAttribute();
		assertEquals("6", sa.getValueByKey("request-accepted"));
	}
	
	@Test
	void testSetHashMap() {
		SystemAttribute sa = new SystemAttribute();
		HashMap<String, String> hm = new HashMap<String, String>();
		sa.setHashMap(hm);
		assertEquals(hm, sa.getHashMap());
	}
	
	@Test
	void testGetInstance() {
		SystemAttribute sa = new SystemAttribute();
		SystemAttribute sysAtt = sa.getInstance();
		assertNotNull(sysAtt);
	}

}
