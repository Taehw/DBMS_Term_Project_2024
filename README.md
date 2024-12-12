# DBMS_Term_Project_2024
2024 Term Project: Club Management System featuring an ERD, relational schema, normalization, and a menu-driven interface with network connectivity

# Club Management System

A simple Java-based **Club Management System** using **JDBC** and **MySQL**. This project provides basic CRUD operations for managing clubs and their members through a menu-driven interface.

---

## Features

- **Club Management**:
  - View, Add, Update, and Delete clubs.
- **Member Management**:
  - View, Add, Update, and Delete members.
- **Database Integration**:
  - MySQL with JDBC for data handling.

---

## Prerequisites

1. **Java Development Kit (JDK)** 11 
2. **MySQL Database** installed on CentOS
3. **MySQL JDBC Connector** added to the project.

---

## How to Run

1. **Set up the database**:
   ```sql
   CREATE DATABASE ClubManagement;
   USE ClubManagement;

   CREATE TABLE Club (
       ClubID INT PRIMARY KEY AUTO_INCREMENT,
       ClubName VARCHAR(50),
       Type VARCHAR(20)
   );

   CREATE TABLE Member (
       MemberID INT PRIMARY KEY AUTO_INCREMENT,
       Name VARCHAR(50),
       ClubID INT,
       FOREIGN KEY (ClubID) REFERENCES Club(ClubID) ON DELETE CASCADE
   );
