package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	 	@Autowired
	    private JavaMailSender mailSender;

	    public void sendSimpleMessage(String to, String subject, String text) {
	        SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setTo(to);                 // 宛先
	        message.setSubject(subject);       // 件名
	        message.setText(text);             // 本文
	        message.setFrom("yuu12maik22@gmail.com"); // 送信元（SMTPで認証されるメール）

	        mailSender.send(message);          // 送信
	    }
	

}
