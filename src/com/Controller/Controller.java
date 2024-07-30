package com.Controller;

import java.util.Scanner;  

import com.Service.*;

public class Controller {

    public static void main(String[] args) {
        ServiceInterface s = new ServiceImplementation();
        Scanner scanner = new Scanner(System.in);
        System.out.println("=-=-=-=-=-=- Welcome -=-=-=-=-=-=-=");
        boolean flag = true;
        while (flag) {
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("1. Add");
            System.out.println("2. Update");
            System.out.println("3. Show");
            System.out.println("4. Transaction");
            System.out.println("5. Delete");
            System.out.println("6. Exit application");
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                	s.addPersonWithAccount();
                    break;

                case 2:
                    System.out.println("1. update Person And Account Details");
                    System.out.println("2. Update Bank Account Details");
                    System.out.println("Enter your choice: ");
                    int uChoice = scanner.nextInt();
                    if (uChoice == 1) {
                        s.updatePersonAndAccountDetails();
                    } else if (uChoice == 2) {
                        s.updateBankAccountDetails();
                    } else {
                        System.out.println("Invalid Choice");
                    }
                    break;

                case 3:
                	System.out.println("1. Get Person With Account Details");
                    System.out.println("2. Get Total Aaccount Balance");
                    System.out.println("3. List Of All Persons And Accounts");
                    System.out.println("4. get Person By Name");
                    System.out.println("Enter your choice: ");
                    int sChoice = scanner.nextInt();
                    if (sChoice == 1) {
                        s.getPersonandAccountDetails();
                    } else if (sChoice == 2) {
                        s.getTotalAaccountBalance();
                    }else if (sChoice == 3) {
                        s.listAllPersonsAndAccounts();
                    }else if (sChoice == 4) {
                        s.searchPersonByName();
                    } else {
                        System.out.println("Invalid Choice");
                    }
                    break;

                case 4:
                	System.out.println("1. Deposite Amount");
                    System.out.println("2. Withdraw Amount");
                    System.out.println("Enter your choice: ");
                    int tChoice = scanner.nextInt();
                    if (tChoice == 1) {
                        s.DepositeAmount();
                    } else if (tChoice == 2) {
                        s.WithdrawAmount();
                    } else {
                        System.out.println("Invalid Choice");
                    }
                    break;

                case 5:
                	System.out.println("1. Delete Person With Account");
                    s.deletPersonWithAccount();
                    break;
                    
                case 6:
                	 flag = false;
                     System.out.println("Thank you!!");
                     break;

                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }
}
