package edu.learn.jpa.repositories;

import edu.learn.jpa.entities.DateTime;
import edu.learn.jpa.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DateTimeRepository extends PagingAndSortingRepository<DateTime, Long> {
	DateTime findByDate(LocalDate localDate);
	DateTime findByDateTime(LocalDateTime localDateTime);
}