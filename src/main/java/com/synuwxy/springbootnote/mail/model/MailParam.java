package com.synuwxy.springbootnote.mail.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailParam {

    /**
     * 发起人email
     */
    private String sponsor;
    /**
     * 发起人名称
     */
    private String sponsorName;
    /**
     * 收件人，多个用英文\";\"分隔
     */
    private String mailTo;
    /**
     * 审批类型
     */
    private String mailType;
    /**
     * 发起时间
     */
    private String mailDate;
    /**
     * 同意url
     */
    private String agreeUrl;
    /**
     * 驳回url
     */
    private String refuseUrl;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 查看项目url
     */
    private String projectUrl;
    /**
     * 发起方式
     */
    private String mailMethod;
    /**
     * 发起方式url
     */
    private String mailMethodUrl;
    /**
     * 更新内容
     */
    String mailContext;
    /**
     * 审批建议
     */
    private String suggest;
}
