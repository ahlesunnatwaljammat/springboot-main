package edu.learn.jpa.launcher;

import edu.learn.jpa.entities.DateTime;
import edu.learn.jpa.entities.User;
import edu.learn.jpa.repositories.DateTimeRepository;
import edu.learn.jpa.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
@EntityScan(basePackages = {"edu.learn.jpa.entities"})
@EnableJpaRepositories(basePackages = {"edu.learn.jpa.repositories"})
@EnableTransactionManagement
public class JPALauncher {

	private static final Logger log = LoggerFactory.getLogger(JPALauncher.class);

	public static void main(String[] args) {
		SpringApplication.run(JPALauncher.class);
	}

	@Bean
	public CommandLineRunner user(UserRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new User("nabbasi", "x"));
			repository.save(new User("wahmed", "x"));
			repository.save(new User("fburney", "x"));

			// fetch all customers
			log.info("Users found with findAll():");
			log.info("-------------------------------");
			for (User user : repository.findAll()) {
				log.info(user.toString());
			}
			log.info("");

			// fetch an individual user by ID
			User user = repository.findOne(1L);
			log.info("User found with findOne(1L):");
			log.info("--------------------------------");
			log.info(user.toString());
			log.info("");

			// fetch users by username
			log.info("User found with findByUsername('Bauer'):");
			log.info("--------------------------------------------");

			for (User nabbasi : repository.findByUsername("Bauer")) {
				log.info(nabbasi.toString());
			}
			log.info("");
		};
	}

	@Bean
	public CommandLineRunner dateTime(DateTimeRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new DateTime(LocalDate.now(), LocalDateTime.now(), Duration.ofDays(2)));

			// fetch all customers
			log.info("DateTime found with findAll():");
			log.info("-------------------------------");
			for (DateTime findAll : repository.findAll()) {
				log.info(findAll.toString());
			}
			log.info("");

			// fetch an individual user by ID
			DateTime dateTime = repository.findOne(1L);
			log.info("DateTime found with findOne(1L):");
			log.info("--------------------------------");
			log.info(dateTime.toString());
			log.info("");

			// fetch users by username
			log.info("DateTime found with findByDate('Bauer'):");
			log.info("--------------------------------------------");

			DateTime findByDate = repository.findByDate(LocalDate.now());
			log.info(findByDate.toString());

			log.info("");
		};
	}
}