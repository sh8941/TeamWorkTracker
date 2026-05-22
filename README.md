# 🚀 TeamWork Tracker Backend API

A production-ready backend REST API for a Team Work Tracking & Task Management System built using Java, Spring Boot, Spring Security, JWT Authentication, PostgreSQL, Docker, and Swagger Documentation.

Designed with clean architecture principles, role-based authentication, secure APIs, exception handling, DTO mapping, and scalable service-layer architecture.

---

## ✨ Features

✅ JWT Authentication & Authorization
✅ Role-Based Access Control (RBAC)
✅ Secure Spring Security Configuration
✅ Task Management APIs
✅ Task Comment System
✅ Admin APIs
✅ Swagger API Documentation
✅ PostgreSQL Database Integration
✅ Dockerized Application
✅ Global Exception Handling
✅ DTO-based Request/Response Structure
✅ Validation & Clean API Responses
✅ Layered Architecture (Controller-Service-Repository)
✅ RESTful API Design
✅ Health Check Endpoint

---

# 🛠️ Tech Stack

| Technology        | Usage                          |
| ----------------- | ------------------------------ |
| Java              | Core Language                  |
| Spring Boot       | Backend Framework              |
| Spring Security   | Authentication & Authorization |
| JWT               | Token-Based Security           |
| Hibernate / JPA   | ORM                            |
| PostgreSQL        | Database                       |
| Docker            | Containerization               |
| Swagger / OpenAPI | API Documentation              |
| Maven             | Dependency Management          |

---

# 📁 Project Structure

```bash
com.haider.TeamdWorkTracker
│
├── config
│   ├── CustomUserDetailsService
│   ├── JwtFilter
│   ├── SecurityFilter
│   ├── SecurityUtils
│   └── SwaggerConfig
│
├── controller
│   ├── AdminController
│   ├── AuthController
│   ├── HealthController
│   ├── TaskCommentController
│   ├── TaskController
│   └── UserController
│
├── dtos
│   ├── request
│   │   ├── AuthRequest
│   │   ├── TaskCommentRequest
│   │   ├── TaskRequest
│   │   └── UserRequest
│   │
│   └── response
│       ├── TaskCommentResponse
│       ├── TaskResponse
│       └── UserResponse
│
├── entity
│   ├── RoleEntity
│   ├── TaskCommentEntity
│   ├── TaskEntity
│   └── UserEntity
│
├── enums
│   ├── Priority
│   ├── Status
│   └── Visibility
│
├── exception
│   ├── GlobalExceptionHandler
│   ├── BadRequestException
│   ├── ResourceNotFoundException
│   ├── UnauthorizedException
│   └── UsernameAlreadyExistsException
│
├── repo
│   ├── RoleRepo
│   ├── TaskCommentRepo
│   ├── TaskRepo
│   └── UserRepo
│
└── service
    ├── AuthService
    ├── JwtService
    ├── RoleService
    ├── TaskCommentService
    ├── TaskService
    └── UserService
```

---

# 🔐 Authentication Flow

This project uses JWT-based authentication.

### Authentication Process

1. User logs in using credentials
2. JWT token is generated
3. Client stores token
4. Token is sent in Authorization header
5. Spring Security validates token for protected APIs

Example:

```http
Authorization: Bearer your_jwt_token
```

---

# 📚 API Documentation

Swagger/OpenAPI documentation is integrated for easy API testing and exploration.

### Swagger UI

```bash
https://teamworktracker-4.onrender.com/swagger-ui/index.html
```

---

# 🐳 Docker Support

This project is fully dockerized using Docker and PostgreSQL containers.

### Run PostgreSQL Container

```bash
docker run --name teamwork-postgres \
-e POSTGRES_DB=teamworktracker \
-e POSTGRES_USER=postgres \
-e POSTGRES_PASSWORD=postgres \
-p 5432:5432 \
-d postgres
```

---

# ⚙️ Run Application

## Clone Repository

```bash
git clone https://github.com/sh8941/TeamWorkTracker
```

## Navigate to Project

```bash
cd TeamWorkTracker
```

## Run Spring Boot App

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

---

# 🗄️ Database Configuration

Example `application.properties`

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASS}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

# 📌 Main Modules

## 👤 User Management

* User Registration
* Authentication
* Role Management
* Secure Access

## ✅ Task Management

* Create Tasks
* Update Tasks
* Assign Tasks
* Manage Task Status
* Priority & Visibility Support

## 💬 Task Comments

* Add Comments
* Fetch Task Discussions
* Comment Management

## 🛡️ Security

* JWT Authentication
* Spring Security Filters
* Role-Based Authorization
* Protected Endpoints

---

# 🚨 Exception Handling

Centralized exception handling using:

* `GlobalExceptionHandler`
* Custom Exceptions
* Clean API Responses

Examples:

* ResourceNotFoundException
* UnauthorizedException
* BadRequestException
* UsernameAlreadyExistsException

---

# 🧪 API Testing

You can test APIs using:

* Swagger UI
* Postman
* Insomnia

---

# 📈 Future Improvements

* Refresh Token Support
* Email Notifications
* File Attachments
* WebSocket Notifications
* Audit Logging
* Pagination & Filtering
* Unit & Integration Tests
* CI/CD Pipeline

---

# 💡 Learning Highlights

This project demonstrates practical experience with:

* Spring Boot Architecture
* Secure REST APIs
* JWT Authentication
* Database Relationships
* Dockerized Deployment
* Exception Handling
* DTO Mapping
* Clean Code Practices
* PostgreSQL Integration
* API Documentation

---

# 👨‍💻 Author

**Samoon Haider**

Backend Developer | Java & Spring Boot Enthusiast

---

# ⭐ Support

If you found this project useful, consider giving it a ⭐ on GitHub.
