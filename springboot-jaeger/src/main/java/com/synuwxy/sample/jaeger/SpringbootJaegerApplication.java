package com.synuwxy.sample.jaeger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringbootJaegerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJaegerApplication.class, args);
    }

}
