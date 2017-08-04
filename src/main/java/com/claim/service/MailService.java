package com.claim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired
	private JavaMailSender emailSender;
	
	public void sendMail(String toEmail, String subject, String message) {
		SimpleMailMessage thisMessage = new SimpleMailMessage();
		thisMessage.setTo(toEmail);
		thisMessage.setSubject(subject);
		thisMessage.setText(message);
		emailSender.send(thisMessage);
	}

}
