package org.example.yono.repositories;


import org.example.yono.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo  extends JpaRepository<Customer,Integer> {
    public Customer findByEmail(String email);
}
