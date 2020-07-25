package src.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import src.controller.DBConnection;

class DBConnectionTest {
	 @Test
	  void testgetDatabaseName() {
	    DBConnection db = new  DBConnection();
	    assertEquals("progettois", db.getDatabaseName());
	  }

	  @Test
	  void testgetUserName() {
		  DBConnection db = new  DBConnection();
	    assertEquals("root", db.getUserName());
	  }

	  @Test
	  void testgetPassword() {
		  DBConnection db = new  DBConnection();
	    assertEquals("root", db.getPassword());
	  }

	  @Test
	  void testgetHostPort() {
		  DBConnection db = new  DBConnection();
	    assertEquals(3306, db.getHostPort());
	  }

	  @Test
	  void testgetHostName() {
		  DBConnection db = new  DBConnection();
	    assertEquals("localhost", db.getHostName());
	  }

	  @Test
	  void testgetConn() {
		  DBConnection db = new  DBConnection();
	    assertNotEquals(null, db.getConn());
	  }

	  @Test
	  void testgetInstance() {
		  DBConnection db =  DBConnection.getInstance();
	    DBConnection db1 =  DBConnection.getInstance();
	    assertEquals(db, db1);
	  }

	  //Test Metodi SET
	  
	  @Test
	  void testsetDatabaseName() {
		  DBConnection db = new  DBConnection();
	    db.setDatabaseName("progettois");
	    assertEquals("progettois", db.getDatabaseName());
	  }

	  @Test
	  void testsetUserName() {
		  DBConnection db = new  DBConnection();
	    db.setUserName("Root");
	    assertEquals("Root", db.getUserName());
	  }

	  @Test
	  void testsetPassword() {
		  DBConnection db = new  DBConnection();
	    db.setPassword("Root");
	    assertEquals("Root", db.getPassword());
	  }

	  @Test
	  void testsetHostPort() {
		  DBConnection db = new  DBConnection();
	    db.setHostPort(3306);
	    assertEquals(3306, db.getHostPort());
	  }

	  @Test
	  void testsetHostName() {
		  DBConnection db = new  DBConnection();
	    db.setHostName("localhost");
	    assertEquals("localhost", db.getHostName());
	  }

	  @Test
	  void testsetConn() throws Exception {
	    new  DBConnection().setConn(null);
	  }

}
