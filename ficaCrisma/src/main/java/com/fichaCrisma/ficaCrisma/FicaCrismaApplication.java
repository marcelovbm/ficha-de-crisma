package com.fichaCrisma.ficaCrisma;

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
	        from("file://C:\\Users\\MMagrinelli\\Documents\\Catequese")
	          .to("bean:execellService?method=process");
	      }
	    };
	  } 
}
