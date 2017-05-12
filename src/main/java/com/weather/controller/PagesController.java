package com.weather.controller;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PagesController {

	private static final Logger logger = LoggerFactory
			.getLogger(WeatherBoardController.class);
	
	@RequestMapping(path="/",method = RequestMethod.GET)
	String home() {
		return "index";
	}
	
	@RequestMapping("/weather")
	public String welcome(Map<String, Object> model) {
		return "weather";
	}
}
