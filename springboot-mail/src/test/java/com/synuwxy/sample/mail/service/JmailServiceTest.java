package com.synuwxy.sample.mail.service;


import com.synuwxy.sample.mail.model.MailParam;
import com.synuwxy.sample.mail.web.service.JmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JmailServiceTest {

    @Autowired
    private JmailService jmailService;

    @Test
    public void sendTest() throws MessagingException {
        MailParam mailParam = MailParam.builder().mailFrom("1062186165@qq.com").mailTo("xiyu.wang@cloudtogo.cn").mailSubject("test").mailContext("test").build();

        jmailService.send(mailParam);
    }
}
