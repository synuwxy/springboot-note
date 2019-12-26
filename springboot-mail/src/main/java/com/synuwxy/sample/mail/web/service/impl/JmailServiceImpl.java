package com.synuwxy.sample.mail.web.service.impl;

import com.synuwxy.sample.mail.model.MailParam;
import com.synuwxy.sample.mail.web.service.JmailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author wxy
 */
@Service
public class JmailServiceImpl implements JmailService {

    private final JavaMailSender javaMailSender;

    public JmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void send(MailParam mailParam) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        mimeMessage.setHeader("mailToken", "123456");
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(mailParam.getMailFrom());
        helper.setTo(mailParam.getMailTo());
        helper.setText(mailParam.getMailContext(), true);
        helper.setSubject(mailParam.getMailSubject());
        javaMailSender.send(mimeMessage);
    }
}