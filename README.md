# Customer Management System – Backend

This is the backend service for the Customer Management System, built with **Spring Boot** and **Java 8**, using **MariaDB** as the database.

## 🔧 Technologies Used
- Java 8
- Spring Boot
- Spring Data JPA
- MariaDB
- Maven
- JUnit 5
- Apache POI (for Excel handling)

## 🧩 Features
- Create, Update, View a single Customer
- View all Customers (with pagination)
- Bulk customer creation via Excel file upload (up to 1,000,000 records)
- Support for multiple:
  - Mobile Numbers
  - Addresses
  - Family Members (linked customers)
- Cities and Countries are stored in master tables (preloaded via DML)

## 🗃️ Entity Relationships
- `Customer` ↔ `CustomerHasAddress` (One-to-Many)
- `Customer` ↔ `CustomerHasMobileNumber` (One-to-Many)
- `Customer` ↔ `CustomerHasDependant` (One-to-Many)

## ⚙️ Getting Started

### 🛠 Prerequisites
- Java 8
- Maven
- MariaDB (with schema `customer_db`)

### 📦 Setup & Run
```bash
# Clone the repo
git clone https://github.com/pixel-mind-learning/customer-management-backend.git
cd customer-management-backend

# Install dependencies and run
mvn spring-boot:run
