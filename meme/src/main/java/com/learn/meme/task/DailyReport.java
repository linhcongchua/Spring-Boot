package com.learn.meme.task;

import com.learn.meme.service.EmailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;

@Service
@Transactional
public class DailyReport {

    @Autowired
    private EmailServiceImpl emailService;

    private final Logger LOGGER= LoggerFactory.getLogger(getClass());

    @Scheduled(cron = "${report.cron.expression}")
    public void reportDaily(){
        LOGGER.debug("do reportDaily");
        try {
            emailService.sendReportMonthly();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "${report.cron.expression}")
    public void reportMonthly(){
        LOGGER.debug("do reportDaily");
        try {
            emailService.sendReportMonthly();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Scheduled(cron = "${report.salary.expression}")
    public void reportSalaryToCustomer(){
        LOGGER.debug("do reportSalry");
    }
}
