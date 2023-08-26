package com.ficha.crisma.ficha.crisma.config;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApacheCamelConfig {

    @Bean
    public RouteBuilder myRouter() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from("file:C:\\Users\\Pichau\\Documents\\CatequeseDTO").to("bean:relatorioService?method=process");
            }
        };
    }
}
