# Project Name

## Overview
This project is built using **Java 23** with **Spring Security**. Although **JPA and Hibernate** are available for future use, they are not currently implemented.

## Prerequisites
- Java 23
- MySQL Database (or any supported relational DB)
- IntelliJ IDEA (Preferred GUI)
- Liquibase (for database migrations)
- Git (for version control)

## Installation
### 1. Clone the Repository

### 2. Configure Database
- Create a new database in MySQL (or your preferred DB)
- Open `src/main/resources/application.properties` and set your database credentials:
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
  spring.datasource.username=your_username
  spring.datasource.password=your_password
  ```

### 3. Run Database Migrations
Ensure **Liquibase** is enabled in your project and has YAML-based changelogs.

## Database Seeder
(optional) You can create records using Liquibase or a database seeder to populate **100,000+ records** for scalability testing, 
for this you need to enable seeder to true and it will be executed automatically when you run the appliction.


## Running the Application
```sh
mvn spring-boot:run
```

## Authentication
After running the code, you need to log in because all other parts are authenticated with a **Bearer Token**.

### Default Login Credentials (for initial use and testing):
- **Admin:** `admin@digitolk-transactions.com`
- **User:** `user@digitolk-transactions.com`
- **Password:** `dummypassword321`

### Default Roles:
1. **Admin** (can perform CRUD operations for translations)
2. **User** (can read the list of translations only)

you can change this by changing th authorization in controllers against the roles


## Version Control with GitHub
### 1. Link IntelliJ Project to GitHub
- Go to **VCS > Enable Version Control Integration** and select **Git**.
- Open **Settings > Version Control > GitHub** and log in.
- Set remote repository:
  ```sh
  git remote add origin <your-repo-url>
  ```
- Commit and push:
  ```sh
  git add .
  git commit -m "Initial commit"
  git push origin main
  ```
## Contributing
Feel free to submit issues and pull requests, this is a test based repo only!
