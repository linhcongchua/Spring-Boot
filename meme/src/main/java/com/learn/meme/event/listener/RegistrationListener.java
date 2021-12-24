package com.learn.meme.event.listener;

import com.learn.meme.event.OnRegistrationCompleteEvent;
import com.learn.meme.model.User;
import com.learn.meme.service.EmailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import javax.mail.MessagingException;
import java.io.IOException;

@Configuration
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private EmailServiceImpl emailService;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        LOGGER.debug("Do OnRegistrationCompleteEvent");
        final User user = event.getUser();

        try {
            emailService.sendPassAfterRegistration(user);
        } catch (MessagingException | IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
