package com.tp.trinken.service;

import com.tp.trinken.entity.EmailDetails;

public interface EmailService {

	String sendSimpleMail(EmailDetails details);

	void sendMail(String email);
}

