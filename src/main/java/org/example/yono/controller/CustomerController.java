package org.example.yono.controller;

import jakarta.validation.Valid;
import org.example.yono.dto.LoginRequest;
import org.example.yono.model.Account;
import org.example.yono.model.Customer;
import org.example.yono.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/open")
    public ResponseEntity<?>createAccount(@Valid @RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.openAccount(customer), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?>login(@Valid @RequestBody LoginRequest req) {
        return new ResponseEntity<>(customerService.verify(req),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?>update(@RequestBody String email) {
        return new ResponseEntity<>(customerService.updateEmail(email),HttpStatus.OK);
    }
    @PutMapping("/updatePhone")
    public ResponseEntity<?>updatePhone(@RequestBody String phone) {
        return new ResponseEntity<>(customerService.updatePhone(phone),HttpStatus.OK);
    }
    @DeleteMapping("/closeAccount")
    public ResponseEntity<?>closeAccount() {
        return new ResponseEntity<>(customerService.closeAccount(),HttpStatus.OK);
    }
}
