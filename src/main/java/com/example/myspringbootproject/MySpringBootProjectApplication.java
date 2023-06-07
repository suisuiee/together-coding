package com.example.myspringbootproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MySpringBootProjectApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MySpringBootProjectApplication.class)
            .properties("spring.config.location=classpath:application-starter.yml")
            .run(args);
    }

}
