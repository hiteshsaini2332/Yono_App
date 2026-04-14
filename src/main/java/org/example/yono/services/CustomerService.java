package org.example.yono.services;

import org.example.yono.dto.LoginRequest;
import org.example.yono.model.Account;
import org.example.yono.model.Customer;
import org.example.yono.repositories.AccountRepo;
import org.example.yono.repositories.CustomerRepo;
import org.example.yono.security.JwtBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CustomerService {
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AccountService accountService;
    @Autowired
    JwtBuilder jwtBuilder;


    public String openAccount( Customer customer){
        if(customer.getInitialAmount()<1000){
            throw new RuntimeException("Minimum amount is 1000");
        }
        Customer c=new Customer();
        c.setName(customer.getName());
        c.setPassword(passwordEncoder.encode(customer.getPassword()));
        c.setEmail(customer.getEmail());
        c.setPhone(customer.getPhone());
        c.setInitialAmount(customer.getInitialAmount());

        Account account= accountService.createAccount(c);
        account.setCustomer(c);
        c.setAccount(account);
        customerRepo.save(c);
        return "Account Created "+account.getAccountNumber();
    }
    public String verify(LoginRequest req){

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            req.getEmail(),
                            req.getPassword()
                    )
            );
        }
        catch (BadCredentialsException e){
            throw new RuntimeException("Invalid email or password");
        }

        // ✅ generate JWT after successful login
        return jwtBuilder.generateToken(req.getEmail());
    }
    public String updateEmail(String email){
        Customer c=getLoggedInCustomer();
        c.setEmail(email);
        customerRepo.save(c);
        return "Email Updated Successfully";
    }

    public String updatePhone(String phone){
        Customer c=getLoggedInCustomer();
        c.setPhone(phone);
        customerRepo.save(c);
        return "Phone Updated Successfully";
    }
    public String closeAccount(){
        Customer c=getLoggedInCustomer();
        customerRepo.delete(c);
        return "Account Closed Successfully";
    }
    public Customer getLoggedInCustomer(){
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer= customerRepo.findByEmail(email);
        if (customer == null) {
            throw new RuntimeException("User not found");
        }
        return customer;
    }
}
