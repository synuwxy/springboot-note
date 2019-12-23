package com.synuwxy.springbootnote;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author wxy
 * create by 2019.08.17
 * <p>
 * desc:
 * springboot 启动类
 * ServletComponentScan 扫描包的位置,不想用其他功能的时候,限制扫描范围
 * ComponentScan 扫描包的位置,不想用其他功能的时候,限制扫描范围
 * EnableSwagger2Doc swagger2 启动默认配置
 * EnableAsync 启用异步
 */
@ServletComponentScan({"com.synuwxy.springbootnote.mail"})
@ComponentScan("com.synuwxy.springbootnote.mail")
@EnableSwagger2Doc
@EnableAsync
@SpringBootApplication
public class SpringbootNoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNoteApplication.class, args);
    }

}
