package com.weather.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
	}

	/**
	 * We need to define 3 things to implement  
	 * 1- Define message resource
	 * 2- Define Local resolver internationalization
	 * 3- Override interceptor 
	 */
	@Bean
	public MessageSource messageSource(){
		ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
	
	@Bean
	public LocaleResolver localeResolver(){
		SessionLocaleResolver resolver =new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.ENGLISH);
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		//you can add more resources here 
		registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		LocaleChangeInterceptor changeInterceptor=new LocaleChangeInterceptor();
		changeInterceptor.setParamName("language");
		registry.addInterceptor(changeInterceptor);
	}
	

}
