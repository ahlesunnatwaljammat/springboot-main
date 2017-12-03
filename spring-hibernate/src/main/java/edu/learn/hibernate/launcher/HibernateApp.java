package edu.learn.hibernate.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = {"edu.learn.hibernate.beans"})
@EnableJpaRepositories(basePackages = {"edu.learn.hibernate.dao"})
@EnableTransactionManagement
public class HibernateApp {
    public static void main(String[] args) {
        SpringApplication.run(HibernateApp.class, args);
    }
}
