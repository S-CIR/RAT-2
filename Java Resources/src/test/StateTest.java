package src.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import src.model.State;

class StateTest {

	@Test 
	void testStateConstructorEmpty() {
		State state= new State();
		assertNotNull(state);
	}
	
	@Test
	void testStateConstructorFull() {
		State state= new State(0,"description");
		assertNotNull(state);
	}
	@Test
	void testGetId_state() {
		State state=new State(0,"description");
		assertEquals(0,state.getId_State());
	}

	@Test
	void testSetId_State() {
		State state=new State(0,"description");
		state.setId_State(1);
		assertEquals(1,state.getId_State());
	}

	@Test
	void testGetDescription() {
		State state=new State(0,"description");
		assertEquals("description",state.getDescription());
	}

	@Test
	void testSetDescription() {
		State state=new State(0,"description");
		state.setDescription("newDescription");
		assertEquals("newDescription",state.getDescription());
	}

}
