package BankingProject;

import java.util.ArrayList;
import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        ArrayList<BankAccount> bankAccounts=new ArrayList<>();

        System.out.println("how many accounts do you want to create?"); // In this how many acount you want to create
        //   and transferring the amount from one account to another account
        int count = sc.nextInt();
        sc.nextLine(); // this empty nextLine() is used to go the cursor next line...


        // how much user want to create account that many times it will iterate.. if user give 2 means.. 2 account will
        // created and store inside the ArrayList...
        for(int i=0;i<count;i++) {

            System.out.println("Enter your name:");
            String name = sc.nextLine();
            System.out.println("Enter your intial balance:");
            double balance = sc.nextDouble();
            System.out.println("Enter your pin number");
            int pin = sc.nextInt();
            sc.nextLine();
            BankAccount acc = new BankAccount(name, balance, pin); // this is the object creation of BankAccount to
            // access the method which present in the bankaccount class(Encapsulation)

            bankAccounts.add(acc);   // this is used to store the account details(name,balance,pin) inside the array
            System.out.println("Account created successfully");
            System.out.println("your Account Number: " + acc.getAccountNumber());
        }

        System.out.println("/n---LOGIN----");
        System.out.println("Enter your Account Number: "); // ask the user to enter the accnumber.. it will check
        // if user enter the account number is same or not in generated account number
        long accNum = sc.nextLong();

        BankAccount account = null;

        for(BankAccount acc : bankAccounts) {
            if(acc.getAccountNumber()==accNum) {
                account = acc;
                break;
            }
        }
        if(account==null) {
            System.out.println("Invalid Account Number");
            System.exit(0);
        }


        // here login process if user give wrong pin means it will give 3attempt in this you dont give properly than it
        //will throw the else wrong pin so..
        int attempt=3;
        System.out.println("-----Login to access your account-----");
        while(attempt>0) {
            System.out.println("Enter your again pin number");
            int againpin= sc.nextInt();
            if (account.checkPin(againpin)) {
                System.out.println("///  Login Successful  ///");
                break;
            }
            else {
                attempt--;
                System.out.println("Wrong PIN. Remaining attempts: " + attempt);

            }
        }

        if(attempt == 0){
            System.out.println("Too many wrong attempts. Account Locked xxx");
            System.exit(0);
        }

        int choice;
            do {
                System.out.println("=== Welcome " + account.getAccountHolderName() + " ===");
                System.out.println("Your Account Number: " + account.getAccountNumber());

                System.out.println("1.Deposit");
                System.out.println("2.Withdraw");
                System.out.println("3.checkbalance");
                System.out.println("4.exit");
                System.out.println("5.checkTransaction history");
                System.out.println("Enter your choice:");
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Enter amount to Deposit:");
                        double depositamount = sc.nextDouble();
                        account.deposit(depositamount);
                        break;
                    case 2:
                        System.out.println("Enter amount to withdraw:");
                        double withdrawamount = sc.nextDouble();
                        account.withdraw(withdrawamount);
                        break;
                    case 3:
                        account.checkBalance();
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    case 5:
                        System.out.println("Enter Target Account number:");
                        long targetAcc = sc.nextLong();
                        BankAccount target = null;
                        for(BankAccount acc : bankAccounts) {
                            if(acc.getAccountNumber()==targetAcc) {
                                target = acc;
                                break;
                            }
                        }
                        if(target==null) {
                            System.out.println("Invalid Account Number");
                            break;
                        }
                        System.out.println("Enter Amount to Transfer:");
                        double amount = sc.nextDouble();
                        if(account.withdrawWithoutRecord(amount)) {
                            target.deposit(amount);
                            account.addTransaction("Transferred: " + amount + " to Account " +targetAcc );
                            target.addTransaction("Received: " + amount + " from Account " + account.getAccountNumber());
                            System.out.println("Transfer Successful ");
                        }
                        else {
                            System.out.println("Transfer Failed");
                        }
                        break;
                    case 6:
                        System.out.println("Thank you");
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } while (choice!=6);

        sc.close();
    }
}
