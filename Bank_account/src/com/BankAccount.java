package com;

public class BankAccount
{
    private long accountNo;
    private double accountBalance;


    public BankAccount(long accountNo, double accountBalance)
    {
        this.accountNo = accountNo;
        this.accountBalance = accountBalance;
    }

    public long getAccountNo()
    {
        return this.accountNo;
    }

    public double getAccountBalance()
    {
        return this.accountBalance;
    }

    public void setAccountBalance(double newBalance)
    {
        this.accountBalance = newBalance;
        System.out.println("Account balance value: " + this.accountBalance);
    }

    public synchronized void sync_deposit(double value, User u)
    {
        accountBalance += value;
        notifyAll();
        System.out.println("Transaction value:  " + value + "\tBalance after transaction: " + accountBalance + "\tClient: " + u.getUName());
    }

    public synchronized void sync_withdraw(double value, User u)
    {
        while(Math.abs(value) > accountBalance){
            try{
                wait();
            }catch (InterruptedException e){
                System.out.println("Thread interrupted");
            }

        }
        accountBalance += value;
        System.out.println("Transaction value: " + value + "\tBalance after transaction: " + accountBalance + "\tClient: " + u.getUName());

    }

    public void deposit(double value, User u)
    {
        accountBalance += value;
        System.out.println("Transaction value:  " + value + "\tBalance after transaction: " + accountBalance + "\tClient: " + u.getUName());
    }

    public void withdraw(double value, User u)
    {
        accountBalance += value;
        System.out.println("Transaction value: " + value + "\tBalance after transaction: " + accountBalance + "\tClient: " + u.getUName());

    }

}