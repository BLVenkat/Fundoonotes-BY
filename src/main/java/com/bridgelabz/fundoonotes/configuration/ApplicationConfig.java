package com.bridgelabz.fundoonotes.configuration;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

	private static MessageSourceAccessor messageSourceAccessor;

	private static MessageSourceAccessor messageSourceAccessor1;

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@PostConstruct
	private void initMessageSourceAccessor() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames( "classpath:messages/successmessages");
		messageSourceAccessor = new MessageSourceAccessor(messageSource, Locale.getDefault());
		
		ReloadableResourceBundleMessageSource messageSource1 = new ReloadableResourceBundleMessageSource();
		messageSource1.setBasenames("classpath:messages/errormessages");
		messageSourceAccessor1 = new MessageSourceAccessor(messageSource1, Locale.getDefault());


	}
	
	

	public static MessageSourceAccessor getMessageAccessor(String type) {
		if(type.equals("Success"))
		return messageSourceAccessor;
		
		return messageSourceAccessor1;
	}
}
