package com.synuwxy.springbootnote;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author wxy
 * create by 2019.08.17
 *
 * desc:
 * springboot 启动类
 */
@ServletComponentScan("com.synuwxy.springbootnote.mybatis")
@SpringBootApplication
public class SpringbootNoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootNoteApplication.class, args);
	}

}
