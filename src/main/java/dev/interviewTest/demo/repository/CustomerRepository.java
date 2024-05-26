package dev.interviewTest.demo.repository;

import dev.interviewTest.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.country IN :countries")
    List<Customer> findByCountries(List<String> countries);
}
