package com.synuwxy.sample.mail.web.service;

import com.synuwxy.sample.mail.model.MailParam;

import javax.mail.MessagingException;

/**
 * @author wxy
 */
public interface JmailService {

    void send(MailParam mailParam) throws MessagingException;
}
