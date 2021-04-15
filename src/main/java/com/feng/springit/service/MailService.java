package com.feng.springit.service;

import com.feng.springit.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;

import java.util.Locale;

@Service
public class MailService {

    private final Logger log =  LoggerFactory.getLogger(MailService.class);
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;
    private final String BASE_URL = "http://localhost:8080";

    public MailService(JavaMailSender javaMailSender, SpringTemplateEngine springTemplateEngine) {
        this.javaMailSender = javaMailSender;
        this.springTemplateEngine  = springTemplateEngine;
    }

    @Async
    public void sendMail(String to, String subject, String content, boolean isMultiPart, boolean isHtml) {
        log.debug("Sending Email");

        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper message  = new MimeMessageHelper(mimeMessage, "UTF-8");
            message.setTo(to);
            message.setFrom("noreply@springit.com");
            message.setSubject(subject);
            message.setText(content,isHtml);
            javaMailSender.send(mimeMessage);
        }
        catch (Exception e) {
            log.warn("Email could not be send to user '{}' : {}", to, e.getMessage());
        }
    }

    @Async
    public void sendMailFromTemplate(User user, String templateName, String subject) {
        Locale locale = Locale.ENGLISH;
        Context context = new Context(locale);
        context.setVariable("user", user);
        context.setVariable("baseURL", BASE_URL);
        String content = springTemplateEngine.process(templateName, context);
        sendMail(user.getEmail(), subject, content, false, true);
    }

    @Async
    public void sendActivationEmail(User user) {
        log.debug("Sending Activation email to '{}'", user.getEmail());
        sendMailFromTemplate(user, "email/activation", "Springit User Activation Email");
    }

    @Async
    public void sendWelcomeEmail(User user) {
        log.debug("Sending Welcome email to '{}'", user.getEmail());
        sendMailFromTemplate(user, "email/welcome", "Springit Welcome Email");
    }




}
