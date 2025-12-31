# Developer Intern – Assignment

This repository contains a secure, scalable backend system with authentication and role-based access, along with a minimal frontend UI to demonstrate and test the APIs.

The primary focus of this assignment is **backend design, security, and API quality**.  
The frontend is intentionally minimal and used only to interact with the backend APIs.


## Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Security
- JWT Authentication
- JPA / Hibernate
- MySQL (can be switched to PostgreSQL)
- Swagger (OpenAPI)

### Frontend (Supportive)
- Flutter (minimal UI)
- HTTP package
- Shared Preferences (JWT storage)

## Project Structure

assignment-repository/
│
├── backend/ # Spring Boot backend
│ ├── src/main/java
│ ├── src/main/resources
│ ├── pom.xml
│ └── README.md
│
├── frontend/ # Minimal Flutter UI
│ ├── lib/
│ │ ├── main.dart
│ │ ├── services/
│ │ │ └── api_service.dart
│ │ └── screens/
│ │ ├── login.dart
│ │ ├── register.dart
│ │ └── tasks.dart
│ └── pubspec.yaml


## Core Features

### Authentication & Authorization
- User registration with password hashing (BCrypt)
- Login with JWT token generation
- Role-based access control (USER / ADMIN)
- Stateless authentication

### Task Management (Secondary Entity)
- Create task
- View all tasks
- Update task
- Delete task
- Access controlled via JWT

### API Design
- RESTful endpoints
- Proper HTTP status codes
- Centralized exception handling
- Request validation
- API versioning (`/api/v1`)

### Documentation
- Swagger UI for live API testing
- Postman collection included

## Backend Setup Instructions

### Prerequisites
- Java 17+
- Maven
- MySQL

### Database Configuration
Update `application.yml` or `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/assignment
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
Run Backend
bash
Copy code
cd backend
mvn clean install
mvn spring-boot:run
Backend runs on:

arduino
Copy code
http://localhost:8080
Swagger UI:

http://localhost:8080/swagger-ui/index.html
Frontend Setup Instructions (Minimal UI)
The frontend is used only to demonstrate and test backend APIs.

Prerequisites
Flutter SDK

Android Emulator or physical device

Configure API Base URL
In frontend/lib/services/api_service.dart:

Android Emulator:

arduino
Copy code
http://10.0.2.2:8080/api/v1
Physical device:

http://<your-system-ip>:8080/api/v1
Run Frontend
bash
Copy code
cd frontend
flutter pub get
flutter run
Security Considerations
Passwords are hashed using BCrypt

JWT tokens are validated on every protected request

Role-based access enforced at controller level

Input validation to prevent malformed requests

Stateless authentication enables horizontal scaling

Scalability Notes
Modular layered architecture (Controller → Service → Repository)

Stateless JWT authentication supports load balancing

Easy to split into microservices (Auth Service / Task Service)

Redis caching can be added for frequent reads

Dockerization possible for deployment

Ready for CI/CD pipelines

API Documentation
Swagger UI included

Postman collection available in /postman directory

Evaluation Alignment
This project demonstrates:

Clean REST API design

Secure authentication & authorization

Proper database schema management

Backend-first system design

Functional frontend integration

Scalability-ready architecture

Author
Priyanshu Kumar

Notes
This assignment was completed within the given time constraints with emphasis on backend correctness, security, and structure. The frontend is intentionally minimal and serves only as an API interaction layer.
