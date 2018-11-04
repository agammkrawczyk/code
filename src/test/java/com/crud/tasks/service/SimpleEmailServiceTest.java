package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {
@InjectMocks
    private SimpleEmailService simpleEmailService;
@Mock
    private JavaMailSender javaMailSender;
@Test
public void shuldSendEmail(){
    //Given
    Mail mail= new Mail("test@test.com","Test","test");
    SimpleMailMessage mailmessage=new SimpleMailMessage();
    mailmessage.setTo(mail.getMailTo());
    mailmessage.setSubject( mail.getSubject() );
    mailmessage.setText(mail.getMessage());
    //When
    simpleEmailService.send(mail);
    //Then
    verify(javaMailSender,times(1)).send(mailmessage);
}
}