# Library Management System API

A simple Spring Boot REST API for managing a library system with users, books, and borrowing records.

## 📋 Features

- **User Management**: Create, read, update, and delete users
- **Book Management**: Create, read, update, and delete books
- **Borrowing System**: Borrow and return books
- **Search Functionality**: Search books by title or author
- **Status Tracking**: Track borrowed/returned books
- **Availability Check**: View available books

## 🚀 Tech Stack

- **Java 17**
- **Spring Boot 3.1.5**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**

## 📁 Project Structure

```
src/main/java/com/library/
├── LibraryApplication.java          # Main application class
├── controller/                      # REST controllers
│   ├── UserController.java
│   ├── BookController.java
│   └── BorrowRecordController.java
├── entity/                          # JPA entities
│   ├── User.java
│   ├── Book.java
│   └── BorrowRecord.java
├── dto/                            # Data Transfer Objects
│   ├── UserDTO.java
│   ├── BookDTO.java
│   └── BorrowRecordDTO.java
├── repository/                     # Spring Data repositories
│   ├── UserRepository.java
│   ├── BookRepository.java
│   └── BorrowRecordRepository.java
└── service/                        # Business logic
    ├── UserService.java
    ├── BookService.java
    └── BorrowRecordService.java
```

## 🗄️ Database Schema

### Users Table
- `id` (Primary Key)
- `name` (String, not null)
- `email` (String, unique, not null)

### Books Table
- `id` (Primary Key)
- `title` (String, not null)
- `author` (String, not null)
- `available` (Boolean, default true)

### BorrowRecords Table
- `id` (Primary Key)
- `borrow_date` (Date, not null)
- `return_date` (Date)
- `status` (Enum: BORROWED/RETURNED)
- `user_id` (Foreign Key to Users)
- `book_id` (Foreign Key to Books)

## ⚙️ Setup Instructions

### 1. Prerequisites
- Java 17 or higher
- Maven 3.6+
- PostgreSQL 12+
- Postman or any API testing tool

### 2. Database Setup
```sql
-- Create database
CREATE DATABASE library_db;

```

### 3. Configuration
Update `src/main/resources/application.properties` with your PostgreSQL credentials:
```properties

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/library_db
spring.datasource.username=username
spring.datasource.password=password

# JPA/Hibernate Configuration
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Logging
logging.level.org.springframework=INFO
logging.level.com.library=DEBUG

```

### 4. Build and Run
```bash
# Clone the project (if applicable)
git clone https://github.com/bhosaleparas/LibraryApplication.git
cd library-api

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start at `http://localhost:8080`

```
OR
copy dto, controller, entity, repository and service folder into your application directory and then run your project
```
## 📚 API Documentation

### User Management

#### Create a User
```http
POST /api/users
Content-Type: application/json

{
    "name": "John Doe",
    "email": "john@example.com"
}
```

#### Get All Users
```http
GET /api/users
```

#### Get User by ID
```http
GET /api/users/{id}
```

#### Update User
```http
PUT /api/users/{id}
Content-Type: application/json

{
    "name": "John Smith",
    "email": "john.smith@example.com"
}
```

#### Delete User
```http
DELETE /api/users/{id}
```

### Book Management

#### Create a Book
```http
POST /api/books
Content-Type: application/json

{
    "title": "The Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "available": true
}
```

#### Get All Books
```http
GET /api/books
```

#### Get Available Books
```http
GET /api/books/available
```

#### Get Book by ID
```http
GET /api/books/{id}
```

#### Explore other Options using Controllers in controller package.


### Borrowing Operations

#### Borrow a Book
```http
POST /api/borrow-records/borrow?userId=1&bookId=1
```

#### Return a Book
```http
PUT /api/borrow-records/return/{borrowRecordId}
```

#### Get All Borrow Records
```http
GET /api/borrow-records
```

#### Get Borrow Records by User
```http
GET /api/borrow-records/user/{userId}
```



#### Get Borrow Records by Status
```http
GET /api/borrow-records/status/{status}
# Status can be: BORROWED or RETURNED
```

## 📝 Sample API Usage Flow

### 1. Create a User
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Alice Smith","email":"alice@example.com"}'
```

### 2. Create a Book
```bash
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{"title":"1984","author":"George Orwell","available":true}'
```

### 3. Borrow the Book
```bash
curl -X POST "http://localhost:8080/api/borrow-records/borrow?userId=1&bookId=1"
```

### 4. Check Book Availability
```bash
curl -X GET http://localhost:8080/api/books/available
```

### 5. Return the Book
```bash
curl -X PUT http://localhost:8080/api/borrow-records/return/1
```

### 6. View Borrow History
```bash
curl -X GET http://localhost:8080/api/borrow-records/user/1
```

## 🔧 Error Handling

The API includes basic error handling for:
- Resource not found (404)
- Duplicate email addresses
- Book already borrowed
- Book not available
- Invalid status values

## 🧪 Testing

### Using Postman
1. Import the provided Postman collection (if available)
2. Set base URL to `http://localhost:8080`
3. Test all endpoints sequentially

### Manual Testing
```bash
# Test users endpoint
curl http://localhost:8080/api/users

# Test books endpoint
curl http://localhost:8080/api/books

# Test health check (Spring Boot default)
curl http://localhost:8080/actuator/health
```

## 🗂️ Database Initialization

The application uses Hibernate's `ddl-auto=update` which will:
- Create tables automatically if they don't exist
- Update schema as needed
- Preserve existing data

For production, consider using:
```properties
spring.jpa.hibernate.ddl-auto=validate
```

## 🐛 Troubleshooting

### Common Issues:

1. **Database Connection Failed**
   - Check PostgreSQL service is running
   - Verify credentials in application.properties
   - Ensure database `library_db` exists

2. **Port Already in Use**
   - Change port in application.properties: `server.port=8081`
   - Or kill the process using port 8080

3. **Compilation Errors**
   - Ensure Java 17 is installed
   - Run `mvn clean install`
   - Check IDE Java version settings

4. **Entity/Table Issues**
   - Check PostgreSQL logs
   - Verify database user has create table permissions
   - Drop and recreate database if schema is corrupted

### Logs
- Application logs are printed to console
- SQL queries are logged (set `spring.jpa.show-sql=true`)
- For more details, check `logs/application.log` (if configured)

## 📊 Sample Data

You can use the following sample data for testing:

**Users:**
```json
[
  {"name": "John Doe", "email": "john@example.com"},
  {"name": "Jane Smith", "email": "jane@example.com"},
  {"name": "Bob Wilson", "email": "bob@example.com"}
]
```

**Books:**
```json
[
  {"title": "To Kill a Mockingbird", "author": "Harper Lee", "available": true},
  {"title": "1984", "author": "George Orwell", "available": true},
  {"title": "The Great Gatsby", "author": "F. Scott Fitzgerald", "available": true},
  {"title": "Pride and Prejudice", "author": "Jane Austen", "available": true}
]
```

## 🔮 Future Enhancements

Potential features to add:
- Authentication and authorization
- Email notifications for due dates
- Book reservations
- Fine calculation for overdue books
- Swagger/OpenAPI documentation
- Unit and integration tests
- Docker support
- Frontend interface

## 📄 License

This project is for educational purposes.

## 🤝 Contributing

As this is a learning project, feel free to:
- Fork the repository
- Report issues
- Suggest improvements
- Create pull requests



**Happy Coding!** 🚀
