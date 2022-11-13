package com;

public class UnSyncSim extends Thread{

    private User user;
    private double[] transactions;
    private double value;
    private BankAccount ba;

    public UnSyncSim(User anyUser)
    {
        super("name");

        this.user = anyUser;
        this.transactions = anyUser.getTransactionList();
        this.ba = user.getBA();
    }

    public void run(){
        try{
            for(int i = 0; i < transactions.length; i++) {
                value = transactions[i];
                if (value > 0) {
                    ba.deposit(value, user);
                }
                if (value < 0) {
                    ba.withdraw(value, user);
                }
                if (value == 0) {
                    System.out.println("No Transaction!\n");
                }
                sleep(5);
            }
        }
        catch(Exception e) {
            System.out.println("No account has been created!\n");
        }
    }

}
