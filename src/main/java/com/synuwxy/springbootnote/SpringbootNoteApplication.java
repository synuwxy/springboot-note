package com.synuwxy.springbootnote;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author wxy
 * create by 2019.08.17
 *
 * desc:
 * springboot 启动类
 */
// swagger2 启动默认配置
@EnableSwagger2Doc
// 扫描包的位置,不想用其他功能的时候,限制扫描范围
@ServletComponentScan("com.synuwxy.springbootnote.jaeger")
@ComponentScan("com.synuwxy.springbootnote.jaeger")
@SpringBootApplication
public class SpringbootNoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootNoteApplication.class, args);
	}

}
