package com.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.Model.BankAccount;
import com.Model.Person;
import com.Util.hibernateUtil;

public class ServiceImplementation implements ServiceInterface {

	SessionFactory sf = hibernateUtil.getConnection();
	Scanner sc = new Scanner(System.in);
	Session s = sf.openSession();

	@Override
	public void addPersonWithAccount() {
		Session s = sf.openSession();
        Transaction tx = null;
        
        try {
            tx = s.beginTransaction();

            Person p = new Person();
            System.out.println("Enter the Person Name: ");
            p.setPname(sc.next());

            BankAccount ba = new BankAccount();
            System.out.println("Enter the Bank Name: ");
            ba.setBankName(sc.next());
            System.out.println("Enter the Bank Account Number: ");
            ba.setAccountNumber(sc.nextLong());
            System.out.println("Enter the Bank Branch Name: ");
            ba.setBranchName(sc.next());
            System.out.println("Enter the Total Amount: ");
            ba.setTotalAmount(sc.nextDouble());

            p.setBankAccount(ba);

            s.save(p);
            tx.commit();
            System.out.println("Success");

        } catch (InputMismatchException e) {
            if (tx != null) tx.rollback();
            System.out.println("Invalid input type.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
	}

	@Override
	public void getPersonandAccountDetails() {
		Session s = sf.openSession();

        try {
            System.out.println("Enter the Person ID: ");
            int id = sc.nextInt();
            Person person = s.get(Person.class, id);
            if (person != null) {
                System.out.println("---------------------------------------------------------");
                System.out.println("--------------Person With Account Details----------------");
                System.out.println("Person Name: " + person.getPname());
                System.out.println("Bank Name: " + person.getBankAccount().getBankName());
                System.out.println("Account Number: " + person.getBankAccount().getAccountNumber());
                System.out.println("Branch Name: " + person.getBankAccount().getBranchName());
                System.out.println("Total Account Amount: " + person.getBankAccount().getTotalAmount());
                System.out.println("---------------------------------------------------------");
            } else {
                System.out.println("Invalid ID");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type. Please enter a valid number.");
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

	@Override
	public void getTotalAaccountBalance() {
		Session s = sf.openSession();

        try {
            System.out.println("Enter the Person ID: ");
            int id = sc.nextInt();
            Person person = s.get(Person.class, id);
            if (person != null) {
                System.out.println("---------------------------------------------------------");
                System.out.println("--------------Total Account Balance----------------------");
                System.out.println("Total Account Balance: " + person.getBankAccount().getTotalAmount());
                System.out.println("---------------------------------------------------------");
            } else {
                System.out.println("Invalid ID");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type. Please enter a valid number.");
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

	@Override
	public void DepositeAmount() {
		Session s = sf.openSession();
        Transaction tx = null;

        try {
            System.out.println("Enter the Person ID: ");
            int id = sc.nextInt();
            Person person = s.get(Person.class, id);
            System.out.println("Enter the Account Number: ");
            long acn = sc.nextLong();
            if (person != null && acn == person.getBankAccount().getAccountNumber()) {
                System.out.println("Enter the Deposit Amount: ");
                double da = sc.nextDouble();
                double ta = person.getBankAccount().getTotalAmount();
                ta = ta + da;
                person.getBankAccount().setTotalAmount(ta);
                tx = s.beginTransaction();
                s.saveOrUpdate(person);
                tx.commit();
                System.out.println("Deposit successful!");
            } else {
                System.out.println("Invalid Choice");
            }
        } catch (InputMismatchException e) {
            if (tx != null) tx.rollback();
            System.out.println("Invalid input type. Please enter a valid number.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
	}

	@Override
	public void WithdrawAmount() {
		Session s = sf.openSession();
        Transaction tx = null;

        try {
            System.out.println("Enter the Person ID: ");
            int id = sc.nextInt();
            Person person = s.get(Person.class, id);
            System.out.println("Enter the Account Number: ");
            long acn = sc.nextLong();
            if (person != null && acn == person.getBankAccount().getAccountNumber()) {
                double ta = person.getBankAccount().getTotalAmount();
                System.out.println("Enter the Withdraw Amount: ");
                double wa = sc.nextDouble();
                if (wa <= ta) {
                    ta = ta - wa;
                    person.getBankAccount().setTotalAmount(ta);
                    tx = s.beginTransaction();
                    s.saveOrUpdate(person);
                    tx.commit();
                    System.out.println("Withdrawal successful!");
                } else {
                    System.out.println("Insufficient balance. Your current balance is " + ta);
                }
            } else {
                System.out.println("Invalid Choice");
            }
        } catch (InputMismatchException e) {
            if (tx != null) tx.rollback();
            System.out.println("Invalid input type. Please enter a valid number.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
	}

	@Override
	public void updatePersonAndAccountDetails() {
		Session s = sf.openSession();
        Transaction tx = null;

        try {
            System.out.println("Enter the Person ID: ");
            int id = sc.nextInt();
            Person person = s.get(Person.class, id);
            if (person != null) {
                System.out.println("Enter new Person Name (current: " + person.getPname() + "): ");
                person.setPname(sc.next());

                BankAccount ba = person.getBankAccount();
                System.out.println("Enter new Bank Name (current: " + ba.getBankName() + "): ");
                ba.setBankName(sc.next());
                System.out.println("Enter new Bank Account Number (current: " + ba.getAccountNumber() + "): ");
                ba.setAccountNumber(sc.nextLong());
                System.out.println("Enter new Bank Branch Name (current: " + ba.getBranchName() + "): ");
                ba.setBranchName(sc.next());
                System.out.println("Enter new Total Amount (current: " + ba.getTotalAmount() + "): ");
                ba.setTotalAmount(sc.nextDouble());

                tx = s.beginTransaction();
                s.saveOrUpdate(person);
                tx.commit();
                System.out.println("Update successful!");
            } else {
                System.out.println("Invalid ID");
            }
        } catch (InputMismatchException e) {
            if (tx != null) tx.rollback();
            System.out.println("Invalid input type. Please enter a valid number.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }

	}

	@Override
	public void deletPersonWithAccount() {
		Session s = sf.openSession();
        Transaction tx = null;

        try {
            System.out.println("Enter the Person ID: ");
            int id = sc.nextInt();
            Person person = s.get(Person.class, id);
            if (person != null) {
                tx = s.beginTransaction();
                s.delete(person);
                tx.commit();
                System.out.println("Person and associated bank account deleted successfully!");
            } else {
                System.out.println("Invalid ID");
            }
        } catch (InputMismatchException e) {
            if (tx != null) tx.rollback();
            System.out.println("Invalid input type. Please enter a valid number.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
	}
	@Override
	public void listAllPersonsAndAccounts() {
	    Session s = sf.openSession();

	    try {
	        List<Person> persons = s.createQuery("from Person", Person.class).list();
	        if (persons != null && !persons.isEmpty()) {
	            System.out.println("---------------------------------------------------------");
	            System.out.println("--------------All Persons With Account Details-----------");
	            for (Person person : persons) {
	                System.out.println("Person ID: " + person.getPid());
	                System.out.println("Person Name: " + person.getPname());
	                System.out.println("Bank Name: " + person.getBankAccount().getBankName());
	                System.out.println("---------------------------------------------------------");
	            }
	        } else {
	            System.out.println("No persons found.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	@Override
	public void searchPersonByName() {
	    Session s = sf.openSession();

	    try {
	        System.out.println("Enter the Person Name: ");
	        String name = sc.next();
	        List<Person> persons = s.createQuery("from Person where pname = :name", Person.class)
	                                .setParameter("name", name).list();
	        if (persons != null && !persons.isEmpty()) {
	            System.out.println("---------------------------------------------------------");
	            System.out.println("--------------Search Results-----------------------------");
	            for (Person person : persons) {
	                System.out.println("Person ID: " + person.getPid());
	                System.out.println("Person Name: " + person.getPname());
	                System.out.println("Bank Name: " + person.getBankAccount().getBankName());
	                System.out.println("Account Number: " + person.getBankAccount().getAccountNumber());
	                System.out.println("Branch Name: " + person.getBankAccount().getBranchName());
	                System.out.println("Total Account Amount: " + person.getBankAccount().getTotalAmount());
	                System.out.println("---------------------------------------------------------");
	            }
	        } else {
	            System.out.println("No persons found with the name " + name);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	@Override
	public void updateBankAccountDetails() {
	    Session s = sf.openSession();
	    Transaction tx = null;

	    try {
	        System.out.println("Enter the Person ID: ");
	        int id = sc.nextInt();
	        Person person = s.get(Person.class, id);
	        if (person != null) {
	            BankAccount ba = person.getBankAccount();
	            System.out.println("Enter new Bank Name (current: " + ba.getBankName() + "): ");
	            ba.setBankName(sc.next());
	            System.out.println("Enter new Bank Account Number (current: " + ba.getAccountNumber() + "): ");
	            ba.setAccountNumber(sc.nextLong());
	            System.out.println("Enter new Bank Branch Name (current: " + ba.getBranchName() + "): ");
	            ba.setBranchName(sc.next());
	            System.out.println("Enter new Total Amount (current: " + ba.getTotalAmount() + "): ");
	            ba.setTotalAmount(sc.nextDouble());

	            tx = s.beginTransaction();
	            s.saveOrUpdate(person);
	            tx.commit();
	            System.out.println("Update successful!");
	        } else {
	            System.out.println("Invalid ID");
	        }
	    } catch (InputMismatchException e) {
	        if (tx != null) tx.rollback();
	        System.out.println("Invalid input type.");
	    } catch (Exception e) {
	        if (tx != null) tx.rollback();
	        e.printStackTrace();
	    }
	}

}
