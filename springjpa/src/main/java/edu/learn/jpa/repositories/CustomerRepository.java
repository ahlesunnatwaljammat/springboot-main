package edu.learn.jpa.repositories;

import java.util.List;

import edu.learn.jpa.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}