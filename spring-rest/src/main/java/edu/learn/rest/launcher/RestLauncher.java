package edu.learn.rest.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"edu.learn.rest", "edu.learn.security", "edu.learn.jpa"})
public class RestLauncher {
    public static void main(String[] args) {
        SpringApplication.run(RestLauncher.class, args);
    }
}
