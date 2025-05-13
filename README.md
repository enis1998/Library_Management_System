# Library Management System

## API Endpoints

### Authentication
- `POST /api/auth/login` — Authenticate user and retrieve JWT token  
- `POST /api/auth/register` — Register a new user (patron by default)  

### Book Management
- `GET    /api/books`          — Get all books with pagination and filtering  
- `GET    /api/books/{id}`     — Get a specific book by ID  
- `POST   /api/books`          — Add a new book (librarian only)  
- `PUT    /api/books/{id}`     — Update book information (librarian only)  
- `DELETE /api/books/{id}`     — Delete a book (librarian only)  

### User Management
- `GET  /api/users/{id}`         — Get user by ID (librarian only)  
- `GET  /api/users/email/{email}`— Get user by email (librarian only)  
- `PUT  /api/users/{id}`         — Update user information (librarian only)  

### Borrowing Management
- `POST /api/borrow`           — Borrow a book  
- `POST /api/borrow/return`    — Return a book  
- `GET  /api/borrow/history`   — Get borrowing history for current user  
- `GET  /api/borrow/all-history` — Get all borrowing history (librarian only)  
- `GET  /api/borrow/overdue`   — Get list of overdue books (librarian only)  

> For a complete API documentation, please refer to the Swagger UI when the application is running.

---

## Database Schema

The database consists of the following main entities:

- **Book**  
  Stores book information (title, author, ISBN, publication date, genre, availability).

- **User**  
  Stores user information (name, email, password, role—LIBRARIAN or PATRON).

- **BorrowingRecord**  
  Tracks borrowing transactions between users and books (borrow date, due date, return date).

### Relationships
- **User → BorrowingRecord**  
  One-to-many: a user can have multiple borrowing records.  
- **Book → BorrowingRecord**  
  One-to-many: a book can be borrowed multiple times over its lifetime.  
- **BorrowingRecord**  
  Joins **User** and **Book** with metadata about each transaction.

---

## Security

This application uses **JWT (JSON Web Tokens)** for stateless authentication and **role-based authorization**.

1. **Obtain a token**  
   Send a `POST` to `/api/auth/login` with valid credentials.  
2. **Use the token**  
   Include the returned JWT in the `Authorization` header of subsequent requests:
3. **Access control**  
  - Endpoints marked “librarian only” require the `LIBRARIAN` role.  
  - All other protected endpoints require a valid, non-expired token.

