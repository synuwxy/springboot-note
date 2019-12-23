package com.synuwxy.springbootnote.mail;

import com.synuwxy.springbootnote.mail.model.MailParam;
import com.synuwxy.springbootnote.mail.web.service.JmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JmailServiceTest {

    @Autowired
    private JmailService jmailService;

    @Test
    public void sendTest() throws MessagingException {
        String sponsor = "1062186165@qq.com";
        String mailTo = "943380203@qq.com";
        String context = "doctype html";

        MailParam mailParam = MailParam.builder().sponsor(sponsor).mailTo(mailTo).mailContext(context).build();
        jmailService.send(mailParam);
    }

    @Test
    public void readFileTest() throws IOException {
        Resource resource = new ClassPathResource("template/transaction.html");

        File file = resource.getFile();
        System.out.println(file.getPath());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringBuilder stringBuilder = new StringBuilder();
        while (bufferedReader.readLine() != null) {
            stringBuilder.append(bufferedReader.readLine()).append("\n");
        }
        System.out.println(stringBuilder.toString());
    }
}
