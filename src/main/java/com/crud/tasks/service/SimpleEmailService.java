package com.crud.tasks.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {

    private final static Logger LOGGER = LoggerFactory.getLogger( SimpleMailMessage.class );
    @Autowired
    private JavaMailSender javaMailSender;

    public void send(final String receiverEmail, final String subject, final String message) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo( receiverEmail );
            mailMessage.setSubject( subject );
            mailMessage.setText( message );
            javaMailSender.send( mailMessage );

            LOGGER.info( "email has been sent" );
        } catch (MailException e) {
            LOGGER.error( "Failed to process email sending:", e.getMessage(), e );
        }
    }
}