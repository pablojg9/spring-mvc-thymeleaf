package com.project.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "com.project.rest.entities")
@ComponentScan(basePackages = {"com.*"})
@EnableJpaRepositories(basePackages = {"com.project.rest.repositories"})
@EnableTransactionManagement
public class RestapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestapiApplication.class, args);

        //        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //      bCryptPasswordEncoder.encode("1234");

    }
}
