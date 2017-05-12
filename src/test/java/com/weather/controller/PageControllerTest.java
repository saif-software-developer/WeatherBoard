package com.weather.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import  org.mockito.Mockito;

public class PageControllerTest {

	PagesController controller;
	
	@Before
	public void setUp() throws Exception {
		controller=new PagesController();
	}

	@Test
	public void shouldReturnHomeString() {
		assertEquals(controller.home(),"index");
	}
	
	@Test
	public void shouldReturnWeatherString() {
		assertEquals(controller.welcome(Mockito.anyMap()),"weather");
	}


}
