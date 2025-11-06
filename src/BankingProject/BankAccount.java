package BankingProject;

import java.util.ArrayList;

public class BankAccount {
    private String AccountHolderName;
    private double balance;
    private int pin;
    private ArrayList<String> transactionHistory = new ArrayList<String>(); // this ArrayList is used to store the history
    // of an accounts
    private static long accountNumberSeed = 10l; // this is used to give the unique account number for the security
    private long accountNumber ;
//----- here is the Ecapsulation technic for the secure transaction and secure access the account------

    public BankAccount(String AccountHolderName, double balance, int pin) {
        this.pin = pin;
        this.AccountHolderName = AccountHolderName;
        this.balance = balance;

        this.accountNumber =accountNumberSeed; // assign unique number
        accountNumberSeed++; // next account will get +1

        this.transactionHistory=new ArrayList<>();
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    // This is used to store the message from deposite and withdraw that will be store into the arraylist..
    public void addTransaction(String message) {
        transactionHistory.add(message);
    }


    //this is used to check the transactionhistory to the user so that.. it is useful..
    public void showTransactionHistory() {
        if(transactionHistory.isEmpty()) {
            System.out.println("No transactions done yet.");
        } else {
            System.out.println("------ Transaction History ------");
            for(String t : transactionHistory) {
                System.out.println(t);
            }
        }
    }


    // this is entered pin which is used to return the true if the condition is true;
    public  boolean checkPin(int EnteredPin) {
        return EnteredPin==this.pin;

    }

    public void deposit(double amount){
        if(amount>0){
            balance+=amount;
            System.out.println("Deposited :" + amount);
            addTransaction("Deposited: ₹" + amount + " | Balance: ₹" + balance);
        }
        else{
            System.out.println("Insufficient Balance");
            addTransaction("Failed Withdrawal Attempt: ₹" + amount + " | Balance: ₹" + balance);
        }
    }

    public void withdraw(double amount){
        if(amount>0 && amount<=balance){
            balance-=amount;
            System.out.println("current balance :" + balance);
            System.out.println("withdraw :" + amount);
            transactionHistory.add("withdraw :" + amount);
        }
        else{
            System.out.println("Insufficient Balance");
        }
    }

    public void checkBalance(){
        System.out.println("Current Balance : " + balance);
    }

    public String getAccountHolderName() {
        return AccountHolderName;
    }

    public boolean withdrawWithoutRecord(double amount){
        if(amount > 0 && amount <= balance){
            balance -= amount;
            return true;
        }
        return false;
    }
}
