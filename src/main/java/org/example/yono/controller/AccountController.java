package org.example.yono.controller;

import org.example.yono.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/getBalance")
    public ResponseEntity<?> getBalance(){
        return new ResponseEntity<>(accountService.getBalance(), HttpStatus.OK);
    }

    @PostMapping("/deposit/{amount}")
    public ResponseEntity<?>depositMoney(@PathVariable long amount ){
        return new ResponseEntity<>(accountService.depositMoney(amount),HttpStatus.OK);
    }

    @GetMapping("/withdraw/{amount}")
    public ResponseEntity<?>withdrawMoney(@PathVariable long amount ){
        return new ResponseEntity<>(accountService.withdrawMoney(amount),HttpStatus.OK);
    }

    @PostMapping("/transfer/{accountNo}/{amount}")
    public ResponseEntity<?>transferMoney(@PathVariable long accountNo, @PathVariable long amount){
        return new ResponseEntity<>(accountService.transferMoney(accountNo,amount),HttpStatus.OK);
    }

    @PostMapping("/debitCard")
    public ResponseEntity<?>debitCard(){
        return new ResponseEntity<>(accountService.requestDebitCard(),HttpStatus.OK);
    }

    @PostMapping("/chequeBook")
    public ResponseEntity<?>chequeBook(){
        return  new ResponseEntity<>(accountService.requestChequeBook(),HttpStatus.OK);
    }

    @PostMapping("/passBook")
    public ResponseEntity<?>passBook(){
        return new ResponseEntity<>(accountService.requestPassBook(),HttpStatus.OK);
    }

}
