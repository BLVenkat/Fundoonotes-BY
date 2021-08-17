package com.bridgelabz.fundoonotes.configuration;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bridgelabz.fundoonotes.interceptor.TokenInterceptor;
import com.bridgelabz.fundoonotes.utils.TokenService;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer{

	private static MessageSourceAccessor messageSourceAccessor;

	private static MessageSourceAccessor messageSourceAccessor1;
	
	@Autowired
	private TokenInterceptor tokenInterceptor;

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
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(tokenInterceptor);
	}

}
