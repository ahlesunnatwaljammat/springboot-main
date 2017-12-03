package edu.learn.jpa.repositories;

import edu.learn.jpa.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	List<User> findByUsername(String name);
}