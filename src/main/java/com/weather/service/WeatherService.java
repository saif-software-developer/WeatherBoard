package com.weather.service;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.weather.entity.Weather;

@Service
public class WeatherService {

	// http://api.openweathermap.org/data/2.5/weather?q=Dallas,us&appid=
	// 9bd9d333cf662906ff4380fd4954de3e
	private static final Logger logger = LoggerFactory
			.getLogger(WeatherService.class);

	private final RestTemplate restTemplate;
	private final static String APP_ID = "9bd9d333cf662906ff4380fd4954de3e";

	public WeatherService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = new RestTemplate();
	}

	@Cacheable(cacheNames="weather", key="#cityCountry")
	public Weather fetchWeather(String cityCountry) throws RestClientException {
		logger.info("Sync : Looking up " + cityCountry);
		String url = String.format(
				"http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s",
				cityCountry, APP_ID);

		return restTemplate.getForObject(url, Weather.class);
	}

	@Async
	public Future<Weather> fetchWeatherForCity(String cityCountry) 
			throws InterruptedException {
		logger.info("Async : Looking up " + cityCountry);
		String url = String.format(
				"http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s",
				cityCountry, APP_ID);
		Weather results = restTemplate.getForObject(url, Weather.class);
		
		return new AsyncResult<>(results);
	}
}
