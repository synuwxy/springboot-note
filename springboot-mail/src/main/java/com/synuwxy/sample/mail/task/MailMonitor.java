package com.synuwxy.sample.mail.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.*;
import java.io.IOException;
import java.util.Properties;

@Component
public class MailMonitor {

    @Value("${mail.pop3.username}")
    private String username;
    @Value("${mail.pop3.password}")
    private String password;
    @Value("${mail.pop3.host}")
    private String host;
    @Value("${mail.pop3.port}")
    private int port;
    @Value("${mail.pop3.auth}")
    private Boolean auth;

    @Scheduled(fixedRate = 50000)
    public void monitor() {
        //设置邮件服务器参数、服务器端口等参数
        Properties props = new Properties();
        props.put("mail.pop3.host", host);
        props.put("mail.pop3.port", port);
        props.put("mail.pop3.auth", auth);
        props.put("mail.transport.protocol", "pop3");

        //设置Session对象，同时配置验证方法
        Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username,password);
            }
        });

        try {
            //创建本地储存对象，并进行配置
            Store store = session.getStore("pop3s");
            store.connect(host,username,password);

            //创建文件夹对象，用于读取本地邮件列表
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_WRITE);

            //从文件夹对象中获取每封邮件的Message对象
            Message[] messages = folder.getMessages();
            System.out.println("The count of the Email is :"+messages.length);

            //输出所有邮件的信息
            for(Message message : messages){
                System.out.println("---------------------------------------");
                if (message.getSubject().contains("postmaster@net.cn")) {
                    message.setFlag(Flags.Flag.DELETED, true);
                    System.out.println("content: "+ message.getContent().toString());
                    System.out.println("删除退信消息");
                }
                else {
                    System.out.println("Subject: "+ message.getSubject());
                    StringBuffer stringBuffer = new StringBuffer();
                    getMailTextContent(message, stringBuffer);
                    System.out.println(stringBuffer.toString());
                }
            }
            //释放相关资源
            folder.close(true);
            store.close();
            System.out.println("释放相关资源");
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void getMailTextContent(Part part, StringBuffer content) throws MessagingException, IOException {
        System.out.println(part.getContentType());
        boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;
        if (part.isMimeType("text/*") && !isContainTextAttach) {
            content.append(part.getContent().toString());
        } else if (part.isMimeType("message/rfc822")) {
            getMailTextContent((Part) part.getContent(), content);
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                getMailTextContent(bodyPart, content);
            }
        }
    }
}
