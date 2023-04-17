package com.tp.trinken.service.impl;


import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.tp.trinken.entity.EmailDetails;
import com.tp.trinken.entity.User;
import com.tp.trinken.repository.UserRepo;
import com.tp.trinken.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	HttpServletRequest request;

	@Value("${spring.mail.username}") private String sender;

	@Override
	public String sendSimpleMail(EmailDetails details)
	{

		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(sender);
			mailMessage.setTo(details.getRecipient());
			mailMessage.setText(details.getMsgBody());
			mailMessage.setSubject(details.getSubject());
			javaMailSender.send(mailMessage);
			return "Mail Sent Successfully...";
		}
		catch (Exception e) {
			return "Error while Sending Mail";
		}
	}

	@Override
	public void sendMail(String email)
	{
		Integer code = (int)Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
		
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(120);
		session.setAttribute("code", String.valueOf(code));
		
		User user=userRepo.findOneByEmail(email).get();
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		String senderName="Trinken";
		String subject = "Please verify your account";
		String mailContent = "<p>Dear :" +user.getFirstName()+" "+user.getLastName()+",</p>";
		mailContent += "<p>Please enter the verifyer code below to verify your account.</p>";

		mailContent += "<h1>" + code + "</h1>";

		mailContent += "<p>Thank You <br> Trinken Team</p>";

		try {
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			try {
				mimeMessageHelper.setFrom(sender, senderName);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			mimeMessageHelper.setTo(email);
			mimeMessageHelper.setText(mailContent,true);
			mimeMessageHelper.setSubject(subject);

			javaMailSender.send(mimeMessage);
		}

		catch (MessagingException e) {

			e.printStackTrace();
		}
	}


}

