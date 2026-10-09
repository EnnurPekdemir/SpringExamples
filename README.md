# Spring Boot Examples

A collection of hands-on Spring Boot modules covering common backend patterns, database integrations, caching, messaging, and best practices.

## Technologies Used

- Java 17
- Spring Boot 3.3.x
- Maven (Multi-module architecture)
- Docker & Docker Compose
- PostgreSQL, MongoDB, Redis, Elasticsearch
- Apache Kafka, RabbitMQ
- Hazelcast IMDG
- Flyway Database Migration

---

## Modules Overview

### 1. Messaging & Event Streaming
- **`spring-boot-kafka`**: Asynchronous messaging and event streaming using Apache Kafka producer (`KafkaTemplate`) and consumer (`@KafkaListener`).
- **`spring-boot-rabbitmq`**: Message queue implementation with RabbitMQ exchanges, queues, and listeners.
- **`spring-boot-events`**: Decoupled in-app event publishing and listening with `ApplicationEventPublisher`.

### 2. Caching & Performance
- **`spring-boot-hazelcast-cache`**: In-memory distributed caching with Hazelcast and PostgreSQL integration using `@Cacheable` and `@CacheEvict`.
- **`spring-boot-redis-cache`**: Redis caching for fast data retrieval and cache management.

### 3. Databases & Migrations
- **`spring-boot-flyway`**: Database schema versioning and automated SQL migrations with Flyway and PostgreSQL.
- **`spring-datajpa-postgresql`**: CRUD operations and database management with Spring Data JPA and PostgreSQL.
- **`spring-mongo-rest-api`**: Document-based REST API with MongoDB.
- **`spring-boot-gridfs-fileupload`**: File upload and binary storage using MongoDB GridFS.
- **`spring-elasticsearch`**: Full-text search and document indexing using Elasticsearch.

### 4. Security & Authentication
- **`spring-boot-jwt`**: Stateless authentication and authorization using Spring Security 6 and JSON Web Tokens (JJWT). Includes token generation, validation filter, and role-based endpoint protection.
- **`spring-security-facebook-login`**: OAuth 2.0 social login integration with Facebook and Spring Security 6 (`spring-boot-starter-oauth2-client`). Features profile retrieval via `@AuthenticationPrincipal` and customized security filter chain.

### 5. REST API & Architecture
- **`spring-boot-versioning`**: 4 different REST API versioning strategies:
  - URI Path (`/api/v1/product`)
  - Request Parameter (`?apiVersion=1`)
  - Custom Header (`X-API-VERSION: 1`)
  - Media Type / Accept Header (`application/vnd.company.app-v1+json`)
- **`spring-boot-exception-handling`**: Centralized error management using `@RestControllerAdvice`, `@ExceptionHandler`, and custom exception models.
- **`spring-boot-pagination`**: Handling large datasets with pagination and sorting using Spring Data `Pageable`.
- **`spring-boot-api-doc`**: API documentation and interactive testing with Swagger / OpenAPI.
- **`spring-boot-rest-template`**: Making external HTTP requests using Spring `RestTemplate`.
- **`spring-boot-graphql`**: Flexible queries and mutations with GraphQL.
- **`spring-boot-websocket`**: Real-time two-way messaging using WebSockets.
- **`spring-boot-aop`**: Aspect-Oriented Programming for logging and method execution tracking.
- **`spring-boot-dockerization`**: Dockerfile configurations for containerizing Spring Boot applications.
- **`spring-hello-world`**: A starter project to verify Spring Boot setup.

---

## Getting Started

### Prerequisites
- JDK 17 or higher
- Docker Desktop (for database and message broker containers)
- Maven (or use the included Maven wrapper `./mvnw`)

### Running a Module

1. Clone the repository:
```bash
git clone https://github.com/haydikodlayalim/spring-examples.git
cd spring-examples
```

2. Start the required Docker containers for the module you want to run (e.g. Kafka, PostgreSQL, etc.):
```bash
docker compose up -d
```

3. Run the specific module with Maven:
```bash
./mvnw spring-boot:run -pl <module-name>
```

Example:
```bash
./mvnw spring-boot:run -pl spring-boot-kafka
```
