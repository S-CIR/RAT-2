package src.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import src.controller.ServletRichiesteSecretary;

class ServletRichiesteSecretaryTest extends Mockito {
	
	private ServletRichiesteSecretary servlet;
	HttpServletRequest request;
    HttpServletResponse response;
	
	@Before
	public void setUp() {
	    servlet = new ServletRichiesteSecretary();
	    response = mock(HttpServletResponse.class);
	    request = mock(HttpServletRequest.class);
	  }
	
	@Test
	public void testViewRequest() throws ServletException, IOException {
		servlet.doPost(request, response);
		assertEquals("json", response.getContentType());
	  }
	
}
