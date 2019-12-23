package com.synuwxy.springbootnote.mail.web.service;

import com.synuwxy.springbootnote.mail.model.MailParam;

import javax.mail.MessagingException;

/**
 * @author wxy
 */
public interface JmailService {

    void send(MailParam mailParam) throws MessagingException;
}
