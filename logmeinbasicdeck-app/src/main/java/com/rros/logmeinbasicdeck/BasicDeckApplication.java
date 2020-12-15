package com.rros.logmeinbasicdeck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.rros.logmeinbasicdeck")
public class BasicDeckApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicDeckApplication.class, args);
    }

}
