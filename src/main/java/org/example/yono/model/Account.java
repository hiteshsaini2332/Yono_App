package org.example.yono.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private long accountNumber;
    private long balance;
    private Date createdAt;

     private boolean debitCard=false;
     private boolean passBook=false;
     private boolean chequeBook=false;

    @OneToOne
    @JoinColumn(name = "user_id")
    Customer customer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isDebitCard() {
        return debitCard;
    }

    public void setDebitCard(boolean debitCard) {
        this.debitCard = debitCard;
    }

    public boolean isPassBook() {
        return passBook;
    }

    public void setPassBook(boolean passBook) {
        this.passBook = passBook;
    }

    public boolean isChequeBook() {
        return chequeBook;
    }

    public void setChequeBook(boolean chequeBook) {
        this.chequeBook = chequeBook;
    }
}
