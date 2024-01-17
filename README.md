# JWT + Spring Security Demo

This is a demo for using **[JWT (JSON Web Token)](https://jwt.io)** with **[Spring Security](https://spring.io/projects/spring-security)** and
**[Spring Boot](https://spring.io/projects/spring-boot)**
<br></br>
## Requirements
This demo is build with Maven 3.6.x and Java 17. You also need to have MySql installed and configured on your machine.

<br></br>
>**Api_users has 3 branches**
<li> main - where we have the project finished
<li> security - this branch is configured whit httpBasic (Basic Authentication Filter)
<li> jwt -  this branch is configured whit JWT</li>
<br></br>

 ---
There are two user accounts to demonstrate the different levels of access to the endpoints in
the API and the different authorization exceptions:
```
ADMIN - Can access to all requests
USER - Can only access GET type requests
```
