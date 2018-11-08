package com.crud.tasks.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminConfigTestSuite {
    @Autowired
    AdminConfig adminConfig;
    @Test
    public void testGetAdminMail() {
        //Given
        //When
        String mail = adminConfig.getAdminMail();
        //Then
        assertEquals("agatest55566@gmail.com", mail);
    }
}
