package org.example.yono.repositories;

import org.example.yono.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Integer> {
    public Account findByCustomerEmail(String email);
    public Account findByAccountNumber(long accountNumber);
}
