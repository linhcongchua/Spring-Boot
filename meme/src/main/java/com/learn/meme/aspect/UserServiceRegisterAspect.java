package com.learn.meme.aspect;

import com.google.common.collect.Sets;
import com.learn.meme.event.OnRegistrationCompleteEvent;
import com.learn.meme.model.Staff;
import com.learn.meme.model.User;
import com.learn.meme.service.EmailServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Aspect
@Component("UserServiceRegisterAspect")
public class UserServiceRegisterAspect {

    Logger LOGGER = LoggerFactory.getLogger(getClass());

        private final ApplicationEventPublisher eventPublisher;
    private final EmailServiceImpl emailService;

    @Autowired
    public UserServiceRegisterAspect(ApplicationEventPublisher eventPublisher, EmailServiceImpl emailService) {
        this.eventPublisher = eventPublisher;
        this.emailService = emailService;
    }

    /*
    * Notice to new User register
    * Case Staff notice to Manager to enabled
    * */
    @AfterReturning(value = "execution(* com.learn.meme.service.UserService.registerOne(..)) and args(user, typeUser)", returning = "result")
    public void afterAdvice(JoinPoint joinPoint, User user, String typeUser, Object result){
        if(result instanceof Staff){
            try {
                emailService.sendNoticeNewStaff((Staff)result);
            } catch (MessagingException e) {
                LOGGER.error(e.getMessage());
            }
        }
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent((User) result));
    }

    /*
    * Disable new Staff register (need Manager confirm to enabled)
    * */
    @Before(value = "execution(* com.learn.meme.service.UserService.registerOne(..)) and args(user, typeUser)")
    public void checkStaffEnabled(JoinPoint joinPoint, User user, String typeUser){
        if("staff".equalsIgnoreCase(typeUser)){
            user.setEnabled(false);
        }
    }
    public boolean isOwnerProfile(Authentication authentication, String username) {
        boolean isStaffOrManager = authentication.isAuthenticated()
                && authentication.getAuthorities()
                .stream().anyMatch(grantedAuthority -> Sets.newHashSet("ROLE_MANAGER", "ROLE_STAFF").contains(grantedAuthority.getAuthority()));
        return authentication.isAuthenticated() && (isStaffOrManager || authentication.getName().equals(username));
    }
}
