package src.test;

import static org.junit.Assert.*;

import org.junit.Test;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import src.model.State;
import src.model.StateDAO;

public class StateDAOTest {

	
		public State s= new State(20,"Esempio");


		@Test
		public void testDoSave() {
			StateDAO.doSave(s);
			StateDAO.ifExist(20);
			StateDAO.doDelete(s);	
		}

		@Test
		public void testDoSaveOrUpdate2()  {
			StateDAO.doSave(s);
			StateDAO.ifExist(20);
			s.setDescription("Esempio cambiato");
			StateDAO.doSaveOrUpdate(s);
			StateDAO.doDelete(s);
		}

		@Test
		public void testDoDelete() {
			State s1= new State(50,"aaaaaa");
			StateDAO.doSave(s1);
			StateDAO.doDelete(s1);
			
		}

		@Test
		public void testDoRetrieveAll() {
			StateDAO.doSaveOrUpdate(s);
			StateDAO.doSaveOrUpdate(s);
			ArrayList<State> states = StateDAO.doRetrieveAll(s);
			assertNotNull(states);
			StateDAO.doDelete(s);	
		}

		@Test
		public void testDoRetrieveById() {
			StateDAO.doSaveOrUpdate(s);
			State s1 = new State(1, "Parzialmente completata");
			ArrayList<State> states = StateDAO.doRetrieveById(s1);
			assertNotNull(states);
			StateDAO.doDelete(s);
		}
		@Test
		public void testDoRetrieveById2() {
			StateDAO.doSaveOrUpdate(s);
			State s1 = new State(120, "Parzialmente completata");
			ArrayList<State> states = StateDAO.doRetrieveById(s1);
			assertNull(states);
			StateDAO.doDelete(s);
		}


}



