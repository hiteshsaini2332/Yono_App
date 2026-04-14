package org.example.yono.services;

import org.example.yono.model.Account;
import org.example.yono.model.Customer;
import org.example.yono.repositories.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepo accountRepo;
    public Account createAccount(Customer customer){
        Account account = new Account();
        account.setAccountNumber(generateAccountNumber());
        account.setBalance(customer.getInitialAmount());
        account.setCustomer(customer);

        return account;
    }

    public Long generateAccountNumber() {
        return System.currentTimeMillis();
    }

    public long getBalance(){
        return getLoggedInAccount().getBalance();
    }

    public String depositMoney(long amount){
        Account account = getLoggedInAccount();
        account.setBalance(account.getBalance() + amount);
        accountRepo.save(account);
        return "Money deposited";
    }
    public String withdrawMoney(long amount){
        Account account = getLoggedInAccount();
        if(account.getBalance() < amount){
            return "Insufficient funds";
        }
        account.setBalance(account.getBalance() - amount);
        accountRepo.save(account);
        return "Money withdrawn";
    }

    public String transferMoney(long accountNo, long amount){
        Account sender = getLoggedInAccount();
        Account receiver = accountRepo.findByAccountNumber(accountNo);
        if(sender.getBalance() < amount){
            return "Insufficient funds";
        }
        sender.setBalance(sender.getBalance() - amount);
        accountRepo.save(sender);
        receiver.setBalance(receiver.getBalance() +amount);
        accountRepo.save(receiver);
        return "Money transferred";
    }
    public String requestDebitCard(){
        Account account = getLoggedInAccount();
        if(account.isDebitCard()){
            return "Debit Card Exists ";
        }
        account.setDebitCard(true);
        accountRepo.save(account);
        return "Debit Card Granted";
    }
    public String requestChequeBook(){
        Account account = getLoggedInAccount();
        if(account.isChequeBook()){
            return "Cheque Book Exists ";
        }
        account.setChequeBook(true);
        accountRepo.save(account);
        return "Cheque Book Granted";
    }
    public String requestPassBook(){
        Account account = getLoggedInAccount();
        if(account.isPassBook()){
            return "Pass Book Exists ";
        }
        account.setPassBook(true);
        accountRepo.save(account);
        return "Pass Book Granted";
    }


    public Account getLoggedInAccount() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Account account = accountRepo.findByCustomerEmail(email);

        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        return account;
    }
}
