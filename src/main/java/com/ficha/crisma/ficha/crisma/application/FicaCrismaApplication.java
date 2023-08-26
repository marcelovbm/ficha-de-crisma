package com.ficha.crisma.ficha.crisma.application;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FicaCrismaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FicaCrismaApplication.class, args);
	}

	@Bean
	public RouteBuilder myRouter() {
		return new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("file:///Users/mmagrinelli/Documents/CatequeseDTO").to("bean:execellService?method=process");
			}
		};
	}
}
