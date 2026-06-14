<![CDATA[# 🔐 User Access Management System

<div align="center">

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-Auth-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

**A secure, production-ready RESTful API for user management and role-based access control**

[Features](#-features) · [Tech Stack](#-tech-stack) · [Architecture](#-architecture) · [API Endpoints](#-api-endpoints) · [Getting Started](#-getting-started)

</div>

---

## 📖 Overview

**User Access Management System** is a backend service built with **Spring Boot 3.5** that provides a complete authentication and authorization solution. The system implements JWT-based stateless authentication with Access/Refresh Token strategy, Role-Based Access Control (RBAC), and comprehensive user lifecycle management.

---

## ✨ Features

### 🔑 Authentication
- **JWT Authentication** — Stateless auth using Access Token & Refresh Token (HS512 algorithm)
- **BCrypt Password Encoding** — Secure password hashing with configurable strength
- **Token Verification** — Signature validation and expiration checking via custom `JwtDecoder`

### 🛡️ Authorization
- **Role-Based Access Control (RBAC)** — 3 roles: `ADMIN`, `USER`, `MODERATOR`
- **Method-Level Security** — Fine-grained access control with `@PreAuthorize` and `@PostAuthorize`
- **Resource Ownership Protection** — Users can only access their own profile data

### 👤 User Management
- **User Registration** — With email validation and password confirmation
- **Profile Management** — View and update personal profile (`/users/me`)
- **Admin Operations** — List all users, delete user (Admin-only)
- **Soft Delete** — Users are marked as `INACTIVE` instead of permanently deleted

### 📊 Account Status Management
- `ACTIVE` — Normal account
- `INACTIVE` — Soft-deleted account
- `LOCKED` — Locked account (cannot login)

### ⚙️ API Quality
- **Standardized API Response** — Unified response wrapper `ApiResponse<T>` for all endpoints
- **Global Exception Handling** — Centralized error handling with `@RestControllerAdvice`
- **Error Code System** — Categorized error codes (Common `10xx`, Auth `20xx`, User `30xx`)
- **Request Validation** — Jakarta Bean Validation with custom error code mapping
- **API Documentation** — Interactive Swagger UI with Bearer JWT authentication support

---

## 🛠 Tech Stack

| Layer | Technology |
|:---|:---|
| **Language** | Java 21 |
| **Framework** | Spring Boot 3.5.8 |
| **Security** | Spring Security 6, OAuth2 Resource Server |
| **Authentication** | JWT (Nimbus JOSE + JWT, HS512) |
| **ORM / Data** | Spring Data JPA, Hibernate |
| **Database** | MySQL 8 |
| **Validation** | Jakarta Bean Validation |
| **API Docs** | SpringDoc OpenAPI 2.6 (Swagger UI) |
| **Build Tool** | Apache Maven |
| **Utilities** | Lombok, SLF4J |

---

## 🏗 Architecture

```
com.devtran
├── config/                  # Security, JWT Decoder, OpenAPI configurations
│   ├── SecurityConfig           # Spring Security filter chain, RBAC setup
│   ├── JwtDecoderConfig         # Custom JWT decoder with token verification
│   ├── JwtAuthenticationEntryPoint
│   └── OpenAPIConfig            # Swagger/OpenAPI configuration
│
├── controller/              # REST API endpoints
│   ├── AuthController           # POST /auth/register, /auth/login
│   └── UserController           # GET/PUT/DELETE /users/**
│
├── service/                 # Business logic layer
│   ├── AuthService              # Registration & login logic
│   └── UserService              # User CRUD operations
│
├── repository/              # Data access layer
│   └── UserRepository           # Spring Data JPA repository
│
├── entity/                  # JPA entities
│   ├── User                     # User entity (implements UserDetails)
│   └── Role                     # Role enum (ADMIN, USER, MODERATOR)
│
├── dto/                     # Data Transfer Objects
│   ├── ApiResponse              # Unified API response wrapper
│   ├── request/                 # LoginRequest, RegisterRequest, UpdateUserRequest
│   └── response/                # AuthResponse, UserResponse
│
├── security/                # Security utilities
│   ├── JwtUtil                  # JWT generation & verification
│   └── CustomDetailsService     # UserDetailsService implementation
│
├── exception/               # Exception handling
│   ├── GlobalExceptionHandler   # @RestControllerAdvice
│   ├── BusinessException        # Custom business exception
│   └── ErrorCode                # Standardized error code enum
│
└── enums/                   # Enumerations
    ├── UserStatus               # ACTIVE, INACTIVE, LOCKED
    └── TokenType                # ACCESS_TOKEN, REFRESH_TOKEN
```

---

## 📡 API Endpoints

### 🔓 Public Endpoints

| Method | Endpoint | Description |
|:---:|:---|:---|
| `POST` | `/api/v1/auth/register` | Register a new user |
| `POST` | `/api/v1/auth/login` | Login and receive JWT tokens |

### 🔒 Protected Endpoints

| Method | Endpoint | Role | Description |
|:---:|:---|:---:|:---|
| `GET` | `/api/v1/users` | `ADMIN` | Get all users |
| `GET` | `/api/v1/users/{id}` | Owner | Get user by ID (owner only) |
| `GET` | `/api/v1/users/me` | Any | Get current user profile |
| `PUT` | `/api/v1/users/me` | Any | Update current user profile |
| `DELETE` | `/api/v1/users/{id}` | `ADMIN` | Soft-delete a user |

### Response Format

```json
{
  "code": 1000,
  "message": "Login successful",
  "result": {
    "accessToken": "eyJhbGciOiJIUzUxMiJ9...",
    "refreshToken": "eyJhbGciOiJIUzUxMiJ9..."
  }
}
```

---

## 🚀 Getting Started

### Prerequisites

- **Java 21** or higher
- **Maven 3.8+**
- **MySQL 8** running on `localhost:3306`

### 1. Clone the repository

```bash
git clone https://github.com/<your-username>/user-access-management.git
cd user-access-management
```

### 2. Create MySQL database

```sql
CREATE DATABASE `user-access-management`;
```

### 3. Configure environment (optional)

Update `src/main/resources/application-dev.yml` or set environment variables:

```bash
export DBMS_CONNECTION=jdbc:mysql://localhost:3306/user-access-management
export DBMS_USERNAME=root
export DBMS_PASSWORD=your_password
```

### 4. Run the application

```bash
mvn spring-boot:run
```

### 5. Access Swagger UI

Open your browser and navigate to:

```
http://localhost:8081/api/v1/swagger-ui/index.html
```

---

## 📄 License

This project is for educational and portfolio purposes.

---

<div align="center">

**Built with ❤️ by [devtran](https://github.com/devtran)**

</div>
]]>
