package com.weather.controller;

import static org.junit.Assert.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import com.weather.entity.Weather;
import com.weather.service.WeatherService;

@RunWith(MockitoJUnitRunner.class)
public class RootControllerTest {

	@Mock
	private WeatherService weatherService;
	
	@Mock
	Future<Weather> futureWeather=new CompletableFuture<Weather>();
	
	@InjectMocks
	WeatherBoardController controller= new WeatherBoardController();
	
	Weather weather;
	
	@Before
	public void setUp() throws Exception {
		weather=new Weather();
		
		when(futureWeather.isDone())
		.thenReturn(true);
		when(futureWeather.get())
		.thenReturn(weather);
		
		when(weatherService
				.fetchWeather(Mockito.anyString()))
				.thenReturn(weather);
		
		when(weatherService
				.fetchWeatherForCity(Mockito.anyString()))
				.thenReturn(futureWeather);
			
	}

	@Test
	public void shouldReturnWeatherObject() throws InterruptedException, ExecutionException {
		assertEquals(weather, controller.weatherInCity());
	}
	
	@Test(expected=InterruptedException.class)
	public void shouldRaiseInterruptedException() throws InterruptedException, ExecutionException {
		
		when(weatherService
				.fetchWeatherForCity(Mockito.anyString()))
				.thenThrow(new InterruptedException());
		
		controller.weatherInCity();
	}
	

}
