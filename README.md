# JWT + Spring Security Demo

This is a demo for using **[JWT (JSON Web Token)](https://jwt.io)** with **[Spring Security](https://spring.io/projects/spring-security)** and **[Spring Boot](https://spring.io/projects/spring-boot)**.

## Requirements

This demo is built with Maven 3.6.x and Java 17. You also need to have MySQL installed and configured on your machine.

## Branches

**Api_users has 3 branches:**

- **main:** Contains the finished project.
- **security:** Configured with HTTP Basic (Basic Authentication Filter).
- **jwt:** Configured with JWT.

## User Accounts

There are two user accounts to demonstrate the different levels of access to the endpoints in the API and the different authorization exceptions:

- **ADMIN:** Can access all requests.
- **USER:** Can only access GET type requests.

## Installation

To get started with the project, follow these steps:

1. **Clone the repository:**

   ```bash
     https://github.com/GNicoDev/api_users

 2. **Navigate to the project directory:**

    ```
    cd api_users

 3. **Switch to the desired branch:**

    ```
    git checkout branch-name

4. **Install the dependencies:**

   ```
   mvn clean install

5. **Configure the database:**
   Ensure MySQL is installed and running on your machine. Update the **application.properties** file with your database credentials:

   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/your-database-name
   spring.datasource.username=your-username
   spring.datasource.password=your-password

   ```

6. **Run the application:**

   ```
   mvn spring-boot:run

## Usage

To test the API, use a tool like Postman or cURL to send requests to the endpoints. Below are some example endpoints:

>### Endpoints

| Method | Endpoint                  | Description                              | Request Body Example                      | Success Response Example                  | Error Codes                              | Access          |
|--------|----------------------------|------------------------------------------|-------------------------------------------|-------------------------------------------|-------------------------------------------|-----------------|
| POST   | /auth/login                | Authenticate user and get JWT token      | ```json {"username": "user", "password": "pass"}``` | 200 OK, {...}                             | 401 Unauthorized                          | PUBLIC          |
| GET    | /users                     | Get all users                            | N/A                                       | 200 OK, [{...}, {...}]                    | N/A                                       | ADMIN           |
| GET    | /users/{username}          | Get a user by username                   | N/A                                       | 200 OK, {...}                             | 404 Not Found                             | USER            |
| POST   | /users                     | Create a new user                        | ```json {"userName": "user", "password": "user123", "email": "user@gmail.com", "role": "USER"}``` | 201 Created, {...}                        | 400 Bad Request, 409 Conflict             | ADMIN           |
| PUT    | /users/{username}          | Update user details                      | ```json {"userName": "user", "password": "user123", "email": "user@gmail.com", "role": "USER", "locked": false, "disabled": true}``` | 200 OK, {...}                             | 400 Bad Request, 404 Not Found, 409 Conflict | ADMIN           |
| DELETE | /users/{username}          | Delete a user                            | N/A                                       | 200 OK                                    | 404 Not Found                             | ADMIN           |


## Key Features

- **JWT Authentication:** Securely authenticate users using JSON Web Tokens.

- **Spring Security Integration:** Leverage Spring Security to handle authentication and authorization.

- **User Role Management:** Demonstrates different levels of access (ADMIN and USER) to endpoints in the API.


