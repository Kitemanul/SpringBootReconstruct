package com.graduationrecons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class GraduationreconsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduationreconsApplication.class, args);
    }

}
