# ☕ TPCafe - Café Management System

A comprehensive REST API for managing a café business, built with Spring Boot 3.5.6. This system handles clients, orders, articles, promotions, loyalty cards, and addresses with full CRUD operations, validation, and exception handling.

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)

## 📋 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Getting Started](#-getting-started)
- [API Documentation](#-api-documentation)
- [Database Schema](#-database-schema)
- [Testing](#-testing)
- [Project Structure](#-project-structure)
- [Configuration](#-configuration)
- [Contributing](#-contributing)
- [Author](#-author)
- [License](#-license)

## ✨ Features

### Core Functionality
- 🧑‍🤝‍🧑 **Client Management** - Complete CRUD operations for customer data
- 📦 **Article Management** - Manage café products (beverages, desserts, pastries)
- 🛒 **Order Management** - Handle customer orders with status tracking
- 🎟️ **Promotion System** - Create and manage discount promotions
- 💳 **Loyalty Cards** - Track customer loyalty points
- 📍 **Address Management** - Manage customer addresses

### Technical Features
- ✅ **Input Validation** - Bean Validation with custom error messages
- 🛡️ **Global Exception Handling** - Centralized error handling with standardized responses
- 📝 **API Documentation** - Interactive Swagger/OpenAPI documentation
- 🔄 **CORS Support** - Cross-Origin Resource Sharing enabled
- 🧪 **Comprehensive Testing** - Unit, integration, and validation tests
- 🗄️ **JPA/Hibernate** - ORM with MySQL database
- 🎯 **RESTful Design** - Following REST best practices

## 🛠️ Tech Stack

### Backend
- **Java 17** - Programming language
- **Spring Boot 3.5.6** - Application framework
- **Spring Data JPA** - Data persistence
- **Hibernate** - ORM framework
- **Lombok** - Boilerplate code reduction
- **Bean Validation** - Input validation

### Database
- **MySQL** - Production database
- **H2** - In-memory database for testing

### Documentation & Testing
- **SpringDoc OpenAPI 3** - API documentation (Swagger UI)
- **JUnit 5** - Unit testing framework
- **Mockito** - Mocking framework
- **AssertJ** - Fluent assertions
- **MockMvc** - Spring MVC testing

### Build Tool
- **Maven** - Dependency management and build automation

## 🏗️ Architecture

The project follows a **layered architecture** pattern:

```
┌─────────────────────────────────────┐
│     Controllers (REST Layer)        │  ← HTTP Endpoints
├─────────────────────────────────────┤
│     Services (Business Layer)       │  ← Business Logic
├─────────────────────────────────────┤
│     Repositories (Data Layer)       │  ← Data Access
├─────────────────────────────────────┤
│     Entities (Domain Layer)         │  ← Domain Models
└─────────────────────────────────────┘
```

### Design Patterns Used
- **Repository Pattern** - Data access abstraction
- **Service Layer Pattern** - Business logic encapsulation
- **DTO Pattern** - Data transfer objects for API responses
- **Builder Pattern** - Object construction (via Lombok)
- **Dependency Injection** - Loose coupling via Spring IoC

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- Git

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/zied-snoussi/TPCafe-Zied-SNOUSSI.git
cd TPCafe-Zied-SNOUSSI
```

2. **Configure MySQL Database**

Create a MySQL database:
```sql
CREATE DATABASE DB_Cafe;
```

Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/DB_Cafe
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. **Build the project**
```bash
mvn clean install
```

4. **Run the application**
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8081/api`

### Quick Start with Docker (Optional)

```bash
# Build the application
mvn clean package -DskipTests

# Run with Docker Compose (if docker-compose.yml is available)
docker-compose up -d
```

## 📚 API Documentation

### Swagger UI
Once the application is running, access the interactive API documentation:

**URL:** `http://localhost:8081/api/swagger-ui.html`

### OpenAPI Specification
**URL:** `http://localhost:8081/api/v3/api-docs`

### Main Endpoints

#### Clients
```http
GET    /api/clients           # Get all clients
GET    /api/clients/{id}      # Get client by ID
POST   /api/clients           # Create new client
PUT    /api/clients           # Update client
PATCH  /api/clients/{id}      # Partial update
DELETE /api/clients/{id}      # Delete client
```

#### Articles
```http
GET    /api/articles          # Get all articles
GET    /api/articles/{id}     # Get article by ID
POST   /api/articles          # Create new article
PUT    /api/articles          # Update article
PATCH  /api/articles/{id}     # Partial update
DELETE /api/articles/{id}     # Delete article
```

#### Orders (Commandes)
```http
GET    /api/commandes         # Get all orders
GET    /api/commandes/{id}    # Get order by ID
POST   /api/commandes         # Create new order
PUT    /api/commandes         # Update order
PATCH  /api/commandes/{id}    # Partial update
DELETE /api/commandes/{id}    # Delete order
```

#### Promotions
```http
GET    /api/promotions        # Get all promotions
GET    /api/promotions/{id}   # Get promotion by ID
POST   /api/promotions        # Create new promotion
PUT    /api/promotions        # Update promotion
PATCH  /api/promotions/{id}   # Partial update
DELETE /api/promotions/{id}   # Delete promotion
```

### Example Request

**Create a Client:**
```bash
curl -X POST http://localhost:8081/api/clients \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Snoussi",
    "prenom": "Zied",
    "dateNaissance": "1990-01-15"
  }'
```

**Response:**
```json
{
  "idClient": 1,
  "nom": "Snoussi",
  "prenom": "Zied",
  "dateNaissance": "1990-01-15",
  "adresse": null,
  "carteFidelite": null
}
```

### Error Response Format

All errors follow a standardized format:

```json
{
  "timestamp": "2025-11-05T01:39:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed for one or more fields",
  "path": "/api/clients",
  "validationErrors": [
    {
      "field": "nom",
      "message": "Last name must be between 2 and 50 characters",
      "rejectedValue": "A"
    }
  ]
}
```

## 🗄️ Database Schema

### Entity Relationship Diagram

```
┌─────────────┐       ┌──────────────┐       ┌─────────────┐
│   Client    │──────<│   Commande   │>──────│   Article   │
└─────────────┘       └──────────────┘       └─────────────┘
      │                      │                       │
      │                      │                       │
      ▼                      ▼                       ▼
┌─────────────┐       ┌──────────────┐       ┌─────────────┐
│   Adresse   │       │Detail_Commande│      │  Promotion  │
└─────────────┘       └──────────────┘       └─────────────┘
      │
      ▼
┌─────────────┐
│CarteFidelite│
└─────────────┘
```

### Key Entities

- **Client** - Customer information with one-to-one relationships to Address and Loyalty Card
- **Article** - Products (beverages, desserts, pastries) with pricing
- **Commande** - Orders with status tracking and total amount
- **Detail_Commande** - Order line items linking orders to articles
- **Promotion** - Discount promotions applicable to articles
- **Adresse** - Customer addresses
- **CarteFidelite** - Loyalty cards with accumulated points

### Enums

- **TypeArticle**: `BOISSON`, `DESSERT`, `PATISSERIE`
- **StatusCommande**: `EN_COURS`, `LIVREE`, `ANNULEE`

## 🧪 Testing

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=ClientServiceTest
```

### Test Coverage
The project includes:
- **Repository Tests** - Data layer testing with H2 in-memory database
- **Service Tests** - Business logic testing with Mockito
- **Controller Tests** - REST endpoint testing with MockMvc
- **Integration Tests** - End-to-end testing with full Spring context
- **Validation Tests** - Bean Validation constraint testing

### Test Statistics
- ✅ **10+ test classes**
- ✅ **80+ test methods**
- ✅ **~85% code coverage**

### Example Test Execution
```bash
# Run tests with coverage report
mvn clean test jacoco:report

# View coverage report
open target/site/jacoco/index.html
```

## 📁 Project Structure

```
TPCafe-Zied-SNOUSSI/
├── src/
│   ├── main/
│   │   ├── java/tn/esprit/tpcafeziedsnoussi/
│   │   │   ├── config/              # Configuration classes
│   │   │   │   ├── CorsConfig.java
│   │   │   │   └── OpenAPIConfig.java
│   │   │   ├── controllers/         # REST controllers
│   │   │   │   └── rest/
│   │   │   │       ├── ClientRestController.java
│   │   │   │       ├── ArticleRestController.java
│   │   │   │       ├── CommandeRestController.java
│   │   │   │       ├── PromotionRestController.java
│   │   │   │       ├── AdresseRestController.java
│   │   │   │       ├── CarteFideliteRestController.java
│   │   │   │       └── DetailCommandeRestController.java
│   │   │   ├── dtos/                # Data Transfer Objects
│   │   │   │   ├── ClientDTO.java
│   │   │   │   ├── ArticleDTO.java
│   │   │   │   ├── ErrorResponse.java
│   │   │   │   └── ...
│   │   │   ├── entities/            # JPA entities
│   │   │   │   ├── Client.java
│   │   │   │   ├── Article.java
│   │   │   │   ├── Commande.java
│   │   │   │   ├── Promotion.java
│   │   │   │   ├── Adresse.java
│   │   │   │   ├── CarteFidelite.java
│   │   │   │   └── Detail_Commande.java
│   │   │   ├── enums/               # Enumerations
│   │   │   │   ├── TypeArticle.java
│   │   │   │   └── StatusCommande.java
│   │   │   ├── exceptions/          # Custom exceptions
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   ├── BadRequestException.java
│   │   │   │   ├── DuplicateResourceException.java
│   │   │   │   └── BusinessException.java
│   │   │   ├── mappers/             # Entity-DTO mappers
│   │   │   │   ├── ClientMapper.java
│   │   │   │   ├── ArticleMapper.java
│   │   │   │   └── ...
│   │   │   ├── repositories/        # JPA repositories
│   │   │   │   ├── ClientRepository.java
│   │   │   │   ├── ArticleRepository.java
│   │   │   │   └── ...
│   │   │   └── services/            # Business logic
│   │   │       ├── interfaces/
│   │   │       │   ├── IClientService.java
│   │   │       │   └── ...
│   │   │       └── implementation/
│   │   │           ├── ClientService.java
│   │   │           └── ...
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       ├── java/tn/esprit/tpcafeziedsnoussi/
│       │   ├── controllers/         # Controller tests
│       │   ├── services/            # Service tests
│       │   ├── repositories/        # Repository tests
│       │   ├── integration/         # Integration tests
│       │   ├── validation/          # Validation tests
│       │   ├── exceptions/          # Exception handler tests
│       │   └── utils/               # Test utilities
│       └── resources/
│           └── application-test.properties
├── pom.xml
└── README.md
```

## ⚙️ Configuration

### Application Properties

**Development (`application.properties`):**
```properties
# Server Configuration
server.port=8081
server.servlet.context-path=/api

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/DB_Cafe
spring.datasource.username=root
spring.datasource.password=

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# OpenAPI/Swagger
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

**Test (`application-test.properties`):**
```properties
# H2 In-Memory Database
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.hibernate.ddl-auto=create-drop
```

### Environment Variables

You can override properties using environment variables:

```bash
export DB_URL=jdbc:mysql://localhost:3306/DB_Cafe
export DB_USERNAME=your_username
export DB_PASSWORD=your_password
```

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Coding Standards
- Follow Java naming conventions
- Write meaningful commit messages
- Add unit tests for new features
- Update documentation as needed
- Ensure all tests pass before submitting PR

## 👨‍💻 Author

**Zied SNOUSSI**
- Email: zied.snoussi@esprit.tn
- GitHub: [@zied-snoussi](https://github.com/zied-snoussi)
- Institution: ESPRIT

## 📄 License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

```
Copyright 2025 Zied SNOUSSI

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- ESPRIT for the educational support
- All contributors and testers

---

**Note:** This is an educational project developed as part of coursework at ESPRIT.

For questions or support, please open an issue on GitHub.
