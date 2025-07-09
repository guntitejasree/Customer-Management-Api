# Customer Management API

This is a Spring Boot-based RESTful API for managing customer data, built as part of a coding challenge. It supports full CRUD operations, real-time membership tier calculation based on `annualSpend` and `lastPurchaseDate`, and includes validation, OpenAPI documentation, unit testing, and H2 in-memory database support.

## Technologies Used

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Lombok
- OpenAPI / Swagger
- JUnit & Mockito
- Maven

---

## How to Build and Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/guntitejasree/Customer-Management-Api.gi

2. **Build the project:**
mvn clean install

3. **Run the application:**
mvn spring-boot:run

4. **Access locally:**
API Base URL: http://localhost:8080
Swagger UI: http://localhost:8080/swagger-ui/index.html
H2 Console: http://localhost:8080/h2-console

5. **Sample API Requests:**
   * POST /customers: http://localhost:8080/customers
Content-Type: application/json

{
  "name": "Jane Doe",
  "email": "jane.doe@example.com",
  "annualSpend": 5200.75,
  "lastPurchaseDate": "2025-05-01T10:00:00"
}

* Get Customer by ID:  /customers/{id}
GET http://localhost:8080/customers/9fa93a83-cc2a-4ecf-b3ad-9f72bd43b402

* Get Customer by Name:  /customers?name={name}
http://localhost:8080/customers?name=teja

* Get Customer by Email: /customers?email={email}
  GET http://localhost:8080/customers?email=teja@yahoo.com

  * Update a Customer: PUT /customers/{id}
    http://localhost:8080/customers/9fa93a83-cc2a-4ecf-b3ad-9f72bd43b402
Content-Type: application/json

{
  "name": "Jane D. Smith",
  "email": "jane.smith@example.com",
  "annualSpend": 11000.00,
  "lastPurchaseDate": "2025-06-10T15:45:00"
}

* Delete a Customer: DELETE /customers/{id}
  http://localhost:8080/customers/9fa93a83-cc2a-4ecf-b3ad-9f72bd43b402
  
7. **H2 Database Console**

8. **Tier Calculation Logic**

9. **Assumptions:**
* id is auto-generated using UUID.
* Email is validated via regex.
* Duplicate names/emails are allowed.
* Tier is calculated on retrieval, not stored.
* No security or authentication implemented (local demo).
