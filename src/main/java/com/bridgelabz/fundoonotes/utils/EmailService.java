package com.bridgelabz.fundoonotes.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public boolean sendMail(String subject,String text,String to, String from) {
		try {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setSubject(subject);
		mail.setText(text);
		mail.setTo(to);
        mail.setFrom(from);
		javaMailSender.send(mail);
		return true;
		}catch (Exception e) {
			return false;
		}

	}
}
