package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;


@SpringBootApplication
public class PortfolioApplication {
    public static void main(String[] args) {
    	
    	Dotenv dotenv = Dotenv.load();
        System.setProperty("MAIL_USERNAME" , dotenv.get("MAIL_USERNAME"));
        System.setProperty("MAIL_PASSWORD" , dotenv.get("MAIL_PASSWORD"));
        
       
    	
        SpringApplication.run(PortfolioApplication.class, args);
    
        
        
    }
    
}
