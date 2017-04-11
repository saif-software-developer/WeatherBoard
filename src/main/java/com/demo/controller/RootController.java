package com.demo.controller;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.demo.entity.Weather;
import com.demo.service.WeatherService;

@Controller
public class RootController {

	private static final Logger logger = LoggerFactory
			.getLogger(RootController.class);

	@Autowired
	WeatherService weatherService;

	@RequestMapping("/")
	String home(Map<String, Object> model) {
		logger.info("$$$$$$$$$$$$$  home page is here bro $$$$$$$$$$$$$ ");
		model.put("message", "$$$$$$$$$$$$$  home page is here bro $$$$$$$$$$$$$ ");
		return "index";
	}

	@RequestMapping(value = "/cityCountry/{cityCountry}", method = RequestMethod.GET)
	String cityCountry(@PathVariable("cityCountry") String cityCountry) {
		return "Hello Dallas ! " + weatherService.fetchWeather(cityCountry);
	}

	@RequestMapping(value = "/sync/weather", method = RequestMethod.GET)
	String weatherSync() {
		Long startTime = System.currentTimeMillis();
		logger.info("Fetch weather async starts : {} ", startTime);

		Weather dallas = weatherService.fetchWeather("Dallas,us");
		Weather houston = weatherService.fetchWeather("Houston,us");
		Weather fairfield = weatherService.fetchWeather("Fairfield,us");

		Long endTime = System.currentTimeMillis();
		logger.info("Fetch weather async ends : {} ", endTime);
		logger.info("It took this much time ( {} ) to fetch data :  ", endTime
				- startTime);

		String weather = "Weather in Dallas city is " + dallas.getClouds()
				+ "<br> Weather in Houstn city is " + houston.getClouds()
				+ "<br> Weather in Fairfield city is " + fairfield.getClouds();

		return weather;
	}

	@RequestMapping(value = "/async/weather", method = RequestMethod.GET)
	String weatherAsync() throws InterruptedException, ExecutionException {
		Long startTime = System.currentTimeMillis();
		logger.info("Fetch weather async starts : {} ", startTime);
		Future<Weather> dallas = weatherService
				.fetchWeatherForCity("Dallas,us");
		Future<Weather> houston = weatherService
				.fetchWeatherForCity("Houston,us");
		Future<Weather> fairfield = weatherService
				.fetchWeatherForCity("Fairfield,jordan");

		// Wait until they are all done
		while (!(dallas.isDone() && houston.isDone() && fairfield.isDone())) {
			Thread.sleep(10); // 10-millisecond pause between each check
		}
		
		Long endTime = System.currentTimeMillis();
		logger.info("Fetch weather async ends : {} ", endTime);
		logger.info("It took this much time ( {} ) to fetch data :  ", endTime
				- startTime);

		String weather = "Weather in Dallas city is " + dallas.get().getClouds()
				+ "<br> Weather in Houstn city is " + houston.get().getClouds()
				+ "<br> Weather in Fairfield city is "
				+ fairfield.get().getClouds();

		return weather;
	}

}
