package com.learn.meme.service;

import com.learn.meme.model.Staff;
import com.learn.meme.model.User;
import com.learn.meme.util.ExcelReporter;
import com.learn.meme.util.HtmlGenerator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
public class EmailServiceImpl{

    @Autowired
    private JavaMailSender emaiSender;
    @Value("${mail.report}")
    private String mailReport;

    public void sendPassAfterRegistration (User user) throws MessagingException, IOException {

        MimeMessage message=emaiSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message, true,"utf-8");

        String html= HtmlGenerator.generateMailPass(user);

        message.setContent(html,"text/html");
        helper.setTo(user.getEmail());
        helper.setSubject("Test send HTML email");

        emaiSender.send(message);
    }

    public void sendReportMonthly() throws MessagingException, IOException {
        MimeMessage message=emaiSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message, true);

        helper.setTo(mailReport);
        helper.setSubject("Report Monthly");
        helper.setText("<h1>Check attachment for report</h1>",true);

        Date current=new Date();
        String filename=String.format("reportOn%d-%d.xlsx", current.getMonth(), current.getYear());

        XSSFWorkbook wb= ExcelReporter.lineChart();

        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        wb.write(bos);

        helper.addAttachment(filename, ExcelReporter.getExcelBytes(wb));

        emaiSender.send(message);
    }

    public void sendNoticeNewStaff(Staff result) throws MessagingException {
        MimeMessage message=emaiSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message, true, "utf-8");

        helper.setTo(mailReport);
        helper.setSubject("New Staff Notification");
        helper.setText(String.format("<h1>New staff name: <b>%s<b/><h1/>", result.getFullname()), true);

        emaiSender.send(message);
    }
}
