package com;

public class User
{
    private String name;
    private String surname;
    private BankAccount bankAccount;
    private double[] transactionList;

    public User(String name, String surname, BankAccount bA, double[] tL)
    {
        this.name = name;
        this.surname = surname;
        this.bankAccount = bA;
        this.transactionList = tL;
    }

    public String getUName(){
        return this.name;
    }

    public BankAccount getBA(){
        return this.bankAccount;
    }

    //get user TranList
    public double[] getTransactionList(){
        return this.transactionList;
    }

    //set user TranList
    public void setTransactionList(double[] list){
        this.transactionList = list;
    }

    public double getTransaction(int index){
        return this.transactionList[index];
    }

    public int lengthTransactionList(){
        return this.transactionList.length;
    }
}