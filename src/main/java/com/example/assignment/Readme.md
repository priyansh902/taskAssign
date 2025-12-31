Overview

This project is a secure, scalable REST API built using Spring Boot that supports JWT-based authentication, role-based access control, and CRUD operations on tasks.
A basic frontend (optional) can consume these APIs for demonstration.

The system is designed following clean architecture, REST principles, and real-world backend practices.

Tech Stack
Backend

Java 17

Spring Boot

Spring Security

JWT (JSON Web Tokens)

Spring Data JPA (Hibernate)

MySQL

Swagger / OpenAPI

Lombok

Optional Frontend

React / Flutter (API consumer)

Features
Authentication & Authorization

User registration & login

Password hashing using BCrypt

JWT-based stateless authentication

Role-based access:

ROLE_USER

ROLE_ADMIN

Task Management

Create task

View own tasks

Update own tasks

Delete own tasks

Admin can view all tasks

Security

JWT token validation

Protected routes

Input validation using jakarta.validation

Global exception handling

No session-based authentication

API Endpoints
Authentication
Method	Endpoint	Description
POST	/api/v1/auth/register	Register new user
POST	/api/v1/auth/login	Login & receive JWT
Tasks (Authenticated)
Method	Endpoint	Access
POST	/api/v1/tasks	User
GET	/api/v1/tasks	User (own tasks)
PUT	/api/v1/tasks/{id}	User (own task)
DELETE	/api/v1/tasks/{id}	User (own task)
GET	/api/v1/tasks/all	Admin only
Admin Access (Demo Purpose)

An admin user is auto-created on application startup.

Email: admin@system.com
Password: admin123


This is for demonstration only. In production, admin creation should be handled securely.

Swagger API Documentation

After running the application, access:

http://localhost:8080/swagger-ui/index.html


Swagger allows:

Viewing all APIs

Testing endpoints

Passing JWT token via Authorize button

Database Schema (Simplified)
User

id

name

email (unique)

password (hashed)

role

Task

id

title

description

status

user_id (owner)

Setup Instructions
Prerequisites

Java 17+

Maven

MySQL

Update database credentials in application.yml.

mvn clean install
mvn spring-boot:run

Environment Configuration

Sensitive values like DB credentials and JWT secret should be provided via environment variables in production.

Example:

jwt:
  secret: YOUR_SECRET_KEY
  expiration: 86400000

Scalability Notes

Stateless JWT authentication allows horizontal scaling

Layered architecture supports modular expansion

Can be extended to microservices

Redis can be added for caching

Docker-ready for containerized deployment

Load balancers can be added without auth changes

Evaluation Alignment
This project demonstrates:
RESTful API design
Secure authentication & authorization
Clean backend architecture
Proper validation & error handling.
Scalability readiness

Author
    -Priyanshu Kumar.
