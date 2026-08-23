package com.contatodireto.minstack;

public class Wallet {
    String accountHolder;
    double balance;

    public Wallet(String accountHolder, double balance){
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0 ) {
            balance = balance + amount;
        } else throw new RuntimeException();

    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance = balance - amount;
        } else throw new RuntimeException();

    }
}
