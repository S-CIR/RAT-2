package src.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import src.controller.ServletRichiesteSecretary;

class ServletRichiesteSecretaryTest extends Mockito {
	
	private ServletRichiesteSecretary servlet;
	
	
	@Before
	  public void setUp() {
	    servlet = new ServletRichiesteSecretary();
	  }
	
}
