package com.jayblog.weisite;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(com.jayblog.weisite.config.DotenvConfig.class)
public class WeisiteApplication implements CommandLineRunner {

    private final Dotenv dotenv;

    public WeisiteApplication(Dotenv dotenv) {
        this.dotenv = dotenv;
    }

    public static void main(String[] args) {
        SpringApplication.run(WeisiteApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("MYSQL_HOST======: " + dotenv.get("MYSQL_HOST"));
        System.out.println("MYSQL_PORT======: " + dotenv.get("MYSQL_PORT"));
        System.out.println("MYSQL_DB======: " + dotenv.get("MYSQL_DB"));
        System.out.println("MYSQL_USER======: " + dotenv.get("MYSQL_USER"));
        System.out.println("MYSQL_PASSWORD======: " + dotenv.get("MYSQL_PASSWORD"));
    }
}