package com.synuwxy.sample.mail.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailParam {

    /**
     * 发起人email
     */
    private String mailFrom;
    /**
     * 收件人，多个用英文\";\"分隔
     */
    private String mailTo;

    private String mailSubject;
    /**
     * 内容
     */
    String mailContext;
}
