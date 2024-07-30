# Hibernate Project with MySQL

This project demonstrates the implementation of Hibernate with MySQL in a Java application. It manages `Person` entities associated with `BankAccount` entities, providing functionalities to add, update, show, transact, and delete persons and their bank accounts.

## Project Structure

src
├── com
│ ├── Controller
│ │ └── Controller.java
│ ├── Model
│ │ ├── BankAccount.java
│ │ └── Person.java
│ ├── Service
│ │ ├── ServiceImplementation.java
│ │ └── ServiceInterface.java
│ └── Util
│ └── hibernateUtil.java


## Technologies Used

- Java
- Hibernate
- MySQL

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or later
- MySQL Server
- IDE such as IntelliJ IDEA or Eclipse

### Setup

1. **Clone the repository:**

    ```sh
    git clone <repository-url>
    ```

2. **Configure MySQL Database:**

    - Create a database named `hibernateprojects`.
    - Update the MySQL credentials in the `hibernateUtil` class.

    ```java
    map.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernateprojects");
    map.put(Environment.USER, "root");
    map.put(Environment.PASS, "root");
    ```

3. **Build and Run the Project:**

    - Open the project in your IDE.
    - Run the `Controller` class.

## Classes and Functionality

### Model

#### `Person`

Represents a person with a one-to-one relationship to a bank account.

#### BankAccount
Represents a bank account associated with a person.

### Service

#### ServiceInterface
Defines the methods for handling operations.

#### ServiceImplementation
Implements the service methods to perform CRUD operations.

### Utility
#### hibernateUtil
Provides the Hibernate SessionFactory configuration.

### Controller
Contains the main method to run the application and interact with the user.

## Features

- **Add Person with Bank Account:** Create a new person and associate them with a bank account.
- **Update Details:** Update personal and bank account details.
- **View Details:** Retrieve and display details of a person and their associated bank account.
- **Transaction Management:** Deposit and withdraw funds from the bank account.
- **Delete Records:** Remove a person and their associated bank account from the database.
- **List All Persons and Accounts:** View a list of all persons and their bank accounts.
- **Search Person by Name:** Find and display details of a person by their name.

## Uses

- **Learning Hibernate:** Understand the basics of Hibernate ORM and its integration with MySQL.
- **Database Operations:** Perform CRUD (Create, Read, Update, Delete) operations on relational data.
- **Transaction Management:** Manage transactions like deposits and withdrawals on bank accounts.
- **Entity Relationships:** Learn about one-to-one bidirectional relationships between entities in Hibernate.

