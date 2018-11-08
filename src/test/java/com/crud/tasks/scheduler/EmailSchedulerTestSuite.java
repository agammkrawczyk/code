package com.crud.tasks.scheduler;
import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTestSuite {
    @InjectMocks
    private EmailScheduler emailScheduler;
    @Mock
    private SimpleEmailService simpleEmailService;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private AdminConfig adminConfig;
         @Test
        public void shouldSendInformationEmail() {
            //Given
            when(adminConfig.getAdminMail()).thenReturn("agatest55566@gmail.com");
            //When
            emailScheduler.sendInformationEmail();
            //Then
            verify(simpleEmailService, times(1)).send(any(Mail.class));
        }
}
