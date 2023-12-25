package com.ficha.crisma.ficha.crisma.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.ficha.crisma.ficha.crisma"})
public class FicaCrismaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FicaCrismaApplication.class, args);
    }

}
