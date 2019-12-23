package com.synuwxy.springbootnote.mail.web.service.impl;

import com.synuwxy.springbootnote.mail.model.MailParam;
import com.synuwxy.springbootnote.mail.web.service.JmailService;
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
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(mailParam.getSponsor());
        helper.setTo(mailParam.getMailTo());
        helper.setText(mailParam.getMailContext(), true);
        helper.setSubject("测试邮件");
        javaMailSender.send(mimeMessage);
    }

//    private String formatContext(MailParam mailParam, String context) {
//
//    }
}
